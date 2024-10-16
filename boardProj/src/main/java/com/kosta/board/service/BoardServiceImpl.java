package com.kosta.board.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dao.BoardDAO;
import com.kosta.board.dao.FileDAO;
import com.kosta.board.dao.HeartDAO;
import com.kosta.board.dto.BFile;
import com.kosta.board.dto.Board;
import com.kosta.board.utils.PageInfo;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDao;
	@Autowired
	private HeartDAO heartDao;
	@Autowired
	private FileDAO fileDao;
	

	
	@Override
	public void boardWrite(Board board, MultipartFile file, MultipartFile dfile) throws Exception {
		//이미지 파일 업로드 : SpringFramework에서는 고정된 경로 사용(servercore아님)
		if(file !=null && !file.isEmpty()) {
			String path = "‪C:/uploadimage/";
		
			BFile bFile = new BFile();
			bFile.setDirectory(path);
			bFile.setName(file.getOriginalFilename());
			bFile.setSize(file.getSize());
			bFile.setContenttype(file.getContentType());
			fileDao.insertFile(bFile);
			
			//board.setFilename(file.getOriginalFilename());
			
			//파일 객체 생성
			File upFile = new File(bFile.getNum()+"");
			
			//System.out.println(upFile.getAbsoluteFile()); 
			//System.out.println(upFile.getAbsolutePath()); -> C:\PSY\sts-bundle\sts-3.9.18.RELEASE
			
			//파일을 경로에 저장 
			file.transferTo(upFile);	
			board.setFilename(bFile.getNum()+"");
			
		}
		// 파일 업로드
		if(dfile !=null && !dfile.isEmpty()) {
			String path = "‪C:/upload/";
			BFile bFile = new BFile();
			bFile.setDirectory(path);
			bFile.setName(dfile.getOriginalFilename());
			bFile.setSize(dfile.getSize());
			bFile.setContenttype(dfile.getContentType());
			fileDao.insertFile(bFile);
			
			System.out.println(bFile.getName());
			
			
			//파일 객체 생성
			File upFile = new File(dfile.getOriginalFilename());  // 원래 지정한 path에 저장될 수 있어야 하지만 C:\PSY\sts-bundle\sts-3.9.18.RELEASE 해당 경로가 계속 잡혀서 일단 뺌
			//파일을 경로에 저장 
			dfile.transferTo(upFile);
			board.setDfilename(bFile.getName());
		}
		
		boardDao.insertBoard(board);
	}
	
	//게시글 수정
	@Override
	public void boardModify(Board board, MultipartFile file,MultipartFile dfile) throws Exception {
	
		if(file!= null && !file.isEmpty()) {
			String path = "‪C:/uploadimage/";
			BFile bFile = new BFile();
			bFile.setDirectory(path);
			bFile.setName(file.getOriginalFilename());
			bFile.setSize(file.getSize());
			bFile.setContenttype(file.getContentType());
			
			fileDao.insertFile(bFile); // 사진 변경이 없는경우 null이니까 파일을 저장할 것도 없음  변경하는경우 삭제없이 그냥 새로운 사진을 경로로 저장 
			
			
			//파일 객체 생성
			File upFile = new File(bFile.getNum()+"");
			
			//파일을 경로에 저장 
			file.transferTo(upFile);	
			board.setFilename(bFile.getNum()+"");
			
		}
		if(dfile!= null && !dfile.isEmpty()) {
			String path = "‪C:/upload/";
			BFile bFile = new BFile();
			bFile.setDirectory(path);
			bFile.setName(dfile.getOriginalFilename());
			bFile.setSize(dfile.getSize());
			bFile.setContenttype(dfile.getContentType());
			fileDao.insertFile(bFile);
			
			System.out.println(bFile.getName());
			
			
			//파일 객체 생성
			File upFile = new File(dfile.getOriginalFilename());  
			//파일을 경로에 저장 
			dfile.transferTo(upFile);
			board.setDfilename(bFile.getName());
			
		}
		
		
		
		 boardDao.updateBoard(board);
	}
	 
	@Override
	public Board boardDetail(Integer num) throws Exception {
		Board board = boardDao.selectBoard(num);
		if(board == null) throw new Exception("글 번호 오류");
		boardDao.updateViewCnt(num);
				
		return board ;
	}


	//paging 처리와 동시에 화면에 보여져야하는 row 반환
	@Override
	public List<Board> boardList(PageInfo pageInfo) throws Exception {
		Integer boardCnt = boardDao.selectBoardCount();
		
		Integer allPage = (int)Math.ceil((double)boardCnt/10); // 반올림 해야 마지막 페이지가 제대로 나올 수 있음.(> 버튼 활성화 비활성화에 활용)
		
		
		//startButton- 1~10 :1 / 11~20: 11
		Integer startPage = (pageInfo.getCurPage()-1)/10*10+1; //1,11,21,31....
		
		
		//endButton
		Integer endPage = startPage+10-1;
		
		if(endPage > allPage) endPage = allPage;
		
		pageInfo.setAllPage(allPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);  // pageInfo객체를 저장함 
		
	    //페이지 의 시작하는  로우를 계산해서 넘김
		Integer row = (pageInfo.getCurPage()-1) *10 +1; // "1"~10/ "11"~20/ "21"~30....  
		
		return  boardDao.selectBoardList(row);
	}

	@Override
	public Integer checkHeart(String memberId, Integer boardNum) throws Exception {
		
		return heartDao.selectHeart(memberId, boardNum);
	}

	@Override
	public boolean toggleHeart(String id, Integer boardNum) throws Exception {
		//heartDAO의 select, insert 활용 있으면 삭제, 없으면 삽입
		Integer heartNum  = heartDao.selectHeart(id, boardNum);
		
		if(heartNum == null) { // 없으면 인서트
			heartDao.insertHeart(id, boardNum);
			return true;
		
		}else {
			heartDao.deleteHeart(id, boardNum);
			return false;
		}
	}

	@Override
	public void fileDown(HttpServletRequest request, HttpServletResponse response) 
			throws Exception , IOException {
		//request로부터 파일명 가져옴
		String file = request.getParameter("file");
		//실제 경로
		String path = request.getServletContext().getRealPath("upload"); 
		FileInputStream fis = new FileInputStream(new File(path,file));
		
		//파일 형식을 가져옴 (경로와 파일명을 보내면)
		String mimeType = request.getServletContext().getMimeType(path+"\\"+file);
		
		if(mimeType == null) { // 파일형식이 없으면  따로 지정해줌
			mimeType = "application/octet-stream"; // octet-stream: 8비트로된 일련의 데이터를 뜻함 (지정되지 않은 파일 타입을 의미) 
		}
		//브라우저에게 어떤 타입의 데이터를 보내는지 알려줌
		response.setContentType(mimeType);
		
		//인코딩 타입 지정 : 파일명이 한글일 수 있으니 깨짐 방지를 위해 지정해줌
		String encoding = new String(file.getBytes("utf-8"),"8859_1");
		
		//헤더에 파일명을 넣어줌
		response.setHeader("content-Disposition", "attachmemt; filename="+encoding);
		
		OutputStream out = response.getOutputStream();
		byte[] buff = new byte[4096];
		int len;
		while((len=fis.read(buff))>0) {
			out.write(buff,0,len);
		}
		fis.close();
	}



	

}
