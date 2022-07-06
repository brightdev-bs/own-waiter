package vanilla.ownwaiter.domain.user;
public enum SiteUserRole {
    USER("유저"), ADMIN("관리자");

    private String role;

    SiteUserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
