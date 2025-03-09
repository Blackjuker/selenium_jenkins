pipeline {
    agent {
        docker {
            image 'selenium/standalone-chrome:latest'
        }
    }

    environment {
        BROWSER = "chrome"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ton-repo/ton-projet.git'
            }
        }

        stage('Install Dependencies') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                sh 'mvn test'
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
            echo "Les tests Selenium ont échoué."
        }
    }
}
