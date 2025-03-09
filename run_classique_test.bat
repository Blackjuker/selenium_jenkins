@echo off

echo Running Maven tests...
mvn test surefire-report:report
pause
