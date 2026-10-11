
pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Starting Jenkins Build'
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo 'Testing the application'
                sh './mvnw test'
            }
        }
    }

    post {
        success {
            echo 'Build Successful!'
        }

        failure {
            echo 'Build Failed!'
        }
    }
}
