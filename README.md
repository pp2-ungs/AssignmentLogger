# AssignmentLogger

A Java library that provides file-based logging for assignment/task events. It is part of the **TASkOcupado** system and implements the `Observer` interface from the `TASkOcupadoCore` module to receive and persist event notifications.

© 2024 <a href="https://github.com/xebertz">Ebertz</a>, <a href="https://github.com/Gonza-JL">López</a>, <a href="https://github.com/rondelli">Rondelli</a>

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Requirements](#requirements)
- [Building](#building)
- [Usage](#usage)
- [Log File Format](#log-file-format)
- [Dependencies](#dependencies)

## Overview

`AssignmentLogger` listens for events emitted by the task management system and writes them — with a timestamp — to a plain-text log file. Each event is also echoed to standard output for debugging purposes.

## Features

- Implements the **Observer** pattern to receive task/assignment events automatically.
- Appends timestamped entries to a configurable log file.
- Falls back to a default log file (`~/AssignmentLogger.txt`) when no path is provided.
- Prints a debug line to the console for every event received.

## Project Structure

```
AssignmentLogger/
├── src/
│   └── logger/
│       └── Logger.java      # Core Observer implementation
├── nbproject/               # NetBeans project configuration
├── build.xml                # Apache Ant build script
└── manifest.mf
```

## Requirements

- **Java 22** or later
- **Apache Ant** (for building with `build.xml`)
- **TASkOcupadoCore** library — must be located at `../TASkOcupadoCore` relative to this project (provides the `Observer` interface and `Settings` class)

## Building

```bash
ant jar
```

The compiled JAR will be placed in `dist/AssignmentLogger.jar`.

To clean build artifacts:

```bash
ant clean
```

## Usage

Instantiate `Logger` with an optional file path, then register it as an observer in your subject/observable:

```java
import logger.Logger;

// Use a custom log file
Logger logger = new Logger("/path/to/my-log.txt");

// Use the default log file (~/AssignmentLogger.txt)
Logger logger = new Logger(null);
```

Whenever the subject notifies its observers, `Logger.update(Object event)` is called automatically, writing a line like:

```
[2024-06-01 14:32]: Task "Math Homework" marked as completed
```

to both the log file and standard output.

## Log File Format

Each log entry follows this format:

```
[yyyy-MM-dd HH:mm]: <event description>
```

Entries are appended to the file, so the full history is preserved across runs.

## Dependencies

| Dependency       | Role                                              |
|------------------|---------------------------------------------------|
| TASkOcupadoCore  | Provides `observer.Observer` and `core.Settings`  |

## Authors

© 2024 <a href="https://github.com/xebertz">Ebertz</a>, <a href="https://github.com/Gonza-JL">López</a>, <a href="https://github.com/rondelli">Rondelli</a>
