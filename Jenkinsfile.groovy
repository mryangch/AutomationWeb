node {
    def timeStamp = Calendar.getInstance().getTime().format('YYYYMMddHHmmss', TimeZone.getTimeZone('Singapore'))
    def packageName = 'Package_' + "${timeStamp}" + '.zip'
    def sourceDir='../AutomationWeb/bin/Release/netcoreapp2.1/publish'

    stage('Checkout') {
        //checkout scm
        echo 'Downloading codes...'
    }
    stage('Build') {
        echo 'Build project...'
        //bat "\"${tool 'msbuild15.0'}\" AutomationWeb.sln /t:Restore"
        //bat "\"${tool 'msbuild15.0'}\" AutomationWeb.sln /p:Configuration=Release /p:Platform=\"Any CPU\""
    }
    stage('ZIP Artifact') {
        //echo 'Workspace:'+"${env.WORKSPACE}"
        echo 'Package Name:' + "${packageName}"
        // dir('Archive') {
        //     zip zipFile: "${packageName}", archive: false, dir: "${sourceDir}"
        //     archiveArtifacts artifacts: "${packageName}", fingerprint: true
        //     bat 'del ' + "${packageName}"
        // }
    }
    stage('Deploy') {
        echo 'Downloading package...'
        //wget http://localhost:8080/job/AutomationWeb/28/artifact/Package_20181218164328.zip
        echo 'Deploy package...'

    }
    emailext attachLog: true, body: '''Hello, 

Please be informed and watch out.''', recipientProviders: [developers()], subject: 'A new Jinkens Build is triggered ', to: 'yang.chun.hai@avanade.com'
}
