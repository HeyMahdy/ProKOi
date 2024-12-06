package com.example.ProKOi.DTO.Team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotBlank;

@Data
public class RequestDto {

    @NotBlank
    private String senderUsername;

    @NotBlank
    private String receiverUsername;

// The @NotNull annotation ensures that the fields senderUsername and receiverUsername in the RequestDto class are not null when the object is created.



}
