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

**Mpi Commands**

1) nano ~/.bashrc
2) export MPJ_HOME=/home/..../mpj
3) export PATH=$PATH:$MPJ_HOME/bin
4) source ~/.bashrc

5) javac -cp mpj/lib/mpj.jar fileName.java
6) mpjrun.sh -cp . -np 4 fileName

(extract mpj in same directory)
https://sourceforge.net/projects/mpjexpress/files/releases/

Crtl O enter cntrl x


repo
https://bitly.cx/IWgI
http://bit.ly/ThalaDs

