import javax.swing.*;
import javax.xml.bind.SchemaOutputResolver;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class Main {
    private static Scanner in = new Scanner(System.in);
    private static boolean needsToBeSaved = false;
    private static ArrayList<String> myArrList = new ArrayList<>();  // note the diamond notation on the type parameter <>

    //Void:- it is a return type that is it does not return any value.
    public static void main(String[] args) {
        boolean quit = false;
        while (!quit) {
            //Display menu
            displayMenu();
            String userChoice = SafeInput.getRegExString(in, "Enter your choice", "[AaDdVvQqOoSsCcQq]");
            if (userChoice.equalsIgnoreCase("A")) {
                addItem();
            } else if (userChoice.equalsIgnoreCase("D")) {
                deleteItem();
            } else if (userChoice.equalsIgnoreCase("v")) {
                printList();
            } else if (userChoice.equalsIgnoreCase("O")) {
                openList();
            } else if (userChoice.equalsIgnoreCase("S")) {
                saveList();
            } else if (userChoice.equalsIgnoreCase("C")) {
                clearList();
            } else if (userChoice.equalsIgnoreCase("Q")) {
                quitProgram();
                System.exit(0);
            } else {
                System.out.println("You get other");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Menu Options:");
        System.out.println("A – Add an item to the list");
        System.out.println("D – Delete an item from the list");
        System.out.println("V – View (display) the list");
        System.out.println("Q – Quit the program");
        System.out.println("O – Open a list file from disk");
        System.out.println("S – Save the current list file to disk");
        System.out.println("C – Clear removes all the elements from the current list");
    }

    ////Insert an element at location m
    private static void addItem() {
        System.out.println("Enter the item to add: ");
        String item = in.nextLine();
        if (myArrList.size() < 1) {
            myArrList.add(item);
        } else {
            if (SafeInput.getYNConfirm(in, "Do you want to rearrange the item?")) {
                int m = SafeInput.getRangedInt(in, "Enter the location of item you want to add: ", 1, myArrList.size()) - 1;
                in.nextLine(); // Consume the leftover newline character after reading the integer
                //When enter an integer, the Scanner consumes only the number and leaves the newline character (\n) in the input buffer.
                //using in.nextLine() to read the item, it reads the leftover newline character instead of waiting for  input.
                //To fix this, can add an additional in.nextLine() after reading the integer to consume the leftover newline character before reading the item.
                myArrList.add(m, item);

            } else {
                myArrList.add(item);
            }
        }
        needsToBeSaved = true;
        System.out.println(myArrList);
    }

    private static void deleteItem() {
        if (myArrList.isEmpty()) {
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }
        int m = SafeInput.getRangedInt(in, "Enter the location of item you want to delete: ", 1, myArrList.size()) - 1;
        myArrList.remove(m); //Overwrite or replace an item at index m:
        in.nextLine();
        needsToBeSaved = true;
        System.out.println(myArrList);
    }

    private static void printList() {
        if (myArrList.isEmpty()) {
            System.out.println("The list is empty. Nothing to print.");
        } else {
            System.out.println("Current list: ");
            System.out.println(myArrList);
        }
    }

    private static void quitProgram() {
        if (needsToBeSaved) {
            if (SafeInput.getYNConfirm(in, "Current list is not saved. Save before quitting?"))
            {saveList();}
        }
        if (SafeInput.getYNConfirm(in, "Are you sure you want to quit? (Y/N): "))
        {System.out.println("Goodbye!");}
            in.close();
        }

    private static void openList() {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        if (needsToBeSaved && !myArrList.isEmpty()) {
            System.out.println("There is unsaved list or list is empty.");
            return;
        }
        try {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {//After the user selects a file and the dialog is closed, this line retrieves the selected file and stored in the "selectedFile"
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while (reader.ready()) {
                    String rec = reader.readLine();
                    System.out.printf("%-1s\n", rec);
                }
                reader.close();
                needsToBeSaved = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void saveList() {
        if (myArrList.isEmpty()) {
            System.out.println("The list is empty. Nothing to save.");
            return;
        }
        if (!needsToBeSaved) {
            System.out.println("No changes, nothing to save.");
            return;
        }
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\MyArrayList.txt");
        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            for (String rec : myArrList) {
                writer.write(rec,0,rec.length());
                writer.newLine();
            }
            writer.close();
            needsToBeSaved = false;
            System.out.println("List saved");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void clearList () {
        if(!myArrList.isEmpty()){
            myArrList.clear();
            needsToBeSaved = true;
            System.out.println("The list has been cleared.");
            deleteListFile();
        } else {
            System.out.println("The list is already empty.");
        }
    }
    private static void deleteListFile() {
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\MyArrayList.txt");
        try {
            Files.deleteIfExists(file);
            System.out.println("List file deleted");
            needsToBeSaved=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}