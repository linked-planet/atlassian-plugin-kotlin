#!/bin/bash

# Determine the location of the script
SCRIPT_DIR="$(
  cd "$(dirname "$0")" || exit
  pwd -P
)"

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
