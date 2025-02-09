package com.yugao.avajlauncher.aircraft;

import com.yugao.avajlauncher.exception.InputException;
import com.yugao.avajlauncher.tower.WeatherTower;
import com.yugao.avajlauncher.utils.FileOperator;

import java.util.List;

public class Simulator {

    private List<String> datas;
    private int simulationTime;
    private static WeatherTower weatherTower;

    public Simulator(String filename) throws InputException {

        if (filename == null)
            throw new InputException("File Format Error: The filename is null!!!");
        FileOperator.readFileContent(filename);
        datas = FileOperator.getFileContent();
        if (datas == null)
            throw new InputException("File Format Error: The File Content is null!!!");
        try {
            simulationTime = Integer.parseInt(datas.getFirst());
        } catch (Exception e) {
            throw new InputException("File Format Error: Invalid SimulationTime Number Input!!!");
        }
        if (simulationTime < 0)
            throw new InputException("Simulation Time Error: The Simulation Time is less than 0!!!");
        datas.removeFirst();


        this.createAircrafts();

    }

    private void createAircrafts() throws InputException {
        AircraftFactory aircraftFactory = AircraftFactory.getInstance();
        weatherTower = new WeatherTower();

        if (datas.isEmpty()) {
            throw new InputException("File Format Error: The File Content is null!!!");
        }
        for (String line : datas) {
            String[] craftInfo = line.split(" ");
            if (craftInfo.length != 5)
                throw new InputException("File Format Error: Invalid Number of Arguments Input!!!");
            Coordinates coordinates;
            try {
                if (Integer.parseInt(craftInfo[2]) < 0 || Integer.parseInt(craftInfo[3]) < 0 || Integer.parseInt(craftInfo[4]) < 0
                        || Integer.parseInt(craftInfo[4]) > 100)
                throw new InputException("File Format Error: Invalid Number of Arguments Input!!!");
                coordinates= new Coordinates(Integer.parseInt(craftInfo[2]), Integer.parseInt(craftInfo[3]), Integer.parseInt(craftInfo[4]));
            } catch (Exception e) {
                throw new InputException("File Format Error: Invalid Number Input!!!");
            }
            aircraftFactory.newAircraft(craftInfo[0], craftInfo[1], coordinates).registerTower(weatherTower);

        }
    }

    public void simulationStart() {
        System.out.println("============ Simulation start ============");
        for (int i = 0; i < simulationTime; i++) {
            weatherTower.changeWeather();
            System.out.println("----------SimulationTimes: " + (i + 1) + " ----------");
        }
        System.out.println("============ Simulation Finished ============");
    }


}
