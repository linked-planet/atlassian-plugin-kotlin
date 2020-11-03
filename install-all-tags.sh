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

export -f install
export -f echo_demarcation
git tag | xargs bash -c 'install "$@"'
