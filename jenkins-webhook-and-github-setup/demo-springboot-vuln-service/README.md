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
3. deploys the container locally
4. verifies the health endpoint
5. runs a lighter Trivy vulnerability analysis using cached data where possible
6. keeps the build successful even if the analysis finds vulnerabilities

The lighter analysis is tuned for local iteration:

- uses only Trivy vulnerability scanning
- ignores unfixed findings
- limits output to `HIGH` and `CRITICAL`
- avoids failing the local deployment job
