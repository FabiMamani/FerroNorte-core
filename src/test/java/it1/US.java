package it1;

import aplicacion.FerroNorte;
import inicializacion.FactoryFerroNorte;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class US {

    @Test
    public void ca_1() throws IOException {
        MockObserver obs = new MockObserver();
        FerroNorte fn = FactoryFerroNorte.create("src/test/resources/config.properties");
        fn.agregarObservador(obs);
        fn.notificar("Tigre");
    }

    @Test
    public void ca_2(){

    }
}
