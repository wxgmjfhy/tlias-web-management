package com.example.tlias_web_management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.tlias_web_management.pojo.Clazz;
import com.example.tlias_web_management.pojo.ClazzQueryParam;

@Mapper
public interface ClazzMapper {

    /*
     * 分页查询
     */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    /*
     * 新增班级
     */
    @Insert("insert into clazz (name, room, begin_date, end_date, master_id, subject, create_time, update_time) " + 
            "values (#{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, #{createTime}, #{updateTime})")
    void insert(Clazz clazz);

    /*
     * 根据 ID 查询班级
     */
    @Select("select * from clazz c where c.id = #{id}")
    Clazz getById(Integer id);

    /*
     * 根据 ID 修改班级
     */
    void updateById(Clazz clazz);

    /*
     * 根据 ID 删除班级
     */
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    /*
     * 查询所有班级
     */
    @Select("select * from clazz")
    List<Clazz> findAll();

}
