package interfaces;

public interface MedioComunicacion extends Observador{
    public void enviarComunicacion(String estacion);
    String obtenerNombre();
}
