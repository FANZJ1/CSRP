package com.sducsrp.csrp.mapper;

import com.sducsrp.csrp.entity.DownLoad;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DownLoadMapper {

    // 根据课程id查找所有选课学生的做题情况
    @Select("select username,realname, problemname,finishtime from stucourse NATURAL JOIN user NATURAL JOIN stuproblem NATURAL JOIN problem where stucourse.courseid=#{courseid}")
    List<DownLoad> sel(Integer courseid);
}
