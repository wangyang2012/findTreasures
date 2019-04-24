package model;

import org.apache.log4j.Logger;
import util.MapUtils;
import util.StringUtils;

import java.util.List;

public class Process {

    public static final Logger logger = Logger.getLogger(Process.class);

    public static final boolean COLLISION_DETECTION = true; // Si true, les tondeuses ne peuvent pas se superposer

    private ObjectOnMap[][] map;

    /****** Constructors ******/
    public Process() {
    }

    /****** actions *******/
    public void run(List<String> lines) {
        try {
            this.init(lines);
//            this.roulerTondeuses();
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
            if (StringUtils.isBlank(line)) {
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

//
//    /**
//     * Création des tondeuses
//     *
//     * @param lignes: Chaque tondeuse a deux lignes, la première ligne décrit sa position initiale, la deuxième ligne décrit son déroulement
//     */
//    private void initTondeuses(List<String> lignes) throws TreasuresException {
//        if (lignes == null || lignes.isEmpty()) {
//            throw new TreasuresException("Il n'y a pas de tondeuse sur le gazon.");
//        }
//
//        if (lignes.size() % 2 != 0) {
//            throw new TreasuresException("Le nombre des lignes de tondeuse n'est pas paire.");
//        }
//
//        // parcourir chaque 2 lignes
//        for (int i = 0; i < lignes.size(); i += 2) {
//            int numberTondeuse = this.tondeuses.size() + 1;
//            try {
//                String ligneUne = lignes.get(i);
//                String[] positions = ligneUne.split(" ");
//                Integer x = Integer.valueOf(positions[0]);
//                Integer y = Integer.valueOf(positions[1]);
//                char orientation = positions[2].charAt(0);
//                String ligneDeux = lignes.get(i + 1);
//                Tondeuse tondeuse = new Tondeuse("Tondeuse" + numberTondeuse, x, y, orientation, ligneDeux.toCharArray());
//                this.tondeuses.add(tondeuse);
//                logger.debug("Nouvelle tondeuse: " + tondeuse);
//            } catch (Exception e) {
//                throw new TreasuresException("Impossible de créer la tondeuse N°" + numberTondeuse);
//            }
//            logger.info("La tondeuse " + numberTondeuse + " est créée correctement");
//        }
//    }
//
//    /**
//     * Dérouler les actions de chaque tondeuse
//     * @throws TreasuresException
//     */
//    private void roulerTondeuses() {
//        if (this.gazon == null || this.tondeuses == null || this.tondeuses.isEmpty()) {
//            return;
//        }
//
//        for (Tondeuse tondeuse : this.tondeuses) {
//            // verifier position actuelle de la tondeuse par rapport au gazon
//            if (isPositionLegalle(tondeuse.getPosition())) {
//                roulerTondeuse(tondeuse);
//            } else {
//                logger.debug("La tondeuse " + tondeuse.getNom() + " n'est pas dans le gazon!");
//            }
//        }
//    }
//
//    /**
//     * Vérifier si la position en paramètre est dans le gazon
//     *
//     * @param position
//     * @return
//     */
//    private boolean isPositionLegalle(Position position) {
//        if (position == null || this.gazon == null || this.gazon.getPosition() == null) {
//            return false;
//        }
//
//        // Détection de collision
//        if (COLLISION_DETECTION) {
//            int countPosition = 0;
//            for (Tondeuse tondeuse : tondeuses) {
//                if (position.equals(tondeuse.getPosition())) {
//                    countPosition++;
//                    if (countPosition > 1) {
//                        return false;
//                    }
//                }
//            }
//        }
//
//        // Vérifier si la position ne dépasse pas le gazon
//        return position.getX() <= this.gazon.getPosition().getX() && position.getY() <= this.gazon.getPosition().getY();
//    }
//
//    /**
//     * Dérouler toutes les actions de la tondeuse
//     * @param tondeuse
//     */
//    private void roulerTondeuse(Tondeuse tondeuse) {
//        for (char action : tondeuse.getActions()) {
//            if (ActionEnum.A == action) {
//                // Si l'action est avancer, vérifier que la tondeuse ne dépasse pas le gazon
//                Position prochainePosition = tondeuse.calculProchainePosition(action);
//                if (!isPositionLegalle(prochainePosition)) {
//                    continue;
//                }
//            }
//            tondeuse.bouger(action);
//        }
//    }
//
//    /**
//     * Afficher le résultat
//     * @throws TreasuresException
//     */
//    private void printResult() {
//        for (Tondeuse tondeuse : this.tondeuses) {
//            tondeuse.printPosition();
//        }
//    }
//
//
//    /****** Getters and Setters ******/
//    public Gazon getGazon() {
//        return gazon;
//    }
//
//    public void setGazon(Gazon gazon) {
//        this.gazon = gazon;
//    }
//
//    public List<Tondeuse> getTondeuses() {
//        return tondeuses;
//    }
//
//    public void setTondeuses(List<Tondeuse> tondeuses) {
//        this.tondeuses = tondeuses;
//    }
}
