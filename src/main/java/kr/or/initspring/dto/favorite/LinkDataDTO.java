package kr.or.initspring.dto.favorite;

public class LinkDataDTO {
	private String Link_code;
	private int link_type;
	private int typeName;
	private String link_name;
	private String link_addr;
	private boolean favorite;

	public String getLink_code() {
		return Link_code;
	}

	public void setLink_code(String link_code) {
		Link_code = link_code;
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

	@Override
	public String toString() {
		return "LinkDataDTO [Link_code=" + Link_code + ", link_type=" + link_type + ", typeName=" + typeName
				+ ", link_name=" + link_name + ", link_addr=" + link_addr + ", favorite=" + favorite + "]";
	}

}
