pipeline {
    agent any

    tools {

        maven "maven.jenkins"
    }
    parameters {
       booleanParams(defaultValue: true, description: 'run SummaryTest tests', name: 'SummaryTest')
       booleanParams(defaultValue: true, description: 'run MainTest tests', name: 'MainTest')
    }

    stages {
        stage('SummaryTest tests') {
            when {
              expression {return params.SummaryTest}
            }
            steps {
                sh "mvn -Dtest=tests.SummaryTest test verify"
            }
        }
        stage('SummaryTest tests') {
                    when {
                      expression {return params.MainTest}
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