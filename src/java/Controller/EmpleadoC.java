package Controller;

import dao.Empleado;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.EmpleadoM;

@Named(value = "empleadoC")
@SessionScoped
public class EmpleadoC implements Serializable {

    private EmpleadoM empleado = new EmpleadoM();
    private List<EmpleadoM> lstEmpleado;
    private EmpleadoM selectEmpleado;

    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
        }
    }

    public void registrar() throws Exception {
        Empleado dao;
        try {
            dao = new Empleado();
            dao.registrar(empleado);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AGREGADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }

    public void eliminar() throws Exception {
        Empleado dao;
        try {
            dao = new Empleado();
            dao.eliminar(selectEmpleado);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ELIMINADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }

    public void listar() throws Exception {
        Empleado dao;
        try {
            dao = new Empleado();
            lstEmpleado = dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {
        Empleado dao;
        try {
            dao = new Empleado();
            dao.modificar(selectEmpleado);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "MODIFICADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }

    public void limpiar() {
        empleado = new EmpleadoM();
    }

    //GETTER AND SETTER
    public EmpleadoM getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoM empleado) {
        this.empleado = empleado;
    }

    public List<EmpleadoM> getLstEmpleado() {
        return lstEmpleado;
    }

    public void setLstEmpleado(List<EmpleadoM> lstEmpleado) {
        this.lstEmpleado = lstEmpleado;
    }

    public EmpleadoM getSelectEmpleado() {
        return selectEmpleado;
    }

    public void setSelectEmpleado(EmpleadoM selectEmpleado) {
        this.selectEmpleado = selectEmpleado;
    }

}
