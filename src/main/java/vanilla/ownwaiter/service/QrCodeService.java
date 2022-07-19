package vanilla.ownwaiter.service;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vanilla.ownwaiter.constant.QRConst;
import vanilla.ownwaiter.file.S3Uploader;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QrCodeService {

    private final S3Uploader s3Uploader;

    public String generateAndUpload(Long restaurantId) {
        Path path = generateQrCode(restaurantId);
        String uploadUrl = uploadS3(path);
        return uploadUrl;
    }

    public Path generateQrCode(Long restaurantId)  {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = qrCodeWriter.encode(QRConst.LINK_URL + "/" + String.valueOf(restaurantId), BarcodeFormat.QR_CODE, QRConst.WIDTH, QRConst.HEIGHT);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }

        Path path = FileSystems.getDefault().getPath(getUniqueName());
        try {
            MatrixToImageWriter.writeToPath(bitMatrix, "png", path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return path.getFileName();
    }

    public String uploadS3(Path path) {
        File file = new File(String.valueOf(path.getFileName()));
        String uploadUrl = s3Uploader.upload(file, "qr");
        return uploadUrl;
    }

    public String readQrCode(String filepath) throws NotFoundException, IOException {
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
                new BufferedImageLuminanceSource(
                        ImageIO.read(new File(filepath)))));
        Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap);
        return qrCodeResult.getText();
    }

    private String getUniqueName() {
        return UUID.randomUUID() + "." + QRConst.FILE_EXTENSION;
    }
}
