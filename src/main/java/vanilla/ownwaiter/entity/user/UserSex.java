package vanilla.ownwaiter.entity.user;

public enum UserSex {

    MAN("남자"), WOMAN("여자");

    private String sex;

    UserSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }
}
