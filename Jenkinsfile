pipeline {
	agent {
		any
    }
  }

  options {
		timestamps()
    disableConcurrentBuilds()
  }

  parameters {

		choice(name: 'ENV', choices: ['uat', 'sit', 'prod'], description: 'Target environment to run against')
    string(name: 'TAGS', defaultValue: '@functionalTest', description: 'Cucumber tag expression (e.g. @functionalTest or @smoke)')
    choice(name: 'BROWSER', choices: ['Chrome', 'Firefox'], description: 'Browser to use for UI tests')
  }

  environment {
		MAVEN_OPTS = '-Dmaven.test.failure.ignore=false'
  }

  stages {
		stage('Checkout') {
			steps {
				checkout scm
      }
    }

    stage('Verify Tooling') {
			steps {
				sh 'java -version'
        sh 'mvn -version'
      }
    }

    stage('Run Tests') {
			steps {
				sh '''
          mvn -B -U clean test \
            -Denv=${ENV} \
            -Dbrowser=${BROWSER} \
            -Dcucumber.filter.tags="${TAGS}"
        '''
      }
    }
  }

  post {
		always {
			junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml, target/failsafe-reports/*.xml'
      archiveArtifacts allowEmptyArchive: true, artifacts: 'target/**/*.json, target/**/*.html, target/**/*.xml, testReport/**, **/extent*.html'
    }
    success {
			echo 'Tests completed successfully.'
    }
    failure {
			echo 'Tests failed. Check reports and logs for details.'
    }
  }
