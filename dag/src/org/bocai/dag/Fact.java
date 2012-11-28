package org.bocai.dag;
/**
 * 
 * 事实，拿来做规则匹配的数据对象
 * @author yikebocai@gmail.com Nov 27, 2012 5:53:56 PM
 */
public class Fact {

    private String country;
    private String amount;
    private String cookie;
    private String email;
    private String os;
    private String login_count_today;
    private String memberType;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getLogin_count_today() {
        return login_count_today;
    }

    public void setLogin_count_today(String login_count_today) {
        this.login_count_today = login_count_today;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

}
