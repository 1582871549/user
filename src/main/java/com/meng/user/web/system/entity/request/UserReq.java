package com.meng.user.web.system.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReq {

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐
     */
    private String salt;
    /**
     * 姓名
     */
    private String name;
    /**
     * （0：不详，1：男，2：女）
     */
    private Integer sex;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 头像路径
     */
    private String photo;
    /**
     * 是否锁定(0:false 1:true)
     */
    private Boolean locked;
    /**
     * 是否进行逻辑删除（0：false，1：true）
     */
    private Boolean deleted;
    /**
     * 最后登陆时间
     */
    private String lastLoginTime;
}