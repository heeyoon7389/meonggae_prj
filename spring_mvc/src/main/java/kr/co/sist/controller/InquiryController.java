package kr.co.sist.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.sist.domain.InquiryDomain;
import kr.co.sist.domain.ReportDomain;
import kr.co.sist.service.InquiryService;
import kr.co.sist.vo.SearchVO;

@Controller
public class InquiryController {

	@Autowired(required = false)
	private InquiryService is;
	
	@GetMapping("/inquiry/inquiry_result.do")
	public String searchInquiry( 
			SearchVO sVO,Model model) {
			
		sVO.setStartNum(1);
		sVO.setEndNum(10);
		System.out.println("-------------------------------------"+sVO);
		
		List<InquiryDomain> list=is.searchInquiry(sVO);
		
		model.addAttribute("listInquiry", list);
		
		return "inquiry/inquiry_result";
	}	
	

	@GetMapping("/inquiry/inquiry_detail00.do")
    public String searchDetailInquiry(HttpServletRequest request, Model model) {
        String inquiryNum = request.getParameter("inquiry_num");
        InquiryDomain id = is.searchDetailInquiry(inquiryNum);

        model.addAttribute("id", id);

        return "inquiry/inquiry_detail00";	
    }

	@PostMapping("/inquiry/updateInquiry.do")
	public String updateInquiry(InquiryDomain id, HttpSession session) {
		try {
			is.updateInquiry(id);
			session.setAttribute("message", "문의가 성공적으로 수정되었습니다.");
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			session.setAttribute("message", "문의 수정에 실패하셨습니다.");
		}
		return "redirect:/inquiry/inquiry_detail00.do?inquiry_num=" + id.getInquiry_num();
	}
	
	  //spring_mvc/inquiry/deleteInquiry.do
    @PostMapping("/inquiry/deleteInquiry.do")
    public String deleteInquiry(InquiryDomain id, HttpSession session) {
        try {
            is.deleteInquiry(id);
            session.setAttribute("message", "문의가 성공적으로 삭제되었습니다.");
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            session.setAttribute("message", "문의 삭제에 실패하셨습니다.");
        }
        return "redirect:/inquiry/inquiry_result.do";
    }
	
}
