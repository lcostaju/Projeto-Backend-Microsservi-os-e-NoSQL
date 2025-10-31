package com.lucio.userapi.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;



import org.springframework.stereotype.Service;

import com.lucio.dto.UserDTO;
import com.lucio.userapi.converter.DTOConverter;
import com.lucio.userapi.models.User;
// import com.lucio.userapi.models.dto.UserDTO;
import com.lucio.userapi.repositories.UserRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDTO> getAll(){
        List<User> usuarios = userRepository.findAll();

        return usuarios.stream()
            .map(DTOConverter::convert)
            .collect(Collectors.toList());
    }

    public UserDTO findById(String userId) {
    User usuario = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));
    return DTOConverter.convert(usuario);
}

public UserDTO save(UserDTO userDTO) {
    userDTO.setDataCadastro(LocalDateTime.now());
    User user = userRepository.save(User.convert(userDTO));
    return DTOConverter.convert(user);
}

public UserDTO delete(String userId) {
    User user = userRepository
        .findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    userRepository.delete(user);
    return DTOConverter.convert(user);
}

public UserDTO findByCpf(String cpf) {
    User user = userRepository.findByCpf(cpf);
    if (user != null) {
        return DTOConverter.convert(user);
    }
    return null;
}

public List<UserDTO> queryByName(String name) {
    List<User> usuarios = userRepository.queryByNomeLike(name);
    return usuarios
        .stream()
        .map(DTOConverter::convert)
        .collect(Collectors.toList());
}

// Métodos na coluna da direita

public UserDTO editUser(String userId, UserDTO userDTO) {
    User user = userRepository
        .findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

    // Lógica de atualização condicional dos campos
    if (userDTO.getEmail() != null && !user.getEmail().equals(userDTO.getEmail())) {
        user.setEmail(userDTO.getEmail());
    }
    if (userDTO.getTelefone() != null && !user.getTelefone().equals(userDTO.getTelefone())) {
        user.setTelefone(userDTO.getTelefone());
    }
    if (userDTO.getEndereco() != null && !user.getEndereco().equals(userDTO.getEndereco())) {
        user.setEndereco(userDTO.getEndereco());
    }

    user = userRepository.save(user);
    return DTOConverter.convert(user);
}

public Page<UserDTO> getAllPage(Pageable page){
    Page<User> users = userRepository.findAll(page);
    return users
    .map(DTOConverter::convert);
}
}
