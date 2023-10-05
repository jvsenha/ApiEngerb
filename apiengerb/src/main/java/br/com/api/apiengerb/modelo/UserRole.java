package br.com.api.apiengerb.modelo;

public enum UserRole {
    EMP("empresa"),
    USER("user");
    
    private String role;

   UserRole(String role) {
        this.role = role;
    }
    
    public String getRole(){
        return role;
    }
}
