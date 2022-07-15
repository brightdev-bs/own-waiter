package vanilla.ownwaiter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class BuilderTestExample {

    @Test
    void test() {
        BuilderExample b = BuilderExample.builder().test("Test").build();

        Assertions.assertEquals(b.getTest(), "Test");
        Assertions.assertEquals(b.getTest1(), null);
        Assertions.assertEquals(b.getNum(), 5);
        Assertions.assertEquals(b.getList(), new ArrayList<>());
    }

}