package com.example.ProKOi.Service.Team;

import com.example.ProKOi.DTO.Team.RequestDto;
import com.example.ProKOi.DTO.Team.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface TeamService {
       ResponseEntity<ResponseDto> addTeamMate(RequestDto requestDto);

       ResponseEntity<ResponseDto> AcceptState(RequestDto requestDto);
       ResponseEntity<ResponseDto> RejectState(RequestDto requestDto);




}
