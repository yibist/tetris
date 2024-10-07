import com.tetris.*;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;

public class testMain {
    @Test
    public void testRandomizers() throws Exception {
        for (int k = 0; k < 100; k++) {
            LinkedList<BlockType> prevElements = new LinkedList<>();
            for (int i = 0; i < 7; i++) {
                prevElements.add(BlockType.getRandomBlockType(prevElements));
            }
            for (BlockType element : prevElements) {
                int j = 0;
                for (BlockType Element : prevElements) {
                    if(Element.equals(element)) j++;
                }
                assert j < 2;
            }
        }
    }
}
