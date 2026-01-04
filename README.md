# Kynos

## Overview

This is a framework for launching and running GUI applications in a consistent, unified environment. It abstracts many runtime concepts so developers can focus on writing GUI applications that run on a variety of environments.

The primary use case is to provide a cross-platform environment that runs on tablets, phones, personal computers, and other hybrid devices (e.g., Raspberry Pi with touch screen displays) with no code changes. Developers can write a Kynos "Pod" and that pod can be run on any device that Kynos supports. The goal is to allow developers to write their pod once and run it anywhere and get a consistent user experience across all execution environments.

Kynos is based on JavaFX UI technology. The ability to run on any platform and load code dynamically during execution are key feature of the Kynos project. Developers can compile and package a Java artifact, and gain the ability to deploy their code into almost any execution platform. 

## Pods

A "Pod" is a Java JAR file that contains all the business logic necessary to run their application. This JAR can then be "deployed" to a Kynos instance and run uniformly regardless of the underlying infrastructure.

While some may find this limiting, adhering to a clear and consistent API will help ensure maximum deployability and consistent operation regardless of the underlying computing infrastructure.


## License

This project is licensed under the PolyForm Noncommercial License 1.0.0.

* **Personal Use:** Free and permitted.
* **Commercial Use:** Restricted. Please contact sdcote.com to obtain a commercial license.

See the [LICENSE](LICENSE.txt) file for the full license text.