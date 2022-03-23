pipeline{
   agent any
   
  stages {
  stage('maven install') {
    steps {
      // One or more steps need to be included within the steps block.
      withMaven(globalMavenSettingsConfig: 'null', jdk: 'null', maven: 'null', mavenSettingsConfig: 'null') {
        // some block
        sh 'mvn clean install'
     }
    }
  }

}
  
}
