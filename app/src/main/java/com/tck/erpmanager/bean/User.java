package com.tck.erpmanager.bean;


/**
 * Created by tck on 2017/6/28.
 */
public class User {


    /**
     * status : 200
     * messgae : 登陆成功
     * data : {"id":1,"username":"13776356351","password":"qwe123","email":"tck6666@163.com","avatar":""}
     */

    private int status;
    private String messgae;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessgae() {
        return messgae;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * username : 13776356351
         * password : qwe123
         * email : tck6666@163.com
         * avatar :
         */

        private int id;
        private String username;
        private String password;
        private String email;
        private String avatar;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
