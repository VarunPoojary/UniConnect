package com.example.mitadttrial;
public class ReadWriteUserDetails
{
    public String fullName, doB, gender, mobile, profilePic ;
    String  mail, password, userId, lastMessage, status;


    //Constructor
    public ReadWriteUserDetails(){};

    ReadWriteUserDetails(String textFullName, String textDoB, String textGender, String textMobile)
    {
        this.fullName = textFullName;
        this.doB = textDoB;
        this.gender = textGender;
        this.mobile = textMobile;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return fullName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

}
