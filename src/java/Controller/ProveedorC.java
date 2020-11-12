package Controller;

import dao.Proveedor;
import modelo.ProveedorM;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "proveedorC")
@SessionScoped
public class ProveedorC implements Serializable {

    private ProveedorM proveedor = new ProveedorM();
    private List<ProveedorM> lstProveedor;
    private ProveedorM selectProveedor;

    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
        }
    }

    public void registrar() throws Exception {
        Proveedor dao;
        try {
            dao = new Proveedor();
            dao.registrar(proveedor);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AGREGADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
        }
    }

    public void limpiar() {
        proveedor = new ProveedorM();
    }

    public void modificar() throws Exception {
        Proveedor dao;
        try {
            dao = new Proveedor();
            dao.modificar(selectProveedor);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "MODIFICADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
        }
    }

    public void listar() throws Exception {
        Proveedor dao;
        try {
            dao = new Proveedor();
            lstProveedor = dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar() throws Exception {
        Proveedor dao;
        try {
            dao = new Proveedor();
            dao.eliminar(selectProveedor);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ELIMINADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
        }
    }

    //GETTER AND SETTER
    public ProveedorM getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorM proveedor) {
        this.proveedor = proveedor;
    }

    public List<ProveedorM> getLstProveedor() {
        return lstProveedor;
    }

    public void setLstProveedor(List<ProveedorM> lstProveedor) {
        this.lstProveedor = lstProveedor;
    }

    public ProveedorM getSelectProveedor() {
        return selectProveedor;
    }

    public void setSelectProveedor(ProveedorM selectProveedor) {
        this.selectProveedor = selectProveedor;
    }

}
