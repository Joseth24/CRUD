package Controller;

import dao.Cliente;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.ClienteM;

@Named(value = "clienteC")
@SessionScoped
public class ClienteC implements Serializable {

    private ClienteM cliente = new ClienteM();
    private List<ClienteM> lstCliente;
    private ClienteM selectCliente;

    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
        }
    }

    public void registrar() throws Exception {
        Cliente dao;
        try {
            dao = new Cliente();
            dao.registrar(cliente);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AGREGADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }

    public void limpiar() {
        cliente = new ClienteM();
    }

    public void listar() throws Exception {
        Cliente dao;
        try {
            dao = new Cliente();
            lstCliente = dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar() throws Exception {
        Cliente dao;
        try {
            dao = new Cliente();
            dao.eliminar(cliente);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ELIMINADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
        }
    }

    public void modificar() throws Exception {
        Cliente dao;
        try {
            dao = new Cliente();
            dao.modificar(selectCliente);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "MODIFICADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }

    }
    //GETTER AND SETTER

    public ClienteM getCliente() {
        return cliente;
    }

    public void setCliente(ClienteM cliente) {
        this.cliente = cliente;
    }

    public List<ClienteM> getLstCliente() {
        return lstCliente;
    }

    public void setLstCliente(List<ClienteM> lstCliente) {
        this.lstCliente = lstCliente;
    }

    public ClienteM getSelectCliente() {
        return selectCliente;
    }

    public void setSelectCliente(ClienteM selectCliente) {
        this.selectCliente = selectCliente;
    }

}
