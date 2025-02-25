# TASHEEH: Cleaning Ill-Formed Rows in CSV Files
This repository contains the code for **TASHEEH**, a Java-based tool designed to clean ill-formed rows in CSV files. It processes a raw CSV file and outputs a cleaned version by correcting incorrectly formatted rows.

## Running the Program
TASHEEH can be executed in two ways: **Command-Line Mode (CLI) or GUI Mode**.

### Running the JAR file via command line with two arguments (CLI Mode)
```sh
java -jar tasheeh.jar "path/to/input.csv" "path/to/output.csv" 
```
- First argument: Path to the input CSV file.
- Second argument: Path where the cleaned CSV file will be saved.

### Running the JAR File Without Arguments (GUI Mode)
```sh
java -jar tasheeh.jar
```
- Choose the input CSV file.
- Choose the output file.


## Output
- The program removes ill-formed rows and saves the cleaned CSV at the provided output path.
- A message confirms successful execution.

## Support
For any updates, open an issue in this repository.
