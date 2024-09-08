pipeline
{
    agent any
    
    tools{
		maven 'Maven 3.9.9'
	}
    
    stages{
        stage("Regression Automation"){
            steps{
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE')
                {
					git 'https://github.com/abhipriya93/MakeMyTripTest'
					sh "mvn clean install"
				}
            }
        }
        stage("Publish Allure Report"){
            steps{
                script{
					allure([
						includeProperties: false,
						jdk: '',
						properties: [],
						reportBuildPolicy: 'ALWAYS',
						results: [[path: '/allure-results']]
					]
						
					)
				}
            }
        }
        stage("Publish Extent Report"){
            steps{
                publishHTML([allowMissing: false,
					 keepAll: false,
					 reportDir: 'build',
					 reportFiles: 'TestExecutionReport.html',
					 reportTitles: '',
					 reportName: 'Extent Report',
					 alwaysLinkToLastBuild: false])
            }
        }
        stage("Production Ready"){
            steps{
                echo("Build is ready for production")
            }
        }
    }
    
}


