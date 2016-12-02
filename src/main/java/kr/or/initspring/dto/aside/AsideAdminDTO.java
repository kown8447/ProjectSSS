package kr.or.initspring.dto.aside;

public class AsideAdminDTO {
	private String admin_code;
	private String member_name;
	private String member_picture;

	public String getAdmin_code() {
		return admin_code;
	}

	public void setAdmin_code(String admin_code) {
		this.admin_code = admin_code;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_picture() {
		return member_picture;
	}

	public void setMember_picture(String member_picture) {
		this.member_picture = member_picture;
	}

	@Override
	public String toString() {
		return "AsideAdminDTO [admin_code=" + admin_code + ", member_name=" + member_name + ", member_picture="
				+ member_picture + "]";
	}

}
