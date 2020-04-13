package com.autentico.entities;

import com.autentico.entities.Ciudad;
import com.autentico.entities.Proveedor;
import com.autentico.entities.Punto;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-09T20:58:10")
@StaticMetamodel(Barrio.class)
public class Barrio_ { 

    public static volatile ListAttribute<Barrio, Proveedor> proveedorList;
    public static volatile SingularAttribute<Barrio, String> nombreBarrio;
    public static volatile SingularAttribute<Barrio, Integer> idBarrio;
    public static volatile SingularAttribute<Barrio, Ciudad> ciudad;
    public static volatile ListAttribute<Barrio, Punto> puntoList;

}