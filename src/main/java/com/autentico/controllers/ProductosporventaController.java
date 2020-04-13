package com.autentico.controllers;

import com.autentico.entities.Productosporventa;
import com.autentico.controllers.util.JsfUtil;
import com.autentico.controllers.util.JsfUtil.PersistAction;
import com.autentico.beans.ProductosporventaFacade;
import com.autentico.entities.Producto;
import com.autentico.entities.ProductosporventaPK;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;


@Named("productosporventaController")
@SessionScoped
public class ProductosporventaController implements Serializable {


    @EJB private com.autentico.beans.ProductosporventaFacade ejbFacade;
    private List<Productosporventa> items = null;
    private Productosporventa selected;

    public ProductosporventaController() {
    }

    public Productosporventa getSelected() {
        return selected;
    }

    public void setSelected(Productosporventa selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
        selected.setProductosporventaPK(new com.autentico.entities.ProductosporventaPK());
    }

    private ProductosporventaFacade getFacade() {
        return ejbFacade;
    }

    public Productosporventa prepareCreate() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        String factura = session.getAttribute("factura").toString();
        selected = new Productosporventa();
        initializeEmbeddableKey();
        int s = Integer.parseInt(factura);
        
        ProductosporventaPK productosporventaPK = new ProductosporventaPK(s, 0);

        
        selected.setProductosporventaPK(productosporventaPK);
        return selected;
    }

    public void create() {
        Producto pr = getFacade().getProductoCantidad(selected.getProductosporventaPK().getProductoidProducto());
        if(selected.getCantidadProductos() <= 0){
            FacesMessage message = new FacesMessage("La cantidad ingresada no es válida, verifique");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else{
        if(selected.getCantidadProductos() > pr.getExistenciasTotales()){
            FacesMessage message = new FacesMessage("No hay existencias suficientes para realizar esta compra","No hay existencias suficientes para realizar esta compra. Del producto " + pr.getNombreProducto() + " solo hay "+ pr.getExistenciasTotales() + " existencias");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        

        } else {
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProductosporventaCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        }
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProductosporventaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ProductosporventaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Productosporventa> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Productosporventa getProductosporventa(com.autentico.entities.ProductosporventaPK id) {
        return getFacade().find(id);
    }

    public List<Productosporventa> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Productosporventa> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass=Productosporventa.class)
    public static class ProductosporventaControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProductosporventaController controller = (ProductosporventaController)facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "productosporventaController");
            return controller.getProductosporventa(getKey(value));
        }

        com.autentico.entities.ProductosporventaPK getKey(String value) {
            com.autentico.entities.ProductosporventaPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.autentico.entities.ProductosporventaPK();
            key.setFacturaidFactura(Integer.parseInt(values[0]));
            key.setProductoidProducto(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.autentico.entities.ProductosporventaPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getFacturaidFactura());
            sb.append(SEPARATOR);
            sb.append(value.getProductoidProducto());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Productosporventa) {
                Productosporventa o = (Productosporventa) object;
                return getStringKey(o.getProductosporventaPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Productosporventa.class.getName()});
                return null;
            }
        }

    }

}
