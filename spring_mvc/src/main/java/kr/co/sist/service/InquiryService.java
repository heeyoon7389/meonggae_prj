package kr.co.sist.service;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kr.co.sist.dao.test.TestInquiryDAO;
import kr.co.sist.domain.InquiryDomain;
import kr.co.sist.domain.ReportDomain;
import kr.co.sist.vo.SearchVO;

@Service
public class InquiryService {
	 
	@Autowired(required = false)
	private TestInquiryDAO  tiDAO;
	
	public List<InquiryDomain> searchInquiry(SearchVO sVO){
		List<InquiryDomain> list=null;
		try {
			
			list=tiDAO.selectInquiry(sVO);//시작번호와 끝번호 사이의 글 조회
			
		}catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		return list;
	}//searchInquiry
	
	  public int getTotalCount(SearchVO sVO) {
		  
		    int totalCount = 0;
		    try {
		      totalCount = tiDAO.selectTotalCount(sVO);
		    } catch (PersistenceException pe) {
		      pe.printStackTrace();
		    }
		    return totalCount;
		  }
	  
	  public InquiryDomain searchDetailInquiry(String inquiry_num) {
		  
	        InquiryDomain inquiry = null;
	        try {
	            inquiry = tiDAO.selectDetailInquiry(inquiry_num);
	        } catch (PersistenceException pe) {
	            pe.printStackTrace();
	        }
	        return inquiry;
	    }
	
	  public void updateInquiry(InquiryDomain id) {
			try {
				tiDAO.updateInquiry(id);
			} catch (PersistenceException pe) {
				pe.printStackTrace();
			}
		}
	
	
	    public void deleteInquiry(InquiryDomain id) {
	        try {
	            tiDAO.deleteInquiry(id);
	        } catch (PersistenceException pe) {
	            pe.printStackTrace();
	        }
	    }
	
}
