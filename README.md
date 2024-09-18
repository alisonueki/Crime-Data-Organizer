# Crime-Data-Organizer

## Overview
This Java program is an interactive command-line interface (CLI) that handles data management using multiple data structures, including arrays, singly linked lists, and hash tables. The program allows users to import data from a file, perform calculations, manipulate the data, and save the results back to a file. The program features a main menu that lets users interact with the data in various ways, such as adding new data, calculating averages, sorting, searching, and more.

## Features
- **Data Loading**: Import data from a file based on the year provided by the user.
- **View and Modify Data**: Add, delete, and update data entries.
- **Data Calculations**: Calculate differences, averages, and values per 1000 people.
- **Sorting**: Sort data alphabetically or by population (ascending or descending).
- **Top and Lowest Five**: Retrieve the top and lowest five data entries based on population.
- **Search**: Search for a specific city within the data.
- **Create and Manage Lists**: Use a hash table to store data with a maximum capacity of 8 entries.
- **File Saving**: Save modifications to the original or a new file.

## Data Structures
- **DataArray**: Holds the array of data and provides methods for adding, removing, and modifying the array.
- **DataCalculations**: Provides methods for calculating differences, averages, per capita values, and other statistics using `DataArray`.
- **LList**: A singly linked list that stores the same data as the array, enabling linked list operations like adding, deleting, and searching.
- **HashTable**: Used for managing a limited-capacity list (max 8 entries) that supports adding, deleting, and printing entries.

## Dependencies
- Java SE Development Kit (JDK) 8 or later.
- Data files in `.txt` format should be available in the same directory as the program.
