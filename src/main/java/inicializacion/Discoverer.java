package inicializacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Discoverer <T> {

    Class<T> type;

    public Discoverer(Class<T> type) { this.type = type; }

    public Set<T> discover(String path) throws FileNotFoundException {
        File target = new File(path);
        if (!target.exists())
            throw new FileNotFoundException(path);
        if (!target.isDirectory())
            throw new IllegalArgumentException(path);

        URL[] cp = null;
        try { cp = new URL[] { target.toURI().toURL() }; }
        catch (MalformedURLException e) { throw new RuntimeException(e); }
        try (URLClassLoader cl = new URLClassLoader(cp)){
            return discover(target, cl);
        } catch (ClassNotFoundException e) {
            System.err.println("user.dir: " + System.getProperty("user.dir"));
            System.err.println("path: " + path);
            System.err.println("classpath: ");
            for (URL it : cp)
                System.err.println(" - " + it);
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Set<T> discover(File target, ClassLoader cl) throws ClassNotFoundException {
        Comparator<T> cmp = Comparator.comparing(t -> t.getClass().getName());
        Set<T> result = new TreeSet<>(cmp);
        for (File f : target.listFiles()) {
            if (!f.getName().endsWith(".class")) continue;
            Class<?> cls = null;
            try {
                cls = cl.loadClass(f.getName().replace(".class", ""));
            } catch (ClassNotFoundException e) {
                System.err.println("filename: " + f.getName());
                throw e;
            }
            if (!type.isAssignableFrom(cls)) continue;
            try {
                @SuppressWarnings("unchecked")
                T instance = (T) cls.getConstructor().newInstance();
                result.add(instance);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }

}
