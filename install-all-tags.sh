#!/bin/bash

set -e

install() {
  TAG=$1
  echo_demarcation "$TAG"
  git checkout "$TAG"
  mvn clean install
}

echo_demarcation() {
  TEXT=$1
  echo
  echo "--------------------------------------------------------------------------"
  echo "INSTALL: $TEXT"
  echo "--------------------------------------------------------------------------"
}

while read -r tag; do
  install "$tag"
done < <(git tag)
