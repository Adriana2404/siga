/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autentico.beans;

public class Productosventa {

    private int facturaidFactura;
    private int productoidProducto;
    private String nombreProducto;
    private int cantidadProductos;
    private int valor;
    private int contenido;
    private String unidadDeMedida;

    public Productosventa(int facturaidFactura, int productoidProducto, String nombreProducto, int cantidadProductos, int valor, int contenido, String unidadDeMedida) {
        this.facturaidFactura = facturaidFactura;
        this.productoidProducto = productoidProducto;
        this.nombreProducto = nombreProducto;
        this.cantidadProductos = cantidadProductos;
        this.valor = valor;
        this.contenido = contenido;
        this.unidadDeMedida = unidadDeMedida;
    }

    

    public int getFacturaidFactura() {
        return facturaidFactura;
    }

    public void setFacturaidFactura(int facturaidFactura) {
        this.facturaidFactura = facturaidFactura;
    }

    public int getProductoidProducto() {
        return productoidProducto;
    }

    public void setProductoidProducto(int productoidProducto) {
        this.productoidProducto = productoidProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(int cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getContenido() {
        return contenido;
    }

    public void setContenido(int contenido) {
        this.contenido = contenido;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }
    
    
    
}
