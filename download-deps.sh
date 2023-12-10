#!/bin/sh
# download-deps.sh: para os hipsters que insistem em trabalhar com
# desenvolvimento java sem por as mãos em nenhuma IDE. Esse script realiza o
# download do JUnit e suas dependências, necessárias à execução dos testes.
mkdir -p deps/
wget https://repo1.maven.org/maven2/junit/junit/4.13.2/junit-4.13.2.jar -O deps/junit-4.13.2.jar
wget https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/hamcrest/hamcrest-all-1.3.jar -O deps/hamcrest-1.3.jar
