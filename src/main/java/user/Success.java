package user;

public class Success {
    private String success;
    public SuccesUser succesUser;
    private String accessToken;
    private String refreshToken;

    public Success(String success, SuccesUser succesUser, String accessToken, String refreshToken) {
        this.success = success;
        this.succesUser = succesUser;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public SuccesUser getSuccesUser() {
        return succesUser;
    }

    public void setSuccesUser(SuccesUser succesUser) {
        this.succesUser = succesUser;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
