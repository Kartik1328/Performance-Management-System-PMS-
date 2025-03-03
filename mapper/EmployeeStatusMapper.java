package com.pms.mapper;

import com.pms.Model.EmployeeStatus;
import com.pms.co.EmployeeStatusCo;
import com.pms.dto.EmployeeStatusDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface EmployeeStatusMapper {
    EmployeeStatus coToEntity(EmployeeStatusCo employeeStatus_co);
    EmployeeStatusDto entityToDTO(EmployeeStatus employeeStatus);
    List<EmployeeStatusDto> entityListToDTOList(List<EmployeeStatus> employeeStatus);
    List<EmployeeStatus> coListToEntityList(List<EmployeeStatusCo> employeeStatusCo);
}

