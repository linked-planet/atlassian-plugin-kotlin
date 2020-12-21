#!/bin/sh

set -e

MAVEN_SETTINGS_FILE=/usr/share/maven/conf/settings.xml
SERVER_XML="<server><id>artifactory</id><username>$ARTIFACTORY_USERNAME</username><password>$ARTIFACTORY_PASSWORD</password></server>"

sed -i~ "/<servers>/ a$SERVER_XML" $MAVEN_SETTINGS_FILE
