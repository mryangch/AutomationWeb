node {
	environment {
		
     //PackageName = "Package_"+${currentBuild.startTimeInMillis}+".zip"
     
    // BinaryFolder=""
   }
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
	   //echo "TimeStamp: ${currentBuild.startTimeInMillis}"
	   def timeStamp = Calendar.getInstance().getTime().format('YYYYMMdd-hhmmss',TimeZone.getTimeZone('Asia/Singapore'))
	   echo 'time stamp:'+"${timeStamp}"
	   def packageName = 'Package_'+"${timeStamp}"+'.zip'
	    echo 'Package Name:'+"${packageName}"

		dir ('Archive') {
			zip zipFile: "${packageName}", archive: false, dir: '../AutomationWeb/bin/Release/netcoreapp2.1/publish'
		  archiveArtifacts artifacts: "${packageName}", fingerprint: true
		  bat 'del ' +"${packageName}"
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
