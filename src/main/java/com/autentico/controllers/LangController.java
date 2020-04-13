package com.autentico.controllers;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("langController")
@SessionScoped
public class LangController implements Serializable {

    public LangController() {
    }
    
    public void switchLanguage(String language){
        if(language.equals("en")){
            Locale locale = new Locale("en");
            Locale.setDefault(locale);
        } else if(language.equals("es")) {
            Locale locale = new Locale("es");
            Locale.setDefault(locale);
        }
        
    }

}
