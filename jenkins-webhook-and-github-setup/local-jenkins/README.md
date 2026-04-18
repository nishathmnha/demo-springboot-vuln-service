# Local Jenkins Setup

This folder starts a Jenkins container that can:

- access Docker on your machine
- read the mounted demo app from `/workspace/demo-python-service`
- run the Jenkins pipeline in `demo-python-service/Jenkinsfile`

## Start Jenkins

```powershell
docker compose up --build -d
```

## Get the admin password

```powershell
docker exec local-jenkins cat /var/jenkins_home/secrets/initialAdminPassword
```

Then open `http://localhost:8080`.
