pipeline {
    agent any

    tools {
        maven "maven.jenkins"
    }
    parameters {
       booleanParam(defaultValue: true, description: 'run SummaryTest tests', name: 'Summary')
       booleanParam(defaultValue: false, description: 'run MainTest tests', name: 'Main')
    }

    stages {
        stage('SummaryTest tests') {
            when {
              expression { return params.Summary }
            }
            steps {
                sh "mvn -Dtest=tests.SummaryTest test verify"
            }
        }
        stage('SummaryTest tests') {
                    when {
                      expression { return params.Main }
                    }
                    steps {
                        sh "mvn -Dtest=tests.MainTest test verify"
                    }
           }
    }
    post {
       always {
          allure([
            reportBuildPolicy: 'ALWAYS',
            result: [[path: 'target/allure-results']]
          ])
       }
    }
}