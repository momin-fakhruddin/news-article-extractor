# NamedEntityResolution

Attempting to resolve and isolate People and Organizational entities from a body of text, formatted in a specific XML format.

## Pre-requisites
* Java 1.8+
* Gradle 2+

## Setup Instructions

Run the below command

`gradle build`

Now that the project is built, you can run the tagger.

`./build/scripts/NamedEntityResolution -xmlFilePath <PATH_TO_INPUT_FILE> -outputFilePath <PATH_TO_OUTPUT_FILE>`

That's it. Once the run is complete, your output file will contain a json annotated list of articles with their respective entities.