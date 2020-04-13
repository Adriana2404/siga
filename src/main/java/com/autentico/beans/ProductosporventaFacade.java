/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autentico.beans;

import com.autentico.entities.Producto;
import com.autentico.entities.Productosporventa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author User
 */
@Stateless
public class ProductosporventaFacade extends AbstractFacade<Productosporventa> {

    @PersistenceContext(unitName = "com.autentico_SIGAProyecto_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductosporventaFacade() {
        super(Productosporventa.class);
    }
    
    public Producto getProductoCantidad(Integer id){
        return em.createQuery("select p from Producto p WHERE p.idProducto = '" + id + "'", Producto.class).getSingleResult();
    }
    
}
