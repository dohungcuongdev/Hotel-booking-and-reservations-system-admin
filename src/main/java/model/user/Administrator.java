/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.user;

/**
 *
 * @author Do Hung Cuong
 */
public class Administrator extends User {

    private String gender;
    private String birthday;
    private String img;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Administrator() {
    }

    public boolean isEnoughInfor() {
        return checkNotNull(name, phone, gender, birthday);
    }

    public Administrator(String username, String password, String name, String gender, String phone, String birthday, String img) {
        setUserInfor(username, password, name, phone);
        this.gender = gender;
        this.birthday = birthday;
        this.img = img;

    }

    @Override
    public String toString() {
        return "Administrator{" + "gender=" + gender + ", birthday=" + birthday + ", img=" + img + '}';
    }

}
