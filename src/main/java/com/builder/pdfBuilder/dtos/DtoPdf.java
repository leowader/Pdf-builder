package com.builder.pdfBuilder.dtos;

import com.builder.pdfBuilder.domain.Format;
import com.builder.pdfBuilder.domain.Theme;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class DtoPdf {
    private boolean includeLogo;
    private String title;
    private boolean includePaymentDetails;
    private boolean includeUserInfo;
    private Theme theme;
    private boolean includeTimeStamp;
    private String footerMessage;
    private Format format;
    private DtoUserInfo userInfo;
    private DtoPaymentDetails paymentDetails;
}
