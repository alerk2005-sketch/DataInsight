#!/bin/sh
set -eu
cd "$(dirname "$0")"
build_dir=$(mktemp -d "${TMPDIR:-/tmp}/lab04.XXXXXX")
trap 'rm -rf "$build_dir"' EXIT HUP INT TERM
javac -encoding UTF-8 --release 17 -d "$build_dir" ./*.java
mkdir -p "$build_dir/META-INF/services"
cp META-INF/services/Analyzer "$build_dir/META-INF/services/Analyzer"
java -cp "$build_dir" DataInsight
