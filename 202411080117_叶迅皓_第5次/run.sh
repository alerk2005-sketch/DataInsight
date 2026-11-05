#!/bin/sh
set -eu
cd "$(dirname "$0")"
build_dir=$(mktemp -d "${TMPDIR:-/tmp}/lab05.XXXXXX")
trap 'rm -rf "$build_dir"' EXIT HUP INT TERM
javac -encoding UTF-8 --release 17 -d "$build_dir" ./*.java
java -cp "$build_dir" RobustLoader
