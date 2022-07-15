package vanilla.ownwaiter;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.ArrayList;
import java.util.List;

@Data
public class BuilderExample {

    @Builder.Default
    private List<Integer> list = new ArrayList<>();

    private String test1;
    private String test;

    @Builder.Default
    private int num = 5;

    @Builder
    private BuilderExample(List<Integer> list, String test1, String test, int num) {
        this.list = list;
        this.test1 = test1;
        this.test = test;
        this.num = num;
    }
}
