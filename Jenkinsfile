pipeline {
    agent any

    tools {
        jdk "ide"
        maven "maven.jenkins"
    }
    parameters {
       booleanParam(defaultValue: true, description: 'run Test tests', name: 'test')
    }

    stages {
        stage('summaryTest tests') {
            when {
              expression { return params.test }
            }
            steps {
                sh "mvn test"
            }
        }

    }
    post {
       always {
          allure([
            reportBuildPolicy: 'ALWAYS',
            results: [[path: 'target/allure-results']]
          ])
       }
    }
}