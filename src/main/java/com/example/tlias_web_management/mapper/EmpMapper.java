package com.example.tlias_web_management.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.example.tlias_web_management.pojo.Emp;
import com.example.tlias_web_management.pojo.EmpQueryParam;

@Mapper
public interface EmpMapper {

    /*
     * 分页查询
     */
    public List<Emp> list(EmpQueryParam empQueryParam);

    /*
     * 添加员工基本信息
     */
    @Options(useGeneratedKeys = true, keyProperty = "id") // 获取到生成的主键 -- 主键返回, 赋值给 emp.id
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values(#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    public void insert(Emp emp);

    /*
     * 批量删除员工基本信息
     */
    public void deleteByIds(List<Integer> ids);

    /*
     * 根据 ID 查询员工基本信息
     */
    public Emp getById(Integer id);

    /*
     * 根据 ID 更新员工基本信息
     */
    public void updateById(Emp emp);

    /*
     * 统计各职位的员工人数
     */
    @MapKey("job")
    public List<Map<String, Object> > countEmpJobData();

    /*
     * 统计员工性别信息
     */
    @MapKey("name")
    public List<Map<String, Object> > countEmpGenderData();

    /*
     * 查询所有员工
     */
    @Select("select * from emp")
    public List<Emp> findAll();

    /*
     * 根据 ID 查询部门关联的员工数量
     */
    @Select("select count(*) from emp where dept_id = #{deptId}")
    public Integer countEmpsOfDeptByDeptId(Integer deptId);

    /*
     * 根据用户名和密码查询员工
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    public Emp getInfoByUsernameAndPassword(Emp emp);
}
