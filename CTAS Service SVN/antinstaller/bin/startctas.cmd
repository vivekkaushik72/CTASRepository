@echo off
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::
:: CTAS Service for COX Communications
::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

:::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Sets the "java.home" System property at runtime
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::
call ctasenv.bat

set JAVA_VERSION_REQUIRED=1.7

::if "%JAVA_HOME%" == "" goto nojavahome

REM Ensure correct version of Java is being used
"%JAVA_HOME%\bin\java" -version 2>&1  | findstr /C:"%JAVA_VERSION_REQUIRED%"
If %ERRORLEVEL% EQU 0 goto :endversion
If %ERRORLEVEL% NEQ 0 goto wrongjavaversion

set MYDIR=%~dp0
cd %MYDIR%
set MYDIR=%CD%

set LIB_HOME=%MYDIR%\lib

::if "%CTAS_CONFIG%" == "" (
::  set CTAS_CONFIG=classpath://config/ctas/CTASConfig.xml
::)

:endversion

set JAVA_MEM_OPTIONS=-Xms84m -Xmx128m
set JAVA_OPTIONS=%JAVA_MEM_OPTIONS%

rem Run the java main class.
set COMMAND=java %JAVA_OPTIONS% com.cox.ctas.service.loader.CTASLoader
echo %COMMAND%
%COMMAND%

:nojavahome
echo You must set JAVA_HOME to a JRE or JDK
goto EOF

:wrongjavaversion
echo
echo Required Java %JAVA_VERSION_REQUIRED% not found under %JAVA_HOME%
goto EOF

:EOF