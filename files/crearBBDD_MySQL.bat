@REM @echo off
@REM echo.
@REM echo Nota: No tiene clave para entrar en la base de datos 
@REM echo Presione Enter para continuar...
@REM %set /p dummyVar=
@REM cmd /k "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -uroot -p < "mysqlAUTO.sql"

@echo off
echo.
echo Nota: Si deseas correr la aplicacion, recuerda colocar tu contrasena en conexion.Conexion.java
set /p mysql_password=Ingresa password de MySQL: 
@REM %set /p dummyVar=
cmd /k "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -uroot -p%mysql_password% < "mysqlAUTO.sql"

