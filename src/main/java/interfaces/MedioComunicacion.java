package interfaces;

import javax.swing.*;

public interface MedioComunicacion {
    public void enviarComunicacion(String estacion);
    String obtenerNombre();
    JPanel obtenerPanel();
    String obtenerDato();
}
