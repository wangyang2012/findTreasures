import model.ObjectOnMap;
import model.TreasuresException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.MapUtils;

public class MapUtilsTest {
    @Test
    public void createMapTest() throws TreasuresException {
        int y = 3;
        int x = 5;

        ObjectOnMap[][] map = MapUtils.createMap(y, x);
        Assertions.assertEquals(y, map.length);
        for (int i=0; i<y; i++) {
            Assertions.assertEquals(x, map[i].length);
        }

        Assertions.assertThrows(TreasuresException.class, () -> MapUtils.createMap(null, null));
        Assertions.assertThrows(TreasuresException.class, () -> MapUtils.createMap(-1, 5));
        Assertions.assertThrows(TreasuresException.class, () -> MapUtils.createMap(1, -5));
        Assertions.assertThrows(TreasuresException.class, () -> MapUtils.createMap(-1, -5));
    }

    @Test
    public void writeMapTest() throws TreasuresException {
        MapUtils.createMap(6, 9);

        String mapStr = MapUtils.mapToString();
        System.out.println(mapStr);
    }


}

