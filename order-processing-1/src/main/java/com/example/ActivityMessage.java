package com.example;

import java.util.Objects;

public class ActivityMessage {

    private String userid;
    private String productId;
    private String activity;
    public ActivityMessage() {
    }
    public ActivityMessage(String userid, String productId, String activity) {
        this.userid = userid;
        this.productId = productId;
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "ActivityMessage{" +
                "userid='" + userid + '\'' +
                ", productId='" + productId + '\'' +
                ", activity='" + activity + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityMessage that = (ActivityMessage) o;
        return Objects.equals(userid, that.userid) && Objects.equals(productId, that.productId) && Objects.equals(activity, that.activity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, productId, activity);
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

}
