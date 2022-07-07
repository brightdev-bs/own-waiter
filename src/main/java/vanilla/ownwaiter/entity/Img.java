package vanilla.ownwaiter.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
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
