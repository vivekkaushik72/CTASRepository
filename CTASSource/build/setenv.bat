:GENERIC
echo Setting enviornment for CTAS Source build
CD ..
SET PROJECT_DIR=%CD%
echo PROJECT_DIR=%PROJECT_DIR%
CD build
SET ANT_OPTS=-Xmx256m
SET ANT_HOME=%PROJECT_DIR%\build\lib\apache-ant-1.9.3
SET PATH=%ANT_HOME%\bin;%JAVA_HOME%\bin;%PATH%
:END
