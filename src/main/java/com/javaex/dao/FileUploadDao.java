package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.FileVo;

@Repository
public class FileUploadDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int insert(FileVo fileVo) {
		System.out.println("Dao의 fileVo: " + fileVo.toString());
		return sqlSession.insert("file.insert", fileVo);
	}
	
	public List<FileVo> selectList() {
		return sqlSession.selectList("file.selectList");
	}
}
