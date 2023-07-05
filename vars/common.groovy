def compile() {
  if (app_lang == "nodejs") {
     sh 'npm install'
     sh 'env'
  }   
   if (app_lang == "maven") {
     sh 'mvn package' 
  }   
} 

def unittests() {

   if (app_lang == "nodejs") {
     sh 'npm test || true'
   }
     
  
   if (app_lang == "maven") {
     sh 'mvn test'
  } 

   if (app_lang == "python") {
     sh 'python3 -m unittest'
  }
}

def email(email_note) {
  mail bcc: '', body: "Job Failed - ${JOB_BASE_NAME}\nJenkins Url - ${JOB_URL}", cc: '', from: 'maheswarbusireddy@gmail.com', replyTo: '', subject: "Jenkins Job Failed - ${JOB_BASE_NAME}", to: 'maheswarbusireddy@gmail.com'
}