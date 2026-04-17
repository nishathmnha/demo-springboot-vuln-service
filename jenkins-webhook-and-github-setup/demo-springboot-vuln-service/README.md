# Demo Spring Boot Vulnerable Service

This folder contains a small Spring Boot microservice meant for Jenkins and container vulnerability scan demos.

## Why this exists

The app is intentionally configured with a couple of outdated dependencies in `pom.xml` so container and dependency scanners have something meaningful to flag during CI/CD testing.

## Endpoints

- `/`
- `/health`
- `/vulnerability-demo`

## Local Docker build

```powershell
docker build -t local/demo-springboot-vuln-service:manual .
docker rm -f demo-springboot-vuln-service 2>$null
docker run -d --name demo-springboot-vuln-service -p 8082:8080 local/demo-springboot-vuln-service:manual
curl http://localhost:8082/health
```

## Jenkins pipeline behavior

The included `Jenkinsfile`:

1. checks Docker availability
2. builds the Spring Boot container image
3. scans the image with Trivy
4. fails the pipeline if Trivy finds HIGH or CRITICAL issues
5. deploys the container only if the scan passes

If you want a deployment-only flow, remove or temporarily comment out the `Scan Image` stage.
