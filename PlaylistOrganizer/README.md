# PlaylistOrganizer (Java)

Simple Java console app to add/remove/list songs and save/load playlist to `playlist.csv`.

## How to run

1. Open the project folder in VS Code.
2. Open Terminal (View → Terminal).
3. Compile:
   ```
   javac -d out src/com/playlist/*.java
   ```
4. Run:
   ```
   java -cp out com.playlist.Main
   ```

The program uses `playlist.csv` in the project root to save and load songs.
