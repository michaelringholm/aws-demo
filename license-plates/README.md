# To deploy to AWS
1. mvn clean package
2. Go to AWS EB console
3. Choose upload and deploy
4. (Only first time) Add the environment setting SERVER_PORT to 5000 in <my app>->Configuration->Software Configuration->Environment Properties

# Deploy location
http://license-plates.eu-central-1.elasticbeanstalk.com/