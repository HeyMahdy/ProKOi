package com.example.ProKOi.Service.Team;
import com.example.ProKOi.DTO.Team.RequestDto;
import com.example.ProKOi.DTO.Team.ResponseDto;
import com.example.ProKOi.Reposetory.Team.CustomTeamRepo;
import com.example.ProKOi.Reposetory.Team.TeamrRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class TeamServiceImpl implements TeamService {

    private CustomTeamRepo customTeamRepo;

    private  TeamrRepo teamrRepo;


    public TeamServiceImpl(CustomTeamRepo customTeamRepo, TeamrRepo teamrRepo) {
        this.customTeamRepo = customTeamRepo;
        this.teamrRepo = teamrRepo;
    }

    @Override
    //@Transactional
    public ResponseEntity<ResponseDto> addTeamMate(RequestDto requestDto) {


            String senderUsername = requestDto.getSenderUsername();
            String receiverUsername = requestDto.getReceiverUsername();

        log.debug("Retrieved senderUsername: {}", senderUsername);
        log.debug("Retrieved receiverUsername: {}", receiverUsername);

        if(senderUsername == null || receiverUsername == null){
               ResponseDto responseDto = new ResponseDto();
               responseDto.setResult("Sender or Receiver can not be null");
               return ResponseEntity.ok(responseDto);

        }
        else if(customTeamRepo.ifUserExit(receiverUsername) == false || customTeamRepo.ifUserExit(senderUsername) == false){

            ResponseDto responseDto = new ResponseDto();
            responseDto.setResult("User Does not exist");
            return ResponseEntity.ok(responseDto);
        }

               else if(senderUsername.equals(receiverUsername)){
                   ResponseDto responseDto = new ResponseDto();
                   responseDto.setResult("You can't add yourself as a team mate");
                   return ResponseEntity.ok(responseDto);
               }

        else if(customTeamRepo.ifFriend(senderUsername,receiverUsername)){
            ResponseDto responseDto = new ResponseDto();
            responseDto.setResult("You are already team mates");
            return ResponseEntity.ok(responseDto);
        }

                  else {

                         customTeamRepo.addFriend(senderUsername,receiverUsername);
                      ResponseDto responseDto = new ResponseDto();
                      responseDto.setResult("Request sent");
                      return ResponseEntity.ok(responseDto);
                  }

               }






    }

