package models;

/**
 * Created by Gurme L on 3/18/2018.
 */
public class OrdinaryUser {
    private User user;

    public OrdinaryUser(User user){
        this.user=user;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private String phoneNumber;

}
