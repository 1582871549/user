package com.meng.user.repository.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class UserDO extends Model<UserDO> {

    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
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
    @TableField("is_locked")
    private Boolean locked;
    /**
     * 是否进行逻辑删除（0：false，1：true）
     */
    @TableField("is_deleted")
    private Boolean deleted;
    /**
     * 最后登陆时间
     */
    private String lastLoginTime;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String modifiedTime;

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }
}