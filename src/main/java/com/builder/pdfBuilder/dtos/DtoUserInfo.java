package com.builder.pdfBuilder.dtos;

import lombok.Getter;

@Getter
public class DtoUserInfo {
    private String identificacion;
    private String nombre;
    @Override
    public String toString() {
        return "Nombre: " + nombre + "\n" +
                "identificacion: " + identificacion + "\n" ;}
}
