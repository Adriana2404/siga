package com.autentico.entities;

import com.autentico.entities.Producto;
import com.autentico.entities.Punto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-09T20:58:10")
@StaticMetamodel(Inventario.class)
public class Inventario_ { 

    public static volatile SingularAttribute<Inventario, Punto> punto;
    public static volatile SingularAttribute<Inventario, Integer> cantocupada;
    public static volatile SingularAttribute<Inventario, Integer> cantdisponible;
    public static volatile SingularAttribute<Inventario, Producto> producto;
    public static volatile SingularAttribute<Inventario, Integer> idInventario;

}