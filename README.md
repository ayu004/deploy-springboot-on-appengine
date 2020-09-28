## Step-by-step guide

You have already got infrastructure created on google cloud project using terraform using our [repo](https://github.com/SKAUL05/gcp-terraform-infra). If not already,please visit that repo first, follow  step by step guide in Readme.md file and then we are good to start here

1. Fork the repository to your namespace.

2. Add the credentials of your google cloud project in Github Secrets(as was done in infrastructure [repository](https://github.com/SKAUL05/gcp-terraform-infra))
  - In the repo, Visit **Settings > Secrets**
  - Click on **New Secret** and add Name as **PROJECT_ID** and value as Project ID of your GCP Project and click on Add Secret
  ![New Secret](/assets/secret-project.JPG)

  - Again click on New Secret and add Name as **GOOGLE_APPLICATION_CREDENTIALS** and value as *contents of json file that you downloaded and click on Add Secret


#### Now you are good to go, whenever you push anything to your repo's master branch it will get deployed to Google App Engine of your GCP Project using Github Actions.
 - Whenever you push to master branch you can see your Github Action Running like in below image
 ![Github Action Running](/assets/gae-deploy-github-action.JPG)
 - When this Action gets completed you can headover to App Engine URL : **https://< GCP_PROJECT_ID >.uc.r.appspot.com/swagger-ui.html** where GCP_PROJECT_ID is Project ID of your Google Cloud Platform project, to check the deployed application.
 ![Github Action Complete](/assets/gae-deploy-complete.JPG)

3. Checking Logs
  - Go to App Engine > Services and click on Tools in your default deployed service and select Logs which will open the logs of application.
  - You can click on Play button on top to start streaming live logs.

![Logs](/assets/logs.PNG) 

- It might take some time to service to get up and running or logs to load initially

## Happy Coding :smile:  



