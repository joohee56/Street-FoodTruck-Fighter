package com.ssafy.foodtruck.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AcceptOrdersReq {

	private Integer ordersId;
	private Integer doneDate;
}
