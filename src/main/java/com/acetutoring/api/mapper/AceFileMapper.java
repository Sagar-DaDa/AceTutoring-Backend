package com.acetutoring.api.mapper;

import com.acetutoring.api.dto.AceFileDto;
import com.acetutoring.api.entities.AceFile;

public class AceFileMapper {
    public static AceFileDto mapToAceFileDto(AceFile aceFile){
        return new AceFileDto(
                aceFile.getId(),
                aceFile.getName(),
                aceFile.getDescription(),
                aceFile.getFileUrl(),
                aceFile.getCreatedAt()
        );
    }

    public static AceFile mapToAceFile(AceFileDto aceFileDto){
        return new AceFile(
                aceFileDto.getId(),
                aceFileDto.getName(),
                aceFileDto.getDescription(),
                aceFileDto.getFileUrl(),
                aceFileDto.getCreatedAt()
        );
    }
}
