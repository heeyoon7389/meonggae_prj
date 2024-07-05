package kr.co.sist.service;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kr.co.sist.dao.test.TestInquiryDAO;
import kr.co.sist.dao.test.TestReportDAO;
import kr.co.sist.domain.InquiryDomain;
import kr.co.sist.domain.ReportDomain;
import kr.co.sist.vo.SearchVO;

@Service
public class ReportService {
	 
	@Autowired(required = false)
	private TestReportDAO  trDAO;
	
	public List<ReportDomain> searchReport(SearchVO sVO){
		List<ReportDomain> list=null;
		try {
			
			list=trDAO.selectReport(sVO);//시작번호와 끝번호 사이의 글 조회
			
		}catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		return list;
	}//searchReport
	
	  public int getTotalCount(SearchVO sVO) {
		  
		    int totalCount = 0;
		    try {
		      totalCount = trDAO.selectTotalCount(sVO);
		    } catch (PersistenceException pe) {
		      pe.printStackTrace();
		    }
		    return totalCount;
		  }
	  
	  public ReportDomain searchDetailReport(String rep_num) {
		  
	        ReportDomain report = null;
	        try {
	            report = trDAO.selectDetailReport(rep_num);
	        } catch (PersistenceException pe) {
	            pe.printStackTrace();
	        }
	        return report;
	    }
	
	  public void updateReport(ReportDomain rd) {
			try {
				trDAO.updateReport(rd);
			} catch (PersistenceException pe) {
				pe.printStackTrace();
			}
		}
	
	  
	
}
