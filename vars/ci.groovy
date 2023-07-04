def call() {
  pipeline {

    agent {
        label 'workstation'
    }

    stages {

      stage('Complie/Build') {
        steps {
            echo 'compile'
        }
    }
    stage('unit test') {
        steps {
            echo 'compile'
        }
    }
    stage('qauality control') {
        steps { 
            echo 'Qauality control'
        }
    }
    satge('upload Code the Centralized Repo') {
        steps {
            echo 'upload'
        }
    }
}

  }