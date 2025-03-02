package com.example.tlias_web_management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.tlias_web_management.pojo.EmpExpr;

@Mapper
public interface EmpExprMapper {

    /*
     * 批量添加员工工作经历信息
     */
    void insertBatch(List<EmpExpr> exprList);

    /*
     * 批量删除员工工作经历信息
     */
    void deleteByEmpIds(List<Integer> empIds);

    /*
     * 根据 ID 查询员工工作经历信息
     */
    @Select("select * from emp_expr where emp_id = #{empId}")
    List<EmpExpr> getByEmpId(Integer empId);

}
