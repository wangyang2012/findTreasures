import model.Process;
import model.TreasuresException;
import org.apache.log4j.Logger;
import util.FileUtils;

import java.util.List;

public class FindTreasures {
    public static final Logger logger = Logger.getLogger(FindTreasures.class);

    public static void main(String[] args) {
        if (args == null || args.length != 1) {
            logger.error("Veuillez renseigner le fichier d'entrée");
        } else {
            String fileName = args[0];

            // read file
            List<String> lines = null;
            try {
                lines = FileUtils.readFile(fileName);
            } catch (TreasuresException e) {
                logger.error("Impossible de lire le fichier d'entrée");
                return;
            }

            // create Travail
            Process process = new Process();

            // Déroulement
            process.run(lines);
        }

    }

    private static void readInputFile(String inputFile) {


    }

    private static void treateFileLine(String line) {

    }

    private static void initMap() {

    }
}
