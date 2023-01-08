package com.rahul.reportapi.servicesImpl;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.rahul.reportapi.entityBinding.CitizenPlan;
import com.rahul.reportapi.entityBinding.SearchRequest;
import com.rahul.reportapi.repo.CitizenPlanRepo;
import com.rahul.reportapi.services.ReportService;

//import jakarta.servlet.ServletOutputStream;
//import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepo cpRepo;

	@Override
	public List<String> getPlandetails() {
		return cpRepo.getPlanNames();
	}

	@Override
	public List<String> getPlanStaus() {
		return cpRepo.getPlanStatuses();
	}

	@Override
	public List<CitizenPlan> getCitizensPlan(SearchRequest searchRequest) {

		// first i need to create object of CitizenPlan entity ok because conditionally
		// need to search the data ok
		CitizenPlan cpentity = new CitizenPlan();

		if (searchRequest.getPlanName() != null && !searchRequest.getPlanName().equals("")) {
			cpentity.setPlanName(searchRequest.getPlanName());

		}
		if (searchRequest.getPlanStatus() != null && !searchRequest.getPlanStatus().equals("")) {
			cpentity.setPlanStatus(searchRequest.getPlanStatus());
		}
		if (searchRequest.getGender() != null && !searchRequest.getGender().equals("")) {
			cpentity.setGender(searchRequest.getGender());
		}

		// data jpa provide exampleOf concept(approach) to generate dynamic query
		// based on data availabe on the entity it will generate query and retrieve the
		// records
		Example<CitizenPlan> dyqueryresult = Example.of(cpentity);
		List<CitizenPlan> records = cpRepo.findAll(dyqueryresult);
		return records;
	}

	@Override
	public void exportExcel(HttpServletResponse response) throws Exception {

		// HSSFWorkbook workbook = new HSSFWorkbook(); // you can use anyone of them
		// either HSSFWorkbook or XSSFWorkbook
		XSSFWorkbook workbook = new XSSFWorkbook(); // creating workbook first
		XSSFSheet sheet = workbook.createSheet("Citizen_Info"); // then sheet
		XSSFRow headerRow = sheet.createRow(0); // row start from 0
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Name");
		headerRow.createCell(2).setCellValue("SSN");
		headerRow.createCell(3).setCellValue("Gender");
		headerRow.createCell(4).setCellValue("Plan Name");
		headerRow.createCell(5).setCellValue("Plan Status");
		headerRow.createCell(6).setCellValue("Phone Number");
		headerRow.createCell(7).setCellValue("Email");

		List<CitizenPlan> records = cpRepo.findAll();

		int dataRowIndex = 1; // data row starting from 1 because headerRow already on 0th index ok

		for (CitizenPlan record : records) {
			XSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(record.getCid());
			dataRow.createCell(1).setCellValue(record.getCname());
			dataRow.createCell(2).setCellValue(record.getSsn());
			dataRow.createCell(3).setCellValue(record.getGender());
			dataRow.createCell(4).setCellValue(record.getPlanName());
			dataRow.createCell(5).setCellValue(record.getPlanStatus());
			dataRow.createCell(6).setCellValue(record.getPhno());
			dataRow.createCell(7).setCellValue(record.getCemail());

			dataRowIndex++;
		}

		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();
	}

	@Override
	public void exportPdf(HttpServletResponse response) throws Exception {
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("Citizen Plans Info", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(8);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f,3.0f, 6.5f, 3.0f, 3.0f, 3.5f, 2.5f, 3.8f });
		table.setSpacingBefore(10);

		// set table header data
		writeTableHeader(table);
		// set table data
		writeTableData(table);

		document.add(table);
		document.close();

	}
	
	
	// setting table header data
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.YELLOW);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.MAGENTA);
	
		cell.setPhrase(new Phrase("ID", font));
		table.addCell(cell);
	
		cell.setPhrase(new Phrase("Name", font));
		table.addCell(cell);
	
		cell.setPhrase(new Phrase("Email", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Gender", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Plan Name", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Plan Status", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("SSN", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Mobile Number", font));
		table.addCell(cell);
		
	}

	// setting table data
	private void writeTableData(PdfPTable table) {
		
		List<CitizenPlan> records = cpRepo.findAll();
		for(CitizenPlan record: records) {
			table.addCell(String.valueOf(record.getCid())); //here id is an integer so converting  okk
			table.addCell(record.getCname());
			table.addCell(record.getCemail());
			table.addCell(record.getGender());
			table.addCell(record.getPlanName());
			table.addCell(record.getPlanStatus());
			table.addCell(String.valueOf(record.getSsn()));
			table.addCell(String.valueOf(record.getPhno()));
			
			
		}
		
	}


	@Override
	public String InsertData(CitizenPlan data) {
		data = cpRepo.save(data);
		if (data.getCid() != null) {
			return "data inserted";
		} else
			return "data Not Inserted";
	}

}
