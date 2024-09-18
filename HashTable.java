import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
/*
 * HashTable create a string array and adds at a given key O(1). If the element at the given key 
 * is filled it generate a new key.(Double Hashing)
 * size() returns the current size of the array 
 * makeKey() makes a key by adding the characters of a city and % 8 to return 0-8
 * addToHash() calles makeKey and uses the key to add to the hash table, if the spot is filled it will make a new key 
 * deleteTable() uses the city to find the key and moves to the next element using a new key
 * searchTable() searches the table to ensure no dublicates are added
 * printTable() prints data as a table, prints empty if the element in the array is null 
 * hashToFile() saves list to a file
 */
public class HashTable {
    String[] table;
    int key;
    int sum;
    static final int cap = 8; //capacity always 8
    int curr; //current

    public HashTable () { //constructor
        this.curr = 0;
        table = new String[this.cap];
    }
    public int size() { //returns current size
        return curr;
    }
    public int makeKey(String city) { //makes a key by adding ascii values
        sum = 0;
        String[] letter = city.split("");
        for (int i = 0; i < city.length(); i++) {
            char c = letter[i].charAt(0); //turns string to a char
            sum += c; //adds all chars
        }
        return sum % 8; //returns a key 0-7
    }
    public void addToHash(String data) { //adds to hash table
        boolean added = false;
        String[] part = data.split(" ");
        String city = part[0];
        if(searchTable(city) == true) { //check for duplicates 
            System.out.println("City is aleady in hash table");
            return;
        }
        key = makeKey(city.toLowerCase()); //makes key/adds all chars in lower case
        if (table[key] == null) { //adds if spot is empty O(1)
            table[key] = data; 
            this.curr++;
            added = true;
        }
        else if (table[key] != null) { //double hashing if collision occurs  
            int i = 0, probed = 0;
            int bucket = (key + i * (7 - key % 7)) % 8;
            while (probed < 8) {
                if (table[bucket] == null) {
                    table[bucket] = data;
                    this.curr++;
                    added = true;
                    break;
                }
                i++;
                probed++;
                bucket = (key + i * (7 - key % 7)) % 8;
            }
        }
        if (added == true) System.out.println("DATA ADDED");
    }
    public void deleteTable(String city) { //deleting a city from a hash table
        boolean deleted = false;
        key = makeKey(city.toLowerCase()); //makes key/adds all chars in lower case
        if (table[key] != null) { //delete if found at key O(1)
            String[] part = table[key].split(" ");
            if (part[0].equalsIgnoreCase(city)) {
                table[key] = null;
                deleted = true;
                curr--;
                System.out.println("DELETED");
            }
        }
        if (deleted == false) { //double hashing if not found   
            int i = 0, probed = 0;
            int bucket = (key + i * (7 - key % 7)) % 8;
            while (probed < 8) {
                if (table[bucket] != null) {
                    String[] part = table[bucket].split(" ");
                    if (part[0].equalsIgnoreCase(city)) {
                        table[bucket] = null;
                        curr--;
                        deleted = true;
                        System.out.println("DELETED");
                        break;
                    }
                }
                i++;
                probed++;
                bucket = (key + i * (7 - key % 7)) % 8;
            }
        }
        if (deleted == false) System.out.println("NOT FOUND"); 
    }
    public boolean searchTable(String str) { //search for city in hash table, returns true if found
        boolean found = false;
        key = makeKey(str.toLowerCase());
        if (table[key] != null) {
            String[] part = table[key].split(" ");
            if (part[0].equalsIgnoreCase(str)) return true;
        }
        else {
            for(int i = 0; i < 8; i++) {
                if (table[i] != null) {
                    String[] part = table[i].split(" ");
                    if (part[0].equalsIgnoreCase(str)) return true;
                }
            }
        }
        return false;
    }
    public void printTable() { //prints hash table
        System.out.println("\nHash Table");
        System.out.println("Index: City               Population          Robbery        Burglary       Arson");
        System.out.println("------------------------------------------------------------------------------------");
        //loops for formating table
        for (int i = 0; i < 8; i++) {
            if (table[i] == null) {
                System.out.println((i+1) + ":    " + "Empty");
            }
            else {
                String[] part = table[i].split(" ");
                System.out.print((i+1) + ":    " + part[0]);
                for (int j = 0; j < (part[0].length() - 20) *-1 ; j++) {
                    System.out.print(" ");
                }
                System.out.print(part[1]);
                for (int j = 0; j < (part[1].length() - 20) *-1; j++) {
                    System.out.print(" ");
                }
                System.out.print(part[2]);
                for (int j = 0; j < (part[2].length() - 15) *-1; j++) {
                    System.out.print(" ");
                }
                System.out.print(part[3]);
                for (int j = 0; j < (part[3].length() - 15) *-1; j++) {
                    System.out.print(" ");
                }
                System.out.println(part[4]);
            }
        }
        System.out.println();
    }
    public void hashToFile(String dataFile) throws Exception{
        FileWriter writer = new FileWriter(dataFile);
        for (int i = 0; i < 8; i++) { //Loops through array and writes to file
            writer.write(table[i] + "\n");
        }
        writer.close();
    }
}
