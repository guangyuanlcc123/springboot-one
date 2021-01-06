package com.example.demo.server;

import com.example.demo.entity.SysSubject;

import java.util.List;

public interface SysSubjectService {

    SysSubject getSysSubject(int sysSubjectId);


    List<SysSubject> getSysSubjects(int pageSize, int page);


    List<SysSubject> getSysSubjectsByStatus(int status);

    List<SysSubject> getSysSubjectsByName(String name);
}
