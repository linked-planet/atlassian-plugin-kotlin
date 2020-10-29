#!/bin/sh

set -e

MAVEN_SETTINGS_FILE=/usr/share/maven/conf/settings.xml
SERVER_XML_RELEASE="<server><id>artifactory.release</id><username>$ARTIFACTORY_USERNAME</username><password>$ARTIFACTORY_PASSWORD</password></server>"
SERVER_XML_SNAPSHOT="<server><id>artifactory.snapshot</id><username>$ARTIFACTORY_USERNAME</username><password>$ARTIFACTORY_PASSWORD</password></server>"

sed -i~ "/<servers>/ a$SERVER_XML_RELEASE" $MAVEN_SETTINGS_FILE
sed -i~ "/<servers>/ a$SERVER_XML_SNAPSHOT" $MAVEN_SETTINGS_FILE
