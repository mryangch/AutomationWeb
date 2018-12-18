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
   stage('ZIP Artifact') {     
                //echo 'Workspace:'+"${env.WORKSPACE}"
	   	//bat 'dir'
		dir ('Archive') {
	          zip zipFile: 'Package.zip', archive: false, dir: '../AutomationWeb/bin/Release/netcoreapp2.1/publish'
		  archiveArtifacts artifacts: 'Package.zip', fingerprint: true
		}
		//bat 'dir'
                
                //bat 'echo test > archive/test.txt'
                //zip zipFile: 'Archive/package.zip', archive: false, dir: 'AutomationWeb/bin/Release/netcoreapp2.1/publish'
                //archiveArtifacts artifacts: 'test.zip', fingerprint: true
           
        }
    stage('Deploy') {
        echo 'Deploy package...'
    }
}
