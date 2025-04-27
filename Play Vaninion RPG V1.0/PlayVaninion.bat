@echo off
title Vaninion RPG
color 0A
echo =================================
echo       VANINION RPG LAUNCHER
echo =================================
echo.


java -version >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    color 0C
    echo ERROR: Java is not installed or not in your PATH.
    echo Please install Java and try again.
    echo.
    pause
    exit /b 1
)


if not exist "Vaninion.jar" (
    color 0C
    echo ERROR: Could not find Vaninion.jar
    echo Make sure the batch file is in the same directory as Vaninion.jar
    echo.
    pause
    exit /b 1
)

echo Starting game, please wait...
echo.


java -jar Vaninion.jar


set EXIT_CODE=%ERRORLEVEL%

if %EXIT_CODE% NEQ 0 (
    color 0C
    echo.
    echo The game exited with an error (code: %EXIT_CODE%).
    echo.
)

echo.
echo Press any key to exit...
pause > nul
exit /b %EXIT_CODE%