package br.edu.ufpe.recife.tads.recgo.services;

import br.edu.ufpe.recife.tads.recgo.models.dto.SignResponseDTO;
import br.edu.ufpe.recife.tads.recgo.models.dto.User;

public class UserService {
    private static UserService single_instance = null;

    private SignResponseDTO signResponseDTO;

    public static UserService getInstance()
    {
        if (single_instance == null)
            single_instance = new UserService();

        return single_instance;
    }

    public User getUser() {
        return signResponseDTO.getUser();
    }

    public String getJWT() {
        return signResponseDTO.getJwt();
    }

    public void setUserData(SignResponseDTO signResponseDTO) {
        this.signResponseDTO = signResponseDTO;
    }

}
