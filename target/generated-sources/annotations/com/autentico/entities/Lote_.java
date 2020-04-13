package com.autentico.entities;

import com.autentico.entities.Producto;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-09T20:58:10")
@StaticMetamodel(Lote.class)
public class Lote_ { 

    public static volatile SingularAttribute<Lote, Date> fechafabricacion;
    public static volatile SingularAttribute<Lote, Producto> producto;
    public static volatile SingularAttribute<Lote, Date> fechavencimiento;
    public static volatile SingularAttribute<Lote, Integer> idLote;

}