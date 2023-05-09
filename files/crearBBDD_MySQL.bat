@echo off
echo.
echo Nota: No tiene clave para entrar en la base de datos 
echo Presione Enter para continuar...
%set /p dummyVar=
cmd /k "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -uroot -p < "mysqlAUTO.sql"

