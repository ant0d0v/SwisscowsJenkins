pipeline {
    agent any

    tools {
        jdk "ide"
        maven "maven.jenkins"
    }
    parameters {
       booleanParam(defaultValue: true, description: 'run SummaryTest tests', name: 'Summary')
       booleanParam(defaultValue: false, description: 'run MainTest tests', name: 'Main')
    }

    stages {
        stage('summaryTest tests') {
            when {
              expression { return params.Summary }
            }
            steps {
                sh "mvn -Dtest=SummaryTest test"
            }
        }
        stage('mainTest tests') {
                    when {
                      expression { return params.Main }
                    }
                    steps {
                        sh "mvn -Dtest=MainTest test"
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