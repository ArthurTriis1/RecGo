package br.edu.ufpe.recife.tads.recgo.models.dto;

public class SignResponseDTO {
    private String jwt;
    User UserObject;


    // Getter Methods

    public String getJwt() {
        return jwt;
    }

    public User getUser() {
        return UserObject;
    }

    // Setter Methods

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public void setUser(User userObject) {
        this.UserObject = userObject;
    }
}

class User {
    private float id;
    private String username;
    private String email;
    private String provider;
    private boolean confirmed;
    private String blocked = null;
    Role RoleObject;
    private String created_at;
    private String updated_at;


    // Getter Methods

    public float getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getProvider() {
        return provider;
    }

    public boolean getConfirmed() {
        return confirmed;
    }

    public String getBlocked() {
        return blocked;
    }

    public Role getRole() {
        return RoleObject;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setBlocked(String blocked) {
        this.blocked = blocked;
    }

    public void setRole(Role roleObject) {
        this.RoleObject = roleObject;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}

class Role {
    private float id;
    private String name;
    private String description;
    private String type;


    // Getter Methods

    public float getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }
}