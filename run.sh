#!/bin/sh
# run.sh: para os hipsters que insistem em trabalhar com desenvolvimento java
# sem por as mãos em nenhuma IDE (pois não tomam banho)
javac -d out br/ufv/truco/*.java
java -cp out br.ufv.truco.Truco
