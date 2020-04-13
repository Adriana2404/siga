/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autentico.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Embeddable
public class ProductosporventaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Factura_idFactura")
    private int facturaidFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Producto_idProducto")
    private int productoidProducto;

    public ProductosporventaPK() {
    }

    public ProductosporventaPK(int facturaidFactura, int productoidProducto) {
        this.facturaidFactura = facturaidFactura;
        this.productoidProducto = productoidProducto;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) facturaidFactura;
        hash += (int) productoidProducto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosporventaPK)) {
            return false;
        }
        ProductosporventaPK other = (ProductosporventaPK) object;
        if (this.facturaidFactura != other.facturaidFactura) {
            return false;
        }
        if (this.productoidProducto != other.productoidProducto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autentico.entities.ProductosporventaPK[ facturaidFactura=" + facturaidFactura + ", productoidProducto=" + productoidProducto + " ]";
    }
    
}
