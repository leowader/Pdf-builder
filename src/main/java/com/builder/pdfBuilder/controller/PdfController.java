package com.builder.pdfBuilder.controller;

import com.builder.pdfBuilder.domain.builder.Pdf;
import com.builder.pdfBuilder.dtos.DtoPdf;
import com.builder.pdfBuilder.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PdfController {

    @Autowired
    PdfService pdfService;
    @PostMapping("/pdf")
    public Pdf processPdf(@RequestBody DtoPdf dtoPdf){

        return pdfService.processPdf(dtoPdf);

    }
}
