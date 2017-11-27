package com.innotek.demotestproject.database.greendaouse;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by admin on 2017/7/25.
 */

@Entity
public class User {
    @Id
    public long userId;
    @Property(nameInDb = "username")
    public String userName;
    @Property(nameInDb = "nickName")
    public String nickName;
    @Property(nameInDb = "height")
    public int height;
    @Property(nameInDb = "age")
    public int age;
    
    @Property(nameInDb = "hasGF")
    public boolean hasGF;

    @Property(nameInDb = "moneyCount")
    public double moneyCount;
    @Property(nameInDb = "woshi")
    public String woshi;

    @Generated(hash = 1989157253)
    public User(long userId, String userName, String nickName, int height, int age,
            boolean hasGF, double moneyCount, String woshi) {
        this.userId = userId;
        this.userName = userName;
        this.nickName = nickName;
        this.height = height;
        this.age = age;
        this.hasGF = hasGF;
        this.moneyCount = moneyCount;
        this.woshi = woshi;
    }



    @Generated(hash = 586692638)
    public User() {
    }



    @Override
    public String toString() {
        return  "User id"+this.userId
                +"User userName" +this.userName
                +"User nickName"+this.nickName
                +"User height"+this.height
                +"User age"+this.age
                +"User hasGF"+this.hasGF
                +"User moneyCount"+this.moneyCount
                +"User woshi"+this.woshi ;
    }



    public double getMoneyCount() {
        return this.moneyCount;
    }



    public void setMoneyCount(double moneyCount) {
        this.moneyCount = moneyCount;
    }



    public boolean getHasGF() {
        return this.hasGF;
    }



    public void setHasGF(boolean hasGF) {
        this.hasGF = hasGF;
    }



    public int getAge() {
        return this.age;
    }



    public void setAge(int age) {
        this.age = age;
    }



    public int getHeight() {
        return this.height;
    }



    public void setHeight(int height) {
        this.height = height;
    }



    public String getNickName() {
        return this.nickName;
    }



    public void setNickName(String nickName) {
        this.nickName = nickName;
    }



    public String getUserName() {
        return this.userName;
    }



    public void setUserName(String userName) {
        this.userName = userName;
    }



    public long getUserId() {
        return this.userId;
    }



    public void setUserId(long userId) {
        this.userId = userId;
    }



    public String getWoshi() {
        return this.woshi;
    }



    public void setWoshi(String woshi) {
        this.woshi = woshi;
    }



}
