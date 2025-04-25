package com.builder.pdfBuilder.service.prototype;

import com.builder.pdfBuilder.domain.Format;
import com.builder.pdfBuilder.domain.Pdf;
import com.builder.pdfBuilder.domain.Prototype.FactoryPrototypePdf;
import com.builder.pdfBuilder.domain.Theme;
import com.builder.pdfBuilder.dtos.DtoPaymentDetails;
import com.builder.pdfBuilder.dtos.DtoUserInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PrototypeServicePdf {


    public PrototypeServicePdf() {
        DtoUserInfo userDefault = new DtoUserInfo("222222222222", "usuario final");
        Pdf pdfDefaultUserInfo = new Pdf.PdfBuilder()
                .includeLogo(true)
                .theme(Theme.LIGHT)
                .includeTimeStamp(true)
                .format(Format.A4)
                .userInfo(userDefault)
                .includeUserInfo(true)
                .includePaymentDetails(false)
                .paymentDetails(new DtoPaymentDetails(0, 0, "DEFAULT", "OK", LocalDate.now()))
                .title("Informe (Titulo Generado por defecto de la plantilla:  userInfo)")
                .footerMessage("Informe generado correctamente (Mensaje Generado por defecto de la plantilla:  userInfo)")
                .build();

        Pdf pdfDefaultPaymentInfo = new Pdf.PdfBuilder()
                .includeLogo(true)
                .theme(Theme.LIGHT)
                .includeTimeStamp(true)
                .format(Format.LETTER)
                .includeUserInfo(false)
                .includePaymentDetails(true)
                .userInfo(userDefault)
                .paymentDetails(new DtoPaymentDetails(0, 0, "DEFAULT", "OK", LocalDate.now()))
                .title("Informe (Titulo Generado por defecto de la plantilla:  paymentInfo)")
                .footerMessage("Informe generado correctamente (Mensaje Generado por defecto de la plantilla:  paymentInfo)")
                .build();
        FactoryPrototypePdf.agregarPrototipo("userInfo", pdfDefaultUserInfo);//PROTOTIPO 1 (PLANTILLA userInfo)
        FactoryPrototypePdf.agregarPrototipo("paymentInfo", pdfDefaultPaymentInfo);//PROTOTIPO 2 (PLANTILLA paymentInfo)
    }

    public Pdf clonacionSimple(String nombre) {
        return (Pdf) FactoryPrototypePdf.obtenerPrototipoSimple(nombre);

    }

    public Pdf clonacionProfunda(String nombre) {
        return (Pdf) FactoryPrototypePdf.obtenerPrototipoProfundo(nombre);
    }

}
