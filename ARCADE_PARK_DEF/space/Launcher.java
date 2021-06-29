package ARCADE_PARK_DEF.space;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Launcher implements Runnable {
    private ArrayList<Wave> waves = new ArrayList<>();



    @Override
    public void run() {
        try (Scanner scanner = new Scanner(new FileInputStream("C:\\Users\\User\\Desktop\\aaaa\\aracade_park\\space\\images\\waves.txt"))) {
            while (scanner.hasNextLine()) {
                Wave wave = new Wave();
                String line = scanner.nextLine();
                String foes[] = line.split("/");
                for (String foe : foes
                ) {
                    System.out.println(foe);
                    String foeType = String.valueOf(foe.charAt(0));
                    int position = Integer.parseInt(foe.substring(1, foe.length()));
                    wave.addFoe(foeType, position);
                }
                waves.add(wave);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {
            if (Game.foes.isEmpty()) {
                try {
                    waves.get(0).launch();
                    waves.remove(waves.get(0));
                } catch (Exception e) {

                }
            }
        }
    }

    public void clearWaves(){
        waves.clear();
    }
}
