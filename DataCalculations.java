import java.util.Scanner;
import java.io.File;
/*
 * Methods for doing calculations on array data. Uses DataArray.  
 * difference() takes the index of two cities and reports who has more
 * resetData() resets all data variables to 0
 * addData() loops through file and adds all data, returns false if file could not be opened
 * avg() uses addData and reports the avg of the given year
 * avgYears() takes two files and adds all the data, reports the avg
 * perPerson() takes an index reports data per 1000 people, converting to a double
 * makeQueue() calculates all crime per 1000 people, adds to and returns a queue
 * topFive() calls makeQueue, UI for moving through queue and deleting
 */
public class DataCalculations extends DataArray {

    public void difference(int inx1, int inx2) { //takes the index of two cities, reports how much more a city has
        inx1 -= 1;
        inx2 -= 1;
        int popDiff = 0, robDiff = 0, burgDiff = 0, arsDiff = 0;
        String[] part1 = arr[inx1].split(" "); //splits string into a temp array to use
        String[] part2 = arr[inx2].split(" ");
        System.out.println(part1[0] + " and " + part2[0]);
        //population
        if (Integer.parseInt(part1[1]) > Integer.parseInt(part2[1])) {
            popDiff = Integer.parseInt(part1[1]) - Integer.parseInt(part2[1]);
            System.out.println(part1[0] + " has " + popDiff + " more people."); 
        } else if (Integer.parseInt(part1[1]) < Integer.parseInt(part2[1])) { 
            popDiff = Integer.parseInt(part2[1]) - Integer.parseInt(part1[1]);
            System.out.println(part2[0] + " has " + popDiff + " more people."); 
        } else { 
            System.out.println(part1[0] + " and " + part2[0] + " have an equal population.");
        }
        //robberies
        if (Integer.parseInt(part1[2]) > Integer.parseInt(part2[2])) {
            robDiff = Integer.parseInt(part1[2]) - Integer.parseInt(part2[2]);
            System.out.println(part1[0] + " has " + robDiff + " more robberies."); 
        } else if (Integer.parseInt(part1[2]) < Integer.parseInt(part2[2])) {  
            robDiff = Integer.parseInt(part2[2]) - Integer.parseInt(part1[2]);
            System.out.println(part2[0] + " has " + robDiff + " more robberies."); 
        } else {
            System.out.println(part1[0] + " and " + part2[0] + " have the same amount of robberies.");
        }
        //burglaries
        if (Integer.parseInt(part1[3]) > Integer.parseInt(part2[3])) {
            burgDiff = Integer.parseInt(part1[3]) - Integer.parseInt(part2[3]);
            System.out.println(part1[0] + " has " + burgDiff + " more burglaries."); 
        } else if (Integer.parseInt(part1[3]) < Integer.parseInt(part2[3])) { 
            burgDiff = Integer.parseInt(part2[3]) - Integer.parseInt(part1[3]);
            System.out.println(part2[0] + " has " + burgDiff + " more burglaries."); 
        } else {
            System.out.println(part1[0] + " and " + part2[0] + " have the same amount of burglaries.");
        }
        //arson
        if (Integer.parseInt(part1[4]) > Integer.parseInt(part2[4])) {
            arsDiff = Integer.parseInt(part1[4]) - Integer.parseInt(part2[4]);
            System.out.println(part1[0] + " has " + arsDiff + " more arsons."); 
        } else if (Integer.parseInt(part1[4]) < Integer.parseInt(part2[4])) { 
            arsDiff = Integer.parseInt(part2[4]) - Integer.parseInt(part1[4]);
            System.out.println(part2[0] + " has " + arsDiff + " more arsons."); 
        } else {
            System.out.println(part1[0] + " and " + part2[0] + " have the same amount of arsons.");
        }
    }
    public void resetData() { //resets variables to 0
        this.count = 0;
        this.pop = 0;
        this.rob = 0;
        this.burg = 0;
        this.ars = 0;
    }
    public boolean addData(String fileName) throws Exception { //adds each data point for the given year and keeps a count
        try {
            File file = new File(fileName);
            Scanner cin = new Scanner(file);
            while (cin.hasNextLine()) { //loops though file
                String[] part = cin.nextLine().split(" "); //splits string to use 
                this.pop += Integer.parseInt(part[1]);
                this.rob += Integer.parseInt(part[2]);
                this.burg += Integer.parseInt(part[3]);
                this.ars += Integer.parseInt(part[4]);
                this.count++;
            }
            return true;
        }
        catch (Exception e) {
            System.out.println("FILE NOT FOUND");
            return false;
        }
    }
    public void avg(String fileName) throws Exception { //takes the average of the file by using addData(), gives a report 
        resetData();
        addData(fileName);
        System.out.println("Average of " + this.count + " cities.");
        System.out.println("Population: " + this.pop/this.count);
        System.out.println("Robbery: " + this.rob/this.count);
        System.out.println("Burglary: " + this.burg/this.count);
        System.out.println("Arson: " + this.ars/this.count);
    }
    public void avgYears(String fileName, String fileName2) throws Exception { 
        //calls addData() for each file and gives a report of the avg of both years
        resetData();
        String file = fileName.replace(" ","") + ".txt";
        String file2 = fileName2.replace(" ","") + ".txt";
        boolean f1 = addData(file);
        boolean f2 = addData(file2);
        if (f1 == true && f2 == true) {
            System.out.println("Average of " + this.count + " cities from " + fileName + " and " + fileName2);
            System.out.println("Population: " + this.pop/this.count);
            System.out.println("Robbery: " + this.rob/this.count);
            System.out.println("Burglary: " + this.burg/this.count);
            System.out.println("Arson: " + this.ars/this.count);
        }
    }
    public void perPerson(int index) { //takes an index and reports data per thousand people, int to double
        resetData();
        index -= 1;
        String[] part = arr[index].split(" ");
        String city = part[0];
        this.pop = Integer.parseInt(part[1]);
        this.rob = Integer.parseInt(part[2]);
        this.burg = Integer.parseInt(part[3]);
        this.ars = Integer.parseInt(part[4]);
        System.out.println("Per 1000 people: ");
        System.out.println(city + "'s population " + this.pop);
        String robbery = String.format("%.3f", (this.rob*1000.0)/this.pop);
        System.out.println("Robbery " + robbery);
        String burglary = String.format("%.3f", (this.burg*1000.0)/this.pop);
        System.out.println("Burglary " + burglary);
        String arson = String.format("%.3f", (this.ars*1000.0)/this.pop);
        System.out.println("Arson " + arson);
    }
    public String[] makeQueue(boolean topFive) { //calulates crime per 1000 and stores top five in a queue
        String[] temp = new String[current];
        String[] queue = new String[5];
        int tempSize = 0;
        for (int i = 0; i < current; i++) { //loop through and add new data to temp O(N)
            String[] part = arr[i].split(" ");
            this.city = part[0];
            this.pop = Integer.parseInt(part[1]);
            this.rob = Integer.parseInt(part[2]);
            this.burg = Integer.parseInt(part[3]);
            this.ars = Integer.parseInt(part[4]);
            String decimal = String.format("%.3f",((this.rob + this.burg + this.ars) * 1000.0) / this.pop);
            double avg = Double.parseDouble(decimal);
            temp[i] = this.city + " " + avg;
            tempSize++;
        }
        double a = 0, b = 0;
        if (topFive == true) {
            for ( int i = 0; i < tempSize-1; i++) { //sort high to low O(N^2)
                for (int j = i+1; j < tempSize; j++) {
                    String[] part1 = temp[i].split(" ");
                    String[] part2 = temp[j].split(" ");
                    a = Double.parseDouble(part1[1]);
                    b = Double.parseDouble(part2[1]);
                    if (a < b) {
                        String t = temp[i];
                        temp[i] = temp[j];
                        temp[j] = t;
                    }
                }
            }
        }
            else {
                for ( int i = 0; i < tempSize-1; i++) { //sort low to high O(N^2)
                    for (int j = i+1; j < tempSize; j++) {
                        String[] part1 = temp[i].split(" ");
                        String[] part2 = temp[j].split(" ");
                        a = Double.parseDouble(part1[1]);
                        b = Double.parseDouble(part2[1]);
                        if (a > b) {
                            String t = temp[i];
                            temp[i] = temp[j];
                            temp[j] = t;
                        }
                    }
                }
            }
            for (int i = 0; i < 5; i++) { //add top five into queue
                queue[i] = temp[i];
            } 
            return queue;
        }
        public void lowestFive() { //UI, prints and deletes from the top
            String[] queue = makeQueue(false);
            char input;
            int count = 4, num = 1;
            Scanner cin = new Scanner(System.in);
            System.out.println("\nTop five cities with the highest crime");
            while(count >= 0) { //breaks after 5 loops
                String[] parts = queue[0].split(" ");
                System.out.println(num + ". " + parts[0] + " " + parts[1] + " per 1000 people");
                for (int i = 0; i < count; i++) { //deletes
                    queue[i] = queue[i+1];
                }
                count--;
                num++;
            }
        }
        public void topFive() { //UI, prints and deletes from the top
            String[] queue = makeQueue(true);
            char input;
            int count = 4, num = 1;
            Scanner cin = new Scanner(System.in);
            System.out.println("\nTop five cities with the highest crime");
            while(count >= 0) { //breaks after 5 loops
                String[] parts = queue[0].split(" ");
                System.out.println(num + ". " + parts[0] + " " + parts[1] + " per 1000 people");
                for (int i = 0; i < count; i++) { //deletes
                    queue[i] = queue[i+1];
                }
                count--;
                num++;
            }
        }
    }
