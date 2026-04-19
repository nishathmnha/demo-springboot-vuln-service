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

1. uses Spring Boot source already checked out in the Jenkins workspace when available
2. can optionally try a remote GitHub clone when `PREFER_REMOTE_CLONE=true`
3. falls back to the locally mounted Spring Boot source for the local Jenkins demo
4. checks Docker availability
5. builds the Spring Boot container image
6. deploys the container locally
7. verifies the deployment through a configured health check URL when one is reachable
8. runs a straightforward Trivy container scan against the built image
9. fails the job when `HIGH` or `CRITICAL` vulnerabilities are found

The lightweight scan is tuned for local iteration:

- uses only Trivy vulnerability scanning
- ignores unfixed findings
- limits output to `HIGH` and `CRITICAL`
- reuses cached DB data automatically after the first run
- avoids waiting on a remote clone in offline/local Docker setups
- skips webhook delivery unless `WEBHOOK_URL` is explicitly set
- keeps the deployment/build flow simple while still enforcing a vulnerability gate
