package com.example.demo.mapper;

import com.example.demo.base.MapperBase;
import com.example.demo.entity.SysSubject;
import com.example.demo.entity.SysSubjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

public interface SysSubjectMapper extends MapperBase<SysSubject> {

    @Select("SELECT SUBJECT_ID, SUBJECT_NAME, STATUS, CREATE_TIME, PARENT_ID, SORT FROM sys_subject WHERE STATUS = #{status}")
    @ResultMap("BaseResultMap")
    List<SysSubject> getSysSubjectsByStatus(int status);

    List<SysSubject> getSysSubjectsByName(String name);
}