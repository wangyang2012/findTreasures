import model.ObjectOnMap;
import model.Process;
import model.TreasuresException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.MapUtils;

public class MapUtilsTest {
    @Test
    public void createMapTest() throws TreasuresException {
        int nbLines = 3;
        int nbColumns = 5;

        ObjectOnMap[][] map = MapUtils.createMap(nbColumns, nbLines);
        Assertions.assertEquals(nbLines, map.length);
        for (int i=0; i<nbLines; i++) {
            Assertions.assertEquals(nbColumns, map[i].length);
        }

        Assertions.assertThrows(TreasuresException.class, () -> MapUtils.createMap(null, null));
        Assertions.assertThrows(TreasuresException.class, () -> MapUtils.createMap(-1, 5));
        Assertions.assertThrows(TreasuresException.class, () -> MapUtils.createMap(1, -5));
        Assertions.assertThrows(TreasuresException.class, () -> MapUtils.createMap(-1, -5));
    }

    @Test
    public void writeMapTest() throws TreasuresException {
        MapUtils.createMap(3, 4);
        String mapStr = MapUtils.mapToString();
        System.out.println(mapStr);
    }

    @Test
    public void createMountainTest() throws TreasuresException {
        MapUtils.createMap(3, 4);
        String[] mountainFields = "C-1-2".split("-");
        MapUtils.addMountain(mountainFields);
        String mapStr = MapUtils.mapToString();
        System.out.println(mapStr);
    }

    @Test
    public void createTreasuresTest() throws TreasuresException {
        MapUtils.createMap(3, 4);

        String[] mountainFields = "C-1-1".split("-");
        MapUtils.addMountain(mountainFields);

        String[] treasureFields = "T-0-3-2".split("-");
        MapUtils.addTreasure(treasureFields);
        String mapStr = MapUtils.mapToString();
        System.out.println(mapStr);
    }


    @Test
    public void testReadFile() throws TreasuresException {
        new Process("I:\\\\test.txt");
        String mapStr = MapUtils.mapToString();
        System.out.println(mapStr);
    }

    @Test
    public void test() {
        String value = "AADDAGA";
        String[] values = value.split("A");
        System.out.println(values.length);
    }
}

