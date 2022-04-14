pipeline{
  
  agent any
  
  stages {
  stage('maven install') {
    steps {
      // One or more steps need to be included within the steps block.
      
      withMaven(maven: 'Maven3') {
              bat 'mvn clean install'
     }
      
      stage("Compile and Clean"){
        
        steps{
            bat "mvn clean compile"
        }
      }
      
      stage("Test"){
        steps{
          bat "mvn test site"
        }
        
        post{
          always{
             junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml
          }
        }
      }
      
      stage('Deploy'){
        steps{
          sh 'mvn package'
        }
      }
      
      
    }
  }
    
    

}

  
  
}
