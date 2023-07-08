def call() {
 try {
    node('workstatio') {
       stage('Checkout') {
        cleanWs()
        git branch: 'main', url: 'https://github.com/maheshbusireddy/cart.git'
       }

       stage('Compile/Build'){
        common.compile()
       }
       stage('Unit Tests'){
        common.unittests()
       }
        stage('Quality Control') {
         environment {
            SONAR_USER = '$(aws ssm get-parameters --region us-east-1 --names sonarqube.user  --with-decryption --query Parameters[0].Value | sed \'s/"//g\')'
            //SONAR_PASS = '$(aws ssm get-parameters --region us-east-1 --names sonarqube.pass  --with-decryption --query Parameters[0].Value | sed \'s/"//g\')'
          }
          steps {
            script {
              SONAR_PASS = '$(aws ssm get-parameters --region us-east-1 --names sonarqube.pass  --with-decryption --query Parameters[0].Value | sed \'s/"//g\')'
              wrap([$class: 'MaskPasswordsBuildWrapper', varPasswordPairs: [[password: "${SONAR_PASS}", var: 'SECRET']]]) {
          sh "sonar-scanner -Dsonar.host.url=http://172.31.95.25:9000 -Dsonar.login=${SONAR_USER} -Dsonar.password=${SONAR_PASS} -Dsonar.projectKey=cart"
            }
    }
       stage('Upload Code To The Centralized Repo') {
         steps {
            echo 'upload'
         }
       }
    }
}
} catch(Exception e) {
    common.email("Failed")
  }
}

 
 

