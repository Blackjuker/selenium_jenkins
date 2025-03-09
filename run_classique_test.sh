#!/bin/bash

echo "Running Maven tests..."
mvn test surefire-report:report

# Pause équivalente (appuyer sur une touche pour continuer)
read -p "Press any key to continue..."
