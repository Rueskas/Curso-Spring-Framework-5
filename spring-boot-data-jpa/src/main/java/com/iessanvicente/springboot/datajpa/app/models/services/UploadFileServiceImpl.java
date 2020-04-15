package com.iessanvicente.springboot.datajpa.app.models.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UploadFileServiceImpl implements IUploadFileService {
	
	private final static String UPLOAD_DIR = "uploads";

	@Override
	public Resource load(String filename) throws MalformedURLException {
		Resource resource = null;
		try {
			resource = new UrlResource(getPath(filename).toUri());
			if (!resource.exists() || !resource.isReadable()) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No se ha podido cargar el avatar");
			}
		} catch (MalformedURLException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No se ha podido cargar el avatar");
		}
		return resource;
	}

	@Override
	public String copy(MultipartFile file) throws IOException {

		String nombreFile = UUID.randomUUID() + "_" + file.getOriginalFilename();
		Path rootPath = getPath(nombreFile);
		Files.copy(file.getInputStream(), rootPath);
		return nombreFile;
	}

	@Override
	public boolean delete(String filename) {
		Path rootPath = getPath(filename);
		File file = rootPath.toFile();
		if (file.exists() && file.canRead()) {
			if (file.delete()) {
				return true;
			}
		}
		return false;
	}

	public Path getPath(String filename) {
		return Paths.get(UPLOAD_DIR).resolve(filename).toAbsolutePath();
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(Paths.get(UPLOAD_DIR).toFile());
	}

	@Override
	public void init() throws IOException {
		Files.createDirectories(Paths.get(UPLOAD_DIR));
	}

}
