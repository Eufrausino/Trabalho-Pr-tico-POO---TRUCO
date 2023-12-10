#!/bin/sh
# test.sh: para os hipsters que insistem em trabalhar com desenvolvimento java
# sem por as mãos em nenhuma IDE. Esse script compila os testes e executa aquele
# cujo nome for especificado pela linha de comando.

if [ $# -lt 1 ]; then
    echo "Uso: $0 <nome-do-teste>"
    exit 1
fi
javac -cp deps/junit-4.13.2.jar:deps/hamcrest-core-1.3.jar:out -d out-test br/ufv/truco/testes/*.java
java  -cp deps/junit-4.13.2.jar:deps/hamcrest-core-1.3.jar:out:out-test "br.ufv.truco.testes.$1"
