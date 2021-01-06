package com.example.demo.server.impl;

import com.example.demo.entity.SysSubject;
import com.example.demo.mapper.SysSubjectMapper;
import com.example.demo.server.SysSubjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysSubjectServiceImpl implements SysSubjectService {

    @Autowired
    SysSubjectMapper sysSubjectMapper;

    @Override
    public SysSubject getSysSubject(int sysSubjectId) {
        return sysSubjectMapper.selectByPrimaryKey(sysSubjectId);
    }

    @Override
    public List<SysSubject> getSysSubjects(int pageSize, int page) {
        PageInfo pageInfo = new PageInfo();
        PageHelper.startPage(page, pageSize);
        pageInfo = new PageInfo(sysSubjectMapper.selectAll());
        return pageInfo.getList();
    }

    @Override
    public List<SysSubject> getSysSubjectsByStatus(int status) {
        return sysSubjectMapper.getSysSubjectsByStatus(status);
    }

    @Override
    public List<SysSubject> getSysSubjectsByName(String name) {
        return sysSubjectMapper.getSysSubjectsByName(name);
    }


}
