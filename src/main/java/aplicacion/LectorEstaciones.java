package aplicacion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorEstaciones {
    public List<String> leerEstaciones(String archivo) throws IOException {
        List<String> estaciones = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String estacion;
            while ((estacion = br.readLine()) != null) {
                estaciones.add(estacion);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return estaciones;
    }

}
