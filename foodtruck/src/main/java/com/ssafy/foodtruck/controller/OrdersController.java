package com.ssafy.foodtruck.controller;

import com.ssafy.foodtruck.dto.*;
import com.ssafy.foodtruck.model.service.OrdersService;
import com.ssafy.foodtruck.util.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ssafy.foodtruck.db.entity.Message.AUTHORIZATION;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrdersController {

	private final OrdersService ordersService;

	@PostMapping("/customer")
	@ApiOperation(value = "주문내역 등록", notes = "<strong>사용자가 주문내역을 등록한다.</strong>")
	public ResponseEntity<?> registerOrders(@RequestHeader(AUTHORIZATION) String bearerToken, @RequestBody List<RegisterOrdersReq> registerOrdersReqList) {
		int customerId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		ordersService.registerOrders(customerId, registerOrdersReqList);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PatchMapping("/ceo/{ordersId}")
	@ApiOperation(value = "Orders ID로 주문 접수", notes = "<strong>Orders ID를 통해 주문을 접수한다.</strong>")
	public ResponseEntity<?> acceptOrders(@RequestHeader(AUTHORIZATION) String bearerToken, @PathVariable int ordersId) {
		int ceoId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		ordersService.acceptOrders(ceoId, ordersId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/customer")
	@ApiOperation(value = "현재 주문내역 조회 - 사용자", notes = "<strong>Customer ID를 통해 현재 주문내역 조회를 한다.</strong>")
	public ResponseEntity<List<CurrentOrdersHistoryRes>> getCustomerOrders(@RequestHeader(AUTHORIZATION) String bearerToken) {
		int customerId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		return new ResponseEntity<>(ordersService.getCustomerOrders(customerId), HttpStatus.OK);
	}

	@GetMapping("/customer/all")
	@ApiOperation(value = "전체 주문내역 조회 - 사용자", notes = "<strong>Customer ID를 통해 전체 주문내역 조회를 한다.</strong>")
	public ResponseEntity<List<OrdersHistoryRes>> getCustomerOrdersAll(@RequestHeader(AUTHORIZATION) String bearerToken) {
		int customerId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		return new ResponseEntity<>(ordersService.getCustomerOrdersAll(customerId), HttpStatus.OK);
	}

	@GetMapping("/ceo")
	@ApiOperation(value = "현재 주문내역 조회 - 사업자", notes = "<strong>Ceo ID를 통해 현재 주문내역 조회를 한다.</strong>")
	public ResponseEntity<List<CurrentOrdersListByFoodtruckRes>> getCeoOrders(@RequestHeader(AUTHORIZATION) String bearerToken) {
		int ceoId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		return new ResponseEntity<>(ordersService.getCeoOrders(ceoId), HttpStatus.OK);
	}

	@GetMapping("/ceo/all")
	@ApiOperation(value = "전체 주문내역 조회 - 사업자", notes = "<strong>Ceo ID를 통해 전체 주문내역 조회를 한다.</strong>")
	public ResponseEntity<List<OrdersListByFoodtruckRes>> getCeoOrdersAll(@RequestHeader(AUTHORIZATION) String bearerToken) {
		int ceoId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		return new ResponseEntity<>(ordersService.getCeoOrdersAll(ceoId), HttpStatus.OK);
	}

	@PatchMapping("/{orderId}")
	@ApiOperation(value = "Orders ID로 주문 취소", notes = "<strong>Orders ID를 통해 주문을 취소한다.</strong>")
	public ResponseEntity<?> cancelOrders(@RequestHeader(AUTHORIZATION) String bearerToken, @PathVariable int orderId) {
		int ceoId = JwtTokenUtil.getUserIdFromBearerToken(bearerToken);
		ordersService.cancelOrders(ceoId, orderId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
