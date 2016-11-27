package kr.or.initspring.dao;

import java.util.List;

import kr.or.initspring.dto.commons.AskTimeDTO;
import kr.or.initspring.dto.commons.BeforeSubjectDTO;
import kr.or.initspring.dto.commons.LiberalDTO;
import kr.or.initspring.dto.commons.MajorDTO;
import kr.or.initspring.dto.commons.PeriodDTO;
import kr.or.initspring.dto.commons.SubjectDTO;
import kr.or.initspring.dto.oprequest.OpClassRoomDTO;
import kr.or.initspring.dto.oprequest.OpRequestDetailDTO;
import kr.or.initspring.dto.oprequest.OpRequestPeriodDTO;
import kr.or.initspring.dto.oprequest.OpRequsetCheckDTO;

public interface OpRequestDAO {

	public List<OpRequsetCheckDTO> OpRequestList();
	public LiberalDTO Liberal(String subject_code);
	public MajorDTO Major(String subject_code);
	public OpRequestDetailDTO detail(String subject_code);
	public OpClassRoomDTO className(String subject_code);
	public List<AskTimeDTO> AskTime(String subject_code);
	public SubjectDTO Subject(String subject_code);
	public BeforeSubjectDTO Before(String subject_code);
	public List<PeriodDTO> Period(String period_code);
	public List<OpRequestPeriodDTO> OpPeriod(String subject_code);
	public int UpdateSuccess(String subject_code);
	public int UpdateReject(String subject_code);
	public int insertRejection(String subject_code, String reject_reason);
}
