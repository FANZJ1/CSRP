package com.sducsrp.csrp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sducsrp.csrp.entity.Forum;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
@Mapper
public interface ForumMapper extends BaseMapper<Forum> {

    // 发布
    @Insert("insert into forum (title,content,username) values (#{title},#{content},#{username})")
    int insert(String title,String content,String username);

    // 根据学号查询
    @Select("select * from forum where username=#{username} and state=#{state}")
    List<Forum> selectbyname(String username, int state);

    // 分页显示
    @Select("select * from  forum where state=#{state} and content like \"%${search}%\" ORDER BY time DESC limit #{beginPage},#{pageSize}")
    List<Forum> getpage(Integer beginPage, Integer pageSize, String search, int state);

    // 查找总个数
    @Select("select count(*) from  forum where state=#{state} and content like \"%${search}%\" ORDER BY time DESC")
    Integer gettotalpage(String search, int state);

    // 删除
    @Update("update forum set state=0 where forumid=#{forumid}")
    int delbyid(Integer forumid);
}
