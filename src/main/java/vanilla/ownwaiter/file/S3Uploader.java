package vanilla.ownwaiter.file;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import vanilla.ownwaiter.common.utils.ImageUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3Uploader {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
        if(multipartFile.isEmpty()) {
            log.debug("이미지 없습니다.");
            return null;
        }
        File uploadFile = convert(multipartFile).orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File 변환 실패"));
        return upload(uploadFile, dirName);
    }

    public String upload(File uploadFile, String dirName) {
        String fileName = dirName + "/" + uploadFile.getName();
        String uploadImageUrl = putS3(uploadFile, fileName);
        removeNewFile(uploadFile);
        return uploadImageUrl;
    }

    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(getUniqueName(file));
        if(convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }

            ImageUtils.resizeImage(convertFile, 200, 200);
            log.info("ImageUtils 실행됨");
            return Optional.of(convertFile);
        }

        return Optional.empty();
    }

    private String getUniqueName(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String extension = file.getOriginalFilename().substring(fileName.lastIndexOf(".") + 1);
        String newName = UUID.randomUUID() + "." + extension;
        return newName;
    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }


    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

}
