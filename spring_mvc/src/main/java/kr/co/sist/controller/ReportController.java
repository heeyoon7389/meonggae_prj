package kr.co.sist.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.sist.domain.InquiryDomain;
import kr.co.sist.domain.ReportDomain;
import kr.co.sist.service.InquiryService;
import kr.co.sist.service.ReportService;
import kr.co.sist.vo.SearchVO;

@Controller
public class ReportController {

	@Autowired(required = false)
	private ReportService rs;
	
	@GetMapping("/report/report_result.do")
	public String searchReport( 
			SearchVO sVO,Model model) {
			
		sVO.setStartNum(1);
		sVO.setEndNum(10);
		System.out.println("-------------------------------------"+sVO);
		
		List<ReportDomain> list=rs.searchReport(sVO);
		
		model.addAttribute("listReport", list);
		
		return "report/report_result";
	}	
	

	@GetMapping("/report/report_detail.do")
    public String searchDetailReport(HttpServletRequest request, Model model) {
        String reportNum = request.getParameter("rep_num");
        ReportDomain rd = rs.searchDetailReport(reportNum);

        model.addAttribute("rd", rd);

        return "report/report_detail";	
    }


	@PostMapping("/report/updateReport.do")
	public String updateReport(ReportDomain rd, HttpSession session) {
		try {
			rs.updateReport(rd);
			session.setAttribute("message", "신고가 성공적으로 수정되었습니다.");
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			session.setAttribute("message", "신고 수정에 실패하셨습니다.");
		}
		return "redirect:/report/report_detail.do?rep_num=" + rd.getRep_num();
	}
	
	
}
