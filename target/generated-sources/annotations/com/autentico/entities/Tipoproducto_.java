package com.autentico.entities;

import com.autentico.entities.Producto;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-09T20:58:10")
@StaticMetamodel(Tipoproducto.class)
public class Tipoproducto_ { 

    public static volatile SingularAttribute<Tipoproducto, String> descripcion;
    public static volatile ListAttribute<Tipoproducto, Producto> productoList;
    public static volatile SingularAttribute<Tipoproducto, String> nombre;
    public static volatile SingularAttribute<Tipoproducto, Integer> idTipoProducto;

}