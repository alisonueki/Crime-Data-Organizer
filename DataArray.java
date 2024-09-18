import java.util.Scanner;
import java.io.File;
import java.time.Clock;
import java.time.Duration;
import java.io.FileWriter;
/*
 * DataArray creates an array by reading from a file. It holds methods for printing, sorting, searching, and modifying.
 * Most methods use O(N) and O(N^2) since the array is small.
 * size() returns current
 * addCity() reads input and returns a string of data
 * openFile() reads from a file and returns false if the file could not be opened
 * writeToFile() reads from array into a file
 * add() adds to array and makes more capacity
 * remove() deletes at an index and shifts array over one
 * get() returns data at an index
 * print() prints data table
 * sort() sorts alphabetically
 * reverseSort() sorts reverse alphabetically
 * popSort() sorts population high to low
 * popReverse() sorts population low to high
 * search() searches for a city and returns a bool, reports time in nanoseconds
 */
public class DataArray {
    static String arr[]; //holds data
    static int capacity; //space in array
    static int current;  //how much of array is being used
    String city;
    int pop = 0, rob = 0, burg = 0, ars = 0, count = 0; 
    public DataArray() { //constructor
        arr = new String[1];
        capacity = 1;
        current = 0;
    }
    public int size() { //size of arr[]
        return current;
    }
    public String addCity() { //reads the input to add a new city, returns a string to use
        String vetData = "Data can not be less than 0";
        Scanner sc = new Scanner(System.in);
        String s, city, data = "No data";
        int pop = 0, rob = 0, burg = 0 , ars = 0;
        System.out.println("Enter data to add");
        System.out.println("City: ");
        city = sc.nextLine();
        city = city.replace(" ","");
        if (!Character.isLetter(city.charAt(0))) System.out.println("INVALID CITY"); //if city doesn't start with a letter
        else {
            while(true) {
                System.out.println("Population: ");
                pop = sc.nextInt();
                if (pop < 0) {
                    System.out.println(vetData); //if negative don't add data
                    break;
                }
                System.out.println("Robbery: ");
                rob = sc.nextInt();
                if (rob < 0) {
                    System.out.println(vetData);
                    break;
                }
                System.out.println("Burglary: ");
                burg = sc.nextInt();
                if (burg < 0) { 
                    System.out.println(vetData);
                    break;
                }
                System.out.println("Arson: ");
                ars = sc.nextInt();
                if (ars < 0) { 
                    System.out.println(vetData);
                    break; 
                }
                data = city +" "+ String.valueOf(pop) +" "+ String.valueOf(rob) +" "+ String.valueOf(burg) +" "+ String.valueOf(ars); 
                //creates one string separated by a space so it can be split later 
                break;
            }
        }
        return data;
    }
    public boolean openFile(String dataFile) { //opens file, reads through line by line, stores in arr[]
        //returns true if the file could be opened
        try {
            if (!Character.isDigit(dataFile.charAt(0))) {
                System.out.println("INVALID YEAR"); //if year doesnt start with a number
                return false;
            }
            File file = new File(dataFile);
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {
                add(in.nextLine());
            }
            return true;
        }
        catch (Exception e) {
            System.out.println("FILE NOT FOUND");
            return false;
        }
    }
    public void writeToFile(String dataFile) throws Exception { //saves to file
        FileWriter writer = new FileWriter(dataFile);
        for(int i = 0; i < current; i++) {
            writer.write(get(i) + "\n");
        }
        writer.close();
    }
    public void add(String data) { 
        //adds to arr, increases capacity by using a temp array if arr runs out of space
        //long start = System.nanoTime();
        if (current == capacity) {
            String temp[] = new String[2 * capacity];
            for (int i = 0; i < capacity; i++) {
                temp[i] = arr[i];
            }   
            capacity *= 2;
            arr = temp;
        }
        arr[current] = data;
        current++;
        //long end = System.nanoTime();
        //System.out.println("Array: " + (end-start) + " nanoseconds");
    }
    public void remove(int index) { //removes data at a given index by shifting array over one
        index -= 1;
        //long start = System.nanoTime();
        if (index < 0 || index > current-1) { System.out.println("INVALID INDEX"); }
        else {
            for (int i = index; i < capacity-1; i++) {
                arr[i] = arr[i+1];
            }
            current--;
            System.out.println("DATA DELETED"); 
        } 
        //long end = System.nanoTime();
        //System.out.println("Array: " + (end-start) + " nanoseconds");
    }
    public String get(int index) { //returns a string with data: city/pop/burg/rob/ars
        if (index < current) return arr[index];
        return "-1";
    }
    public void print() { //prints table of data
        System.out.println("Index: City               Population          Robbery        Burglary       Arson");
        System.out.println("------------------------------------------------------------------------------------");
        //loops for formating table
        for (int i = 0; i < current; i++) {
            String[] part = arr[i].split(" ");
            if (i < 9) System.out.print((i+1) + ":    " + part[0]);
            else System.out.print((i+1) + ":   " + part[0]);
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
        System.out.println();
    }
    //Bubble sort: sorting a small array
    //O(N^2)
    //Alphabetically
    public void sort() {
        for(int a = 0; a < current -1 ; a++) {
            for(int b = a + 1; b < current; b++) {
                if(arr[a].compareTo(arr[b]) > 0) {
                    String temp = arr[a];
                    arr[a] = arr[b];
                    arr[b] = temp;
                }   
            }      
        }
    }
    //Bubble sort
    //Reverse alphabetically
    public void reverseSort() {
        for(int a = 0; a < current -1 ; a++) {
            for(int b = a + 1; b < current; b++) {
                if(arr[a].compareTo(arr[b]) < 0) {
                    String temp = arr[a];
                    arr[a] = arr[b];
                    arr[b] = temp;
                }   
            }      
        }
    }

    //Bubble sort
    //Compares population/sorts high to low
    public void popSort() {
        int a = 0, b = 0; 
        for (int i = 0; i < current-1; i++) {
            for (int j = i + 1; j < current; j++) {
                String[] part1 = arr[i].split(" ");
                String[] part2 = arr[j].split(" ");
                a = Integer.parseInt(part1[1]);
                b = Integer.parseInt(part2[1]);
                if (a < b) {
                    String temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    //Bubble sort
    //Compares population/sorts low to high
    public void popReverse() {
        int a = 0, b = 0; 
        for (int i = 0; i < current-1; i++) {
            for (int j = i + 1; j < current; j++) {
                String[] part1 = arr[i].split(" ");
                String[] part2 = arr[j].split(" ");
                a = Integer.parseInt(part1[1]);
                b = Integer.parseInt(part2[1]);
                if (a > b) {
                    String temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    //Searches for city because it's unique to the data
    //Linear search O(N)
    //Reports time complexity
    public boolean search(String str) {
        boolean found = false;
        int count = 0;
        long start = System.nanoTime();
        for (int i = 0; i < current; i++) {
            count++;
            String[] part = arr[i].split(" ");
            if(part[0].equalsIgnoreCase(str)) { //if found city
                found = true;
                this.city = part[0];
                this.pop = Integer.parseInt(part[1]);
                this.rob = Integer.parseInt(part[2]);
                this.burg = Integer.parseInt(part[3]);
                this.ars = Integer.parseInt(part[4]);
                break;
            }
        }
        long end = System.nanoTime();
        System.out.println("Array: " + (end-start) + " nanoseconds");
        if (found == true) {
            System.out.println("\n" + this.city + " at index " + count);
            System.out.println("Population: " + this.pop);
            System.out.println("Robberies: " + this.rob);
            System.out.println("Burglaries: " + this.burg);
            System.out.println("Arsons: " + this.ars);
        }
        return found;
    }
}
