package com.pms.beans;


import com.pms.dto.employeeDto.EmpResDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileAndObjectTypeBean {
    FileAndContentTypeBean fileAndContentTypeBean;
    EmpResDTO empResDTO;
}
