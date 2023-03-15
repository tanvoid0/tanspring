package com.tanvoid0.tanspring.models.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class FileUtils {

  public static byte[] compressFile(byte[] data) {
    Deflater deflater = new Deflater();
    deflater.setLevel(Deflater.BEST_COMPRESSION);
    deflater.setInput(data);
    deflater.finish();

    final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
    byte[] tmp = new byte[4 * 1024];
    while (!deflater.finished()) {
      int size = deflater.deflate(tmp);
      outputStream.write(tmp, 0, size);
    }
    try {
      outputStream.close();
    } catch (Exception ex) {

    }
    return outputStream.toByteArray();
  }

  public static byte[] decompressFile(final byte[] data) {
    Inflater inflater = new Inflater();
    inflater.setInput(data);

    final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
    byte[] tmp = new byte[4 * 1024];
    try {
      while (!inflater.finished()) {
        int count = inflater.inflate(tmp);
        outputStream.write(tmp, 0, count);
      }
      outputStream.close();
    } catch (Exception ex) {
    }
    return outputStream.toByteArray();
  }

  public static void saveFile(final String folderPath, final String fileName, final MultipartFile file) throws IOException {
    Path uploadPath = Paths.get(folderPath);

    if (!Files.exists(uploadPath)) {
      Files.createDirectories(uploadPath);
    }

    try (InputStream inputStream = file.getInputStream()) {
      final Path filePath = uploadPath.resolve(fileName);
      Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException ex) {
      throw new IOException("Could not save file: " + fileName, ex);
    }
  }
}
