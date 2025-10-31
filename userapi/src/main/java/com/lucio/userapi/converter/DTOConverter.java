package com.lucio.userapi.converter;

import com.lucio.dto.UserDTO;
import com.lucio.userapi.models.User;

public class DTOConverter {
public static UserDTO convert(User user) {
UserDTO userDTO = new UserDTO();
userDTO.setId(user.getId());
userDTO.setNome(user.getNome());
userDTO.setEndereco(user.getEndereco());
userDTO.setCpf(user.getCpf());
userDTO.setEmail(user.getEmail());
userDTO.setTelefone(user.getTelefone());
userDTO.setDataCadastro(user.getDataCadastro());
return userDTO;
}
}