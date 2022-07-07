package vanilla.ownwaiter.entity.user;
public enum UserRole {
    USER("유저"), ADMIN("관리자");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
