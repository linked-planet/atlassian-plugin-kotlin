#set($h = '#')
#set($d = '$')
#!/bin/sh

set -e

GROUP_ID=${groupId}
ARTIFACT_ID=${artifactId}

if [ "$#" -lt 1 ]; then
  echo "Usage: $0 version [file]"
  exit 1
fi

VERSION=$1
FILE=$2

# deploy file if it is given (used for snapshots)
if [ "${FILE}" != "" ]; then
  fileName="$PWD/$FILE"

else
  # otherwise download release artifact from artifactory
  folder=$(echo "$GROUP_ID" | tr "." "/")
  downloadUrl="$ARTIFACTORY_CONTEXT_URL/libs-release-local/$folder/$ARTIFACT_ID/$VERSION/$ARTIFACT_ID-$VERSION.jar"
  echo "Download URL = ${downloadUrl}"

  echo "Downloading JAR file from Artifactory ..."
  curl --fail -X GET -u "${ARTIFACTORY_USERNAME}":"${ARTIFACTORY_PASSWORD}" "${downloadUrl}" -O

  fileName="$PWD/${d}{downloadUrl${h}*${d}{VERSION}/}"
fi

# install plugin via upm
echo "File Name = ${fileName}"
echo "Installing plugin ..."
mvn -B -Ddeploy.url="$DEPLOY_URL" -Ddeploy.username="$DEPLOY_USERNAME" -Ddeploy.password="$DEPLOY_PASSWORD" \
  upm:uploadPluginFile -DpluginKey="$GROUP_ID.$ARTIFACT_ID" -DpluginFile="$fileName"
