#!/bin/sh

# ----------------------------------------------------------------
# Starts and initializes local docker services for testing.
#
# Requires:
# - docker-compose
# ----------------------------------------------------------------

# Start docker services
sudo docker-compose -f docker-env/docker-compose.yml up -d
