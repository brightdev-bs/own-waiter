package vanilla.ownwaiter.domain.user;

public enum SiteUserSex {

    MAN("남자"), WOMAN("여자");

    private String sex;

    SiteUserSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }
}
