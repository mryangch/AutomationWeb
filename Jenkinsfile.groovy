node {
    stage('Checkout') {
        checkout scm
        echo 'Downloading codes...'
    }
    stage('Build') {
        echo 'Build project...'
        
	bat "\"${tool 'msbuild15.0'}\" AutomationWeb.sln /t:Restore"
	bat "\"${tool 'msbuild15.0'}\" AutomationWeb.sln /p:Configuration=Release /p:Platform=\"Any CPU\""
    }
    stage('Archive') {
        echo 'Run test cases...'
        //archiveArtifacts 'AzureWebOAuth2/bin/Release'
    }
    stage('Deploy') {
        echo 'Deploy package...'
    }
}