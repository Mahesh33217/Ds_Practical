Corba run commands


idlj -fall Reverse.idl

orbd -ORBInitialPort 1050 &
javac *.java
java ReverseServer -ORBInitialPort 1050 -ORBInitialHost localhost

2nd terminal 
javac *.java
java ReverseClient -ORBInitialPort 1050 -ORBInitialHost localhost


sudo apt update
sudo apt install openjdk-8-jdk
export PATH=/usr/lib/jvm/java-8-openjdk-amd64/bin:$PATH
java -version

sudo update-alternatives --config java
sudo update-alternatives --config javac

Mpi Commands

