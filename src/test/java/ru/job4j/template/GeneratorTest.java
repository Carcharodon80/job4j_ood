package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class GeneratorTest {

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenNoKey() {
        Generator generator = new StarWarsGenerator();
        String template = ("${name}, I am your ${subject}!");
        Map<String, String> map = new HashMap<>();
        map.put("name", "Luke");
        generator.produce(template, map);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenExtraKey() {
        Generator generator = new StarWarsGenerator();
        String template = ("${name}, I am your ${subject}!");
        Map<String, String> map = new HashMap<>();
        map.put("name", "Luke");
        map.put("subject", "father");
        map.put("nanny", "Mary Poppins");
        generator.produce(template, map);
    }

    @Ignore
    @Test
    public void whenOK() {
        Generator generator = new StarWarsGenerator();
        String template = ("${name}, I am your ${subject}!");
        Map<String, String> map = new HashMap<>();
        map.put("name", "Luke");
        map.put("subject", "father");
        String expected = "Luke, I am your father!";
        assertEquals(expected, generator.produce(template, map));
    }
}