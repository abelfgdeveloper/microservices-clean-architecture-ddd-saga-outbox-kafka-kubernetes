# microservices-clean-architecture-ddd-saga-outbox-kafka-kubernetes
Code of Course https://www.udemy.com/course/microservices-clean-architecture-ddd-saga-outbox-kafka-kubernetes/


Arrancar Kafka

docker-compose -f common.yml -f .\zookeeper.yml up
docker-compose -f common.yml -f .\kafka_cluster.yml up
docker-compose -f common.yml -f .\init_kafka.yml up



http://localhost:9000/



Crear Cluster:

food-ordering-system-cluster
zookeeper:2181