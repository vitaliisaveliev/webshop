package com.epam.util;

import com.epam.db.bean.ProductBean;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.*;

public class AvatarContainer {

    private static final String PICTURE_DIR = "./src/main/resources/avatar/";
    private static final String LOGO_DIR = "./src/main/resources/productLogo/";

    public static String createPath(String email) {
        return PICTURE_DIR + email + "." + "jpg";
    }

    private static String createPath(ProductBean productBean) {
        return LOGO_DIR + productBean.getName() + "." + "jpg";
    }

    public static void save(Part filePart, String email) throws IOException {
        String path = createPath(email);
        File avatar = new File(path);
        OutputStream out = new FileOutputStream(avatar);
        InputStream fileContent = filePart.getInputStream();
        int read;
        byte[] bytes = new byte[1024];
        while ((read = fileContent.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        out.close();
    }

    static String save(Part filePart, ProductBean productBean) throws IOException {
        String path = createPath(productBean);
        File avatar = new File(path);
        OutputStream out = new FileOutputStream(avatar);
        InputStream fileContent = filePart.getInputStream();
        int read;
        byte[] bytes = new byte[1024];
        while ((read = fileContent.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        out.close();
        return path.substring(LOGO_DIR.length());
    }

    public static BufferedImage readFile(File userAvatar) throws IOException {
        return ImageIO.read(userAvatar);
    }
}
