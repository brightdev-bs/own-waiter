package vanilla.ownwaiter.entity.user;
public enum UserRole {
    USER("손님"), ADMIN("점주");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
