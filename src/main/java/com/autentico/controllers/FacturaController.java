package com.autentico.controllers;

import com.autentico.beans.CantidadVentas;
import com.autentico.entities.Factura;
import com.autentico.controllers.util.JsfUtil;
import com.autentico.controllers.util.JsfUtil.PersistAction;
import com.autentico.beans.FacturaFacade;
import com.autentico.beans.Productosventa;
import com.autentico.entities.Producto;

import com.autentico.entities.Productosporventa;
import com.autentico.entities.Usuario;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpSession;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartSeries;

@Named("facturaController")
@SessionScoped
public class FacturaController implements Serializable {

    private LineChartModel model = new LineChartModel();
    
    @EJB
    private com.autentico.beans.FacturaFacade ejbFacade;
    private List<Factura> items = null;
    private Factura selected;

    private List<Productosventa> productosventa = null;

    public LineChartModel getModel() {
        model = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        List<CantidadVentas> chartItems = ejbFacade.getFacturaSales();
        
        for(CantidadVentas item: chartItems){ 
            series1.set(item.getFecha(), 10);
        }
        series1.setLabel("Ventas por día");
        
        Date lastDate = chartItems.get(chartItems.size() - 1).getFecha();
 
        model.addSeries(series1);
 
        model.setTitle("Facturaciones realizadas por día");
        model.setZoom(true);
        model.getAxis(AxisType.Y).setLabel("Ventas Realizadas");
        DateAxis axis = new DateAxis("Fechas");
        axis.setTickFormat("%b %#d, %y");
 
        model.getAxes().put(AxisType.X, axis);
        
        return model;
    }

    public void setModel(LineChartModel model) {
        this.model = model;
    }
    
    
    public List<Productosventa> getProductosventa() {
        return productosventa;
    }
    
    public FacturaController() {
    }

    public Factura getSelected() {
        return selected;
    }

    public void setSelected(Factura selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FacturaFacade getFacade() {
        return ejbFacade;
    }

    public Factura prepareCreate() {
        selected = new Factura();
        initializeEmbeddableKey();
        Date d1 = new Date(); 
        selected.setFecha(d1);

        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        String username = session.getAttribute("user").toString();
        
        Usuario user = getFacade().getUser(username);
        
        selected.setVendedor(user);
        String numstr = 1000 + getFacade().getLastElement() + "00" + user.getIdusuario() + "";
        Integer num =  Integer.parseInt(numstr);
        selected.setNumfactura(num);
        selected.setEstado("Creado");
        return selected;
    }
    public Factura prepareShow() {
        productosventa = getFacade().getProductosPorVenta(selected.getIdFactura());
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("FacturaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FacturaUpdated"));
    }
    
    public void finish() {
        selected.setEstado("Terminado");
        productosventa = getFacade().getProductosPorVenta(selected.getIdFactura());
        
        for(Productosventa prventa : productosventa)
        {
            getFacade().updateProductQuantity(prventa.getProductoidProducto(), prventa.getCantidadProductos());
        }
        
        
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FacturaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("FacturaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void rowSelected(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        String iduser = session.getAttribute("iduser").toString();
        session.setAttribute("factura", selected.getIdFactura());
    }

    public List<Factura> getItems() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        String iduser = session.getAttribute("iduser").toString();
        String role = session.getAttribute("role").toString();

        if (items == null) {
            if(role.equals("Cliente")){
                items = getFacade().getFacturaByUser(iduser);
            } else {
                items = getFacade().findAll();
            }
            
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

    public Factura getFactura(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Factura> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Factura> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Factura.class)
    public static class FacturaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FacturaController controller = (FacturaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "facturaController");
            return controller.getFactura(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Factura) {
                Factura o = (Factura) object;
                return getStringKey(o.getIdFactura());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Factura.class.getName()});
                return null;
            }
        }

    }

}
