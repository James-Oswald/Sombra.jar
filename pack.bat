@echo off
cd %~dp0
javac -d ./tmp ./src/Sombra.java
cd tmp
jar cfe ../build/Sombra.jar Sombra *.class
cd %~dp0