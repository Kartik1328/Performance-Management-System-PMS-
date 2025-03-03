package com.pms.beans;



import com.pms.dto.userDto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpAndUserResponse {
    private FileAndObjectTypeBean fileAndObjectTypeBean;
    private UserDTO userDTO;
}
