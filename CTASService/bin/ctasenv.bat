
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Set the JAVA_HOME below
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::
SET JAVA_HOME=C:\CTAS\Java\jdk1.7.0_25

:::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Set the runtime path
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::
SET PATH=.;%JAVA_HOME%\bin;%PATH%;

:::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Sets path for finding
:: properties and config files.
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::SET CLASSPATH=..\config\log4j\log4j.xml
SET CLASSPATH=%CLASSPATH%;.;..;..\config

:::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Sets Class path
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::

call addtoclasspath.bat ..\lib
call addtoclasspath.bat ..\lib\jetty

:: pass the classpath to a log file for records
echo %CLASSPATH% > ..\logs\classpath.log