@echo off
echo.
echo ****************** Starting the process ***************** 
echo.
echo ****************** Java Copy Started ********************
echo.
echo   Copying JDK 1.7 to C:\CTAS\Java\jdk1.7.0_25
if exist "C:\CTAS\Java\jdk1.7.0_25" goto install
XCOPY Java\jdk1.7.0_25 C:\CTAS\Java\jdk1.7.0_25 /i /E /F /R /y
echo   JDK Copied to C:\CTAS\Java\jdk1.7.0_25

:install
set  JAVA_HOME=C:\CTAS\Java\jdk1.7.0_25
echo   JAVA_HOME set to %JAVA_HOME%
echo.
echo *************** Source Build Process Started ************
echo.
echo   Setting enviornment for Ant by calling setenv.bat
call setenv.bat > log\ctasinstall.log
echo   Compiling source and moving jars to installer
call "%ANT_HOME%\bin\ant.bat" installer >> log\ctasinstall.log
echo   Source build completed. All the compiled jars are copied to Installer
goto EOF
:EOF