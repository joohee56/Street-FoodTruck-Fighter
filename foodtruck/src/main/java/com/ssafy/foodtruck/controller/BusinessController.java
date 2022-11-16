package com.ssafy.foodtruck.controller;

import com.ssafy.foodtruck.db.entity.User;
import com.ssafy.foodtruck.dto.request.RegisterFoodtruckReq;
import com.ssafy.foodtruck.dto.response.GetBusinessRes;
import com.ssafy.foodtruck.model.service.BusinessService;
import com.ssafy.foodtruck.model.service.UserService;
import com.ssafy.foodtruck.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ssafy.foodtruck.constant.FoodtruckConstant.DUPLICATED_FOODTRUCK_ERROR_MESSAGE;
import static com.ssafy.foodtruck.constant.FoodtruckConstant.REGISTER_FOODTRUCK_SUCCESS;

@Api(value = "결산 API", tags = {"Business"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/business")
public class BusinessController {
	private final UserService userService;
	private final BusinessService businessService;

	@PostMapping
	@ApiOperation(value = "결산 등록", notes = "<strong>내 하루 결산을 등록한다.</strong>")
	public ResponseEntity<?> registerBusiness(@RequestHeader("Authorization") @ApiParam(value="Access Token", required = true) String bearerToken) {
		User user = userService.getUserByEmail(JwtTokenUtil.getEmailFromBearerToken(bearerToken));
		businessService.registerBusiness(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping
	@ApiOperation(value = "결산 확인", notes = "<strong>내 모든 결산을 가져온다.</strong>")
	public ResponseEntity<?> getBusinuess(@RequestHeader("Authorization") @ApiParam(value="Access Token", required = true) String bearerToken) {
		User user = userService.getUserByEmail(JwtTokenUtil.getEmailFromBearerToken(bearerToken));
		return new ResponseEntity<>(businessService.getBusinuess(user), HttpStatus.OK);
	}
}
