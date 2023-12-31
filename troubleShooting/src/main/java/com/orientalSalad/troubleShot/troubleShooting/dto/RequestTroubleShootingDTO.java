package com.orientalSalad.troubleShot.troubleShooting.dto;

import java.util.List;
import java.util.Set;

import com.orientalSalad.troubleShot.global.dto.BaseDTO;
import com.orientalSalad.troubleShot.global.dto.RequestDTO;
import com.orientalSalad.troubleShot.member.dto.SimpleMemberDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestTroubleShootingDTO extends RequestDTO {
	@Schema(description = "트러블 슈팅 문서")
	private TroubleShootingDTO troubleShooting;
}