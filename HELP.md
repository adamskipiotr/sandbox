Graph database insight:
http://localhost:7474/browser/
docker run --name neo4j -p 7474:7474 -p 7687:7687 -e NEO4J_AUTH=neo4j/password neo4j:5.15


Widecolumn:
docker run --name cassandra -p 9042:9042 -e CASSANDRA_CLUSTER_NAME=TestCluster -e CASSANDRA_DC=datacenter1 -e CASSANDRA_RACK=rack1 -d cassandra:4.1

Document:
docker run -d --name mongo -p 27017:27017 -e MONGO_INITDB_DATABASE=demo mongo:latest

Key-value:
docker run -d --name redis -p 6379:6379 redis
 