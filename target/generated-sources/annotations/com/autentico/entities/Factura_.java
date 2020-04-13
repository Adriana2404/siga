package com.autentico.entities;

import com.autentico.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-09T20:58:10")
@StaticMetamodel(Factura.class)
public class Factura_ { 

    public static volatile SingularAttribute<Factura, Date> fecha;
    public static volatile SingularAttribute<Factura, Long> numfactura;
    public static volatile SingularAttribute<Factura, Usuario> vendedor;
    public static volatile SingularAttribute<Factura, String> estado;
    public static volatile SingularAttribute<Factura, Integer> idFactura;

}