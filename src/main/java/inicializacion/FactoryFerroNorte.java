package inicializacion;


import aplicacion.FerroNorte;
import interfaces.Observer;
import interfaces.Source;

import java.io.FileNotFoundException;
import java.util.Set;

public class FactoryFerroNorte  {

    public FerroNorte iniciar(Source source, String path) throws FileNotFoundException {
        Discovery discover = new Discovery();
        Set<Observer> notificadores = discover.discover(path);
        FerroNorte ferroNorte = new FerroNorte();
        notificadores.forEach(ferroNorte::agregarObservador);

        return ferroNorte;
    }


}
