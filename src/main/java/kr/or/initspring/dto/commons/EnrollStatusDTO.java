/*
 * @class Name : EnrollStatusDTO
 * @Date : 2016.11.19
 * @Author : 조장현
 * @Desc : 전공구분 테이블
*/

package kr.or.initspring.dto.commons;

public class EnrollStatusDTO {

	private int enroll_grade; // 학년
	private int enroll_active; // 수강신청 활성
	private int enroll_type; // 수강신청 상태(예비, 본, 정정)

	public int getEnroll_type() {
		return enroll_type;
	}

	public void setEnroll_type(int enroll_type) {
		this.enroll_type = enroll_type;
	}

	public int getEnroll_grade() {
		return enroll_grade;
	}

	public void setEnroll_grade(int enroll_grade) {
		this.enroll_grade = enroll_grade;
	}

	public int getEnroll_active() {
		return enroll_active;
	}

	public void setEnroll_active(int enroll_active) {
		this.enroll_active = enroll_active;
	}

	@Override
	public String toString() {

		return "EnrollStatusDTO [enroll_grade=" + enroll_grade + ", enroll_active=" + enroll_active + ", enroll_type="
				+ enroll_type + "]";
	}

}
