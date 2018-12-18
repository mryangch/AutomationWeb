node {
    stage('Checkout') {
        checkout scm
        echo 'Downloading codes...'
    }
    stage('Build') {
        echo 'Build project...'
        
	//bat "\"${tool 'msbuild15.0'}\" AutomationWeb.sln /t:Restore"
	//bat "\"${tool 'msbuild15.0'}\" AutomationWeb.sln /p:Configuration=Release /p:Platform=\"Any CPU\""
	
    }
   stage ('zip artifact') {
           
		jsonPath = "${env.WORKSPACE}"
                echo jsonPath
                sh 'mkdir archive'
                sh 'echo test > archive/test.txt'
                //zip zipFile: 'test.zip', archive: false, dir: 'archive'
                //archiveArtifacts artifacts: 'test.zip', fingerprint: true
           
        }
    stage('Deploy') {
        echo 'Deploy package...'
    }
}
