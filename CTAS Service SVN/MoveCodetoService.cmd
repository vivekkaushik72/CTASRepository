@echo off
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