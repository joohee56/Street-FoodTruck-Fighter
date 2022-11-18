package com.ssafy.foodtruck.dto.request;

import com.ssafy.foodtruck.db.entity.Category;
import com.ssafy.foodtruck.dto.response.MenuReq;
import com.ssafy.foodtruck.dto.response.MenuRes;
import com.ssafy.foodtruck.dto.ScheduleDateDto;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RegisterFoodtruckReq {
	private List<MenuReq> menuList; // 메뉴리스트

	private String name; //상호명
	private Category category; //카테고리
	private String phone; //전화번호
	private String description; //설명
	private String address; //주소
	private Double latitude; // 위도
	private Double longitude; // 경도

	List<ScheduleDateDto> dateDtoList;	// 운영시간리스트

	private Map<String, MultipartFile> menuImgList; 	// 메뉴 이미지 리스트
}
