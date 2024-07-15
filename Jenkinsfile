pipeline {
    agent any
//This command to check  
    environment {
        DOCKER_CREDENTIALS_ID = 'Amgad-Docker-Cred'
    }
    stages {           
      stage('Build Frontend') {
            steps {
                    script {
                        sh 'npm install'
                        sh 'npm run build'
                            }
                        }
                    }

        stage('Test Frontend') {
            steps {
                    script {
                        sh 'npm install'
                        sh 'npm test'
                    }
                }
            }
               

        stage('Build frontend docker Image') {
                    steps {
                            withCredentials([usernamePassword(credentialsId: DOCKER_CREDENTIALS_ID, usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                                script {
                                    def imageTag = params.DOCKER_IMAGE_TAG
                                    // Login to Docker Hub
                                    sh """
                                    echo \$DOCKER_PASSWORD | docker login -u \$DOCKER_USERNAME --password-stdin
                                    """
                                    // Build Docker Image
                                    sh "docker build -t amgadashraf/ffrontend:v6 ."
                                    // Push Docker Image
                                    sh "docker push amgadashraf/ffrontend:v6 ."
                                    // Logout from Docker Hub
                                    sh 'docker logout'
                    }
                }
                        }
                    }

        stage('Deploy') {
            steps {
                // Add your deployment steps here
                echo 'Deploying Frontend and Backend...'
            }
        }
    }

    post {
        always {
            cleanWs() // Clean the workspace after the build
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}

