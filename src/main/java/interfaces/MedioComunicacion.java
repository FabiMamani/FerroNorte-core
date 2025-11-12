package interfaces;

import javax.swing.*;

public interface MedioComunicacion extends Observador{
    public void enviarComunicacion(String estacion);
    String obtenerNombre();
    JPanel obtenerPanel();
}
