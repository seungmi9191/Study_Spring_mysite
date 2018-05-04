package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.FileUploadDao;
import com.javaex.vo.FileVo;

@Service
public class FileUploadService {
	
	@Autowired
	private FileUploadDao fileUploadDao;
	
	public int restore(MultipartFile file) { //multi~ 객체 = 멤버변수있고, 메소드 있고 ...
		///////////DB에 저장해두려고 아래 내용들 지정하는 것/////////
		
		String saveDir = "D:\\javaStudy\\upload";
		
		//원본 파일명 (사용자 컴퓨터에 저장되어있던 이름)
		String orgName = file.getOriginalFilename();
		System.out.println("orgName: " + orgName);
		
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println("exName: " + exName);
		
		//저장파일명(여러 사용자가 올린 원본 파일명이 중복될 수 있기때문에 중복방지를 위한 이름을 줘서 hdd에 저장) - 중복네이밍 안될 수 있게 로직필요
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName; //UUID는 난수 겁나 생성해줌
		System.out.println("saveName: "+ saveName);
		System.out.println("saveName: "+ System.currentTimeMillis());
		
		//파일패스(어느 디렉토리에 어떤 이름으로 HDD에 가지고있는지 경로를 가지고있는것) = 진짜 파일이 있는 위치, 어디에 저장할 건지 지정함(서버 어디에 저장할지 지정)
		String filePath = saveDir + "\\" + saveName; //디렉토리 밑에(\\) 저장파일명으로 관리!
		System.out.println("filePath: " + filePath);
		
		//파일사이즈
		long fileSize = file.getSize();
		System.out.println("fileSize: "+ fileSize);
		
		////////////////쿼리에 넣기 전 한번에 넣어주기 위해 vo만들기
		FileVo fileVo = new FileVo(orgName,saveName,filePath,fileSize);
		System.out.println(fileVo.toString());
		
		//Dao 연결 DB저장
		
		//파일 서버에 복사
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream( saveDir + "/" + saveName);
			BufferedOutputStream bout = new BufferedOutputStream(out); //버퍼하나 끼워주면 빠름
			
			bout.write(fileData);
			
			if(bout != null) { //데이터랑 상관없이 연결이 있으면 닫아버령
				bout.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		int count = fileUploadDao.insert(fileVo);
		if(count>0) {
			System.out.println("정보저장성공!");
		} else {
			System.out.println("정보저장실패!");
		}
		
		return count;
	}
	
	public List<FileVo> list(){
		return fileUploadDao.selectList();
	}

}
