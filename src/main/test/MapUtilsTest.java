import model.ObjectOnMap;
import model.TreasuresException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.MapUtils;

public class MapUtilsTest {
    @Test
    public void initMapTest() throws TreasuresException {
        int x = 3;
        int y = 5;

        ObjectOnMap[][] map = MapUtils.initMap(x, y);
        Assertions.assertEquals(x, map.length);
        for (int i=0; i<x; i++) {
            Assertions.assertEquals(y, map[i].length);
        }

        Assertions.assertThrows(TreasuresException.class, () -> MapUtils.initMap(null, null));
        Assertions.assertThrows(TreasuresException.class, () -> MapUtils.initMap(-1, 5));
        Assertions.assertThrows(TreasuresException.class, () -> MapUtils.initMap(1, -5));
        Assertions.assertThrows(TreasuresException.class, () -> MapUtils.initMap(-1, -5));

    }
}

