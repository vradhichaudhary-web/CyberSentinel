package com.cybersentinel.cyberthreatplatform.controller;

import com.cybersentinel.cyberthreatplatform.entity.ScanHistory;
import com.cybersentinel.cyberthreatplatform.service.ExcelReportService;
import com.cybersentinel.cyberthreatplatform.service.PdfReportService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/report")
@CrossOrigin
public class ReportController {

    private final PdfReportService pdfReportService;
    private final ExcelReportService excelReportService;

    public ReportController(PdfReportService pdfReportService,
                            ExcelReportService excelReportService) {

        this.pdfReportService = pdfReportService;
        this.excelReportService = excelReportService;
    }

    // ==========================
    // PDF REPORT
    // ==========================

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> downloadPdf() {

        try {

            List<ScanHistory> scans = pdfReportService.getAllScans();

            Document document = new Document();

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfWriter.getInstance(document, out);

            document.open();

            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);

            Paragraph title = new Paragraph("Cyber Sentinel Scan Report", titleFont);

            title.setAlignment(Element.ALIGN_CENTER);

            document.add(title);

            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(4);

            table.addCell("Type");
            table.addCell("Input");
            table.addCell("Risk");
            table.addCell("Score");

            for (ScanHistory scan : scans) {

                table.addCell(scan.getScanType());
                table.addCell(scan.getInputData());
                table.addCell(scan.getRiskLevel());
                table.addCell(String.valueOf(scan.getScore()));

            }

            document.add(table);

            document.close();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=CyberSentinelReport.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(out.toByteArray());

        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();

        }

    }

    // ==========================
    // EXCEL REPORT
    // ==========================

    @GetMapping("/excel")
    public ResponseEntity<byte[]> downloadExcel() {

        try {

            List<ScanHistory> scans = excelReportService.getAllScans();

            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet("Cyber Sentinel Report");

            int rowNum = 0;

            Row header = sheet.createRow(rowNum++);

            header.createCell(0).setCellValue("Type");
            header.createCell(1).setCellValue("Input");
            header.createCell(2).setCellValue("Risk");
            header.createCell(3).setCellValue("Score");

            for (ScanHistory scan : scans) {

                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(scan.getScanType());
                row.createCell(1).setCellValue(scan.getInputData());
                row.createCell(2).setCellValue(scan.getRiskLevel());
                row.createCell(3).setCellValue(scan.getScore());

            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            workbook.write(out);

            workbook.close();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=CyberSentinelReport.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(out.toByteArray());

        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();

        }

    }

}