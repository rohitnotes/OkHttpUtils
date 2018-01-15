package com.example.dbz.okhttputils.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class Banner {

    /**
     * usertoken : {"status":1,"username":"11000**76**","user_phone":"11000**76**","head_img":"https://twww.99jr.cn/picup/up/201801/20180110140134_11358.jpg","appmsg":"已登录","token":"e2edd7f613a1b3532d6d07645d8aaf98","xingname":"四星会员"}
     * ader : [{"url":"https://www.baidu.com/","pic":"https://twww.99jr.cn/picup/up/201801/20180111094736_6806.gif","ty":"0","val":""},{"url":"http://tm.99jr.cn/app/newuser/?isapp=yes","pic":"https://twww.99jr.cn/picup/up/201801/20180104143243_4097.png","ty":"0","val":""},{"url":"https://tm.99jr.cn/hd/px/?isapp=yes","pic":"https://twww.99jr.cn/picup/up/201712/20171226191145_5317.png","ty":"0","val":""},{"url":"https://m.99jr.cn/safe/control/?isapp=yes","pic":"https://twww.99jr.cn/picup/up/201712/20171225144559_9534.png","ty":"0","val":""},{"url":"http://tm.99jr.cn/app/lcs/?isapp=yes","pic":"https://twww.99jr.cn/picup/up/201712/20171219111508_4615.png","ty":"0","val":""},{"url":"https://m.99jr.cn/borrow/info/?id=394","pic":"https://twww.99jr.cn/picup/up/201712/20171229160625_4643.png","ty":"1","val":"268"}]
     * unread : 3
     * data : {"id":"365","borrow_interest_rate":"11.1","borrow_name":"新享170155号","borrow_type":"7","daytime":"4天","type":"次日计息","borrow_min":"100","havelv":"50.75","activitytext":"更多精彩等你来","classroomtext":"久久为您助力财富增值"}
     */

    private String unread;
    private List<AderBean> ader;

    public String getUnread() {
        return unread;
    }

    public void setUnread(String unread) {
        this.unread = unread;
    }

    public List<AderBean> getAder() {
        return ader;
    }

    public void setAder(List<AderBean> ader) {
        this.ader = ader;
    }


    public static class AderBean {
        /**
         * url : https://www.baidu.com/
         * pic : https://twww.99jr.cn/picup/up/201801/20180111094736_6806.gif
         * ty : 0
         * val :
         */

        private String url;
        private String pic;
        private String ty;
        private String val;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTy() {
            return ty;
        }

        public void setTy(String ty) {
            this.ty = ty;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }
    }
}
