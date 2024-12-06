package com.example.ProKOi.MainController.Team;

import com.example.ProKOi.DTO.Team.RequestDto;
import com.example.ProKOi.DTO.Team.ResponseDto;
import com.example.ProKOi.Service.Team.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/teamMate")
public class teamControler {


   private final TeamService teamService;

    public teamControler(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addTeamMate(@RequestBody RequestDto requestDto){

        log.debug("Received RequestDto: {}", requestDto);
        return teamService.addTeamMate(requestDto);


    }
}
