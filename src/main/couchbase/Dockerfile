# Dockerfile
FROM couchbase:latest

# Copy the configuration script
COPY src/main/couchbase/configure.sh /opt/couchbase/configure.sh

# Run the configuration script
CMD ["/bin/bash", "/opt/couchbase/configure.sh"]
