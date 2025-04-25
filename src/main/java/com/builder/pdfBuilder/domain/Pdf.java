package com.builder.pdfBuilder.domain;

import com.builder.pdfBuilder.domain.Prototype.IPrototype;
import com.builder.pdfBuilder.domain.builder.IBuilder;
import com.builder.pdfBuilder.dtos.DtoPaymentDetails;
import com.builder.pdfBuilder.dtos.DtoUserInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Pdf implements IPrototype<Pdf> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean includeLogo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean includePaymentDetails;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean includeUserInfo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Theme theme;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean includeTimeStamp;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String footerMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Format format;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DtoPaymentDetails paymentDetails;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DtoUserInfo userInfo;


    public Pdf(Pdf pdf) {
        this.includeLogo = pdf.includeLogo;
        this.title = pdf.getTitle();
        this.includePaymentDetails = pdf.includePaymentDetails;
        this.includeUserInfo = pdf.includeUserInfo;
        this.theme = pdf.getTheme();
        this.includeTimeStamp = pdf.includeTimeStamp;
        this.footerMessage = pdf.getFooterMessage();
        this.format = pdf.getFormat();
        this.paymentDetails = pdf.getPaymentDetails();
        this.userInfo = pdf.getUserInfo();
    }

    @Override
    public Pdf clonacionSimple() {
        return new Pdf(this);
    }

    @Override
    public Pdf clonacionProfunda() {
        DtoUserInfo user = this.getUserInfo().clonacionSimple();
        DtoPaymentDetails paymentInfo = this.getPaymentDetails().clonacionSimple();
        return new Pdf.PdfBuilder()
                .userInfo(user)//CLONACION PROFUNDA
                .includeLogo(this.isIncludeLogo())
                .title(this.getTitle())
                .includePaymentDetails(this.isIncludePaymentDetails())
                .includeUserInfo(this.isIncludeUserInfo())
                .theme(this.getTheme())
                .includeTimeStamp(this.isIncludeTimeStamp())
                .footerMessage(this.getFooterMessage())
                .format(this.getFormat())
                .paymentDetails(paymentInfo)//CLONACION PROFUNDA
                .build();
    }


    public static class PdfBuilder implements IBuilder {
        private final Pdf pdf = new Pdf();


        public PdfBuilder includeLogo(boolean includeLogo) {
            pdf.includeLogo = includeLogo;
            return this;
        }

        public PdfBuilder title(String title) {
            pdf.title = title;
            return this;
        }

        public PdfBuilder includePaymentDetails(boolean includePaymentDetails) {
            pdf.includePaymentDetails = includePaymentDetails;
            return this;
        }

        public PdfBuilder includeUserInfo(boolean includeUserInfo) {
            pdf.includeUserInfo = includeUserInfo;
            return this;
        }

        public PdfBuilder theme(Theme theme) {
            pdf.theme = theme;
            return this;
        }

        public PdfBuilder includeTimeStamp(boolean includeTimeStamp) {
            pdf.includeTimeStamp = includeTimeStamp;
            return this;
        }

        public PdfBuilder footerMessage(String footerMessage) {
            pdf.footerMessage = footerMessage;
            return this;
        }

        public PdfBuilder format(Format format) {
            pdf.format = format;
            return this;
        }

        public PdfBuilder paymentDetails(DtoPaymentDetails paymentInfo) {
            pdf.paymentDetails = paymentInfo;
            return this;
        }

        public PdfBuilder userInfo(DtoUserInfo userInfo) {
            pdf.userInfo = userInfo;
            return this;
        }

        @Override
        public String toString() {
            return "PdfBuilder{" +
                    "pdf=" + pdf +
                    '}';
        }

        @Override
        public Pdf build() {
            return pdf;
        }
    }
}
