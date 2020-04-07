/*
Title: Chatter
Description:
   Chatter is an online chat system that utilizes a php based
   website to create, save, and record chat rooms and messages.
Developers:
       Alec Blance
       Almira Ruby Montalvo
*/

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static URL url;
    public static String name = "Anonymous", room = "", domain = "DOMAIN";
    public static String[] dir = {"txt/", "?name=", "?check=", "?name1=", "&message=", "&chatter=", "&connected="
                                  , "&disconnected=", "?name2="};
    public static BufferedReader reader;

    public static void clear() throws Exception {
        /*
        Clear function here is optional
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
        */
    }

    public static void roomChecker() throws Exception {
        String newRoom = room.replaceAll("\\s+", "_");
        url = new URL(domain + dir[2] + newRoom);
        reader = new BufferedReader(new InputStreamReader(url.openStream()));
        clear();
        if (reader.readLine() == null) {
            System.out.println("Room do not exist.\n");
            reChoice(1);
        }
    }

    public static void reChoice(int intChoice) throws Exception {
        String[] choices = {"(/b) Back to menu", "(/e) Exit"};
        Scanner input = new Scanner(System.in);
        String choice;
        if (intChoice == 0) {
            System.out.println("Sorry? ");
            System.out.print(choices[0] + " " + choices[1]);
        } else if (intChoice == 1) {
            System.out.print(choices[0] + " " + choices[1]);
        }
        System.out.print("\n> ");
        choice = input.nextLine();
        if (choice.equals("/b")) {
            banner();
        } else if (choice.equals("/e")) {
            System.exit(0);
        } else {
            clear();
            reChoice(0);
        }
    }

    public static String reChoice2(int num) throws Exception {
        if (num == 0) {
            reChoice(1);
        }
        String newRoom = room.replaceAll("\\s+", "_");
        String newName = name.replaceAll("\\s+", "%20");
        Scanner input = new Scanner(System.in);
        System.out.print("(/b) Back to menu (/s) Save to computer (/r) Refresh\n" + name + " > ");
        String choice = input.nextLine();
        if (choice.equals("/b")) {
            url = new URL(domain + dir[8] + newRoom + dir[7] + newName);
            url.openStream();
            banner();
        } else if (choice.equals("/r")) {
            Room();
        } else if (choice.equals("/s")) {
            save(1);
        }
        return choice;
    }

    public static void Room() throws Exception {
        clear();
        String line, choice, newRoom = room.replaceAll("\\s+", "_");
        String newName = name.replaceAll("\\s+", "%20");
        System.out.println("---- " + room + " ----");
        url = new URL(domain + dir[0] + newRoom);
        reader = new BufferedReader(new InputStreamReader(url.openStream()));
        while ((line = reader.readLine()) != null) {
            System.out.print(line + "\n");
        }
        System.out.println("");
        choice = reChoice2(1);
        url = new URL(domain + dir[3] + newRoom + dir[4] + choice.replaceAll("\\s+", "%20") + dir[5] + newName);
        url.openStream();
        reader.close();
        Room();
    }

    public static void createRoom() throws Exception {
        Scanner input = new Scanner(System.in);
        String newRoom, newName = name.replaceAll("\\s+", "%20");
        System.out.print("Room name: ");
        room = input.nextLine();
        newRoom = room.replaceAll("\\s+", "_");
        url = new URL(domain + dir[2] + newRoom);
        reader = new BufferedReader(new InputStreamReader(url.openStream()));
        clear();
        if (reader.readLine() != null) {
            System.out.println("Room already exists.\n");
            reChoice(1);
        }
        url = new URL(domain + dir[1] + newRoom);
        url.openStream();
        url = new URL(domain + dir[8] + newRoom + dir[6] + newName);
        url.openStream();
        reader.close();
        Room();
    }

    public static void connect() throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Room name: ");
        room = input.nextLine();
        String newRoom = room.replaceAll("\\s+", "_"), newName = name.replaceAll("\\s+", "%20");
        roomChecker();
        url = new URL(domain + dir[8] + newRoom + dir[6] + newName);
        url.openStream();
        reader.close();
        Room();
    }

    public static void changeName() throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("New name: ");
        name = input.nextLine();
        banner();
    }

    public static void save(int choice) throws Exception {
        String line;
        if (choice == 0) {
            Scanner input = new Scanner(System.in);
            System.out.print("Room to save: ");
            room = input.nextLine();
            roomChecker();
        }
        String newRoom = room.replaceAll("\\s+", "_");
        FileWriter file = new FileWriter(newRoom + ".txt", true);
        url = new URL(domain + dir[0] + newRoom);
        reader = new BufferedReader(new InputStreamReader(url.openStream()));
        while ((line = reader.readLine()) != null) {
            file.write(line + "\n");
        }
        file.close();
        clear();
        System.out.println("Saved!\n");
        reChoice2(choice);
    }

    public static void banner() throws Exception {
        int choice;
        Scanner input = new Scanner(System.in);
        clear();
        System.out.println("   ___ _         _   _           ");
        System.out.println("  / __| |_  __ _| |_| |_ ___ _ _ ");
        System.out.println(" | (__| ' \\/ _` |  _|  _/ -_) '_|");
        System.out.println("  \\___|_||_\\__,_|\\__|\\__\\___|_|  ");
        System.out.println("                                 ");
        System.out.println(" Hello " + name + "!\n");
        System.out.println(" (1) Create a room");
        System.out.println(" (2) Connect to a room");
        System.out.println(" (3) Change name");
        System.out.println(" (4) Download copy of chat");
        System.out.println(" (5) Exit");
        System.out.print("\n > ");
        try {
            choice = input.nextInt();
            if (choice == 1) {
                createRoom();
            } else if (choice == 2) {
                connect();
            } else if (choice == 3) {
                changeName();
            } else if (choice == 4) {
                save(0);
            } else if (choice == 5) {
                System.exit(0);
            }
            input.close();
        } catch (Exception e) {
            System.out.println("Sorry I don't understand");
            System.exit(0);
        }
    }

    public static void main(String[] args) throws Exception {
        banner();
    }
}
