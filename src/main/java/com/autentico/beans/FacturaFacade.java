/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autentico.beans;

import com.autentico.entities.Factura;
import com.autentico.entities.Productosporventa;
import com.autentico.entities.Producto;
import com.autentico.entities.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author User
 */
@Stateless
public class FacturaFacade extends AbstractFacade<Factura> {

    @PersistenceContext(unitName = "com.autentico_SIGAProyecto_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Integer getLastElement(){
        return em.createQuery("select max(t.idFactura) from Factura t", Integer.class).getSingleResult();
    }
    
    public List<Factura> getFacturaByUser(String vendedorid){
        return em.createQuery("select t from Factura t where t.vendedor.idusuario = " + vendedorid + "", Factura.class).getResultList();
    }
  
    public Usuario getUser(String email){
        return em.createQuery("select u from Usuario u WHERE u.email = '" + email + "'", Usuario.class).getSingleResult();
    }
    
    public List<CantidadVentas> getFacturaSales() {
    List<CantidadVentas> sales = em.createQuery(
        "SELECT new com.autentico.beans.CantidadVentas(f.fecha, COUNT(f)) as cantidadVentas FROM Factura f GROUP BY f.fecha", CantidadVentas.class).getResultList();
    return sales;
    }
    
    public List<Productosventa> getProductosPorVenta(Integer id) {
    List<Productosventa> products = em.createQuery(
        "SELECT new com.autentico.beans.Productosventa(c.productosporventaPK.facturaidFactura, p.idProducto, p.nombreProducto, c.cantidadProductos, p.valor, p.contenido, p.unidadDeMedida) FROM Productosporventa c JOIN Producto p ON c.productosporventaPK.productoidProducto = p.idProducto WHERE c.productosporventaPK.facturaidFactura = :idFactura", Productosventa.class)
        .setParameter("idFactura", id).getResultList();
    return products;
    }
    
    public void updateProductQuantity(Integer id, int cantidad){
        Query query = em.createQuery("UPDATE Producto p SET p.existenciasTotales = :cantidad WHERE p.idProducto = :idpr");
        query.setParameter("cantidad", cantidad);
        query.setParameter("idpr", id);
        query.executeUpdate();
    }
    
    public FacturaFacade() {
        super(Factura.class);
    }
    

    
}