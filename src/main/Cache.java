package main;

import java.util.ArrayList;

/**
 * Created by carlos, alberto, pablo y ruben on 23/02/17.
 */

public class Cache {
    private final int id;
    private final int size;
    private int freeSize;
    private ArrayList<Video> videos = new ArrayList<>();

    public Cache (int id, int size) {
        this.id = id;
        this.size = size;
        this.freeSize = size;
    }

    public int getId() {
        return id;
    }

    public int getFreeSize() {
        return freeSize;
    }

    public int getSize() {
        return size;
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }


    public void addVideo (Video video) {
        videos.add(video);
        freeSize = freeSize - video.getSize();
    }

    public boolean isThere(Video video) {
        return videos.contains(video);
    }
}