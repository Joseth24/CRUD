package Controller;

import dao.Login;
import modelo.AdministradorM;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@Named(value = "loginC")
@SessionScoped
public class LoginC implements Serializable {
//Objeto de Sesion

    private AdministradorM administrador = new AdministradorM();

    private List<AdministradorM> lstUsuario;

    //Variables de Logeo
    private String IdUsuario;
    private String NombreUsuario;
    private String Pass;
    private String Nombre;
    private String Estado;
    private int intentos, number;

    public void increment() {
        number++;
        if (number > 5) {
            number = 0;
            intentos = 0;
            RequestContext.getCurrentInstance().execute(" location.reload (); ");
        }
    }

    public void startSessionEmpleado() throws Exception {
        Login ImplDAO;
        try {
            ImplDAO = new Login();
            administrador = ImplDAO.IniciarSesion(NombreUsuario, Pass);

            if ((getAdministrador()) != null) {
                intentos = 0;
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("AdministradorM", getAdministrador());
                switch (administrador.getTRABAJADOR_TIPO()) {
                    case "A":
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/Reserva_Final/faces/template/MensajeUsuario.xhtml");
                        break;
                    case "C":
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/Reserva_Final/faces/template/MensajeUsuario.xhtml");
                        break;
                }

            } else {
                setPass(null);
                administrador = new AdministradorM();
                intentos++;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("CONTRASEÑA O USUARIO INCORRECTO"));
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void finishSession() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear(); //Cierra la Session
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Reserva_Final/faces/Login.xhtml"); // Mandamos al UsuarioC
    }

    public void securityLogin() throws IOException {
        AdministradorM us = SesionUtils.obtenerObjetoSesion();
        if (us != null) {
            switch (us.getTRABAJADOR_TIPO()) {
                case "A":
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/Reserva_Final/faces/template/MensajeUsuario.xhtml");
                    break;
                case "C":
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/Reserva_Final/faces/template/MensajeUsuario.xhtml");
                    break;
            }
        }
    }

    public void securitySession() throws IOException {
        if (SesionUtils.obtenerObjetoSesion() == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Reserva");
        }
    }

    public void obtenerDatos() {
        System.out.println(SesionUtils.ObtenerCodigoSesion());
        System.out.println(SesionUtils.ObtenerNombreSesion());
    }

    //GETTER AND SETTER
    public AdministradorM getAdministrador() {
        return administrador;
    }

    public void setAdministrador(AdministradorM administrador) {
        this.administrador = administrador;
    }

    public List<AdministradorM> getLstUsuario() {
        return lstUsuario;
    }

    public void setLstUsuario(List<AdministradorM> lstUsuario) {
        this.lstUsuario = lstUsuario;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
