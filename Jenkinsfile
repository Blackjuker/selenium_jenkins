pipeline {
    agent {
        docker {
            image 'maven:3.8.6-openjdk-11'
        }
    }

     parameters {
        choice(
            name: 'ENVIRONMENT',
            choices: [
                'test_env',
                'prod'
            ],
            description: 'Choisir l’environnement Selenium Grid'
        )
    }

    environment {
        SELENIUM_GRID_URL = "${params.ENVIRONMENT == 'test_env' ? 'http://172.18.0.3:4444' : 'http://192.168.1.95:4444'}"
    }

    stages {
        // stage('Checkout') {
        //     steps {
        //         git branch: 'main', url: 'https://github.com/ton-repo/ton-projet.git'
        //     }
        // }

        stage('Afficher URL') {
            steps {
                script {
                    echo "Environnement sélectionné : ${params.ENVIRONMENT}"
                    echo "URL Selenium Grid utilisée : ${SELENIUM_GRID_URL}"
                }
            }
        }

        stage('Install Dependencies') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                sh 'mvn test -Dselenium.grid.url=$SELENIUM_GRID_URL'
            }
        }
        stage('Generate Allure Report') {
             steps {
                 sh 'mvn allure:report'
             }
         }
 
         stage('Publish Allure Report') {
             steps {
                 allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
             }
         }
 
        //  stage('Generate Allure Report') {
        //     steps {
        //         sh 'mvn allure:report'
        //     }
        // }

        // stage('Publish Allure Report') {
        //     steps {
        //         allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
        //     }
        // }

        // stage('Publish Test Reports') {
        //     steps {
        //         junit '**/target/surefire-reports/*.xml'
        //     }
        // }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/surefire-reports/', fingerprint: true
        }
        failure {
            echo "❌ Les tests Selenium ont échoué."
        }
    }
    // post {
    //     always {
    //         archiveArtifacts artifacts: '**/target/allure-results/', fingerprint: true
    //     }
    //     failure {
    //         echo "❌ Les tests Selenium ont échoué."
    //     }
    // }
    
}
