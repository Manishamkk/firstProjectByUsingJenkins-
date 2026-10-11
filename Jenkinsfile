
pipeline {
    agent any

    environment {
        APP_NAME = 'firstprojectbyusingjenkins'
        IMAGE_TAG = '1.0'
        CONTAINER_NAME = 'firstprojectbyusingjenkins-container'

        // Unique host port to avoid conflicts
        HOST_PORT = '18082'
        CONTAINER_PORT = '1111'
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
                echo 'Running test cases...'

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

                echo 'Docker image created successfully.'

                sh 'docker images'
            }
        }

        stage('Docker Run') {
            steps {
                echo 'Removing previous container if present...'

                sh '''
                    docker rm -f ${CONTAINER_NAME} || true
                '''

                echo 'Checking whether host port is available...'

                sh '''
                    if docker ps --format '{{.Ports}}' | grep -q '0.0.0.0:${HOST_PORT}->'; then
                        echo "ERROR: Host port ${HOST_PORT} is already in use by a Docker container."
                        exit 1
                    fi
                '''

                echo 'Starting Spring Boot container...'

                sh '''
                    docker run -d \
                      --name ${CONTAINER_NAME} \
                      -p ${HOST_PORT}:${CONTAINER_PORT} \
                      ${APP_NAME}:${IMAGE_TAG}
                '''

                echo 'Checking running containers...'

                sh 'docker ps'
            }
        }
    }

    post {
        success {
            echo '===================================='
            echo 'PIPELINE SUCCESS'
            echo "Docker Image: ${env.APP_NAME}:${env.IMAGE_TAG}"
            echo "Container: ${env.CONTAINER_NAME}"
            echo "Application URL: http://localhost:${env.HOST_PORT}"
            echo '===================================='
        }

        failure {
            echo '===================================='
            echo 'PIPELINE FAILED'
            echo 'Please check Jenkins Console Output.'
            echo '===================================='
        }
    }
}
