#!/bin/bash

# Determine the location of the script
SCRIPT_DIR="$(
  cd "$(dirname "$0")" || exit
  pwd -P
)"

pushd "$PWD" || exit
cd "$SCRIPT_DIR" && cd "../" || exit


FOLDER_NAME=$(basename "$SCRIPT_DIR")

echo "---------------------------------------------------"
echo "Renaming existing project to $FOLDER_NAME-swap ..."
echo "---------------------------------------------------"
echo
mv "$FOLDER_NAME" "$FOLDER_NAME-swap"


echo "---------------------------------------------------"
echo "Regenerating project files ..."
echo "---------------------------------------------------"
echo
mvn archetype:generate -B -U \
    "-DarchetypeGroupId=com.linked-planet.maven.archetype" \
    "-DarchetypeArtifactId=atlassian-plugin-kotlin" \
    "-DarchetypeVersion=3.0.1-SNAPSHOT" \
    "-DatlassianApp=$atlassianApp" \
    "-DgroupId=$groupId" \
    "-DartifactId=$artifactId" \
    "-Dpackage=$package" \
    "-DnameHumanReadable='$nameHumanReadable'" \
    "-Ddescription='$description'" \
    "-DorganizationNameHumanReadable='$organizationNameHumanReadable'" \
    "-DorganizationUrl=$organizationUrl" \
    "-DinceptionYear=$inceptionYear" \
    "-DdeveloperConnection=$developerConnection" \
    "-DgenerateBitbucketPipelines=$generateBitbucketPipelines"


echo "---------------------------------------------------"
echo "Copy files into swap ..."
echo "---------------------------------------------------"
echo
cp -R "$FOLDER_NAME" "$FOLDER_NAME-swap"


echo "---------------------------------------------------"
echo "Clean up ..."
echo "---------------------------------------------------"
echo
rm -r "$FOLDER_NAME"
mv "$FOLDER_NAME-swap" "$FOLDER_NAME"

popd || exit
