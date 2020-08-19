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
 * @author 大橙子
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_permission")
public class PermissionDO extends Model<PermissionDO> {

    /**
     * 资源id
     */
    @TableId(value = "permission_id", type = IdType.AUTO)
    private Long permissionId;
    /**
     * 资源名称
     */
    private String permissionName;
    /**
     * 描述
     */
    private String description;
    /**
     * 父元素id
     */
    private Long parentId;
    /**
     * url
     */
    private String url;
    /**
     * 操作
     */
    private String operation;
    /**
     * 类型（0：菜单，1：按钮）
     */
    private Integer type;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 图标
     */
    private String icon;
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
        return this.permissionId;
    }
}