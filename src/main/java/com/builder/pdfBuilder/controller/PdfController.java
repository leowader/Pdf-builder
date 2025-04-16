package com.builder.pdfBuilder.controller;

import com.builder.pdfBuilder.dtos.DtoPdf;
import com.builder.pdfBuilder.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PdfController {

    @Autowired
    PdfService pdfService;

    @PostMapping("/pdf")
    public ResponseEntity<Map<String, String>> processPdf(@RequestBody DtoPdf dtoPdf) {
        String pdfPath = pdfService.generatePdf(pdfService.processPdf(dtoPdf), "src/main/resources/static/pdf/");
        Map<String, String> response = new HashMap<>();
        response.put("message", "PDF generated successfully");
        response.put("pdfPath", pdfPath);
        return ResponseEntity.ok(response);
    }
}
