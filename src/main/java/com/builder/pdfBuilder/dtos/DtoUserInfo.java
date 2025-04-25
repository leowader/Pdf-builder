package com.builder.pdfBuilder.dtos;

import com.builder.pdfBuilder.domain.Prototype.IPrototype;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DtoUserInfo implements IPrototype<DtoUserInfo> {
    private String identificacion;
    private String nombre;

    public DtoUserInfo(DtoUserInfo dtoUserInfo) {
        this.identificacion=dtoUserInfo.getIdentificacion();
        this.nombre=dtoUserInfo.getNombre();
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\n" +
                "identificacion: " + identificacion + "\n" ;}

    @Override
    public DtoUserInfo clonacionSimple() {
        return new DtoUserInfo(this);
    }

    @Override
    public DtoUserInfo clonacionProfunda() {
        return this.clonacionSimple();
    }
}
