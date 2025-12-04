package com.NicFacturas.NicFacturasDemo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MavenLauncher {
    public static String runRunEnvio(String projectPomPath) throws IOException, InterruptedException {    
        // Detectar automáticamente ruta a mvn
        String mavenHome = System.getenv("MAVEN_HOME");
        String mvnCommand = mavenHome + "\\bin\\mvn.cmd";

        System.out.println("Usando Maven: " + mvnCommand);
        System.out.println("Working directory (user.dir): " + System.getProperty("user.dir"));
        
        Process p = Runtime.getRuntime().exec(new String[]{
            mvnCommand, "exec:exec@run-envio"
        });
        
        String IDEnvio = null;
        // Patrón regex para <TRACKID>número</TRACKID>
        Pattern pattern = Pattern.compile("<TRACKID>(\\d+)</TRACKID>");
        
        try (BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String line;
            while ((line = r.readLine()) != null) {
                System.out.println(line);
                
                // Buscar el TRACKID en cada línea
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    IDEnvio = matcher.group(1);  // Captura solo el número
                    System.out.println("✅ TRACKID encontrado: " + IDEnvio);
                }
            }
        }

        int exitCode = p.waitFor();
        System.out.println("\nMaven terminó con código: " + exitCode);
        
        if (IDEnvio == null) {
            System.err.println("⚠️ No se encontró TRACKID en la respuesta del SII");
            return "Error: No se encontró TRACKID";
        }
        
        return IDEnvio;
    }
}
