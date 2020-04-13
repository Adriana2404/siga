package com.autentico.entities;

import com.autentico.entities.Barrio;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-09T20:58:10")
@StaticMetamodel(Ciudad.class)
public class Ciudad_ { 

    public static volatile SingularAttribute<Ciudad, String> nombreCiudad;
    public static volatile ListAttribute<Ciudad, Barrio> barrioList;
    public static volatile SingularAttribute<Ciudad, String> departamento;
    public static volatile SingularAttribute<Ciudad, Integer> idCiudad;

}