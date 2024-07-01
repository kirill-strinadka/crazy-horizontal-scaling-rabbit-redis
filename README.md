# CrazyTask Management System

This project is a Spring Boot application designed to manage and execute "CrazyTasks" with robust messaging and locking mechanisms using RabbitMQ and Redis.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [Building and Running](#building-and-running)
- [Contributing](#contributing)
- [License](#license)

## Introduction

CrazyTask Management System is a Spring Boot-based application that handles task initialization, sending, and listening using RabbitMQ and provides a distributed locking mechanism using Redis. This ensures tasks are processed efficiently and without conflicts in a distributed environment.

## Features

- Task management and processing using RabbitMQ
- Distributed locking using Redis
- Spring Boot for streamlined setup and deployment
- Configuration via `application.yml`
- Gradle for build automation

## Project Structure

The project includes the following key files and directories:

- **RabbitMQConfig.java**: Configuration for RabbitMQ.
- **RedisConfig.java**: Configuration for Redis.
- **RedisLock.java**: Implementation of a distributed lock using Redis.
- **CrazyTask.java**: Representation of the task entity.
- **CrazyTaskListener.java**: Listener for processing incoming tasks from RabbitMQ.
- **CrazyTaskSender.java**: Sender for dispatching tasks to RabbitMQ.
- **CrazyTaskInitializerService.java**: Service for initializing tasks.
- **application.yml**: Application configuration file.
- **build.gradle.kts**: Gradle build script.

## Getting Started

### Prerequisites

- Java 11 or higher
- Gradle
- Docker (optional, for running RabbitMQ and Redis)

