package com.NicFacturas.NicFacturasDemo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MavenLauncher {
    public static int runRunEnvio(String projectPomPath) throws IOException, InterruptedException {    
        // Detectar automáticamente ruta a mvn
        String mavenHome = System.getenv("MAVEN_HOME");
        String mvnCommand = mavenHome + "\\bin\\mvn.cmd";

        System.out.println("Usando Maven: " + mvnCommand);
        System.out.println("Working directory (user.dir): " + System.getProperty("user.dir"));
        
        Process p = Runtime.getRuntime().exec(new String[]{
            mvnCommand, "exec:exec@run-envio"
        });

        try (BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String line;
            while ((line = r.readLine()) != null) {
                System.out.println(line);
            }
        }

        System.out.println("Post envio :/");

        return p.waitFor();
    }
}
