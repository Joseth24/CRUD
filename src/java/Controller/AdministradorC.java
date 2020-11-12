package Controller;

import dao.Administrador;
import modelo.AdministradorM;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "administradorC")
@SessionScoped
public class AdministradorC implements Serializable {

    private AdministradorM administrador = new AdministradorM();
    private List<AdministradorM> lstAdministrador;
    private AdministradorM selectAdministrador;

    //METODO INICIAL
    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
        }
    }

    //METODO PARA REGISTRAR
    public void registrar() throws Exception {
        Administrador dao;
        try {
            dao = new Administrador();
            dao.registrar(administrador);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AGREGADO", null));
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", null));
            throw e;
        }
    }

    public void listar() throws Exception {
        Administrador dao;
        try {
            dao = new Administrador();
            lstAdministrador = dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {
        Administrador dao;
        try {
            dao = new Administrador();
            dao.modificar(selectAdministrador);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "MODIFICADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }

    public void eliminar() throws Exception {
        Administrador dao;
        try {
            dao = new Administrador();
            dao.eliminar(selectAdministrador);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ELIMINADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
        }
    }

    public void limpiar() {
        administrador = new AdministradorM();
    }

    //GETTER AND SETTER
    public AdministradorM getAdministrador() {
        return administrador;
    }

    public void setAdministrador(AdministradorM administrador) {
        this.administrador = administrador;
    }

    public List<AdministradorM> getLstAdministrador() {
        return lstAdministrador;
    }

    public void setLstAdministrador(List<AdministradorM> lstAdministrador) {
        this.lstAdministrador = lstAdministrador;
    }

    public AdministradorM getSelectAdministrador() {
        return selectAdministrador;
    }

    public void setSelectAdministrador(AdministradorM selectAdministrador) {
        this.selectAdministrador = selectAdministrador;
    }

}
