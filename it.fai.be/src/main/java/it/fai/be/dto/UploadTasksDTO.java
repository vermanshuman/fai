package it.fai.be.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UploadTasksDTO extends ResponseDto {

    private List<UploadTaskDTO> uploadTasks;
}
