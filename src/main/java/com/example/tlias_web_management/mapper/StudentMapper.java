package com.example.tlias_web_management.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.tlias_web_management.pojo.Student;
import com.example.tlias_web_management.pojo.StudentQueryParam;

@Mapper
public interface StudentMapper {

    /*
     * 根据 ID 查询班级关联的学生数量
     */
    @Select("select count(*) from student where clazz_id = #{clazzId}")
    Integer countStudentsOfClazzByClazzId(Integer clazzId);

    /*
     * 分页查询
     */
    List<Student> list(StudentQueryParam studentQueryParam);

    /*
     * 新增学生
     */
    @Insert("insert into student (name, no, gender, phone, degree, clazz_id, id_card, is_college, address, graduation_date, create_time, update_time) " + 
            "values (#{name}, #{no}, #{gender}, #{phone}, #{degree}, #{clazzId}, #{idCard}, #{isCollege}, #{address}, #{graduationDate}, #{createTime}, #{updateTime})")
    void insert(Student student);

    /*
     * 根据 ID 查询学生
     */
    @Select("select * from student s where s.id = #{id}")
    Student getById(Integer id);

    /*
     * 根据 ID 修改学生
     */
    void updateById(Student student);

    /*
     * 批量删除学生
     */
    void deleteByIds(List<Integer> ids);

    /*
     * 班级人数统计
     */
    @MapKey("clazz")
    List<Map<String, Object> > countStudentClazzData();

    /*
     * 学生学历统计
     */
    @MapKey("name")
    List<Map<String, Object> > countStudentDegreeData();
}
