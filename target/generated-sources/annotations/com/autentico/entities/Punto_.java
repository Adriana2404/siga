package com.autentico.entities;

import com.autentico.entities.Barrio;
import com.autentico.entities.Inventario;
import com.autentico.entities.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-09T20:58:10")
@StaticMetamodel(Punto.class)
public class Punto_ { 

    public static volatile SingularAttribute<Punto, Barrio> barrio;
    public static volatile ListAttribute<Punto, Usuario> usuarioList;
    public static volatile SingularAttribute<Punto, Integer> idPunto;
    public static volatile SingularAttribute<Punto, String> direccion;
    public static volatile ListAttribute<Punto, Inventario> inventarioList;
    public static volatile SingularAttribute<Punto, String> nombrePunto;
    public static volatile SingularAttribute<Punto, Long> telefono;

}