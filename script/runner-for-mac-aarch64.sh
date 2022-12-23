#!/usr/bin/env bash

getBinaries() {
  curl -sL https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-22.3.0/graalvm-ce-java17-darwin-aarch64-22.3.0.tar.gz | tar -xzf - -C bin/graalvm --strip-components=1
  curl -sL https://dlcdn.apache.org/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.tar.gz | tar -xzf - -C bin/maven --strip-components=1
}

#xcode-select --install
mkdir -p bin/graalvm
mkdir -p bin/maven
getBinaries
JAVA_HOME=`pwd`"/bin/graalvm/Contents/Home"
bin/graalvm/Contents/Home/bin/gu install native-image
bin/maven/bin/mvn clean install -Pnative-image
cp target/jiggler4j jiggler4j
tar -czf bin/jiggler4j-macos-aarch64.tar.gz jiggler4j
rm jiggler4j