package aplicacion;

import java.util.HashMap;
import java.util.Map;
public class Diferenciador {
    private Map<String, Integer> cronogramaOriginal;
    public Diferenciador(Map<String, Integer> cronogramaOriginal) {
        this.cronogramaOriginal = new HashMap<>(cronogramaOriginal);
    }
    public Map<String, Integer> sacarDiferencia(Map<String, Integer> cronogramaActual)
    {
        Map<String, Integer> diferencias = new HashMap<>();
        for (Map.Entry<String, Integer> entrada : cronogramaActual.entrySet()) {
            String estacion = entrada.getKey();
            int demoraActual = entrada.getValue();
            if (cronogramaOriginal.containsKey(estacion)) {
                int demoraOriginal = cronogramaOriginal.get(estacion);
                if (demoraActual != demoraOriginal) {
                    diferencias.put(estacion, demoraActual);
                }
            } else {
                diferencias.put(estacion, demoraActual);
            }
        }
        return diferencias;
    }
}
