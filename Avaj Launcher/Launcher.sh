find * -name "*.java" > sources.txt
javac @sources.txt
java -cp src com.yugao.avajlauncher.Entry ./scenario.txt