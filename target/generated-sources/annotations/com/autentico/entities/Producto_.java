package com.autentico.entities;

import com.autentico.entities.Inventario;
import com.autentico.entities.Lote;
import com.autentico.entities.Proveedor;
import com.autentico.entities.Tipoproducto;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-09T20:58:10")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, Integer> contenido;
    public static volatile SingularAttribute<Producto, Tipoproducto> tipoProducto;
    public static volatile SingularAttribute<Producto, String> unidadDeMedida;
    public static volatile SingularAttribute<Producto, Integer> valor;
    public static volatile ListAttribute<Producto, Inventario> inventarioList;
    public static volatile ListAttribute<Producto, Lote> loteList;
    public static volatile SingularAttribute<Producto, Proveedor> proveedor;
    public static volatile SingularAttribute<Producto, Integer> idProducto;
    public static volatile SingularAttribute<Producto, Integer> existenciasTotales;
    public static volatile SingularAttribute<Producto, String> nombreProducto;

}