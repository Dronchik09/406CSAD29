package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

class AppTest {
    @Test
    void greetingReturnsHelloWorld() {
        assertEquals("Hello, World!", App.greeting());
    }

    @Test
    void mainPrintsHelloWorld() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try (PrintStream capturedOut = new PrintStream(output, true, StandardCharsets.UTF_8)) {
            System.setOut(capturedOut);
            App.main(new String[0]);
        } finally {
            System.setOut(originalOut);
        }

        assertEquals("Hello, World!" + System.lineSeparator(),
                output.toString(StandardCharsets.UTF_8));
    }
}
