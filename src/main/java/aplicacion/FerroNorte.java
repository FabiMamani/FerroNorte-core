package aplicacion;

import interfaces.MedioComunicacion;
import interfaces.Observable;
import interfaces.Observador;

import java.util.*;

public class FerroNorte implements Observable {
    List<String> estaciones;
    //Map<String, MedioComunicacion> mediosComunicacion;

    Set<MedioComunicacion> mediosComunicacion;
    Set<Observador> observadores;
    public FerroNorte(Set<MedioComunicacion> medioComunicacion, List<String> estaciones){
        this.mediosComunicacion = medioComunicacion;
        this.estaciones = estaciones;
        this.observadores = new HashSet<>();
    }

    public void agregarObservador(MedioComunicacion medioComunicacion) {
        if (medioComunicacion == null)
            throw new NullPointerException();
        this.mediosComunicacion.add(medioComunicacion);
        System.out.println("Se agrega observador");
    }


    public Set<MedioComunicacion> obtenerMedios(){
        return mediosComunicacion;
    }

    /*public MedioComunicacion obtenerValoresDeMedios(String seleccion){
        return mediosComunicacion.get(seleccion);
    }*/

    @Override
    public void notificar(String mensaje) {
        this.mediosComunicacion.forEach(medioComunicacion -> medioComunicacion.enviarComunicacion(mensaje));
    }
}