package com.example.tlias_web_management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.tlias_web_management.pojo.Dept;

@Mapper
public interface DeptMapper {

    /*
     * 查询部门列表
     */
    @Select("SELECT * FROM dept ORDER BY update_time DESC")
    List<Dept> findAll();

    /*
     * 根据 ID 删除部门
     */
    @Delete("DELETE FROM dept WHERE id = #{id}")
    void deleteById(Integer id);

    /*
     * 新增部门
     */
    @Insert("INSERT INTO dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    /*
     * 根据 ID 查询部门
     */
    @Select("SELECT * FROM dept where id = #{id}")
    Dept getById(Integer id);

    /*
     * 根据 ID 修改部门
     */
    @Update("UPDATE dept SET name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);

}
