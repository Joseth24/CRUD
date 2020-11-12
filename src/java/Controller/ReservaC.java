package Controller;

import dao.Cancha;
import dao.Cliente;
import dao.Empleado;
import dao.Producto;
import dao.Reserva;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.ReservaM;

@Named(value = "reservaC")
@SessionScoped
public class ReservaC implements Serializable {

    private ReservaM reserva = new ReservaM();
    private List<ReservaM> lstReserva;
    private ReservaM selectReserva;

    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
        }
    }

    public void registrar() throws Exception {
        Reserva dao;
        Cliente cliente;
        Producto producto;
        Cancha cancha;
        Empleado empleado;
        try {
            dao = new Reserva();
            cliente = new Cliente();
            producto = new Producto();
            cancha = new Cancha();
            empleado = new Empleado();
            reserva.setCLIENTE_CLIENTE_ID(cliente.obtenerCodigoCliente(reserva.getCLIENTE_CLIENTE_ID()));
            reserva.setPRODUCTO_PRODUCTO_ID(producto.obtenerCodigoProducto(reserva.getPRODUCTO_PRODUCTO_ID()));
            reserva.setCANCHA_CANCHA_ID(cancha.obtenerCodigoCancha(reserva.getCANCHA_CANCHA_ID()));
            reserva.setEMPLEADO_EMPLEADO_ID(empleado.obtenerCodigoEmpleado(reserva.getEMPLEADO_EMPLEADO_ID()));
            dao.registrar(reserva);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AGREGADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }

    public void modificar() throws Exception {
        Reserva dao;
        try {
            dao = new Reserva();
            dao.modificar(selectReserva);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "MODIFICADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }

    public void listar() throws Exception {
        Reserva dao;
        try {
            dao = new Reserva();
            lstReserva = dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar() throws Exception {
        Reserva dao;
        try {
            dao = new Reserva();
            dao.eliminar(selectReserva);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ELIMINADO", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", null));
            throw e;
        }
    }

    public void limpiar() {
        reserva = new ReservaM();
    }

    public List<String> completeCancha(String query) throws Exception {
        Cancha ubigeo = new Cancha();
        return ubigeo.autocompleteCancha(query);
    }

    public List<String> completeCliente(String query) throws Exception {
        Cliente ubigeo = new Cliente();
        return ubigeo.autocompleteCliente(query);
    }

    public List<String> completeProducto(String query) throws Exception {
        Producto ubigeo = new Producto();
        return ubigeo.autocompleteProducto(query);
    }

    public List<String> completeEmpleado(String query) throws Exception {
        Empleado ubigeo = new Empleado();
        return ubigeo.autocompleteEmpleado(query);
    }

    //GETTER AND SETTER
    public ReservaM getReserva() {
        return reserva;
    }

    public void setReserva(ReservaM reserva) {
        this.reserva = reserva;
    }

    public List<ReservaM> getLstReserva() {
        return lstReserva;
    }

    public void setLstReserva(List<ReservaM> lstReserva) {
        this.lstReserva = lstReserva;
    }

    public ReservaM getSelectReserva() {
        return selectReserva;
    }

    public void setSelectReserva(ReservaM selectReserva) {
        this.selectReserva = selectReserva;
    }

}
