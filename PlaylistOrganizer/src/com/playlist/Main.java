package com.playlist;

import java.util.Scanner;

public class Main {
    private static final String DATA_FILE = "playlist.csv";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Playlist playlist = new Playlist();

        // try loading existing file (if present)
        playlist.loadFromFile(DATA_FILE);

        while (true) {
            System.out.println("\n🎵 Playlist Organizer Menu 🎵");
            System.out.println("1) Add Song");
            System.out.println("2) Remove Song by ID");
            System.out.println("3) Show Playlist");
            System.out.println("4) Save Playlist to file");
            System.out.println("5) Load Playlist from file");
            System.out.println("0) Exit");
            System.out.print("Enter your choice: ");

            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println("Please enter a number.");
                continue;
            }
            int choice;
            try {
                choice = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input - please type a number.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter song title: ");
                    String title = sc.nextLine().trim();
                    System.out.print("Enter artist: ");
                    String artist = sc.nextLine().trim();
                    playlist.addSong(title, artist);
                }
                case 2 -> {
                    System.out.print("Enter song ID to remove: ");
                    String idLine = sc.nextLine().trim();
                    try {
                        int id = Integer.parseInt(idLine);
                        playlist.removeSong(id);
                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid ID.");
                    }
                }
                case 3 -> playlist.listSongs();
                case 4 -> playlist.saveToFile(DATA_FILE);
                case 5 -> playlist.loadFromFile(DATA_FILE);
                case 0 -> {
                    System.out.println("👋 Exiting. Saving before exit...");
                    playlist.saveToFile(DATA_FILE);
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
