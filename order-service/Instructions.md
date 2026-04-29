## Pod Lifecycle

```sh
kubectl apply -f pod.yaml
```

## Pod Inspection

```sh
kubectl get pods
```

```sh
kubectl describe pod order-service-bare
```

## Pod Access

```sh
kubectl port-forward pod/order-service-bare 8080:8080
```

## Pod Cleanup

```sh
kubectl delete pod order-service-bare
```

## Verify Cleanup

```sh
kubectl get pods
```

## Deployment Creation

```sh
kubectl apply -f deployment.yaml
```

## Deployment Status

```sh
kubectl get deployments
```

```sh
kubectl get replicasets
```

```sh
kubectl get pods
```

```sh
kubectl describe deployment order-service
```

## Running Pod Access

```sh
kubectl logs <pod-name> -f
```

```sh
kubectl exec -it <pod-name> -- /bin/sh
```

```sh
kubectl get pods -o wide
```

```sh
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{"orderId":"order-123","quantity":2}'
```

## Running Pod Deletion

```sh
kubectl delete pod <pod-name>
```
