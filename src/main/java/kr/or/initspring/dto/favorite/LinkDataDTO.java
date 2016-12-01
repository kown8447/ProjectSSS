package kr.or.initspring.dto.favorite;

public class LinkDataDTO {
	private String link_code;
	private int link_type;
	private int typeName;
	private String link_name;
	private String link_addr;
	private boolean favorite;
	private String member_id;

	public String getLink_code() {
		return link_code;
	}

	public void setLink_code(String link_code) {
		this.link_code = link_code;
	}

	public int getLink_type() {
		return link_type;
	}

	public void setLink_type(int link_type) {
		this.link_type = link_type;
	}

	public int getTypeName() {
		return typeName;
	}

	public void setTypeName(int typeName) {
		this.typeName = typeName;
	}

	public String getLink_name() {
		return link_name;
	}

	public void setLink_name(String link_name) {
		this.link_name = link_name;
	}

	public String getLink_addr() {
		return link_addr;
	}

	public void setLink_addr(String link_addr) {
		this.link_addr = link_addr;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	@Override
	public String toString() {
		return "LinkDataDTO [link_code=" + link_code + ", link_type=" + link_type + ", typeName=" + typeName
				+ ", link_name=" + link_name + ", link_addr=" + link_addr + ", favorite=" + favorite + ", member_id="
				+ member_id + "]";
	}

}
