@echo off

:: Setting the paths
set project_path=%userprofile%\Documents\0.BitBucket_Projects\JavaEE_Projects\SpringLegacyTutorials\SpringRest
set jar_path=%userprofile%\.m2\repository\com\h2database\h2\2.3.232
set properties_path=%project_path%\src\main\resources\db_files\h2_db_files\h2_properties
set databases_path=%project_path%\src\main\resources\db_files\h2_db_files\h2_databases

::mkdir "%databases_path%"

:: Starting the H2-Database
::java -cp %jar_path%\h2-2.3.232.jar org.h2.tools.Server -browser -web -webAllowOthers -ifNotExists
java -jar %jar_path%\h2-2.3.232.jar -properties "%properties_path%" -browser -web -webAllowOthers

pause
