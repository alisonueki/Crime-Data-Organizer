//Alison Ueki
import java.util.Scanner;
import java.io.FileWriter;
/*
 * Main is the UI. It uses DataArray, DataCalculations, and LList.
 * DataArray holds the array of data and the methods from any modifications to the array.
 * DataCalculations used DataArray. It does calulations using diiference, avg, avgYears, perPeron, and topFive.
 * LList is a singly linked list that adds, deleted, and searches along with the array.
 * You are prompted to enter a year that will find and open a file. The main menu is opened in a loop.
 * Options 2, 3, and 5 open new menus with more options
 */

public class Main {
    public static void main(String[] args) throws Exception{
        DataArray array = new DataArray();
        DataCalculations arrData = new DataCalculations();
        LList list = new LList();
        HashTable hash = new HashTable(); 
        Scanner scan = new Scanner(System.in);
        int choice = 0;
        String dataFile = "File not open";
        String trash;
        while (true) { //takes year input and adds to array/list
            System.out.println("Enter year: ");
            dataFile = scan.nextLine(); 
            dataFile = dataFile.replace(" ","") + ".txt";
            boolean open = array.openFile(dataFile); //adds to array 
            list.fileToList(dataFile); //adds to list
            if (open == true) break;
        }
        while (true) { 
            //MAIN MENU
            System.out.println();
            System.out.println("Current file: " + dataFile);
            System.out.println("1) View data");
            System.out.println("2) Modify data"); //Add,Delete,Change file
            System.out.println("3) Data calculations"); //Difference,Avg,Avg of two years, Per 1000
            System.out.println("4) Top five");
            System.out.println("5) Lowest five");
            System.out.println("6) Sort"); //Alphabetically/reverse, Population/reverse 
            System.out.println("7) Search");
            System.out.println("8) Create a list"); //Add,Delete,Print using a hash table 
            System.out.println("9) Quit");

            try {
                choice = scan.nextInt();
                if (choice < 1 || choice > 9) System.out.println("INVALID INPUT");
                if (choice == 1) { //VIEW DATA
                    array.print();
                    //list.printList();
                }
                else if (choice == 2) { //MODIFY DATA
                    while (true) { //loop to a new menu
                        System.out.println("Current file: " + dataFile);
                        System.out.println("1) Add data"); 
                        System.out.println("2) Delete data");
                        System.out.println("3) Add new year");
                        System.out.println("4) Change year");
                        System.out.println("5) Menu");
                        int n = scan.nextInt();
                        if (n < 1 || n > 5) System.out.println("INVALID INPUT");
                        if (n == 1) { //ADD DATA
                            String newData = array.addCity();
                            if (newData != "No data") {
                                array.add(newData); //adds to array
                                list.addToList(newData); //adds to list
                                System.out.println("DATA ADDED");
                                array.writeToFile(dataFile);
                                //list.closeList(dataFile);
                            }
                            else System.out.println("Data not added to file");
                        }
                        else if (n == 2) { //DELETE DATA
                            array.print();
                            System.out.println("Enter an index between 1-" + array.size() + " to delete:");
                            String s = scan.nextLine();
                            int index = scan.nextInt();
                            array.remove(index); //removes from array
                            list.removeList(index); //removes from list
                        }
                        else if (n == 3) { //ADD YEAR
                            array.writeToFile(dataFile);
                            //list.closeList(dataFile);
                            trash = scan.nextLine();
                            while(true) { //reads a year
                                System.out.println("Enter new year "); 
                                dataFile = scan.nextLine(); 
                                if (Character.isDigit(dataFile.charAt(0))) break;
                                else System.out.println("INVALID YEAR");
                            }
                            dataFile = dataFile.replace(" ","") + ".txt";
                            DataArray newArray = new DataArray();
                            LList newList = new LList();
                            String newData = newArray.addCity(); //goes into a loop to collect data
                            if (newData != "No data") {
                                newArray.add(newData); //adds data
                                System.out.println("DATA ADDED");
                                newArray.writeToFile(dataFile);
                            }
                            else System.out.println("Data not added to file");
                        }
                        else if (n == 4) { //CHANGE FILE 
                            array.writeToFile(dataFile); //saves to file
                            //list.closeList(dataFile);
                            String oldFile = dataFile;
                            DataArray array2 = new DataArray(); //new object to switch files
                            System.out.println("Enter year: ");
                            trash = scan.nextLine();
                            dataFile = scan.nextLine(); 
                            dataFile = dataFile.replace(" ","") + ".txt";
                            boolean open = array.openFile(dataFile); // add to array 
                            //list.fileToList(dataFile); //adds new file to list
                            if (open == false) {
                                dataFile = oldFile; //if the new file doesn't open keep the old file
                                array.openFile(dataFile);
                                //list.fileToList(dataFile); //adds new file to list
                            }
                        }
                        else if (n == 5) break; //BACK TO MENU
                    }
                    array.writeToFile(dataFile);//saves to file
                    //list.closeList(dataFile);
                }
                else if (choice == 3) { //DIFFERENCE OF TWO CITIES
                    while(true) { //loops to new menu
                        System.out.println("1) Difference of two cities");
                        System.out.println("2) Average data");
                        System.out.println("3) Average of two years");
                        System.out.println("4) Per 1000 people");
                        System.out.println("5) Menu");
                        int n = scan.nextInt();
                        if (n < 1 || n > 5) System.out.println("INVALID INPUT");
                        if (n == 1) { //DIFFERENCE
                            int inx1 = 0, inx2 = 0;
                            array.print();
                            System.out.println("Enter an index between 1-" + array.size() + ":");
                            inx1 = scan.nextInt();
                            if (inx1-1 > array.size()-1 || inx1-1 < 0) { //vets first index
                                System.out.println("INVALID INDEX");
                            }
                            else { 
                                System.out.println("Enter a second index between 1-" + array.size() + ":");
                                inx2 = scan.nextInt();
                                if (inx2-1 > array.size()-1 || inx2-1 < 0) System.out.println("INVALID INDEX"); //vets second index
                                else arrData.difference(inx1, inx2); //returns report 
                            }
                        }
                        if (n == 2) arrData.avg(dataFile); //AVERAGE OF A YEAR
                        if (n == 3) { //AVG OF TWO YEARS 
                            System.out.println("Enter first year: ");
                            trash = scan.nextLine();
                            String file = scan.nextLine(); 
                            System.out.println("Enter second year: ");
                            String file2 = scan.nextLine(); 
                            arrData.avgYears(file, file2); //AVERAGE OF BOTH YEARS
                        }
                        if (n == 4) { //PER 1000 PEOPLE
                            int inx = 0;
                            array.print();
                            System.out.println("Enter an index between 1-" + array.size() + ":");
                            inx = scan.nextInt();
                            if (inx-1 >= 0 && inx-1 <= array.size()-1) arrData.perPerson(inx); //returns report
                            else System.out.println("INVALID INDEX");
                        }
                        if (n == 5) break; //BACK TO MENU
                    }
                }
                else if (choice == 4) { //TOP FIVE
                    arrData.topFive();
                }
                else if (choice == 5) { //LOWEST FIVE
                    arrData.lowestFive();
                }
                else if (choice == 6) { //SORT DATA
                    System.out.println("1) Alphabetical");
                    System.out.println("2) Reverse alphabetical");
                    System.out.println("3) Population low to high");
                    System.out.println("4) Population high to low");
                    int num = scan.nextInt();
                    if (num < 1 || num > 4) System.out.println("INVALID INPUT");
                    else if (num == 1) { //alphabetically
                        array.sort();
                        array.print();
                    }
                    else if (num == 2) { //reverse alphabetically
                        array.reverseSort(); 
                        array.print();
                    }
                    else if (num == 3) { //population low to high
                        array.popReverse();
                        array.print();
                    }
                    else if (num == 4) { //population high to low
                        array.popSort();
                        array.print();
                    }
                    array.writeToFile(dataFile);
                    //list.closeList(dataFile);
                }
                else if (choice == 7) { //SEARCH 
                    DataArray array2 = new DataArray(); //new object to open file
                    trash = scan.nextLine();
                    while(true) {
                        System.out.println("Enter year: ");
                        dataFile = scan.nextLine(); 
                        dataFile = dataFile.replace(" ","") + ".txt";
                        boolean open = array.openFile(dataFile);
                        if (open == true) { 
                            list.fileToList(dataFile);
                            break;
                        }
                    }
                    System.out.println("Enter city to search:");
                    String s = scan.nextLine(); //reads city
                    if (!Character.isLetter(s.charAt(0))) System.out.println("INVALID CITY"); //vet city input
                    else { 
                        list.searchList(s); //search linked list, report time
                        array2.search(s); //search array, report time
                    }
                    array2.writeToFile(dataFile);
                }
                else if (choice == 8) { //CREATE A LIST
                    while(true) {
                        System.out.println("1) Add data"); 
                        System.out.println("2) Delete data");
                        System.out.println("3) Print");
                        System.out.println("4) Menu");
                        int input = scan.nextInt();
                        if (input < 1 || input > 4) System.out.println("INVALID INPUT");
                        else if (input == 1) { //add to hash table
                            if (hash.size() >= 8) System.out.println("List is full"); //capacity is 8
                            else { 
                                array.print(); 
                                System.out.println("Enter an index to add: ");
                                int index = scan.nextInt();
                                if (index > array.size() || index <= 0) System.out.println("INVALID INDEX");
                                else hash.addToHash(array.get(index-1)); //adds to hash table
                            }
                        }
                        else if (input == 2) { //delete from hash table
                            System.out.println("Enter city to delete:");
                            trash = scan.nextLine();
                            String s = scan.nextLine(); //reads city
                            if (!Character.isLetter(s.charAt(0))) System.out.println("INVALID CITY"); //vet city input
                            else hash.deleteTable(s);
                        }
                        else if (input == 3) hash.printTable(); //print hash table
                        else if (input == 4) { //back to menu
                            hash.hashToFile("hashTable.txt"); 
                            break;
                        }
                    }
                }
                else if (choice == 9) { //QUIT
                    list.closeList(dataFile);
                    FileWriter writer = new FileWriter(dataFile);
                    for (int i = 0; i < array.size(); i++) { //Loops through array and writes to file 
                        writer.write(array.get(i) + "\n");
                    }
                    writer.close();
                    break; //breaks loop to quit program
                }
            }
            catch (Exception e) { //catches errors thrown
                System.out.println("INVALID INPUT");
                break;
            }
        }
    }
}
