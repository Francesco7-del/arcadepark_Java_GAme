package ARCADE_PARK_DEF.snake__game.model;

public  class BoardSettingsOptions{

    // dimensioni
    public static final int DOT_SIZE = 16;
    public static final int DOTS_NUMBER_PER_DIMENSION = 40;

    //imagini
    public static final String APPLE_IMAGE_LOCATION = "C:\\Users\\User\\Desktop\\aaaa\\aracade_park\\snake__game\\assets\\mela_1.png";
    public static final String SNAKE_DOT_IMAGE_LOCATION = "C:\\Users\\User\\Desktop\\arcadepark_cerenzia_maddocco\\ARCADE_PARK_DEF\\snake__game\\assets\\serp.png";

    //variabili pannello
    private final int dotSize;
    private final int windowSizePerDimension;
    private final int allDotsNumber;

    private final String snakeDotImageLocation;
    private final String appleImageLocation;

    //costruttore
    public BoardSettingsOptions(
            int dotSize,
            int dotNumberPerDimension,
            String snakeDotImageLocation,
            String appleImageLocation) {
           this.dotSize = dotSize;

        windowSizePerDimension = this.dotSize * dotNumberPerDimension;
        allDotsNumber = (int) Math.pow(dotNumberPerDimension, 2);

        this.snakeDotImageLocation = snakeDotImageLocation;
        this.appleImageLocation = appleImageLocation;
    }
    //ottiene la dimensione del punto
    public int getDotSize() {
        return dotSize;
    }
    //ottiene dimensione finestra
    public int getWindowSizePerDimension() {
        return windowSizePerDimension;
    }
    //ottiene tutti i numeri del piano
    public int getAllDotsNumber() {
        return allDotsNumber;
    }
    //ottiene la posizione del serpente nel piano
    public String getSnakeDotImageLocation() {
        return snakeDotImageLocation;
    }
    //ottiene la posizione della mela nel piano
    public String getAppleImageLocation() {
        return appleImageLocation;
    }
}
