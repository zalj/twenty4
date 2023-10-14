package soundsystem;

import java.util.List;

public class BlankDisk implements CompactDisc {
    private String title;
    private String artist;
    private List<String> tracks;

    public BlankDisk(String title, String artist, List<String> tracks) {
        this.title = title;
        this.artist = artist;
        this.tracks = tracks;
    }

    @Override
    public void play() {
        System.out.println("Playing " + title + " By " + artist);
        for (String track : tracks) {
            System.out.println("-Track: " + track);
        }
    }
}
