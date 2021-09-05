package br.edu.ufpe.recife.tads.recgo.models.dto;

public class SignResponseDTO {
    private String jwt;
    private User user;

    // Getter Methods

    public String getJwt() {
        return jwt;
    }

    public User getUser() {
        return user;
    }

    // Setter Methods

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public void setUser(User userObject) {
        this.user = userObject;
    }
}

