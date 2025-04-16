package com.builder.pdfBuilder.service;

import com.builder.pdfBuilder.domain.builder.Pdf;
import com.builder.pdfBuilder.dtos.DtoPdf;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


@Service
public class PdfService {

    public Pdf processPdf(DtoPdf dtoPdf) {
        return new Pdf.PdfBuilder()
                .includeLogo(dtoPdf.isIncludeLogo())
                .title(dtoPdf.getTitle())
                .includePaymentDetails(dtoPdf.isIncludePaymentDetails())
                .includeUserInfo(dtoPdf.isIncludeUserInfo())
                .theme(dtoPdf.getTheme())
                .includeTimeStamp(dtoPdf.isIncludeTimeStamp())
                .footerMessage(dtoPdf.getFooterMessage())
                .format(dtoPdf.getFormat())
                .userInfo(dtoPdf.getUserInfo())
                .paymentDetails(dtoPdf.getPaymentDetails())
                .build();
    }


    public String generatePdf(Pdf pdfData, String filePath) {
        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            Document document = createDocument(pdfData);
            PdfWriter.getInstance(document, outputStream);
            document.open();

            BaseColor textColor = determineTextColor(String.valueOf(pdfData.getTheme()));

            if (pdfData.isIncludeLogo()) {
                addLogo(document);
            }

            addTitle(document, pdfData.getTitle(), textColor);
            addUserInfo(document, pdfData, textColor);
            addPaymentDetails(document, pdfData, textColor);
            addTimeStamp(document, pdfData, textColor);
            addFooterMessage(document, pdfData.getFooterMessage(), textColor);

            document.close();
            return filePath;
        } catch (Exception e) {
            return handleException(e);
        }
    }

    private Document createDocument(Pdf pdfData) {
        if ("LETTER".equalsIgnoreCase(String.valueOf(pdfData.getFormat()))) {
            return new Document(PageSize.LETTER);
        } else if ("A4".equalsIgnoreCase(String.valueOf(pdfData.getFormat()))) {
            return new Document(PageSize.A4);
        } else {
            return new Document();
        }
    }

    private BaseColor determineTextColor(String theme) {
        return "DARK".equalsIgnoreCase(theme) ? BaseColor.GREEN : BaseColor.BLACK;
    }

    private void addLogo(Document document) throws DocumentException, IOException {
        Image logo = Image.getInstance(new ClassPathResource("static/logo.png").getURL());
        logo.scaleToFit(100, 50);
        logo.setAlignment(Element.ALIGN_CENTER);
        document.add(logo);
    }

    private void addTitle(Document document, String title, BaseColor textColor) throws DocumentException {
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, textColor);
        Paragraph titleParagraph = new Paragraph(title, titleFont);
        titleParagraph.setAlignment(Element.ALIGN_CENTER);
        document.add(titleParagraph);
        document.add(Chunk.NEWLINE);
    }

    private void addUserInfo(Document document, Pdf pdfData, BaseColor textColor) throws DocumentException {
        if (pdfData.isIncludeUserInfo()) {
            Font sectionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, textColor);
            document.add(new Paragraph("Información de Usuario:", sectionFont));
            document.add(new Paragraph(String.valueOf(pdfData.getUserInfo()), new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, textColor)));
            document.add(Chunk.NEWLINE);
        }
    }

    private void addPaymentDetails(Document document, Pdf pdfData, BaseColor textColor) throws DocumentException {
        if (pdfData.isIncludePaymentDetails()) {
            Font sectionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, textColor);
            document.add(new Paragraph("Detalles de Pago:", sectionFont));
            document.add(new Paragraph(String.valueOf(pdfData.getPaymentDetails()), new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, textColor)));
            document.add(Chunk.NEWLINE);
        }
    }

    private void addTimeStamp(Document document, Pdf pdfData, BaseColor textColor) throws DocumentException {
        if (pdfData.isIncludeTimeStamp()) {
            document.add(new Paragraph("Generado el: " + new java.util.Date(), new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, textColor)));
            document.add(Chunk.NEWLINE);
        }
    }

    private void addFooterMessage(Document document, String footerMessage, BaseColor textColor) throws DocumentException {
        if (footerMessage != null) {
            Paragraph footer = new Paragraph(footerMessage, new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC, textColor));
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);
        }
    }

    private String handleException(Exception e) {
        return "Error al generar el PDF: " + e.getMessage();
    }

}