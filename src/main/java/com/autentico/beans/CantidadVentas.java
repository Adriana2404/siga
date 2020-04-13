/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autentico.beans;

import java.util.Date;

/**
 *
 * @author User
 */
public class CantidadVentas {
    private Date fecha;
    private Long cantidadVentas;

    public CantidadVentas(Date fecha, Long cantidadVentas) {
        this.fecha = fecha;
        this.cantidadVentas = cantidadVentas;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getCantidadVentas() {
        return cantidadVentas;
    }

    public void setCantidadVentas(Long cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }
    
    
}
