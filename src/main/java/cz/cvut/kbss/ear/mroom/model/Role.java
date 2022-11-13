package cz.cvut.kbss.ear.mroom.model;

public enum Role {
    STUDENT("ROLE_STUDENT"),
    ADMIN("ROLE_ADMIN"),
    CLIENT("ROLE_CLIENT");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
