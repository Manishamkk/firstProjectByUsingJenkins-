
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


stage('Docker Build') {
    steps {
        echo 'Building Docker Image'
        sh 'docker build -t firstproject:1.0 .'
    }
}

stage('Docker Run') {
    steps {
        echo 'Running Spring Boot Container'
        sh 'docker rm -f firstproject-container || true'
        sh 'docker run -d --name firstproject-container -p 1111:1111 firstproject:1.0'
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
