# Installation

<strong>*Kafka*</strong>:

`docker compose -f docker-compose-core.yml -p core up -d`

`docker compose -f docker-compose-core.yml -p core down`

# Using Kafka

`docker exec -it kafka bash`

`kafka-topics.sh` (to see documentation)

`kafka-topics.sh --bootstrap-server localhost:9092 --create --topic t-hello --partitions 1 --replication-factor 1`

`kafka-topics.sh --bootstrap-server localhost:9092 --list`

Multi partitions

`kafka-topics.sh --bootstrap-server localhost:9092 --create --partitions 3 --replication-factor 1 --topic t-multi-partitions`

`kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic t-multi-partitions`

`kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic t-multi-partitions --offset earliest --partition 0`

`kafka-topics.sh --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-employee`

`kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group cg-dashboard --describe`

## Rebalancing

`kafka-topics.sh --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-rebalance`

`kafka-topics.sh --bootstrap-server localhost:9092 --alter --topic t-rebalance --partitions 2`

`kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic t-rebalance`

## Idempotency

`kafka-topics.sh --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-purchase-request`


``

``

``
