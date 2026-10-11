
pipeline {
    agent any

    environment {
        APP_NAME = 'firstproject'
        IMAGE_TAG = '1.0'
        CONTAINER_NAME = 'firstproject-container'
        DOCKER_PORT = '1111'
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building Spring Boot application...'
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                sh './mvnw test'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Building Docker image...'
                sh '''
                    docker build \
                      -t ${APP_NAME}:${IMAGE_TAG} \
                      -t ${APP_NAME}:latest \
                      .
                '''
            }
        }

        stage('Docker Run') {
            steps {
                echo 'Removing previous container if present...'
                sh '''
                    docker rm -f ${CONTAINER_NAME} || true
                '''

                echo 'Starting Spring Boot container...'
                sh '''
                    docker run -d \
                      --name ${CONTAINER_NAME} \
                      -p ${DOCKER_PORT}:${DOCKER_PORT} \
                      ${APP_NAME}:${IMAGE_TAG}
                '''

                sh 'docker ps'
            }
        }
    }

    post {
        success {
            echo '================================'
            echo 'PIPELINE SUCCESS'
            echo "Docker Image: ${env.APP_NAME}:${env.IMAGE_TAG}"
            echo 'Application Port: 1111'
            echo '================================'
        }

        failure {
            echo 'PIPELINE FAILED'
            echo 'Please check the Jenkins Console Output.'
        }
    }
}
