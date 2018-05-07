package com.sms.service.score.impl;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.sms.entity.CourseMessage;
import com.sms.entity.Page;
import com.sms.entity.Score;
import com.sms.service.BaseService;
import com.sms.service.score.ScoreService;
import com.sms.util.Const;
import com.sms.util.PageData;

/**
* @author 蔡名锋:
* @version 创建时间：2018年5月4日 上午12:46:40
* <pre>
* 类说明:
* </pre>
*/
@Service("scoreService")
public class ScoreServiceImpl extends BaseService implements ScoreService {

	public List<PageData> listPdPageStudent(Page page) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>) dao.findForList("ScoreMapper.scorelistPage", page);
	}

	public String deleteStudentByGradeId(PageData pd) throws Exception {
		String msg = "";
		String[] ids = pd.getString("gradeIds").split(",");
		for (int i = 0; i < ids.length; i++) {
			dao.delete("ScoreMapper.deleteByPrimaryKey", ids[i]);
		}
		msg = "success";
		return msg;
	}

	public String addAllS(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		String msg = "";
		String[] snos = pd.getString("snos").split(",");
		
		for (int i = 0; i < snos.length; i++) {
			pd.put("sno", snos[i]);
			int count = (int) dao.findForObject("ScoreMapper.findBySnoAndCoursesMessageId", pd);
			if (count == 0) {
				
				Subject user = SecurityUtils.getSubject();
				Session session = user.getSession();
				
				pd.put("createdBy", session.getAttribute(Const.SESSION_USERNAME));
				pd.put("creationDate", new Date());
				
				pd.put("term", getTerm());
				dao.insert("ScoreMapper.insertSelective", pd);
			}
		}
		
		msg="success";
		return msg;
	}

	//展示教师没有提交过成绩的教学班
	public List<CourseMessage> findTeachingClass(Page page) throws Exception {
		Subject user = SecurityUtils.getSubject();
		Session session = user.getSession();
		
		page.getPd().put("tno", session.getAttribute(Const.SESSION_USERNAME));
		page.getPd().put("editScore", "N");
		// TODO Auto-generated method stub
		return (List<CourseMessage>)dao.findForList("CourseMessageMapper.listPageCMByTno", page);
	}

	public void editScore(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		dao.update("CourseMessageMapper.updateScoreType", pd);
	}

	public List<PageData> findByCoursesMessageId(Page page) throws Exception {
		
		return (List<PageData>) dao.findForList("ScoreMapper.listPageStudentByCoursesMessageId", page);
	}

	public void saveScore(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		dao.update("ScoreMapper.saveScore", pd);
	}

	public void saveFinal(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		CourseMessage cm = (CourseMessage) dao.findForObject("CourseMessageMapper.findByCoursesMessageId", pd);
		
		int scoreType = cm.getScoreType();
		switch (scoreType) {
		case 1:
			List<Score> scores = (List<Score>) dao.findForList("ScoreMapper.findByCoursesMessageId", pd);
			totalScore(cm.getRegularPer(),cm.getExamPer(),scores);
			break;
		case 2:
			List<Score> scores2 = (List<Score>) dao.findForList("ScoreMapper.findByCoursesMessageId", pd);
			totalScoreGpa(scores2);
			break;
		default:
			break;
		}
		dao.update("CourseMessageMapper.updateEditScore", pd);
	}

	private void totalScoreGpa(List<Score> scores2) throws Exception {
		for (Score score : scores2) {
			String finalGrade = score.getFinalGrade();
			Double gpa = 0.0;
			switch (finalGrade) {
			case "优秀":
				gpa = 4.5;
				break;
			case "良好":
				gpa = 3.5;
				break;
			case "中等":
				gpa = 2.5;
				break;
			case "及格":
				gpa = 1.5;
				break;
			default:
				break;
			}
			score.setGpa(gpa);
			dao.update("ScoreMapper.editGpa", score);
		}
		
	}

	private void totalScore(double regularPer,double examPer,List<Score> scores) throws Exception {
		if (scores==null) {
			return;
		}
		
		for (Score score : scores) {
			Double reGrade = 0.0;
			Double exGrade = 0.0;
			Double gpa = 0.0;
			Double finalGrade=0.0;
			
			if (score.getRegularGrade()!=null) {
				reGrade = Double.parseDouble(score.getRegularGrade());
			}
			if (score.getExamGrade()!=null) {
				exGrade = Double.parseDouble(score.getExamGrade());
			}
			
			finalGrade = reGrade*regularPer+examPer*exGrade;
			Double a = finalGrade%10*0.1;
			switch (finalGrade.intValue()/10) {
			case 10:
				gpa = 5.0;
				break;
			case 9:
				gpa = 4.0+a;
				break;
			case 8:
				gpa = 3.0+a;
				break;
			case 7:
				gpa = 2.0+a;
				break;
			case 6:
				gpa = 1.0+a;
				break;
			default:
				break;
			}
			
			score.setGpa(gpa);
			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setMaximumFractionDigits(2);
			score.setFinalGrade(nf.format(finalGrade));
			dao.update("ScoreMapper.editFinalGrade", score);
		}
		
	}
	
	//展示教师所有教学班
	public List<CourseMessage> findTeachingClass2(Page page) throws Exception {
		Subject user = SecurityUtils.getSubject();
		Session session = user.getSession();
		
		page.getPd().put("tno", session.getAttribute(Const.SESSION_USERNAME));
		// TODO Auto-generated method stub
		return (List<CourseMessage>)dao.findForList("CourseMessageMapper.listPageCMByTno", page);
	}
	
	//展示教师课程表
	public List<PageData> findCourseMessage(Page page) throws Exception {
		Subject user = SecurityUtils.getSubject();
		Session session = user.getSession();
		PageData pd = page.getPd();
		pd.put("tno", session.getAttribute(Const.SESSION_USERNAME));
		
		if(pd.getString("term") == null|| pd.getString("term") == ""){
			pd.put("term", getTerm());
		}
		
		if(pd.getString("week")==null||pd.getString("week")==""){
			pd.put("week", 1);
		}
		return (List<PageData>)dao.findForList("CourseMessageMapper.listPageCMByTno2", page);
	}

	public List<PageData> findBySno(Page page) throws Exception {
		// TODO Auto-generated method stub
		PageData pd = page.getPd();
		if (pd.getString("term") == null || pd.getString("term") == "") {
			pd.put("term", getTerm());
		}
		
		Subject user = SecurityUtils.getSubject();
		Session session = user.getSession();
		pd.put("sno", session.getAttribute(Const.SESSION_USERNAME));
		
		return (List<PageData>) dao.findForList("ScoreMapper.listPageScoreBySno", page);
	}

	

	public Score findByGradeId(PageData pd) throws Exception {
		return (Score) dao.findForObject("ScoreMapper.findByGradeId", pd);
	}
	
	
	private String getTerm(){
		Calendar date = Calendar.getInstance();
	    String term = String.valueOf(date.get(Calendar.YEAR)+"年");
		int month = date.get(Calendar.MONTH);
		if (month >= 1 && month <= 7) {
			term = term + " 春季";
		} else {
			term = term + " 秋季";
		}
		return term;
	}

	public Map<String, Object> calculationGpa(PageData pd) throws Exception {
		Map<String, Object> data = new HashMap<>();
		
		Subject user = SecurityUtils.getSubject();
		Session session = user.getSession();
		pd.put("sno", session.getAttribute(Const.SESSION_USERNAME));
		
		List<PageData> list = (List<PageData>) dao.findForList("ScoreMapper.findBySno", pd);
		if (!list.isEmpty()) {
			int size = list.size();
			double sum = 0.0;
			double sumCredit = 0.0;
			for (int i = 0; i < size; i++) {
				sum = Double.parseDouble(list.get(i).get("count").toString()) + sum;
				sumCredit = Double.parseDouble(list.get(i).get("credit").toString()) + sumCredit;
			}
			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setMaximumFractionDigits(2);
			String avgGpa = nf.format(sum/sumCredit);
			data.put("avgGpa", avgGpa);
			data.put("msg", "success");
			data.put("term", pd.get("term"));
		}else {
			data.put("msg", "该学期没有成绩");
		}
		return data;
	}
	
	//展示学生课程表
	public List<PageData> findCourseMessage2(Page page) throws Exception {
			Subject user = SecurityUtils.getSubject();
			Session session = user.getSession();
			PageData pd = page.getPd();
			pd.put("sno", session.getAttribute(Const.SESSION_USERNAME));
			
			if(pd.getString("term") == null|| pd.getString("term") == ""){
				pd.put("term", getTerm());
			}
			
			if(pd.getString("week")==null||pd.getString("week")==""){
				pd.put("week", 1);
			}
			return (List<PageData>)dao.findForList("CourseMessageMapper.listPageCMBySno", page);
		}
}
