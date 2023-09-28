# TASHEEH: Cleaning ill-formed Rows in CSV Files
Code repository of the TASHEEH project.

TASHEEH takes a raw CSV file containing ill-formed rows as input and outputs a cleaned version of the file by correcting the structure of the incorrectly formatted rows.

## Setup

1. The code is written using Java 8.
2. Download the available code as a zip file or cloned TASHEEH repository.
3. Include the following external libraries: 
	- univocity-parsers-2.9.1 or its latest version
	- commons-lang3-3.11
	- guava-31.0.1-jre
   
 4. Input/Output: It can be executed directly from the command line.
 5. For the IDE, import TASHEEH as a project, and you can set the arguments in the `Run Configuration`. 
	   
The system requires two arguments: 1) the input file path, and 2) the output cleaned file path.
