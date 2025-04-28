Corba run commands


idlj -fall Reverse.idl 

**terminal 1**
1) orbd -ORBInitialPort 1050 &
2) javac *.java
3) java ReverseServer -ORBInitialPort 1050 -ORBInitialHost localhost

**2nd terminal**
4) javac *.java
5) java ReverseClient -ORBInitialPort 1050 -ORBInitialHost localhost

**java**
6) sudo apt update
7) sudo apt install openjdk-8-jdk
8) export PATH=/usr/lib/jvm/java-8-openjdk-amd64/bin:$PATH
9) java -version

sudo update-alternatives --config java
sudo update-alternatives --config javac

Mpi Commands

