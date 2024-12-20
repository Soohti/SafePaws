# SafePaws README

A pet adoption system featuring a client-server architecture. Different users can log in to the system with different roles and perform various operations. The system is implemented using Java and SQLite.

Apart from the basic functionalities, the system also includes the following advanced features:

- Pet Recommendation System: The system will calculate the matching score between users and pets.
- Pet Allocation System: The system will allocate pets to users based on their preferences.
- Optimal Addressing System: The system will recommend the optimal address for new shelters based on the location of stray animals.
- Optimal Pathfinding System: The system will recommend the optimal path for animal shelters to pick up stray animals.

Project Structure
-----------------

- conf/: Contains configuration files.
  - conf/sqlite.db: The database that stores all data.
- src/: Source code directory.
- pom.xml: The Maven build file.

JDK Version
------------

This project requires JDK version 21.

Building the Project
---------------------

To build the project, run the following command:

    mvn package

Linting
--------

To perform code linting (Checkstyle and SpotBugs), execute:

    mvn verify

Usage
------

1. Navigate to the release folder.
2. Start the server by running SPServer.jar.
3. Start the client by running SPClient.jar.

Example Accounts
-----------------

- Username: memberm1, Password: memberm1, Role: Member
- Username: shelter1, Password: shelter1, Role: Shelter
- Username: adminaaa, Password: adminaaa, Role: Admin
