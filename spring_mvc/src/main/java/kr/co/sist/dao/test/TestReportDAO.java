package kr.co.sist.dao.test;

import java.util.List;


import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.sist.dao.MyBatisDAO;
import kr.co.sist.domain.InquiryDomain;
import kr.co.sist.domain.ReportDomain;
import kr.co.sist.vo.SearchVO;

@Component
public class TestReportDAO {

	@Autowired(required = false)
	private MyBatisDAO mbDAO; 
	
	public List<ReportDomain> selectReport( SearchVO sVO)throws PersistenceException {
		
		List<ReportDomain> list=null;
		SqlSession ss=mbDAO.getMyBatisHandler(false);
		System.out.println("-----------"+ list);
		list=ss.selectList("kr.co.sist.report.selectReportList", sVO);
		mbDAO.closeHandler(ss);
		return list;
	}
	
	 public int selectTotalCount(SearchVO sVO) throws PersistenceException {
		 
	        int totalCount = 0;
	        SqlSession ss = mbDAO.getMyBatisHandler(false);
	        totalCount = ss.selectOne("kr.co.sist.report.selectTotalCount", sVO);
	        mbDAO.closeHandler(ss);
	        return totalCount;
	    }
	 
	 public ReportDomain selectDetailReport(String rep_num) throws PersistenceException {
		 
		    ReportDomain report = null;
		    SqlSession ss = mbDAO.getMyBatisHandler(false);
		    report = ss.selectOne("kr.co.sist.report.selectDetailReport", rep_num);
		    mbDAO.closeHandler(ss);
		    return report;
		}

	 
	 public void updateReport(ReportDomain rd) throws PersistenceException {
	        SqlSession ss = mbDAO.getMyBatisHandler(true);
	        try {
	        	System.out.println(rd);
	            ss.update("kr.co.sist.report.updateReport", rd);
	        
	        } catch (PersistenceException pe) {
	         System.out.println(pe);
	           
	        } finally {
	            mbDAO.closeHandler(ss);
	        }
	    }
	 
	   
	
}
  