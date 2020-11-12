package Controller;

import modelo.AdministradorM;
import javax.faces.context.FacesContext;

public class SesionUtils {

    public static AdministradorM obtenerObjetoSesion() {
        return (AdministradorM) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
    }

    public static String ObtenerNombreSesion() {
        AdministradorM us = (AdministradorM) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        if (us != null) {
            return us.getTRABAJADOR_NOMBRE();
        } else {
            return null;
        }
    }

    public static String ObtenerCodigoSesion() {
        AdministradorM us = (AdministradorM) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        if (us != null) {
            return us.getTRABAJADOR_NOMBRE();
        } else {
            return null;
        }
    }

}
