package net.koreate.qnaboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.koreate.qnaboard.service.QnABoardService;
import net.koreate.qnaboard.util.QnACriteria;
import net.koreate.qnaboard.util.QnAPageMaker;
import net.koreate.qnaboard.vo.QnABoardVO;

@Controller
@RequestMapping("qnaboard")
public class QnABoardController {
	
	@Inject
	QnABoardService qs;
	

	@RequestMapping("/list")
	public String list(
			Model model,
			QnACriteria cri
			) throws Exception{
		System.out.println(cri);
		model.addAttribute("list",qs.list(cri));
		QnAPageMaker pm = qs.getPageMaker(cri);
		model.addAttribute("pm",pm);
		return "qnaboard/list";
	}
	
	@GetMapping("/write")
	public String write() {
		return "qnaboard/write";
	}
	
	@GetMapping("/modify")
	public String modify(
			int qno,
			Model model
			) throws Exception {
		QnABoardVO vo = qs.detail(qno);
		model.addAttribute("vo",vo);
		return "qnaboard/modify";
	}
	@Transactional
	@PostMapping("/resister")
	public String resister(QnABoardVO vo) throws Exception {
		qs.regist(vo);
		qs.setRoot();
		return "redirect:/qnaboard/list";
	}
	
	@PostMapping("/update")
	public String update(QnABoardVO vo) throws Exception{
		qs.update(vo);
		return"redirect:/qnaboard/list";
	}
	
	@GetMapping("/detail")
	public String detail(
			int qno,
			Model model
			) throws Exception{
		QnABoardVO vo = qs.detail(qno);
		model.addAttribute("vo",vo);
		return "qnaboard/detail";
	}
	
	@GetMapping("/delete")
	public String delete(
			int qno
			)throws Exception{
		qs.delete(qno);
		System.out.println("삭제호출");
		return "redirect:/qnaboard/list";
	}
	
	@GetMapping("/reply")
	public String reply(
			int qno,
			Model model
			) throws Exception{
		QnABoardVO vo = qs.detail(qno);
		model.addAttribute("vo",vo);
		return "qnaboard/reply";
	}
	
	@PostMapping("/resisterReply")
	public String resisterReply(
			QnABoardVO vo
			) throws Exception{
		qs.registerReply(vo);
		System.out.println("답글달기 완료");
		return "redirect:/qnaboard/list";
	}
}
