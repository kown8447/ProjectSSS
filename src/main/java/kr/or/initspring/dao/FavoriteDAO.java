package kr.or.initspring.dao;

import java.util.List;

import kr.or.initspring.dto.favorite.LinkDataDTO;

public interface FavoriteDAO {
	public List<LinkDataDTO> getAllLinkList();
	public List<LinkDataDTO> getUserFavoriteList(String memberid);
	public String getUserRole(String memberid);
	public List<String> getRoleList(String link_code);
	public int favoriteAppend(LinkDataDTO link);
	public int favoriteDelete(LinkDataDTO link);
}
