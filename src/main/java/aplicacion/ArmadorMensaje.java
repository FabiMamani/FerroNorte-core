package aplicacion;

public class ArmadorMensaje {
    public static String armarMensaje(String estacion, int demora) {
        return String.format(
                "El tren está demorado %d minutos",
                estacion, demora
        );
    }
}