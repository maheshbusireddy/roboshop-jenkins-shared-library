def compile() {
  if (app_lang == "nodejs") {
     sh 'npm install'
  }   
   if (app_lang == "maven") {
     sh 'mvn package'
  }   
} 

def unittests() {

   if (app_lang == "nodejs") {
     sh 'npm test'
     
  }
   if (app_lang == "maven") {
     sh 'mvn test'
  } 

   if (app_lang == "python") {
     sh 'python3 -m unittest'
  }
}

def email(email_note) {
  mail bcc: '', body: 'TEST', cc: '', from: 'maheswarbusireddy@gmail.com', replyTo: '', subject: 'TEST FROM JENKINS', to: 'maheswarbusireddy@gmail.com'
}