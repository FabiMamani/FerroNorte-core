package it1;

import interfaces.Observador;

import javax.swing.*;

public class MockObserver implements Observador {
    @Override
    public void enviarComunicacion(String estacion) {
        System.out.println("Demora reportada en estación: "+estacion);
    }

    @Override
    public String obtenerNombre() {
        return null;
    }

    @Override
    public JPanel obtenerPanel() {
        return null;
    }

    @Override
    public String obtenerDato() {
        return null;
    }

    @Override
    public void actualizar(String notificacion) {

    }
}
