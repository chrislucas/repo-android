package vc.com.icomon.camlib.actions.camera2;

public class UtilsException {

    public static String getMessage(Exception e) {
        return e != null && e.getMessage() != null ? e.getMessage() : "Nã foi possível recuperar a mensagem de erro";
    }

}
