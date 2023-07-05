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
         steps {
            echo 'quality control'
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