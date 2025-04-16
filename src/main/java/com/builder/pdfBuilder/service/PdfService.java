package com.builder.pdfBuilder.service;

import com.builder.pdfBuilder.domain.Theme;
import com.builder.pdfBuilder.domain.builder.Pdf;
import com.builder.pdfBuilder.dtos.DtoPdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PdfService {
    public Pdf processPdf(DtoPdf dtoPdf) {

        return new Pdf.PdfBuilder()
                .theme(dtoPdf.getTheme())
                .includeLogo(dtoPdf.isIncludeLogo())
                .footerMessage(dtoPdf.getFooterMessage())
                .includeTimeStamp(dtoPdf.isIncludeTimeStamp())
                .build();
    }
}
