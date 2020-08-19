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

/**
 * AUTO             数据库自增
 * INPUT            自行输入
 * ID_WORKER        分布式全局唯一ID 长整型类型
 * UUID             32位UUID字符串
 * NONE             无状态
 * ID_WORKER_STR    分布式全局唯一ID 字符串类型
 *
 * @author 大橙子
 * @TableName(value = "sys_role", resultMap = "RoleResultMap")
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "sys_role")
public class RoleDO extends Model<RoleDO> {

    /**
     * 角色id
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色描述
     */
    private String description;
    /**
     * 是否可用(0:false 1:true)
     */
    @TableField("is_activation")
    private Boolean activation;
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
        return this.roleId;
    }
}