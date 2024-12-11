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

    private TeamrRepo teamrRepo;


    public TeamServiceImpl(CustomTeamRepo customTeamRepo, TeamrRepo teamrRepo) {
        this.customTeamRepo = customTeamRepo;
        this.teamrRepo = teamrRepo;
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDto> addTeamMate(RequestDto requestDto) {


        String senderUsername = requestDto.getSenderUsername();
        String receiverUsername = requestDto.getReceiverUsername();


        if (senderUsername == null || receiverUsername == null) {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setResult("Sender or Receiver can not be null");
            return ResponseEntity.ok(responseDto);

        } else if (customTeamRepo.ifUserExit(receiverUsername) == false || customTeamRepo.ifUserExit(senderUsername) == false) {

            ResponseDto responseDto = new ResponseDto();
            responseDto.setResult("User Does not exist");
            return ResponseEntity.ok(responseDto);
        } else if (senderUsername.equals(receiverUsername)) {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setResult("You can't add yourself as a team mate");
            return ResponseEntity.ok(responseDto);
        } else if (customTeamRepo.IsReqeustSent(senderUsername, receiverUsername)) {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setResult("Request already sent");
            return ResponseEntity.ok(responseDto);
        } else if (customTeamRepo.ifFriend(senderUsername, receiverUsername)) {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setResult("You are already team mates");
            return ResponseEntity.ok(responseDto);
        } else {

            customTeamRepo.addFriend(senderUsername, receiverUsername);
            ResponseDto responseDto = new ResponseDto();
            responseDto.setResult("Request sent");
            return ResponseEntity.ok(responseDto);
        }

    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDto> AcceptState(RequestDto requestDto) {

        String Sender = requestDto.getSenderUsername();
        String Receiver = requestDto.getReceiverUsername();

        if(Sender == null || Receiver == null){
            ResponseDto responseDto = new ResponseDto();
            responseDto.setResult("Sender or Receiver can not be null");
            return ResponseEntity.ok(responseDto);
        }
        else if(customTeamRepo.ifUserExit(Receiver) == false){
            ResponseDto responseDto = new ResponseDto();
            responseDto.setResult("User Does not exist");
            return ResponseEntity.ok(responseDto);
        }

        else if (Sender.equals(Receiver)) {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setResult("invalid");
            return ResponseEntity.ok(responseDto);
        }



        else{
                  boolean bb =  customTeamRepo.Friend(Sender, Receiver);
            ResponseDto responseDto = new ResponseDto();
            if(bb==true){
                responseDto.setResult("Request Accepted");
                return ResponseEntity.ok(responseDto);
            }
            else {
                responseDto.setResult("Request not found");
                return ResponseEntity.ok(responseDto);
            }


        }


    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDto> RejectState(RequestDto requestDto) {
        String Sender = requestDto.getSenderUsername();
        String Receiver = requestDto.getReceiverUsername();

        if(Sender == null || Receiver == null){
            ResponseDto responseDto = new ResponseDto();
            responseDto.setResult("Sender or Receiver can not be null");
            return ResponseEntity.ok(responseDto);
        }
        else if(customTeamRepo.ifUserExit(Receiver) == false){
            ResponseDto responseDto = new ResponseDto();
            responseDto.setResult("User Does not exist");
            return ResponseEntity.ok(responseDto);
        }

        else if (Sender.equals(Receiver)) {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setResult("invalid");
            return ResponseEntity.ok(responseDto);
        }



        else{
            boolean bb =  customTeamRepo.notFriend(Sender, Receiver);
            ResponseDto responseDto = new ResponseDto();
            if(bb==true){
                responseDto.setResult("Request Rejected");
                return ResponseEntity.ok(responseDto);
            }
            else {
                responseDto.setResult("Request not found");
                return ResponseEntity.ok(responseDto);
            }


        }

    }


}
