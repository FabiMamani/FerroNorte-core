package aplicacion;

import interfaces.MedioComunicacion;
import interfaces.Observable;
import interfaces.Observador;

import java.util.*;

public class FerroNorte implements Observable {

    Set<MedioComunicacion> mediosComunicacion;
    Set<Observador> observadores;
    String medioPriorizado;
    public FerroNorte(Set<MedioComunicacion> medioComunicacion){
        this.mediosComunicacion = medioComunicacion;
        this.observadores = new HashSet<>();
    }
    public void agregarObservador(Observador observador){

        if (observador instanceof Observador){
            this.observadores.add((Observador) observador);
        } else{
            System.out.println("Se agrega observador");
            this.mediosComunicacion.add(observador);
        }

    }



    public Set<MedioComunicacion> obtenerMedios(){
        return mediosComunicacion;
    }

    public void priorizarMedio(String medio){
        medioPriorizado = medio;
    }


    @Override
    public void notificar(String mensaje) {

        //      this.mediosComunicacion.forEach(medioComunicacion -> medioComunicacion.enviarComunicacion(mensaje));
        this.mediosComunicacion.stream()
                .filter(m -> m.obtenerNombre().equalsIgnoreCase(medioPriorizado))
                .findFirst()
                .ifPresent(m -> m.enviarComunicacion(mensaje));

    }
}