package com.sakura.dev.service;

import com.sakura.dev.domain.CpFile;
import com.sakura.dev.repository.CpFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Created by rc452 on 2017/6/22.
 */
@Service
public class CpFileService {
	@Autowired
	CpFileRepository cpFileRepository;

	public CpFile store(MultipartFile file){
		if(!file.isEmpty()){
			String originName = file.getOriginalFilename();
			String suffix = originName.substring(originName.lastIndexOf('.'), originName.length());
			String convertName = UUID.randomUUID().toString()+suffix;
			try {
//				File upload = new File("upload/"+file.getName());
//				if(upload.exists()){
//					upload.getParentFile().mkdirs();
//					upload.createNewFile();
//				}
				Path path = Paths.get("upload/").resolve(convertName);
				if(!Files.exists(path.getParent())){
					Files.createDirectories(path.getParent());
				}
//				if (!Files.exists(path)){
//					Files.createFile(path);
//				}
				Files.copy(file.getInputStream(), path);
				return new CpFile(originName,convertName);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}else{
			return null;
		}
	}
}
