package aplicacion;

import interfaces.MedioComunicacion;
import interfaces.Observable;
import interfaces.Observador;

import java.util.*;

public class FerroNorte implements Observable {
    List<String> estaciones;
    Set<MedioComunicacion> mediosComunicacion;
    Set<Observador> observadores;
    public FerroNorte(Set<MedioComunicacion> medioComunicacion, List<String> estaciones){
        this.mediosComunicacion = medioComunicacion;
        this.estaciones = estaciones;
        this.observadores = new HashSet<>();
    }
    public void notificar(String notificacion){
        observadores.forEach(observador -> observador.actualizar(notificacion));
    }

    public Set<MedioComunicacion> obtenerMedioComunicacion(){
        return this.mediosComunicacion;
    }

    @Override
    public void agregarObservador(MedioComunicacion medioComunicacion) {
        if (medioComunicacion == null)
            throw new NullPointerException();
        this.mediosComunicacion.add(medioComunicacion);
    }

    public void agregarObservador(Observador o) {
        observadores.add(o);
    }
}