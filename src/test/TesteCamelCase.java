package test;

import exception.ConverterException;
import models.ConverterCamelCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class TesteCamelCase {

    private ConverterCamelCase converter;

    // ? -> Tipo genérico, espera qualquer tipo de dado
    private List<?> lista;

    @Before
    public void inicializa(){
        converter = new ConverterCamelCase("Teste");
        lista = new ArrayList<>();
    }

    @Test(expected = ConverterException.class)
    public void testeException(){
        converter.converterCamelCase(null);
        converter.converterCamelCase(" ");
        converter.converterCamelCase("10Primeiros");
    }

    @Test
    public void testeConverterCamelCase(){
        // string simples
        lista = converter.converterCamelCase("jax");
        assertEquals(1, lista.size());
        assertEquals("jax", lista.get(0));

        // string camelCase
        lista = converter.converterCamelCase("jaxTeller");
        assertEquals(2, lista.size());
        assertEquals("jax", lista.get(0));
        assertEquals("teller", lista.get(1));

        // string CamelCase
        lista = converter.converterCamelCase("ClayMorrow");
        assertEquals(2, lista.size());
        assertEquals("clay", lista.get(0));
        assertEquals("morrow", lista.get(1));

        // string SIMPLES
        lista = converter.converterCamelCase("SONS");
        assertEquals(1, lista.size());
        assertEquals(lista.get(0), "SONS");

        // string SonsOfAnarchy
        lista = converter.converterCamelCase("SonsOfAnarchy");
        assertEquals(3, lista.size());
        assertEquals(lista.get(0), "sons");
        assertEquals(lista.get(1), "of");
        assertEquals(lista.get(2), "anarchy");

        // string com número
        lista = converter.converterCamelCase("numero10Jogador");
        assertEquals(3, lista.size());
        assertEquals("10", lista.get(1));
    }

    @Test
    public void testeTamanho(){
        Assert.assertEquals(3, converter.tamanho("dev"));
    }

    @Test
    public void testeNull(){
        assertFalse(converter.stringValida(""));
        assertFalse(converter.stringValida(" "));
        assertFalse(converter.stringValida(null));
    }

    @Test
    public void testeIniciaComNumero(){
        assertTrue(converter.iniciaComNumero("10Numeros"));
        assertFalse(converter.iniciaComNumero("NaoIniciaComNumero10"));
    }

    @Test
    public void testeCaracterEspecial(){
        assertTrue(converter.contemCaracterEspecial("&&&---"));
        assertFalse(converter.contemCaracterEspecial("CaracterEspecial"));
    }

    @Test
    public void testeNumeroNaString(){
        assertTrue(converter.contemNumeroNaString("Jogador10"));
        assertFalse(converter.contemNumeroNaString("NaoTemJogadorDez"));
    }
}
