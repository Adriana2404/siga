/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autentico.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "productosporventa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productosporventa.findAll", query = "SELECT p FROM Productosporventa p")
    , @NamedQuery(name = "Productosporventa.findByFacturaidFactura", query = "SELECT p FROM Productosporventa p WHERE p.productosporventaPK.facturaidFactura = :facturaidFactura")
    , @NamedQuery(name = "Productosporventa.findByProductoidProducto", query = "SELECT p FROM Productosporventa p WHERE p.productosporventaPK.productoidProducto = :productoidProducto")
    , @NamedQuery(name = "Productosporventa.findByCantidadProductos", query = "SELECT p FROM Productosporventa p WHERE p.cantidadProductos = :cantidadProductos")})
public class Productosporventa implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductosporventaPK productosporventaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidadProductos")
    private int cantidadProductos;

    public Productosporventa() {
    }

    public Productosporventa(ProductosporventaPK productosporventaPK) {
        this.productosporventaPK = productosporventaPK;
    }

    public Productosporventa(ProductosporventaPK productosporventaPK, int cantidadProductos) {
        this.productosporventaPK = productosporventaPK;
        this.cantidadProductos = cantidadProductos;
    }

    public Productosporventa(int facturaidFactura, int productoidProducto) {
        this.productosporventaPK = new ProductosporventaPK(facturaidFactura, productoidProducto);
    }

    public ProductosporventaPK getProductosporventaPK() {
        return productosporventaPK;
    }

    public void setProductosporventaPK(ProductosporventaPK productosporventaPK) {
        this.productosporventaPK = productosporventaPK;
    }

    public int getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(int cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productosporventaPK != null ? productosporventaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productosporventa)) {
            return false;
        }
        Productosporventa other = (Productosporventa) object;
        if ((this.productosporventaPK == null && other.productosporventaPK != null) || (this.productosporventaPK != null && !this.productosporventaPK.equals(other.productosporventaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autentico.entities.Productosporventa[ productosporventaPK=" + productosporventaPK + " ]";
    }
    
}
