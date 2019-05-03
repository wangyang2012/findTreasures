package model;

import org.apache.log4j.Logger;
import util.FileUtils;
import util.MapUtils;
import util.StringUtils;

import java.util.List;

public class Process {

    public static final Logger logger = Logger.getLogger(Process.class);

    private ObjectOnMap[][] map;

    /****** Constructors ******/
    public Process(String fileToRead) {
        try {
            List<String> lines = FileUtils.readFile(fileToRead);
            run(lines);
        } catch (TreasuresException e) {
            logger.error("Une erreur est survenue lors de la création de la carte");
        }
    }

    /****** actions *******/
    public void run(List<String> lines) {
        try {
            this.init(lines);
            MapUtils.moveAdventurers();
            logger.info("Travail est terminé avec succès");
//            this.printResult();
        } catch (TreasuresException e) {
            logger.error("Erreur: " + e.getMessage());
        }
    }


    /**
     * Create map, mountains, treasors and adventurers
     * @param lines
     * @throws TreasuresException
     */
    private void init(List<String> lines) throws TreasuresException {
        if (lines == null || lines.isEmpty()) {
            throw new TreasuresException("Le fichier d'entrée est vide");
        }
        for (String line : lines) {
            // empty lines or lines which start with "#" should be ignored
            if (StringUtils.isBlank(line) || line.startsWith("#")) {
                continue;
            }
            // remove espaces beside "-"
            line = line.replaceAll(" - ", "-");
            String[] fields = line.split("-");
            switch (fields[0]) {
                case "C":
                    MapUtils.initMap(fields);
                    break;
                case "M":
                    MapUtils.addMountain(fields);
                    break;
                case "T":
                    MapUtils.addTreasure(fields);
                    break;
                case "A":
                    MapUtils.createAdventurer(fields);
                    break;
                default:
                    logger.error("Ligne inconnue");
            }
        }
    }
}
