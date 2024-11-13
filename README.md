# Java Based Applications on Distinct Datasets Using Different Algorithms

# Group Members and their projects: 
     BONGU ASHISH - 23BDS014 (Nearest BRTS station finder)
     CHABALA SAI ARAVIND - 23BDS075 (Starlink intersatellite transmission) 
     TARAN JAIN - 23BDS062 (Flight route finder) 

# Table of Contents

1. A* ALGORITHM- Starlink Intersatellite shortest pathfinding algorithm.
2. HAVERSINE ALGORITHM- finding nearest BRTS Stop.
3. KRUSKAL ALGORITHM- optimized flight route.

# Project Folder Usage Guide

This folder contains multiple projects. Below is a brief explanation on how to use each of them.
## Project 1: Starlink Intersatellite Transmission Pathfinding Using A* Algorithm and TLE Data

Demonstrates a robust approach to satellite pathfinding for Starlink intersatellite transmission using TLE data, ECEF conversion, and the A* algorithm. The user inputs the coordinates of their location and the server's location to get the shortest path between the nearest Starlink satellites.


### How to Use:
1. Clone this repository to your local machine:
   ```bash
   git clone https://github.com/ashishbongu/Oops-Project.git 
2. Navigate to the project folder using:<br>

**COMMAND:  cd "D:/Oops_Project/A-star ALGORITHM"**

Inside the project folder, you'll find the src folder with the Java code and other necessary files.<br>
The dataset containing the geographical information is located outside the src folder, in the root project directory.<br>
The TLE dataset is for a particular instant of time and it has many properties such as satellite name, satellite ID and information about Satellite's orbital mechanics.

To download the data from the source : [Download dataset](https://celestrak.org/NORAD/elements/)

D:/Oops_Project/<br>
├── A-star ALGORITHM/<br>
│   ├── src/<br>
│   │   └── [main](https://github.com/ashishbongu/Oops-Project/blob/master/A-star%20ALGORITHM/src/Main.java)<br>
│   ├── starlink(1).tle<br>
│   └── (other project files)<br><br>

3. Compile the Java file:

**COMMAND: javac main.java**

## Project 2: Nearest BRTS Station Finder

This project helps you find the nearest Bus Rapid Transit System (BRTS) station using geographical data.

### How to Use:
1. Clone this repository to your local machine:
   ```bash
   git clone https://github.com/ashishbongu/Oops-Project.git 
2. Navigate to the project folder using:<br>

**COMMAND:  cd "D:/Oops_Project/HAVERSINE ALGORITHM"**

Inside the project folder, you'll find the src folder with the Java code and other necessary files.<br>
The dataset containing the geographical information is located outside the src folder, in the root project directory.<br>

D:/Oops_Project/<br>
├── HAVERSINE ALGORITHM/<br>
│   ├── src/<br>
│   │   └── [BusStationGraph.java](https://github.com/ashishbongu/Oops-Project/blob/master/HAVERSINE%20ALGORITHM/src/BusStationGraph.java)<br>
│   ├── station.txt<br>
│   └── (other project files)<br><br>

3. Compile the Java file:

**COMMAND: javac BusStationGraph.java**

## Project 3: Optimized Flight Route Finder Using KRUSKAL Algorithm


a project that leverages a graph-based approach to find the most efficient flight routes between two airports. By utilizing a Minimum Spanning Tree (MST) algorithm


### How to Use:
1. Clone this repository to your local machine:
   ```bash
   git clone https://github.com/ashishbongu/Oops-Project.git 
2. Navigate to the project folder using:<br>

**COMMAND:  cd "D:/Oops_Project/KRUSKAL ALGORITHM"**

Inside the project folder, you'll find the src folder with the Java code and other necessary files.<br>
The dataset containing the geographical information is located outside the src folder, in the root project directory.<br>

D:/Oops_Project/<br>
├── KRUSKAL ALGORITHM/<br>
│   ├── src/<br>
│   │   └── [FlightRoutes.java](https://github.com/ashishbongu/Oops-Project/blob/master/KRUSKAL%20ALGORITHM/src/FlightRoutes.java)<br>
│   ├── flights.csv<br>
│   └── (other project files)<br><br>

3. Compile the Java file:

**COMMAND: javac FlightRoutes.java**


