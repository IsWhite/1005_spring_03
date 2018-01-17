package com.ssh.domain;

import java.io.Serializable;

/**
 *  Serializable序列化:用文件存储适用
 */
public class Clazz implements Serializable{
    private  int cid;
    private  String cname;
    private  String cinfor;




    public Clazz() {
    }

    public Clazz(String cname, String cinfor) {
        this.cname = cname;
        this.cinfor = cinfor;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCinfor() {
        return cinfor;
    }

    public void setCinfor(String cinfor) {
        this.cinfor = cinfor;
    }



    @Override
    public String toString() {
        return "Clazz{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", cinfor='" + cinfor + '\'' +
                '}';
    }
}
