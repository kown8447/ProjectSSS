package kr.or.initspring.service;
/*
 * @Class :  FavoriteService 
 * @Date : 2016.11.16
 * @Author : 최준호
 * @Desc
 * 즐겨찾기 서비스
*/
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kr.or.initspring.dao.FavoriteDAO;
import kr.or.initspring.dto.favorite.LinkDataDTO;

@Service
public class FavoriteService {
	@Autowired
	private SqlSession sqlsession;
	/*
	 * @method Name : getFavariteConfigData
	 * @Author : 최준호
	 * @description 
	 * 즐겨찾기 링크들을 가져와 종류별로 정리하고 사용자의 즐겨찾기 목록을 가져와
	 * 전체 링크둘중 해당하는 항목에 체크한후 사용자에게 전달을 위한 model 객체에 담는 함수
	 */
	public void getFavariteConfigData(String memberid, Model model) {
		FavoriteDAO favoritedao = sqlsession.getMapper(FavoriteDAO.class);
		String role = favoritedao.getUserRole(memberid);
		List<LinkDataDTO> allList = favoritedao.getAllLinkList();
		List<LinkDataDTO> configList = new ArrayList<LinkDataDTO>();
		for (int i = 0; i < allList.size(); i++) {
			boolean configOk = false;
			List<String> roleList = favoritedao.getRoleList(allList.get(i).getLink_code());
			for (int j = 0; j < roleList.size(); j++) {
				if (roleList.get(j).equals(role)) {
					configOk = true;
					break;
				}
			}
			if (configOk) {
				configList.add(allList.get(i));
			}
		}
		List<LinkDataDTO> subjectLinks = new ArrayList<LinkDataDTO>();
		List<LinkDataDTO> allUserLinks = new ArrayList<LinkDataDTO>();
		List<LinkDataDTO> studentInfoLinks = new ArrayList<LinkDataDTO>();
		List<LinkDataDTO> enrollLinks = new ArrayList<LinkDataDTO>();
		List<LinkDataDTO> adminLinks = new ArrayList<LinkDataDTO>();
		List<LinkDataDTO> favoLinks = favoritedao.getUserFavoriteList(memberid);
		for (int i = 0; i < configList.size(); i++) {
			for (int j = 0; j < favoLinks.size(); j++) {
				if(configList.get(i).getLink_code().equals(favoLinks.get(j).getLink_code())){
					configList.get(i).setFavorite(true);
				}	
			}
			if (configList.get(i).getLink_type() == 0) {
				allUserLinks.add(configList.get(i));
			} else if (configList.get(i).getLink_type() == 1) {
				studentInfoLinks.add(configList.get(i));
			} else if (configList.get(i).getLink_type() == 2) {
				enrollLinks.add(configList.get(i));
			} else if (configList.get(i).getLink_type() == 3) {
				subjectLinks.add(configList.get(i));
			} else if (configList.get(i).getLink_type() == 4) {
				adminLinks.add(configList.get(i));
			}
		}

		model.addAttribute("favoLinks", favoLinks);
		model.addAttribute("allUserLinks", allUserLinks);
		model.addAttribute("studentInfoLinks", studentInfoLinks);
		model.addAttribute("enrollLinks", enrollLinks);
		model.addAttribute("subjectLinks", subjectLinks);
		model.addAttribute("adminLinks", adminLinks);

	}
	/*
	 * @method Name : getFavariteList
	 * @Author : 최준호
	 * @description 
	 * 사용자의 즐겨찾기 목록을 가져오는 메서드
	 */
	public void getFavariteList(String memberid, Model model) {
		FavoriteDAO favoritedao = sqlsession.getMapper(FavoriteDAO.class);
		List<LinkDataDTO> favoLinks = favoritedao.getUserFavoriteList(memberid);
		model.addAttribute("favoLinks", favoLinks);
	}
	/*
	 * @method Name : appendFavorite
	 * @Author : 최준호
	 * @description 
	 * 회원의 즐겨찾기를 DB에 추가하는 메서드
	 */
	public boolean appendFavorite(String memberid, String link_code) {
		FavoriteDAO favoritedao = sqlsession.getMapper(FavoriteDAO.class);
		LinkDataDTO link = new LinkDataDTO();
		link.setMember_id(memberid);
		link.setLink_code(link_code);
	int resultNum=favoritedao.favoriteAppend(link);
		if(resultNum>0){
			return true;
		}else{
			return false;
		}
		
	}
	/*
	 * @method Name : deleteFavarite
	 * @Author : 최준호
	 * @description 
	 * 회원의 즐겨찾기를 DB에 삭제하는 메서드
	 */
	public boolean deleteFavarite(String memberid, String link_code) {
		FavoriteDAO favoritedao = sqlsession.getMapper(FavoriteDAO.class);
		LinkDataDTO link = new LinkDataDTO();
		link.setMember_id(memberid);
		link.setLink_code(link_code);
		int resultNum=favoritedao.favoriteDelete(link);
		if(resultNum>0){
			return true;
		}else{
			return false;
		}
	}
}
