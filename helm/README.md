# Banking Helm Deployment

This folder contains Helm charts for the banking microservices and supporting platform components.

## Structure

- `banking-common`: shared template helpers used by service charts
- `banking-services/*`: service charts (`configserver`, `eurekaserver`, `account`, `card`, `loan`, `gatewayserver`, `message`)
- `environments/dev-env`: dev umbrella chart for all banking services
- `environments/qa-env`: qa umbrella chart for all banking services
- `environments/prod-env`: prod umbrella chart for all banking services
- `kafka`, `keycloak`, `grafana`, `grafana-loki`, `grafana-tempo`, `kube-prometheus`: infra/observability charts

## Prerequisites

- Kubernetes cluster access (`kubectl config current-context`)
- Helm v3+

## 1) Update Chart Dependencies

Run this from `helm/`:

```powershell
$charts = @(
  "banking-services/account",
  "banking-services/card",
  "banking-services/configserver",
  "banking-services/eurekaserver",
  "banking-services/gatewayserver",
  "banking-services/loan",
  "banking-services/message",
  "environments/dev-env",
  "environments/qa-env",
  "environments/prod-env"
)

foreach ($chart in $charts) {
  helm dependency update $chart --skip-refresh
}
```

## 2) Deploy Platform (Optional but Recommended)

```powershell
helm upgrade --install kafka ./kafka -n banking --create-namespace
helm upgrade --install keycloak ./keycloak -n banking
helm upgrade --install kube-prometheus ./kube-prometheus -n monitoring --create-namespace
helm upgrade --install grafana ./grafana -n monitoring
helm upgrade --install loki ./grafana-loki -n monitoring
helm upgrade --install tempo ./grafana-tempo -n monitoring
```

If you deploy dependencies in non-default namespaces, set matching URLs in the target environment `values.yaml`.

## 3) Deploy Banking Services

Choose one environment chart:

```powershell
helm upgrade --install banking-dev ./environments/dev-env -n banking --create-namespace
helm upgrade --install banking-qa ./environments/qa-env -n banking-qa --create-namespace
helm upgrade --install banking-prod ./environments/prod-env -n banking-prod --create-namespace
```

## 4) Verify

```powershell
kubectl get pods -n banking
kubectl get svc -n banking
helm list -A
```
