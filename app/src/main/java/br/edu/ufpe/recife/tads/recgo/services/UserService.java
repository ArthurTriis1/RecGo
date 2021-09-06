package br.edu.ufpe.recife.tads.recgo.services;

import br.edu.ufpe.recife.tads.recgo.models.dto.SignResponseDTO;
import br.edu.ufpe.recife.tads.recgo.models.dto.User;

public class UserService {
    private static UserService single_instance = null;

    private String jwt;

    private User user;

    public static UserService getInstance()
    {
        if (single_instance == null)
            single_instance = new UserService();

        return single_instance;
    }

    public User getUser() {
        return user;
    }

    public String getJWT() {
        return jwt;
    }

    public void setUserData(SignResponseDTO signResponseDTO) {
        this.user = signResponseDTO.getUser();
        this.jwt = signResponseDTO.getJwt();
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
