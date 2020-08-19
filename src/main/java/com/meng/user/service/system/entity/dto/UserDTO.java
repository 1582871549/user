package com.meng.user.service.system.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseDTO {

    /**
     * 用户id
     */
    private Long userId;
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