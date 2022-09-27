package com.thaddeus.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QRCodeUtil {

    public static void createQRCode(String contents, Integer width, Integer height, HttpServletResponse response) {

        // 二维码属性
        Map<EncodeHintType, Object> hints = new HashMap<>();

        // 二维码容错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);

        // 二维码字符集
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

        // 二维码边框
        hints.put(EncodeHintType.MARGIN, 1);

        try {
            // 1. 读取文件转换为字节数组
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);

            BufferedImage image = toBufferedImage(bitMatrix);

            ImageIO.write(image, "png", response.getOutputStream());
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }

    }

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }

}
