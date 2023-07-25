package user;

public class Succes {
    private String succes;
    public SuccesUser succesUser;
    private String accessToken;
    private String refreshToken;

    public Succes(String succes, SuccesUser succesUser, String accessToken, String refreshToken) {
        this.succes = succes;
        this.succesUser = succesUser;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getSucces() {
        return succes;
    }

    public void setSucces(String succes) {
        this.succes = succes;
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
