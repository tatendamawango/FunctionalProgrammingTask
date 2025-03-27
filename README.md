# 🧩 Functional Programming Demonstration – Java

This repository contains a demonstration of functional programming concepts using Java, focused on geographical data processing. The program determines whether specific locations fall within defined regions using bounding box logic.

---

## 🚀 Functionality Overview

The program reads location and region data from JSON files, checks which locations fall inside which regions, and outputs a mapping of matched locations per region to `result.json`.

### 🧠 How It Works:
- Each **region** contains a name and an array of bounding coordinates.
- Each **location** has a name and a single point coordinate.
- For each location, the script checks if its coordinate is within any of the bounding boxes defined for a region using:
  - `min`/`max` bounds
  - Axis-aligned rectangle logic
- Matching results are written to a `result.json` file in a human-readable format.

---

## 🛠️ Tech Stack

- Java (Standard I/O, JSON parsing)
- [org.json](https://github.com/stleary/JSON-java) library
- Functional decomposition (breaking tasks into small, reusable functions)

---

## 📄 Example Output (`result.json`)

[
  {
    "region": "Region A",
    "matched_locations": ["Loc1", "Loc4"]
  },
  {
    "region": "Region B",
    "matched_locations": ["Loc2"]
  }
]

---

## 📌 Concepts Demonstrated
- File I/O (read/write JSON)
- Array and List operations
- Bounding box geometry
- Functional-style decomposition in Java
- Robust error handling with try/catch

---

## 📂 How to Run
# Compile
javac -cp .:json.jar src/main/java/org/example/Main.java

# Run
java -cp .:json.jar src/main/java/org/example/Main

