def call() {

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
                common.unittest()
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
}