package kr.co.sist.dao.test;

import java.util.List;


import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.sist.dao.MyBatisDAO;
import kr.co.sist.domain.InquiryDomain;
import kr.co.sist.vo.SearchVO;

@Component
public class TestInquiryDAO { 

	@Autowired(required = false)
	private MyBatisDAO mbDAO;
	
	public List<InquiryDomain> selectInquiry( SearchVO sVO)throws PersistenceException {
	
		List<InquiryDomain> list=null;
		SqlSession ss=mbDAO.getMyBatisHandler(false);
		System.out.println("-----------"+ list);
		list=ss.selectList("kr.co.sist.inquiry.selectInquiryList", sVO);
		mbDAO.closeHandler(ss);
		return list;
	}
	
	 public int selectTotalCount(SearchVO sVO) throws PersistenceException {
		 
	        int totalCount = 0;
	        SqlSession ss = mbDAO.getMyBatisHandler(false);
	        totalCount = ss.selectOne("kr.co.sist.inquiry.selectTotalCount", sVO);
	        mbDAO.closeHandler(ss);
	        return totalCount;
	    }
	 
	 public InquiryDomain selectDetailInquiry(String inquiry_num) throws PersistenceException {
		 
		    InquiryDomain inquiry = null;
		    SqlSession ss = mbDAO.getMyBatisHandler(false);
		    inquiry = ss.selectOne("kr.co.sist.inquiry.selectDetailInquiry", inquiry_num);
		    mbDAO.closeHandler(ss);
		    return inquiry;
		}

	 
	 public void updateInquiry(InquiryDomain id) throws PersistenceException {
	        SqlSession ss = mbDAO.getMyBatisHandler(true);
	        try {
	        	
	            ss.update("kr.co.sist.inquiry.updateInquiry", id);
	           
	        } catch (PersistenceException pe) {
	        
	        	 
	        } finally {
	            mbDAO.closeHandler(ss);
	        }
	    }
	 
	    public void deleteInquiry(InquiryDomain id) throws PersistenceException {
	        SqlSession ss = mbDAO.getMyBatisHandler(true);
	        try {
	        
	        	ss.update("kr.co.sist.inquiry.updateDeleteFlag", id);
	          
	        } catch (PersistenceException pe) {
	        
	            
	        } finally {
	            mbDAO.closeHandler(ss);
	        }
	    }
	
}
