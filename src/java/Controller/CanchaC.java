package Controller;

import dao.Cancha;
import modelo.CanchaM;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "canchaC")
@SessionScoped
public class CanchaC implements Serializable {

    private CanchaM cancha = new CanchaM();
    private List<CanchaM> lstCancha;
    private CanchaM selectCancha;

    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
        }
    }

    public void registrar() throws Exception {
        Cancha dao;
        try {
            dao = new Cancha();
            dao.registrar(cancha);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AGREGADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }

    public void limpiar() {
        cancha = new CanchaM();
    }

    public void listar() throws Exception {
        Cancha dao;
        try {
            dao = new Cancha();
            lstCancha = dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar() throws Exception {
        Cancha dao;
        try {
            dao = new Cancha();
            dao.eliminar(cancha);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ELIMINADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
        }
    }

    public void modificar() throws Exception {
        Cancha dao;
        try {
            dao = new Cancha();
            dao.modificar(selectCancha);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "MODIFICADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }

    //GETTER AND SETTER
    public CanchaM getCancha() {
        return cancha;
    }

    public void setCancha(CanchaM cancha) {
        this.cancha = cancha;
    }

    public List<CanchaM> getLstCancha() {
        return lstCancha;
    }

    public void setLstCancha(List<CanchaM> lstCancha) {
        this.lstCancha = lstCancha;
    }

    public CanchaM getSelectCancha() {
        return selectCancha;
    }

    public void setSelectCancha(CanchaM selectCancha) {
        this.selectCancha = selectCancha;
    }

}
