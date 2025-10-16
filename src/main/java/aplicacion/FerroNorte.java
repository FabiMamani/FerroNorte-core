package aplicacion;

import interfaces.Observable;
import interfaces.Observer;

import java.util.HashMap;

public class FerroNorte implements Observable {
    private HashMap<String, Observer> observadores;
    public FerroNorte(){
        this.observadores = new HashMap<>();
    }

    @Override
    public void agregarObservador(Observer o) {
        if (o == null)
            throw new NullPointerException();
        this.observadores.put(o.getClass().getSimpleName(), o);
    }
}