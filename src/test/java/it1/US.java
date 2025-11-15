package it1;
import aplicacion.FerroNorte;
import interfaces.MedioComunicacion;
import interfaces.Observador;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;


public class US {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void ca_1_notificar() {
        MedioComunicacion medioMock =  Mockito.mock(MedioComunicacion.class);
        Mockito.when(medioMock.obtenerNombre()).thenReturn("Gmail");
        Mockito.doAnswer(invocation -> {
            String estacion = invocation.getArgument(0);
            System.out.println("Demora reportada en estación : " + estacion);
            return null;
        }).when(medioMock).enviarComunicacion(Mockito.anyString());

        Set<MedioComunicacion> medioComunicacion = new HashSet<>();
        medioComunicacion.add(medioMock);
        FerroNorte fn = new FerroNorte(medioComunicacion);

        fn.priorizarMedio("Gmail");
        String msj = "Demora reportada en estación : Tigre";
        fn.notificar("Tigre");
        String salida = outContent.toString().trim();
        System.out.println(salida);
        Assertions.assertEquals(msj, salida);
    }

    @Test
    public void ca_2_omitir_notificación() {
        MedioComunicacion medioMock =  Mockito.mock(MedioComunicacion.class);
        Mockito.when(medioMock.obtenerNombre()).thenReturn("Gmail");
        Mockito.doAnswer(invocation -> {
            String estacion = invocation.getArgument(0);
            System.out.println("Demora reportada en estación : " + estacion);
            return null;
        }).when(medioMock).enviarComunicacion(Mockito.anyString());

        Set<MedioComunicacion> medioComunicacion = new HashSet<>();
        medioComunicacion.add(medioMock);
        FerroNorte fn = new FerroNorte(medioComunicacion);

        //fn.priorizarMedio("Telegram");
        fn.notificar("Tigre");
        String salida = outContent.toString().trim();
        Assertions.assertEquals(salida,"");
        Assertions.assertTrue(outContent.toString().isEmpty());

    }
}
