pipeline {
    agent {
        docker {
            image 'maven:3.8.6-openjdk-11'
        }
    }

    environment {
        SELENIUM_GRID_URL = "http://172.18.0.4:4444"
    }

    stages {
        // stage('Checkout') {
        //     steps {
        //         git branch: 'main', url: 'https://github.com/ton-repo/ton-projet.git'
        //     }
        // }

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

        stage('Publish Test Reports') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/surefire-reports/', fingerprint: true
        }
        failure {
            echo "❌ Les tests Selenium ont échoué."
        }
    }
}
