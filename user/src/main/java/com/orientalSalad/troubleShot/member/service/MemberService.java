package com.orientalSalad.troubleShot.member.service;

import org.springframework.stereotype.Service;

import com.orientalSalad.troubleShot.global.dto.RequestDTO;
import com.orientalSalad.troubleShot.global.utill.HashEncrypt;
import com.orientalSalad.troubleShot.global.utill.ObjectConverter;
import com.orientalSalad.troubleShot.login.dto.LoginDTO;
import com.orientalSalad.troubleShot.member.dto.MemberDTO;
import com.orientalSalad.troubleShot.member.dto.RequestMemberDTO;
import com.orientalSalad.troubleShot.member.entity.MemberEntity;
import com.orientalSalad.troubleShot.member.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
	private final MemberRepository memberRepository;
	private final HashEncrypt hashEncrypt;
	private final ObjectConverter<MemberDTO,MemberEntity> memberConverter;

	@Transactional
	public Boolean insertMember(MemberDTO memberDTO){
		Long exist = memberRepository.countMemberEntityByEmail(memberDTO.getEmail());
		
		//이미 이메일이 존재하면
		if(exist > 0){
			log.info(memberDTO.getEmail()+"은 이미 존재하는 이메일 입니다.");
			return false;
		}
		
		//sha-256으로 비밀번호 해싱
		memberDTO.setPassword(hashEncrypt.hashWithSHA256(memberDTO.getPassword()));

		MemberEntity memberEntity = memberConverter.toEntity(memberDTO);

		memberRepository.save(memberEntity);

		return true;
	}

	public MemberDTO findMemberBySeq(Long seq){
		MemberEntity memberEntity = memberRepository.findMemberEntityBySeq(seq);

		MemberDTO memberDTO = memberConverter.toDTO(memberEntity);

		return memberDTO;
	}

	public MemberDTO findMemberByEmailAndPassword(LoginDTO loginDTO){
		//sha-256으로 비밀번호 해싱
		loginDTO.setPassword(hashEncrypt.hashWithSHA256(loginDTO.getPassword()));

		MemberEntity memberEntity = memberRepository.findMemberEntityByEmailAndPasswordAndDeleteTimeIsNull(loginDTO.getEmail(),loginDTO.getPassword());

		//없는 유저인 경우
		if(memberEntity == null){
			return null;
		}

		MemberDTO memberDTO = memberConverter.toDTO(memberEntity);

		return memberDTO;
	}
	public MemberDTO updateMember(RequestMemberDTO requestMemberDTO) throws Exception {
		if(requestMemberDTO.getLoginSeq() != requestMemberDTO.getMemberDTO().getSeq()){
			throw new Exception("로그인 정보와 회원 정보가 다릅니다.");
		}

		MemberDTO memberDTO = requestMemberDTO.getMemberDTO();

		MemberEntity memberEntity = memberRepository.findMemberEntityBySeq(memberDTO.getSeq());

		//없는 유저인 경우
		if(memberEntity == null){
			throw new Exception(memberDTO.getSeq()+"번은 없는 유저입니다.");
		}

		memberEntity.update(memberDTO);

		memberRepository.save(memberEntity);

		return memberDTO;
	}
	public boolean deleteMember(RequestDTO requestDTO, Long userSeq) throws Exception {
		if(requestDTO.getLoginSeq() != userSeq){
			throw new Exception("로그인 정보와 회원 정보가 다릅니다.");
		}

		MemberEntity memberEntity = memberRepository.findMemberEntityBySeq(userSeq);

		//없는 유저인 경우
		if(memberEntity == null){
			throw new Exception(userSeq+"번은 없는 유저입니다.");
		}

		memberRepository.delete(memberEntity);

		return true;
	}
}
