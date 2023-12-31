package com.orientalSalad.troubleShot.member.converter;

import org.springframework.stereotype.Component;

import com.orientalSalad.troubleShot.global.utill.ObjectConverter;
import com.orientalSalad.troubleShot.member.dto.MemberDTO;
import com.orientalSalad.troubleShot.member.entity.MemberEntity;

@Component
public class MemberConverter implements ObjectConverter<MemberDTO, MemberEntity> {
	@Override
	public MemberEntity toEntity(MemberDTO memberDTO) {
		return MemberEntity.builder()
			.email(memberDTO.getEmail())
			.nickname(memberDTO.getNickname())
			.password(memberDTO.getPassword())
			.profileImg(memberDTO.getProfileImg())
			.locale(memberDTO.getLocale())
			.build();
	}

	@Override
	public MemberDTO toDTO(MemberEntity memberEntity) {
		return MemberDTO.builder()
			.seq(memberEntity.getSeq())
			.email(memberEntity.getEmail())
			.nickname(memberEntity.getNickname())
			.locale(memberEntity.getLocale())
			.profileImg(memberEntity.getProfileImg())
			.profileImg(memberEntity.getProfileImg())
			.createTime(memberEntity.getCreateTime())
			.deleteTime(memberEntity.getDeleteTime())
			.updateTime(memberEntity.getUpdateTime())
			.build();
	}
}
