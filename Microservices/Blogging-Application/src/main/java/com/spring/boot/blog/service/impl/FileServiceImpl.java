package com.spring.boot.blog.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.blog.exception.ResourceNotFoundException;
import com.spring.boot.blog.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile image) {

		// File name (abc.jpg)
		String originalFilename = image.getOriginalFilename();

		// To generate random name to file (84dsd45s4d5v5c5c5.jpg)
		String randomId = UUID.randomUUID().toString();
		String randomName = randomId.concat(originalFilename.substring(originalFilename.lastIndexOf('.')));

		// Full path
		String fullPath = path + File.separator + randomName;

		// Create folder if not created
		File file = new File(fullPath);

		if (file.exists()) {
			file.mkdir();
		}

		// file copy
		try {
			Files.copy(image.getInputStream(), Paths.get(fullPath));
		} catch (IOException e) {
			throw new ResourceNotFoundException("originalFilename", "fullPath", 0);
		}

		return randomName;
	}

	@Override
	public InputStream getResource(String path, String fileName) {
		String fullPath = path + File.separator + fileName;
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(fullPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputStream;
	}

}
