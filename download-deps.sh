#!/bin/sh
# download-deps.sh: para os hipsters que insistem em trabalhar com
# desenvolvimento java sem por as mãos em nenhuma IDE. Esse script realiza o
# download do JUnit e suas dependências, necessárias à execução dos testes.
wget https://repo1.maven.org/maven2/junit/junit/4.13.2/junit-4.13.2.jar > deps/junit-4.13.2.jar
wget http://search.maven.org/remotecontent?filepath=org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar > deps/hamcrest-core-1.3.jar
rm *.jar # remove artefatos temporários do wget
