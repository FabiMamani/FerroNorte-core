package inicializacion;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import aplicacion.FerroNorte;
import aplicacion.LectorEstaciones;
import interfaces.MedioComunicacion;


public class FactoryFerroNorte {

    public static final String defaultConfigPath = "config.properties";
    public static final String defaultDiscoveryPath = "plugins";
    public static final String defaultEstacionesPath = "estaciones.txt";

    public static FerroNorte create(String propertiesPath)
            throws IOException, IllegalArgumentException {
        Properties cfg = loadConfig(propertiesPath);
        //var dr = new Discoverer<>(MedioComunicacion.class);
        String pluginsPath = cfg.getProperty("discoveryPath");
        Discovery dr = new Discovery();
        Set<MedioComunicacion> plugins = dr.discover(pluginsPath);
        for (MedioComunicacion plugin : plugins) {
            if (plugin == null){
                System.out.println("Verificar null");
            }else {
                System.out.println("Plugin encontrado: " + plugin.obtenerNombre());
            }

        }

        LectorEstaciones lectorEstaciones = new LectorEstaciones();
        List<String> estaciones = lectorEstaciones.leerEstaciones(cfg.getProperty("estacionesPath"));

        return new FerroNorte(plugins, estaciones);
    }

    static Properties loadConfig(String propertiesPath)
            throws FileNotFoundException, IllegalArgumentException {
        Properties result = new Properties();
        if (propertiesPath == null) {
            if (Files.isRegularFile(Paths.get(defaultConfigPath)))
                propertiesPath = defaultConfigPath;
            else {
                result.setProperty("discoveryPath", defaultDiscoveryPath);
                result.setProperty("estacionesPath", defaultEstacionesPath);
                return result;
            }
        }
        if (Files.isDirectory(Paths.get(propertiesPath))) {
            result.setProperty("discoveryPath", propertiesPath);
            return result;
        }
        if (Files.exists(Paths.get(propertiesPath)) &&
                !propertiesPath.endsWith(".properties"))
            throw new IllegalArgumentException(propertiesPath);
        try (FileReader reader = new FileReader(propertiesPath)){
            result.load(reader);
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
}

