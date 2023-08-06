package com.tanvoid0.tanspring.models.file;

import com.github.javafaker.Faker;
import com.tanvoid0.tanspring.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanspring.models.user.AppUser;
import com.tanvoid0.tanspring.security.auth.AuthenticationService;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class StorageService {

  private final UserFileDataRepository fileRepo;
  private final AuthenticationService authenticationService;
  private final Faker faker;
  private final ModelMapper mapper;

  @Value("${image.upload.directory}")
  private String uploadDirectory;


//  @Transactional
  // TODO: just a note implementation to know how to save in db
//  public String uploadFile(final MultipartFile file) throws IOException {
//    final AppUser user = authenticationService.getAuthUser();
//
//    final String fileName = getUniqueFileName(file.getOriginalFilename());
//
//    final DBFileData entity = DBFileData.builder()
//        .name(fileName)
//        .type(file.getContentType())
//        .user(user)
//        .data(FileUtils.compressFile(file.getBytes()))
//        .build();
////    final DBFileData savedEntity = repository.save(entity);
//    return fileName;
//  }

//  public byte[] downloadFile(final String fileName) {
//    final DBFileData entity = repository.findByName(fileName).orElseThrow(() -> new ResourceNotFoundException("File", "name", fileName));
//
//    byte[] images = FileUtils.decompressFile(entity.getData());
//    return images;
//  }

  @Transactional
  public UserFileDataVO uploadFileToStorage(final MultipartFile file) throws IOException {
    if (file.isEmpty()) {
      return null;
    }
    final AppUser user = authenticationService.getAuthUser();
    String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

    final Path path = Paths.get(uploadDirectory + fileName);
    Files.write(path, file.getBytes());

    // TODO: it doesn't work as it's an abstract. need to create another class that can reuse this.
    final String fileType = file.getContentType();
    final UserFileData entity = UserFileData.builder()
        .name(fileName)
        .type(fileType)
        .createdByUser(user)
        .build();
    final FileData savedEntity = fileRepo.save(entity);
    return convertEntityToVO(savedEntity);
  }

  public URL readFileFromStorage(final String filename) throws IOException {
    final FileData file = findByName(filename);
    final Path path = Paths.get(uploadDirectory + file.getName());
    return path.toUri().toURL();
  }

  @Transactional
  public void deleteFileFromStorage(final String filename) throws IOException {
    if (fileRepo.existsByName(filename)) {
      fileRepo.deleteByName(filename);
    }

    final Path path = Paths.get(uploadDirectory + filename);
    Files.delete(path);
  }

  private FileData findByName(final String filename) {
    return fileRepo.findByName(filename).orElseThrow(() -> new ResourceNotFoundException("File", "file_path", filename));
  }

  private UserFileDataVO convertEntityToVO(final FileData entity) {
    return mapper.map(entity, UserFileDataVO.class);
  }
}
