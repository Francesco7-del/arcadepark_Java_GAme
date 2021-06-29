package ARCADE_PARK_DEF.space;

import java.awt.image.BufferedImage;

public class PlayableShip {
    private final BufferedImage body;
    private final int width;
    private final int height;

    public PlayableShip(BufferedImage body, int width, int height){
        this.body = body;
        this.width = width;
        this.height = height;
    }

    public BufferedImage getBody() {
        return body;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
