package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_subject")
public class SysSubject {
    /**
     * 主键
     */
    @Id
    @Column(name = "SUBJECT_ID")
    private Integer subjectId;

    /**
     * 专业名称
     */
    @Column(name = "SUBJECT_NAME")
    private String subjectName;

    /**
     * 状态 0:默认 1:删除
     */
    @Column(name = "STATUS")
    private Boolean status;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 父ID
     */
    @Column(name = "PARENT_ID")
    private Integer parentId;

    /**
     * 排序字段
     */
    private Integer sort;

    /**
     * 获取主键
     *
     * @return SUBJECT_ID - 主键
     */
    public Integer getSubjectId() {
        return subjectId;
    }

    /**
     * 设置主键
     *
     * @param subjectId 主键
     */
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * 获取专业名称
     *
     * @return SUBJECT_NAME - 专业名称
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * 设置专业名称
     *
     * @param subjectName 专业名称
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    /**
     * 获取状态 0:默认 1:删除
     *
     * @return STATUS - 状态 0:默认 1:删除
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置状态 0:默认 1:删除
     *
     * @param status 状态 0:默认 1:删除
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return CREATE_TIME - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取父ID
     *
     * @return PARENT_ID - 父ID
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父ID
     *
     * @param parentId 父ID
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取排序字段
     *
     * @return sort - 排序字段
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序字段
     *
     * @param sort 排序字段
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}