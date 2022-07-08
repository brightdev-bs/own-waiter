package vanilla.ownwaiter.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Img {

    private String uploadFileName;
    private String storeFileName;

    public Img() {}

    public Img(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
