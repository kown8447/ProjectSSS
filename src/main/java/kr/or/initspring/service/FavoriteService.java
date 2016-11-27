package kr.or.initspring.service;

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

		List<LinkDataDTO> userList = favoritedao.getUserFavoriteList(memberid);

		List<LinkDataDTO> subjectLinks = new ArrayList<LinkDataDTO>();
		List<LinkDataDTO> allUserLinks = new ArrayList<LinkDataDTO>();
		List<LinkDataDTO> studentInfoLinks = new ArrayList<LinkDataDTO>();
		List<LinkDataDTO> enrollLinks = new ArrayList<LinkDataDTO>();
		List<LinkDataDTO> adminLinks = new ArrayList<LinkDataDTO>();

		for (int i = 0; i < configList.size(); i++) {
			for (int j = 0; j < userList.size(); j++) {
				if (configList.get(i).getLink_code().equals(userList.get(j).getLink_code())) {
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

		model.addAttribute("allUserLinks", allUserLinks);
		model.addAttribute("studentInfoLinks", studentInfoLinks);
		model.addAttribute("enrollLinks", enrollLinks);
		model.addAttribute("subjectLinks", subjectLinks);
		model.addAttribute("adminLinks", adminLinks);

	}
}
