Graph database insight:
http://localhost:7474/browser/
docker run --name neo4j -p 7474:7474 -p 7687:7687 -e NEO4J_AUTH=neo4j/password neo4j:5.15


Widecolumn:
docker run --name cassandra -p 9042:9042 -e CASSANDRA_CLUSTER_NAME=TestCluster -e CASSANDRA_DC=datacenter1 -e CASSANDRA_RACK=rack1 -d cassandra:4.1
