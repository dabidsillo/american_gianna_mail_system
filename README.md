# README - Java™ Platform, Standard Edition 8 Development Kit - JDK™ 8

## Contents

- [Introduction](#introduction)
- [System Requirements & Installation](#system-requirements--installation)
- [JDK Documentation](#jdk-documentation)
- [Release Notes](#release-notes)
- [Compatibility](#compatibility)
- [Bug Reports and Feedback](#bug-reports-and-feedback)
- [Contents of the JDK](#contents-of-the-jdk)
- [Java Runtime Environment](#java-runtime-environment)
- [Redistribution](#redistribution)
- [Java Endorsed Standards Override Mechanism](#java-endorsed-standards-override-mechanism)
- [Web Pages](#web-pages)

## Introduction

Thank you for downloading this release of the Java Platform, Standard Edition Development Kit (JDK). The JDK is a development environment for building applications, applets, and components using the Java programming language.

The JDK includes tools useful for developing and testing programs written in the Java programming language and running on the Java platform.

## System Requirements & Installation

System requirements, installation instructions, and troubleshooting tips are located on the Java Platform web site at:

- [Installation Instructions](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)

## JDK Documentation

The online Java Platform, Standard Edition (Java SE) Documentation contains API specifications, feature descriptions, developer guides, reference pages for JDK tools and utilities, demos, and links to related information. The Java SE documentation is also available in a download bundle which you can install on your machine. To obtain the documentation bundle, see the [download page](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html). For API documentation, refer to the [Java Platform, Standard Edition API Specification](https://docs.oracle.com/javase/8/docs/api/). This documentation provides brief descriptions of the API with an emphasis on specifications, not on code examples.

## Release Notes

See the [Release Notes](https://www.oracle.com/java/technologies/javase/8-relnotes.html) on the Java Platform web site for additional information pertaining to this release. Please check the online release notes occasionally for the latest information as they will be updated as needed.

## Compatibility

See [Compatibility with Previous Releases](https://www.oracle.com/java/technologies/javase/8-compatibility-guide.html) on the Java Platform web site for the list of known compatibility issues. Every effort has been made to support programs written for previous versions of the Java platform. Although some incompatible changes were necessary, most software should migrate to the current version with no reprogramming. Any failure to do so is considered a bug, except for a small number of cases where compatibility was deliberately broken, as described on our compatibility web page. Some compatibility-breaking changes were required to close potential security holes or to fix implementation or design bugs.

## Bug Reports and Feedback

The [JDK Bug Database](https://bugs.java.com/) web site lets you search for and examine existing bug reports, submit your own bug reports, and tell us which bug fixes matter most to you. To directly submit a bug or request a feature, fill out this form:

- [Submit a Bug or Feature Request](http://bugreport.java.com/bugreport/)

You can send feedback to the Java SE documentation team.

**Note:** Please do not seek technical support through the Bug Database or our development teams. For support options, see [Support and Services](https://www.oracle.com/support/) on the Oracle Support web site.

## Contents of the JDK

This section contains a general summary of the files and directories in the JDK. For details on the files and directories, see the [File Structure](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jdkfiles.html) section of the Java SE documentation for your platform.

- **Development Tools**  
  (In the `bin/` subdirectory) Tools and utilities that will help you develop, execute, debug, and document programs written in the Java programming language. For further information, see the [tools documentation](https://docs.oracle.com/javase/8/docs/technotes/tools/).

- **Runtime Environment**  
  (In the `jre/` subdirectory) An implementation of the Java Runtime Environment (JRE) for use by the JDK. The JRE includes a Java Virtual Machine (JVM™), class libraries, and other files that support the execution of programs written in the Java programming language.

- **Additional Libraries**  
  (In the `lib/` subdirectory) Additional class libraries and support files required by the development tools.

- **Java DB**  
  (In the `db/` subdirectory) Java DB, Oracle's distribution of the Apache Derby relational database. For further information, see the [Java DB documentation](https://docs.oracle.com/javadb/).

- **C header Files**  
  (In the `include/` subdirectory) Header files that support native-code programming using the Java Native Interface, the JVM Tool Interface, and other functionality of the Java platform.

- **Source Code**  
  (In `src.zip`) Java programming language source files for all classes that make up the Java core API. To extract these files, use any common zip utility or the Jar utility:

  ```bash
  jar xvf src.zip
  ```

## Java Runtime Environment

The Java Runtime Environment (JRE) allows you to run applications written in Java. See the [download page](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html) for more details.

## Redistribution

Current releases of Oracle Java SE 8 Development Kit require a commercial license for redistribution. See [this FAQ](https://www.oracle.com/java/technologies/javase/jdk-faqs.html) for details.

## Java Endorsed Standards Override Mechanism

The Endorsed Standards Override Mechanism is deprecated. See [more information here](https://docs.oracle.com/javase/8/docs/technotes/guides/standards/).

## Web Pages

- [Java SE Overview](https://www.oracle.com/java/technologies/javase-overview.html)
- [Java SE Documentation](https://docs.oracle.com/javase/8/docs/)
- [Oracle Technology Network](https://www.oracle.com/technical-resources/)
- [Java SE Support](https://www.oracle.com/java/technologies/javase-support.html)

Oracle and Java are registered trademarks of Oracle Corporation and/or its affiliates. Other names may be trademarks of their respective owners.
