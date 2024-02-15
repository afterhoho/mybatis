package com.oracle.oBootBoard.command;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.oracle.oBootBoard.dao.BDao;
import com.oracle.oBootBoard.dto.BDto;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class BExecuteCommand {
	private final BDao jdbcDao;
	public BExecuteCommand(BDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	// list 
	public void bListCmd(Model model) {
		// Dao 연결
		ArrayList<BDto> boardDtoList = jdbcDao.boardList();
	      System.out.println("BListCommand boardDtoList.size()->"+boardDtoList.size());
	      model.addAttribute("boardList", boardDtoList);
	}
	// 글작성
	public void bWriteCmd(Model model) {
//		  1)  model이용 , map 선언
		Map<String, Object>map =model.asMap();
//		  2) request 이용 ->  bName  ,bTitle  , bContent  추출
		HttpServletRequest request =(HttpServletRequest) map.get("request");

//		  3) dao  instance 선언
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
//		  4) write method 이용하여 저장(bName, bTitle, bContent)
		jdbcDao.write(bName,bTitle,bContent);
	}
	// 글 보이는거
	public void bContentCmd(Model model) {
		// 1. model 를 Map으로 전환
		Map<String, Object>map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		// 2. request->bId Get
		int bId = Integer.parseInt(request.getParameter("bId"));
		// 3. 
		BDto board =jdbcDao.contentView(bId);
		
		model.addAttribute("mvc_board",board);
		
	}
	// 수정
	public void bModifyCmd(Model model) {
		// 1. model Map선언
		Map<String, Object>map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		// 2. parameter ->  bId, bName , bTitle , bContent
		int bId = Integer.parseInt(request.getParameter("bId"));
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		jdbcDao.modify(bId, bName, bTitle, bContent);
		
	}
	// 답변 글 쓰는거 보이는거
	public void bReplyViewCmd(Model model) {
		// model이용, map 선언
		Map<String, Object>map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		// request 이용 -> bid 추출
		int bId = Integer.parseInt(request.getParameter("bId"));
		// reply_view method 이용하여(bid)
		// -BDto dto = dto.reply_view(bid)
		BDto dto = jdbcDao.reply_view(bId);
		
		model.addAttribute("reply_view",dto);
		
	}
	// 답변 작성
	public void bReplyCmd(Model model) {
		System.out.println("BExecuteCommand bReplyCmd start");
//		  1)  model이용 , map 선언
		Map<String, Object>map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
//		  2) request 이용 -> bid,         bName ,  bTitle,
//		                    bContent ,  bGroup , bStep ,
//		                    bIndent 추출
		int bId = Integer.parseInt(request.getParameter("bId"));
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		int bGroup = Integer.parseInt(request.getParameter("bGroup"));
		int bStep = Integer.parseInt(request.getParameter("bStep"));
		int bIndent = Integer.parseInt(request.getParameter("bIndent"));
		
		int bIntGroup = Integer.parseInt(request.getParameter("bGroup"));
		System.out.println("BReplyCommand bIntGroup->"+bIntGroup);
//		  3) reply method 이용하여 댓글저장 
//		    - dao.reply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent);
//		    [1] bId SEQUENCE = bGroup 
//		    [2] bName, bTitle, bContent -> request Value
//		    [3] 홍해 기적
//		    [4] bStep / bIndent   + 1
		jdbcDao.reply(bId,bName,bTitle,bContent,bGroup,bStep,bIndent);
		
		

	}
	
	//삭제
	public void bDeleteCmd(Model model) {
		Map<String, Object>map=model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int bId = Integer.parseInt(request.getParameter("bId"));
		jdbcDao.delete(bId);
		
	}


}
