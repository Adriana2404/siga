/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autentico.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inventario.findAll", query = "SELECT i FROM Inventario i")
    , @NamedQuery(name = "Inventario.findByIdInventario", query = "SELECT i FROM Inventario i WHERE i.idInventario = :idInventario")
    , @NamedQuery(name = "Inventario.findByCantocupada", query = "SELECT i FROM Inventario i WHERE i.cantocupada = :cantocupada")
    , @NamedQuery(name = "Inventario.findByCantdisponible", query = "SELECT i FROM Inventario i WHERE i.cantdisponible = :cantdisponible")})
public class Inventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idInventario")
    private Integer idInventario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantocupada")
    private int cantocupada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantdisponible")
    private int cantdisponible;
    @JoinColumn(name = "producto", referencedColumnName = "idProducto")
    @ManyToOne(optional = false)
    private Producto producto;
    @JoinColumn(name = "punto", referencedColumnName = "idPunto")
    @ManyToOne(optional = false)
    private Punto punto;

    public Inventario() {
    }

    public Inventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    public Inventario(Integer idInventario, int cantocupada, int cantdisponible) {
        this.idInventario = idInventario;
        this.cantocupada = cantocupada;
        this.cantdisponible = cantdisponible;
    }

    public Integer getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    public int getCantocupada() {
        return cantocupada;
    }

    public void setCantocupada(int cantocupada) {
        this.cantocupada = cantocupada;
    }

    public int getCantdisponible() {
        return cantdisponible;
    }

    public void setCantdisponible(int cantdisponible) {
        this.cantdisponible = cantdisponible;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Punto getPunto() {
        return punto;
    }

    public void setPunto(Punto punto) {
        this.punto = punto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInventario != null ? idInventario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventario)) {
            return false;
        }
        Inventario other = (Inventario) object;
        if ((this.idInventario == null && other.idInventario != null) || (this.idInventario != null && !this.idInventario.equals(other.idInventario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.autentico.entities.Inventario[ idInventario=" + idInventario + " ]";
    }
    
}
