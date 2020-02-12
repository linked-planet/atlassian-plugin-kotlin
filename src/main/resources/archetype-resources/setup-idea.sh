#!/bin/sh

# Determine the location of the script
# resolve symbolic links
PRG="${0}"
while [ -h "${PRG}" ]; do
  ls=$(ls -ld "${PRG}")
  link=$(expr "${ls}" : '.*-> \(.*\)$')
  if expr "${link}" : '/.*' >/dev/null; then
    PRG="${link}"
  else
    PRG=$(dirname "${PRG}")/"${link}"
  fi
done
SCRIPT_DIR=$(dirname "${PRG}")

# ------------------------------------------------------------------------------------------------------------
# Register prepared run configurations with IntelliJ Project configuration
# Note: These are not checked into the repository under .idea folder, as users
#       often delete these when opening a project for the first time in Idea
# ------------------------------------------------------------------------------------------------------------
mkdir -p "$SCRIPT_DIR/.idea/runConfigurations"
for f in "$SCRIPT_DIR"/runConfigurations/*; do
  echo "symlink $f"
  filename=$(basename -- "$f")
  ln -sfn "$f" "$SCRIPT_DIR/.idea/runConfigurations/$filename"
done
