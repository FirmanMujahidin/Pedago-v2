package com.pedago2.Moel;

/**
 * Created by firma on 23-Oct-17.
 */

public class MapelModel {

    private String title_judl;
    private String title_play_video;

    public MapelModel(String title_judl, String title_play_video) {
        this.title_judl = title_judl;
        this.title_play_video = title_play_video;
    }


    public MapelModel(){

    }

    public String getTitle_judl() {
        return title_judl;
    }

    public void setTitle_judl(String title_judl) {
        this.title_judl = title_judl;
    }

    public String getTitle_play_video() {
        return title_play_video;
    }

    public void setTitle_play_video(String title_play_video) {
        this.title_play_video = title_play_video;
    }
}
