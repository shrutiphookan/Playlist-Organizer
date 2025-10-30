package com.playlist;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private List<Song> songs = new ArrayList<>();

    public void addSong(String title, String artist) {
        songs.add(new Song(songs.size() + 1, title, artist));
        System.out.println("✅ Song added!");
    }

    public void removeSong(int id) {
        if (id <= 0 || id > songs.size()) {
            System.out.println("❌ Invalid song ID!");
            return;
        }
        songs.remove(id - 1);
        // reassign IDs to keep them sequential
        for (int i = 0; i < songs.size(); i++) {
            Song s = songs.get(i);
            // create new Song object with updated id
            songs.set(i, new Song(i + 1, s.getTitle(), s.getArtist()));
        }
        System.out.println("🗑️ Song removed!");
    }

    public void listSongs() {
        if (songs.isEmpty()) {
            System.out.println("📂 Playlist is empty!");
        } else {
            System.out.println("\nYour Playlist:");
            for (Song s : songs) {
                System.out.println(s);
            }
        }
    }

    public void saveToFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Song s : songs) {
                // write CSV: id,title,artist (commas in data replaced)
                pw.println(s.getId() + "," + s.getTitle().replace(",", ";") + "," + s.getArtist().replace(",", ";"));
            }
            System.out.println("💾 Playlist saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving playlist: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            System.out.println("No saved playlist found (" + filename + ")");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            songs.clear();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length >= 3) {
                    int id = Integer.parseInt(parts[0].trim());
                    String title = parts[1];
                    String artist = parts[2];
                    songs.add(new Song(id, title, artist));
                }
            }
            // ensure IDs are sequential starting at 1
            for (int i = 0; i < songs.size(); i++) {
                Song s = songs.get(i);
                songs.set(i, new Song(i + 1, s.getTitle(), s.getArtist()));
            }
            System.out.println("📂 Playlist loaded from " + filename);
        } catch (IOException e) {
            System.out.println("Error loading playlist: " + e.getMessage());
        }
    }
}
