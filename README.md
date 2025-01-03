# EmojiGenmoji Project
## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Usage](#usage)
- [Results](#results)
- [Acknowledgements](#acknowledgements)

## Introduction
Welcome Dr. Pauli Lai! This is the **Emoji 2 Genmoji** project! This project is developed using Java as the primary programming language and serves as my inaugural venture into Java-based applications. The main objective is to recreate the underlying principles of Apple's AI-driven Genmoji for iOS, providing a customized set of emojis tailored to user preferences.

This project originally try to scrape the emoji data from [Emojigraph](https://emojigraph.org/) and download the emoji images, and then train the Flux.1 development model, but since the Flux.1 development model is a Python runtime model with both training and inference, it is beyond the scope of this Java programming project.

However, I still want to show you some sample results derived from the scraped and downloaded emoji dataset.

By undertaking this project, I aim to demonstrate my proficiency in Java, web scraping, and data manipulation while contributing a useful tool for emoji enthusiasts. 

## Features
- **Emoji Scraping**: Extracts emoji data from [Emojigraph](https://emojigraph.org/) using a web scraper.
- **Image Downloading**: Downloads high-quality emoji images and stores them locally.
- **Data Structuring**: Organizes emoji information, including names, image URLs, local paths, and skin tones.
- **JSON Handling**: Converts and fixes JSON files to ensure correct local path references.
- **User-Friendly Interface**: Provides a simple command-line interface to execute scraping and downloading tasks.

## Technologies Used
- **Java**: Primary programming language.
- **Jsoup**: For web scraping and parsing HTML content.
- **Gson**: For JSON serialization and deserialization.
- **Maven**: Project management and dependency management.

## Project Structure

```
EmojiGenmoji/
├── src/
│   └── main/
│       └── java/
│           └── emoji/
│              ├── EmojiScraper.java
│              ├── EmojiDownloader.java
│              ├── Emoji.java
│              ├── EmojiJsonFix.java
│              └── EmojiProject.java
├── emojis/
│   └── apple/
│       └── light/
│           └── emojis.png
├── emoji_mapping.json
├── emoji_mapping_local.json
├── pom.xml
└── README.md
```
### Class Descriptions
- **EmojiScraper.java**:  
  Responsible for scraping emoji data from the [Emojigraph](https://emojigraph.org/) website. It parses the HTML content and extracts relevant emoji information, storing it in a JSON format.

- **EmojiDownloader.java**:  
  Handles the downloading of emoji images based on the URLs obtained from the scraper. It saves the images to a specified local directory.

- **Emoji.java**:  
  Defines the structure of an emoji object, including attributes such as name, image URL, local path, and skin tone variations.

- **EmojiJsonFix.java**:  
  Processes the initial JSON file generated by the scraper to replace web URLs with local file paths. It ensures that the JSON references point to the downloaded images, producing a corrected JSON file.

- **EmojiProject.java**:  
  The main entry point of the application. It orchestrates the scraping, downloading, and JSON fixing processes by invoking the respective classes.

## Installation
### Prerequisites
- **Java Development Kit (JDK)**: Ensure that JDK 8 or higher is installed on your system. You can download it from [Oracle](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or [OpenJDK](https://openjdk.java.net/).
- **Maven**: Used for project management and dependency handling. Download and install Maven from [here](https://maven.apache.org/install.html).

### Steps
1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/EmojiGenmoji.git
   ```

2. **Navigate to the Project Directory**
   ```bash
   cd EmojiGenmoji
   ```

3. **Build the Project**
   Use Maven to compile the project and resolve dependencies.

   ```bash
   mvn clean install
   ```

## Usage
After successful installation, you can run the project using the following steps:
1. **Execute the Main Program**

   ```bash
   mvn exec:java -Dexec.mainClass="EmojiProject"
   ```
2. **Process Flow**

   - **Scraping**: The program scrapes emoji data from [Emojigraph](https://emojigraph.org/) and saves it as `emojis.json`.
   - **Downloading**: It then downloads each emoji image to the `images/` directory.
   - **JSON Fixing**: Finally, it updates the `emojis.json` file to reference the local image paths instead of the original URLs.
3. **Output**
   - **emoji_mapping.json**: Contains structured emoji data with internet image paths.
   - **emoji_mapping_local.json**: Contains structured emoji data with local image paths.
   - **emojis/apple/light/**: Directory containing all downloaded emoji images.

## Results
Although the training of the Flux.1 development model is beyond the scope of this Java programming project since it is a python runtime model with both training and inference, instead, here are some sample results derived from the scraped and downloaded emoji dataset:

### Sample Emojis
Dim Sum
> ![Dim Sum|width=100](./showcase/dimsum.png)

Database
> ![DataBase|width=100](./showcase/DataBase.png)

Scientist Holding a gun
>  ![Scientist Holding a gun|width=100](./showcase/Scientist_Holding_a_gun.png)

Baby King
> ![Baby King|width=100](./showcase/Baby_King.png)


## Acknowledgements
- **[Emojigraph](https://emojigraph.org/)**: For providing a comprehensive source of emoji data.
- **Jsoup**: For simplifying the web scraping process.
- **Gson**: For efficient JSON handling in Java.
---
