package com.builder.pdfBuilder.domain.Prototype;

import com.builder.pdfBuilder.domain.Pdf;
import java.util.HashMap;
import java.util.Map;

public class FactoryPrototypePdf {
    private static final Map<String, IPrototype<Pdf>> prototiposPdf = new HashMap<>();


    public static void agregarPrototipo(String nombre, IPrototype<Pdf> p) {
        prototiposPdf.put(nombre, p);
    }

    public static IPrototype<Pdf> obtenerPrototipoSimple(String nombre) {
        return prototiposPdf.get(nombre).clonacionSimple();
    }

    public static IPrototype<Pdf> obtenerPrototipoProfundo(String nombre) {
        return prototiposPdf.get(nombre).clonacionProfunda();
    }
}
