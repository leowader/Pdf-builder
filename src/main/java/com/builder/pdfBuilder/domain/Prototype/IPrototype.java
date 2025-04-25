package com.builder.pdfBuilder.domain.Prototype;

public interface IPrototype <T>{
    T clonacionSimple();
    T clonacionProfunda();
}
