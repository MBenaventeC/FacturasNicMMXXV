#!/usr/bin/env pwsh
# Script para ejecutar EnviaDocumento con configuración JVM

$env:MAVEN_OPTS = @"
--add-opens=java.xml/com.sun.org.apache.xerces.internal.jaxp=ALL-UNNAMED
--add-opens=java.xml/com.sun.org.apache.xerces.internal.parsers=ALL-UNNAMED
--add-opens=java.xml/com.sun.org.apache.xerces.internal.dom=ALL-UNNAMED
--add-exports=java.xml/com.sun.org.apache.xerces.internal.jaxp=ALL-UNNAMED
--add-exports=java.xml/com.sun.org.apache.xerces.internal.dom=ALL-UNNAMED
--add-exports=java.xml/com.sun.org.apache.xerces.internal.parsers=ALL-UNNAMED
"@ -replace "`r`n", " " -replace "`n", " "

Write-Host "🚀 Ejecutando EnviaDocumento con Java $($env:JAVA_HOME)" -ForegroundColor Cyan
Write-Host "📝 JVM Args: $env:MAVEN_OPTS" -ForegroundColor Gray

# Ejecutar Maven
mvn clean compile exec:java

# Verificar resultado
if ($LASTEXITCODE -eq 0) {
    Write-Host "`n✅ Ejecución exitosa!" -ForegroundColor Green
} else {
    Write-Host "`n❌ Ejecución falló con código: $LASTEXITCODE" -ForegroundColor Red
    exit $LASTEXITCODE
}