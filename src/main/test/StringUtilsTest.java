import model.TreasuresException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.StringUtils;

public class StringUtilsTest {
    @Test
    public void isEmptyTest() throws TreasuresException {
        Assertions.assertEquals(false, StringUtils.isEmpty("This is a string"));
        Assertions.assertEquals(true, StringUtils.isEmpty(""));
        Assertions.assertEquals(true, StringUtils.isEmpty(null));
    }
}

