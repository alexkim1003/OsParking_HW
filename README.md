#OsParking_HW(Eng)- support both Korean and English GUI

OsParking- English & Korean GUI, 3 device simulators, real hardware for E-board and gate bar, needs OsParking-DB(another OsParking repo')

OsParking (pronounced 'Oz' Parking, as in The Wizard of Oz) is an Open Source Parking Lot Management Software. In an extended sense, it is a term which describes a simulation package which includes OsParking(manager) and three device simulators(Camera, E-Board, GateBar).

OsParking software assumes the use of LPR (License Plate Recognition) function a external module to identify car tag numbers.

As of Jan. 15th, 2016, OsParking_HW supports LEDnotice E-board(both Socket and Serial connection) and Nara gate bar(both Serial and Socket connection) real hardware devices.

Open source Parking, Inc.

Jan. 15, 2016

-

Softwares Needed to Run OsParking

1. JRE 1.8.0 or later

2. MySQL 5.6.24 or later

Additionally Needed Softwares for the Developers

1. IDE used: netbeans 8.0.2 or later

2. IDE augmenting Maven : apache-maven-3.3.3 or later

3. Java Compiler: JDK 1.8.0_45 or later

4. (Optionally) Toad for MySQL 7.5.0 or later

-

After the Maven is installed, the rs2xml.jar need to be registered manually as follows(this jar file is in 'lib' folder of this repository):

>C:\DOS> // first move to the directory where this jar file exists

>C:\DOS> mvn install:install-file -Dfile=rs2xml.jar -DgroupId=net.proteanit.sql -DartifactId=rs2xml -Dversion=1.0 -Dpackaging=jar

Project Size: // including simulators(Camera, E-board, Gate bar)
Number of Java files: 158
Total number of lines in all Java files: 70,500
Simulator Size:
Number of Java files: 42
Total number of lines in all Java files: 18,500
.
