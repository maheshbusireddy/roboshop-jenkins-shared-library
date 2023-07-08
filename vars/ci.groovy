def call() {
 try {

 pipeline {
    agent {
        label 'workstation'
    }

    stages {
    
       stage('Compile/Build') {
         steps {
            script {
                common.compile()
            }
         }
       }

       stage('Unit tests') {
         steps {
            script {
                common.unittests()
            }
         }
       }

       stage('Quality Control') {
         //environment {
            //SONAR_USER = '$(aws ssm get-parameters --region us-east-1 --names sonarqube.user  --with-decryption --query Parameters[0].Value | sed \'s/"//g\')'
            //SONAR_PASSWORD = '$(aws ssm get-parameters --region us-east-1 --names sonarqube.password  --with-decryption --query Parameters[0].Value | sed \'s/"//g\')'
          }
          steps {
            withAWSParameterStore(credentialsId: '', naming: 'relative', path: '/service', recursive: true, regionName: 'eu-west-1') 
            {

              echo "${env.SONARQUBE_USER}"
            }
            sh "sonar-scanner -Dsonar.host.url=http://172.31.95.25:9000 -Dsonar.login=${SONAR_USER} -Dsonar.password=${SONAR_PASSWORD} -Dsonar.projectKey=cart"

         }
       }

       stage('Upload Code To The Centralized Repo') {
         steps {
            echo 'upload'
         }
       }
    }
}
 }catch(Exception e){
   common.email("Failed")
 }
}