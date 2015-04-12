::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::
:: CTAS Service for COX Communications
::
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

:::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Sets the "java.home" System property at runtime
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::
set JAVA_VERSION_REQUIRED=1.7

if "%JAVA_HOME%" == "" goto nojavahome

REM Ensure correct version of Java is being used
"%JAVA_HOME%\bin\java" -version 2>&1  | findstr /C:"%JAVA_VERSION_REQUIRED%"
If %ERRORLEVEL% EQU 0 goto :endversion
If %ERRORLEVEL% NEQ 0 goto wrongjavaversion

set MYDIR=%~dp0
cd %MYDIR%
set MYDIR=%CD%

set LIB_HOME=%MYDIR%\lib

:::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Sets Class path
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::

set CLASSPATH=%CLASSPATH%;%LIB_HOME%\concurrent.jar
set CLASSPATH=%CLASSPATH%;%LIB_HOME%\dom.jar
set CLASSPATH=%CLASSPATH%;%LIB_HOME%\jbcl3.0.jar
set CLASSPATH=%CLASSPATH%;%LIB_HOME%\jcl.jar
set CLASSPATH=%CLASSPATH%;%LIB_HOME%\jgl3.1.0.jar
set CLASSPATH=%CLASSPATH%;%LIB_HOME%\jpos113-controls.jar
set CLASSPATH=%CLASSPATH%;%LIB_HOME%\jpos113.jar
set CLASSPATH=%CLASSPATH%;%LIB_HOME%\log4j-1.2.17.jar
set CLASSPATH=%CLASSPATH%;%LIB_HOME%\RXTXcomm.jar
set CLASSPATH=%CLASSPATH%;%LIB_HOME%\sax.jar
set CLASSPATH=%CLASSPATH%;%LIB_HOME%\vfjpos_dist.jar
set CLASSPATH=%CLASSPATH%;%LIB_HOME%\xercesImpl.jar

if "%CTAS_CONFIG%" == "" (
  set CTAS_CONFIG=classpath://config/ctas/CTASConfig.xml
)


rem Run the java main class.
set COMMAND=java com.cox.ctas.service.loader.CTASLoader %CTAS_CONFIG%
echo %COMMAND%
%COMMAND%

:endversion
set PATH=%JAVA_HOME%\bin;%PATH%

:nojavahome
echo You must set JAVA_HOME to a JRE or JDK
goto EOF

:wrongjavaversion
echo
echo Required Java %JAVA_VERSION_REQUIRED% not found under %JAVA_HOME%
goto EOF

:EOF