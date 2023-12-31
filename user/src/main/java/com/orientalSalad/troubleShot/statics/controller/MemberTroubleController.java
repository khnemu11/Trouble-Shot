package com.orientalSalad.troubleShot.statics.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orientalSalad.troubleShot.statics.dto.RequestTroubleHistoryDTO;
import com.orientalSalad.troubleShot.statics.dto.ResponseCountTroubleDTO;
import com.orientalSalad.troubleShot.statics.dto.ResponsePolygonDTO;
import com.orientalSalad.troubleShot.statics.dto.ResponseTroubleHistoryDTO;
import com.orientalSalad.troubleShot.statics.dto.ResponseTroubleShootingTypeGroupDTO;
import com.orientalSalad.troubleShot.statics.dto.TroubleShootingHistoryDTO;
import com.orientalSalad.troubleShot.statics.dto.TroubleShootingTypeGroupDTO;
import com.orientalSalad.troubleShot.statics.service.StaticsService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(path = "/members/{userSeq}/trouble-shootings")
@RequiredArgsConstructor
@Log4j2
public class MemberTroubleController {
	private final StaticsService staticsService;

	@Operation(summary = "해결/미해결 트러블 슈팅 문서 개수 가져오기")
	@GetMapping("/by-solve")
	public ResponseEntity<?> get (
		@PathVariable(name = "userSeq") Long userSeq){
		log.info("==== 해결/미해결 트러블 슈팅 문서 개수 가져오기 시작 ====");

		long solvedCount = staticsService.getSolvedTroubleCount(userSeq);
		long notSolvedCount = staticsService.getNotSolvedTroubleCount(userSeq);

		ResponseCountTroubleDTO resultDTO = ResponseCountTroubleDTO.builder()
			.success(true)
			.message("해결/미해결 트러블 슈팅 문서 개수 가져오기를 성공했습니다.")
			.solvedCount(solvedCount)
			.notSolvedCount(notSolvedCount)
			.totalCount(solvedCount+notSolvedCount)
			.build();

		log.info("==== 해결/미해결 트러블 슈팅 문서 개수 가져오기 끝 ====");
		return new ResponseEntity<>(resultDTO, HttpStatus.ACCEPTED);
	}
	@Operation(summary = "작성한 트러블슈팅 히스토리")
	@GetMapping("/history")
	public ResponseEntity<?> getTroubleShootingHistory (
		@PathVariable(name = "userSeq") Long userSeq,
		@ModelAttribute RequestTroubleHistoryDTO requestTroubleHistoryDTO){
		log.info("==== 작성한 트러블슈팅 히스토리 가져오기 시작 ====");
		log.info("[PARAM] : "+requestTroubleHistoryDTO);
		List<TroubleShootingHistoryDTO> troubleShootingHistoryDTOList = staticsService.getAllTroubleShootingHistory(requestTroubleHistoryDTO);

		ResponseTroubleHistoryDTO resultDTO = ResponseTroubleHistoryDTO.builder()
			.success(true)
			.message("작성한 트러블슈팅 히스토리 가져오기를 성공했습니다.")
			.troubleShootingHistoryList(troubleShootingHistoryDTOList)
			.build();

		log.info("==== 작성한 트러블슈팅 히스토리 가져오기 끝 ====");
		return new ResponseEntity<>(resultDTO, HttpStatus.ACCEPTED);
	}
	@Operation(summary = "등록한 기기별 트러블 슈팅 문서 개수")
	@GetMapping("/by-post-type")
	public ResponseEntity<?> countTroubleShootingByPostType (
		@PathVariable(name = "userSeq") Long userSeq){
		log.info("==== 등록한 기기별 트러블 슈팅 문서 개수 가져오기 시작 ====");

		List<TroubleShootingTypeGroupDTO> groupDTOList
			= staticsService.countAllTroubleShootingByPostType(userSeq);

		int totalCount = 0;

		for(int i=0; i<groupDTOList.size(); i++){
			totalCount+= groupDTOList.get(i).getCount();
		}

		ResponseTroubleShootingTypeGroupDTO resultDTO
			= ResponseTroubleShootingTypeGroupDTO.builder()
			.success(true)
			.message("등록한 기기별 트러블 슈팅 문서 개수 가져오기를 성공했습니다.")
			.troubleShootingTypeGroupList(groupDTOList)
			.totalCount(totalCount)
			.build();

		log.info("==== 등록한 기기별 트러블 슈팅 문서 개수 가져오기 끝 ====");
		return new ResponseEntity<>(resultDTO, HttpStatus.ACCEPTED);
	}


}
