package com.kosta.board.controller;

import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonsController {

	@RequestMapping(value="image/{filename}", method=RequestMethod.GET)
	public void imageView(@PathVariable String filename, HttpServletResponse response) {
		try {
			String path = "C:/PSY/upload-image/";
		
			FileInputStream fis = new FileInputStream(filename);
			FileCopyUtils.copy(fis, response.getOutputStream()); //path를 넣어줘야 하지만 경로가 이상하게 잡혀서 일단 뺌
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
