package com.example.dbz.okhttputils.entity;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class Share {

    /**
     * share : {"share_title":"生活每天都该有一点点小惊喜！","share_message":"好运气，就该与好朋友一同分享","share_img":"https://tm.99jr.cn/images/share/cj.png","share_url":"https://tm.99jr.cn/lotteryh5/index/?uid=761","copy":"我参与了久久财富抽奖活动，快来参加https://tm.99jr.cn/lotteryh5/index/?uid=761"}
     * usertoken : {"status":1,"username":"11000**76**","user_phone":"11000**76**","head_img":"https://twww.99jr.cn/picup/up/201801/20180105135051_28120.jpg","appmsg":"已登录","token":"e2edd7f613a1b3532d6d07645d8aaf98","xingname":"四星会员"}
     */

    private ShareBean share;
    private UsertokenBean usertoken;

    public ShareBean getShare() {
        return share;
    }

    public void setShare(ShareBean share) {
        this.share = share;
    }

    public UsertokenBean getUsertoken() {
        return usertoken;
    }

    public void setUsertoken(UsertokenBean usertoken) {
        this.usertoken = usertoken;
    }

    public static class ShareBean {
        /**
         * share_title : 生活每天都该有一点点小惊喜！
         * share_message : 好运气，就该与好朋友一同分享
         * share_img : https://tm.99jr.cn/images/share/cj.png
         * share_url : https://tm.99jr.cn/lotteryh5/index/?uid=761
         * copy : 我参与了久久财富抽奖活动，快来参加https://tm.99jr.cn/lotteryh5/index/?uid=761
         */

        private String share_title;
        private String share_message;
        private String share_img;
        private String share_url;
        private String copy;

        public String getShare_title() {
            return share_title;
        }

        public void setShare_title(String share_title) {
            this.share_title = share_title;
        }

        public String getShare_message() {
            return share_message;
        }

        public void setShare_message(String share_message) {
            this.share_message = share_message;
        }

        public String getShare_img() {
            return share_img;
        }

        public void setShare_img(String share_img) {
            this.share_img = share_img;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public String getCopy() {
            return copy;
        }

        public void setCopy(String copy) {
            this.copy = copy;
        }
    }

    public static class UsertokenBean {
        /**
         * status : 1
         * username : 11000**76**
         * user_phone : 11000**76**
         * head_img : https://twww.99jr.cn/picup/up/201801/20180105135051_28120.jpg
         * appmsg : 已登录
         * token : e2edd7f613a1b3532d6d07645d8aaf98
         * xingname : 四星会员
         */

        private int status;
        private String username;
        private String user_phone;
        private String head_img;
        private String appmsg;
        private String token;
        private String xingname;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getAppmsg() {
            return appmsg;
        }

        public void setAppmsg(String appmsg) {
            this.appmsg = appmsg;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getXingname() {
            return xingname;
        }

        public void setXingname(String xingname) {
            this.xingname = xingname;
        }
    }
}
