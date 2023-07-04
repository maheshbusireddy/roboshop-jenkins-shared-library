def call() {

 pipeline {
    agent{
        lable 'workstation'
    }

    stages {
    
       stage('Compile/Build') {
         steps {
            echo 'compile'
         }
       }

       stage('Unit tests') {
         steps {
            echo 'unit tests'
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
}