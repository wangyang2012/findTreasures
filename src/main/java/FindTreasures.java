import model.Process;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class FindTreasures {
    public static final Logger logger = Logger.getLogger(FindTreasures.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();

        if (args == null || args.length != 1) {
            logger.error("Veuillez renseigner le fichier d'entrée");
        } else {
            String fileToRead = args[0];

            // create Travail
            Process process = new Process(fileToRead);
        }

    }

    private static void readInputFile(String inputFile) {


    }

    private static void treateFileLine(String line) {

    }

    private static void initMap() {

    }
}
