package com.tech.spring.controller;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tech.spring.dto.BoardDto;
import com.tech.spring.dto.RecommendDto;
import com.tech.spring.service.CustomBoardService;
import com.tech.spring.service.CustomUserService;
import com.tech.spring.vopage.PageVO;
import com.tech.spring.vopage.PageVO_review;

@Controller
@RequestMapping("/board")
public class CustomBoardController {
	
	@Autowired CustomBoardService service;
	@Autowired ServletContext context;
	
	//1월9일 게시판 목록 //1월10일 게시판 검색
	@RequestMapping("/board")
	public ModelAndView board(HttpServletRequest request,
							  @RequestParam(value="pageNumber", required=false) String pageNumber,
							  @RequestParam(value="whatColumn", required=false) String whatColumn,
							  @RequestParam(value="keyword", defaultValue="", required=false) String keyword) {
		
		ModelAndView mav=new ModelAndView();
		Map<String,String>map = new HashMap<String,String>();
		map.put("whatColumn", whatColumn);
		map.put("keyword", keyword);
		System.out.println("검색:"+map);
		
		int count = service.countBoard(map);
		System.out.println("게시글 개수:"+count);
		
		//2월12일 페이징
		String url=request.getContextPath()+"/board/board";
		PageVO pageInfo=new PageVO(pageNumber, "5", count, url, whatColumn, keyword, null);
		
		List<BoardDto> list = service.board(pageInfo, map);
		
		mav.addObject("list", list);
		mav.addObject("pageInfo", pageInfo);
		mav.addObject("count", count);
		mav.addObject("keyword", keyword);
		mav.setViewName("/board/board");
		return mav;
	}
	
	//1월11일 게시판 등록 페이지
	@RequestMapping("/write")
	public String write() {
		return "/board/boardInsert";
	}
	
	//1월11일 게시판 등록
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(BoardDto dto,
						HttpServletResponse response) throws IOException {
		
		System.out.println(dto.getBoard_writer()+"/"+dto.getBoard_title()+"/"+dto.getBoard_text()+"/"+dto.getImg().getOriginalFilename());
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer=response.getWriter();
		
		//1월16일 파일업로드
		if(dto.getImg().getOriginalFilename() != "" ) {
			MultipartFile multiFile=dto.getImg();
			
			//2월6일 파일명랜덤생성(중복방지)
			UUID uuid = UUID.randomUUID();
			dto.setBoard_img_path(uuid+"."+FilenameUtils.getExtension(multiFile.getOriginalFilename()));
			
			File file=new File(context.getRealPath("/resources/img/")+"/"+uuid+"."+FilenameUtils.getExtension(multiFile.getOriginalFilename()));
			multiFile.transferTo(file);
		}
		int result = service.write(dto);
		System.out.println("등록성공시1:"+result);
		
		if(result == 1) {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('글이 등록되었습니다.');");
			writer.println("location.href='board'");
			writer.println("</script>");
			writer.flush();
		}
		return "redirect:/board/board";
	}
	
	//1월11일 게시판 상세
	@RequestMapping("/detail")
	public String detail(Model model,
						 HttpServletRequest request,
						 @RequestParam(required = true) int board_seq,
						 String pageNumber,
						 String pageNumber_review) {
		System.out.println(board_seq);
		
		BoardDto boardDto = service.detail(board_seq);
		
		//3월6일 게시판 댓글 페이징
		int count = service.countReview(board_seq);
		System.out.println("댓글 개수:"+count);
		
		String url=request.getContextPath()+"/board/detail?board_seq="+board_seq+"&pageNumber="+pageNumber;
		PageVO_review pageInfo=new PageVO_review(pageNumber_review, "5", count, url, null, null, null);
		
		List<BoardDto> list = service.review(pageInfo, board_seq);
		
		//2월27일 게시판 댓글
		model.addAttribute("BoardDto", boardDto);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("list", list);
		model.addAttribute("pageInfo_review", pageInfo);
		model.addAttribute("count", count);
		return "/board/boardDetail";
	}
	
	//1월12일 게시판 삭제
	@RequestMapping("/remove")
	public String remove(@RequestParam(required = true) String board_seq,
						 @RequestParam(required = true) String board_img_path,
						 HttpServletResponse response) throws IOException {
		System.out.println(board_seq+"/"+board_img_path);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer=response.getWriter();

		//1월16일 파일 삭제
		String img = context.getRealPath("/resources/img/")+board_img_path;
		File file = new File(img);
		file.delete();		
		
		int result = service.remove(board_seq);
		System.out.println("삭제성공시1:"+result);
		if(result == 1) {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('글이 삭제되었습니다.');");
			writer.println("location.href='board'");
			writer.println("</script>");
			writer.flush();
		}
		return "redirect:/board/board";
	}
	
	//1월13일 게시판 수정 페이지
	@RequestMapping("/edit")
	public String edit(Model model,
					   @RequestParam(required = true) int board_seq) {
		System.out.println("게시글 번호:"+board_seq);
		
		BoardDto boardDto = service.editPage(board_seq);
		model.addAttribute("BoardDto", boardDto);
		return "/board/boardDetailModi";
	}
	
	//1월 13일 게시판 수정
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String edit(BoardDto dto,
					   @RequestParam("board_img_path_old") String board_img_path_old,
					   HttpServletResponse response) throws IOException {
		System.out.println(board_img_path_old+"/"+dto.getImg().getOriginalFilename()+"/"+dto.getBoard_seq()+"/"+dto.getBoard_title()+"/"+dto.getBoard_text()+"/"+dto.getBoard_writer());
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer=response.getWriter();
		
		//1월16일 수정시 기존파일 삭제
		String img = context.getRealPath("/resources/img/")+board_img_path_old;
		File file = new File(img);
		file.delete();
		
		if(dto.getImg().getOriginalFilename() != "" ) {
			MultipartFile multiFile=dto.getImg();
			
			//2월6일 파일명랜덤생성(중복방지)
			UUID uuid = UUID.randomUUID();
			dto.setBoard_img_path(uuid+"."+FilenameUtils.getExtension(multiFile.getOriginalFilename()));
			File file2=new File(context.getRealPath("/resources/img/")+"/"+uuid+"."+FilenameUtils.getExtension(multiFile.getOriginalFilename()));
			multiFile.transferTo(file2);
		}
		
		
		int result = service.edit(dto);
		System.out.println("수정성공시1:"+result);
		
		if(result == 1) {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('글이 수정되었습니다.');");
			writer.println("location.href='detail?board_seq="+dto.getBoard_seq()+"'");
			writer.println("</script>");
			writer.flush();
		}
		return "redirect:/board/detail?board_seq="+dto.getBoard_seq();
	}
	
	//1월 13일 게시판 선택삭제
	@RequestMapping(value="/removeCheckbox", method=RequestMethod.POST)
	public String removeCheckbox(HttpServletRequest request) {
		String[] checkbox=request.getParameterValues("array");
		String[] imgCheckbox=request.getParameterValues("imgArray");

		//1월16일 파일 선택삭제
		for(int i=0; i<imgCheckbox.length; i++) {
			String img = context.getRealPath("/resources/img/")+imgCheckbox[i];
			File file = new File(img);
			file.delete();			
		}
		for(int i=0; i<checkbox.length; i++) {
			int result = service.remove(checkbox[i]);
			System.out.println("삭제성공시1:"+result);
		}
		return "redirect:/board/board";
	}
	
	//3월2일 게시판 리뷰 작성
	@RequestMapping(value="/writeReview", method=RequestMethod.POST)
	public String writeReview(BoardDto dto,
						 HttpServletResponse response) throws IOException {
		System.out.println(dto.getReview_writer()+"/"+dto.getReview_text()+"/"+dto.getBoard_seq());
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer=response.getWriter();
		
		int result = service.writeReview(dto);
		System.out.println("등록성공시1:"+result);
		
		if(result == 1) {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('댓글이 등록되었습니다.');");
			writer.println("location.href='detail?board_seq="+dto.getBoard_seq()+"'");
			writer.println("</script>");
			writer.flush();
		}
		return null;
	}
	
	//3월6일 게시판 리뷰 삭제
	@RequestMapping(value="/deleteReview", method=RequestMethod.GET)
	public String delteReview(int review_seq,
							  int board_seq,
						 	  HttpServletResponse response) throws IOException {
		System.out.println(board_seq+"/"+review_seq);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer=response.getWriter();
		
		int result = service.deleteReview(review_seq);
		System.out.println("삭제성공시1:"+result);
		
		if(result == 1) {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('댓글이 삭제되었습니다.');");
			writer.println("location.href='detail?board_seq="+board_seq+"'");
			writer.println("</script>");
			writer.flush();
		}
		return null;
	}	
	
	//3월16일 게시판 추천
	@RequestMapping(value="/recommend", method=RequestMethod.GET)
	public String recommend(RecommendDto dto,
							int pageNumber,
							HttpServletResponse response) throws IOException {
		System.out.println(dto.getBoard_seq()+"/"+dto.getCustom_user_nick());
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer=response.getWriter();
		
		int count = service.countRecommend(dto);
		
		if(count == 1) {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('이미 추천한 글입니다.');");
			writer.println("location.href='detail?board_seq="+dto.getBoard_seq()+"&pageNumber="+pageNumber+"'");
			writer.println("</script>");
			writer.flush();			
		}
		else {
			int result = service.recommend(dto);
			System.out.println("추천성공시1:"+result);
			if(result == 1) {
				writer.println("<script type='text/javascript'>");
				writer.println("alert('게시글을 추천합니다.');");
				writer.println("location.href='detail?board_seq="+dto.getBoard_seq()+"&pageNumber="+pageNumber+"'");
				writer.println("</script>");
				writer.flush();
			}
		}
		
		
		return null;
	}
	
}