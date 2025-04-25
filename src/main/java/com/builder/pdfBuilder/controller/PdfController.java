package com.builder.pdfBuilder.controller;

import com.builder.pdfBuilder.domain.Pdf;
import com.builder.pdfBuilder.dtos.DtoPdf;
import com.builder.pdfBuilder.service.PdfService;
import com.builder.pdfBuilder.service.prototype.PrototypeServicePdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;


@RestController
public class PdfController {

    @Autowired
    PdfService pdfService;
    @Autowired
    PrototypeServicePdf prototypeServicePdf; //SE INSTANCIA EL SERVICIO Y SE AGREGAN LO PROTOTIPOS DE UNA VEZ


    @PostMapping("/pdf")
    public ResponseEntity<Map<String, String>> processPdf(@RequestBody DtoPdf dtoPdf) {
        String pdfPath = pdfService.generatePdf(pdfService.processPdf(dtoPdf), "src/main/resources/static/pdf/");
        Map<String, String> response = new HashMap<>();
        response.put("message", "PDF generated successfully");
        response.put("pdfPath", "http://localhost:8082/view/pdf/" + pdfPath.split("/pdf/")[1]);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/view/pdf/{fileName}")
    public ResponseEntity<Resource> getPdf(@PathVariable String fileName) throws IOException {

        File file = new File("src/main/resources/static/pdf/" + fileName);

        if (!file.exists()) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + fileName)
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(file.length())
                .body(resource);
    }

    //ENDPOINT DE PRUEBA PATRON PROTOTYPE
    @GetMapping("/pdf/default")
    public ResponseEntity<Map<String, String>> processPdfDefaultClone() {
        Pdf pdfDefaultCopia = prototypeServicePdf.clonacionProfunda("paymentInfo");//USO DEL PATRON (PLANTILLA userInfo)
        String pdfPath = pdfService.generatePdf(pdfDefaultCopia, "src/main/resources/static/pdf/");
        Map<String, String> response = new HashMap<>();
        response.put("pdfPath", "http://localhost:8082/view/pdf/" + pdfPath.split("/pdf/")[1]);
        return ResponseEntity.ok(response);
    }
}
