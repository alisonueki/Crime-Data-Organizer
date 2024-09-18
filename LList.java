import java.util.Scanner;
import java.io.File;
import java.time.Clock;
import java.time.Duration;
import java.io.FileWriter; 
/*
 * Class to create a singly linked list. Reads from a file and has methods to modify and search list.
 * isEmpty() checks if head is null
 * sizeList() loops through list and returns size
 * printList() prints data in the list as a string
 * clear() sets head/tail to null
 * fileToList() clears list, then reads from a new file and adds to list
 * closeList() reads from list into a file
 * addToList() adds to the tail
 * removeList() deletes at a given index
 * searchList() loops through list until the city is found and reports time in nanoseconds
 */
public class LList {   
    public class Node { 
        String value;
        Node next;
        Node(String val, Node n) {
            value = val;
            next = n;
        }
        Node (String val) {
            this(val, null);
        }
    }
    public Node first;
    public Node last;
    public LList() {
        first = null;
        last = null;
    }
    public boolean isEmpty() { //checks if list is empty
        return first == null;
    }
    public int sizeList() { //loops through list, returns size
        int count = 0;
        Node p = first;
        while (p != null) {
            count++;
            p = p.next;
        }
        return count;
    }
    public void printList() { //Prints list
        Node p = first;
        int i = 0;
        System.out.println("LINKED LIST: ");
        if (p == null) System.out.println("LIST EMPTY");
        System.out.println("Index: City               Population          Robbery        Burglary       Arson");
        System.out.println("------------------------------------------------------------------------------------");
        //loops for formating table
        while(p != null) {
            String[] part = p.value.split(" ");
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
            p = p.next;
            i++;
        }
        System.out.println();
    }
    public void fileToList(String dataFile) { //loops through file and adds to list
        clear();
        int count = 0;
        try {
            File file = new File(dataFile);
            Scanner in = new Scanner(file);
            while(in.hasNextLine()) {
                addToList(in.nextLine());
                count++;
            }
        }
        catch (Exception e) {
            return;
        }
    }
    public void closeList(String dataFile) throws Exception { //saves to file
        Node p = first;
        FileWriter writer = new FileWriter(dataFile);
        while(true) {
            writer.write(p.value + "\n");
            p = p.next;
            if (p == null) break;
        }
        writer.close();
    }
    public void addToList(String e) { //adds to linked list
        //long start = System.nanoTime();
        if (isEmpty()) { //if empty create new node and head/tail point to value
            first = new Node(e);
            last = first;
        }
        else { //if not empty add to tail
            last.next = new Node(e);
            last = last.next;
        }
        //long end = System.nanoTime();
        //System.out.println("Linked list: " + (end-start) + " nanoseconds");
    }
    public void clear() { //clears list to change file
        first = null;
        last = null;
    }
    public void removeList(int index) { //removes data from linked list
        index -= 1; 
        //long start = System.nanoTime();
        if (index < 0 || index > sizeList()-1) { //vets index
            return;
        }
        else {
            String element;
            if (index == 0) { //if deleting fom head
                element = first.value;
                first = first.next; //moves head to next 
                if (first == null) last = null; //is list is empty head/tail are null
            }
            else {
                Node pred = first; 
                for (int k = 1; k <= index -1; k++) { //loops through list to find index 
                    pred = pred.next;
                }
                element = pred.next.value;
                pred.next = pred.next.next; 
                if (pred.next == null) last = pred; //if at the end of list
            }
        }
        // long end = System.nanoTime();
        //System.out.println("Linked list: " + (end-start) + " nanoseconds");
    }
    public boolean searchList(String str) { //searches list for the city, reports time complexity
        System.out.println();
        boolean found = false;
        long start = System.nanoTime();
        Node curr = first;
        while (curr != null) { //loops through list
            String[] parts = curr.value.split(" "); //splits string into an array to use
            if (parts[0].equalsIgnoreCase(str)) { 
                found = true;
                break;
            } 
            curr = curr.next;
        }
        long end = System.nanoTime();
        System.out.println("Linked list: " + (end-start) + " nanoseconds");
        return found;
    }
}
