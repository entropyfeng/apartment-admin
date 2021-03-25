package com.github.entropyfeng.apartment.domain.handler;


import com.github.entropyfeng.apartment.domain.StudentAccountStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(StudentAccountStatus.class)
public class StudentAccountStatusTypeHandler implements TypeHandler<StudentAccountStatus> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, StudentAccountStatus studentStatus, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, studentStatus.getCode());

    }

    @Override
    public StudentAccountStatus getResult(ResultSet resultSet, String s) throws SQLException {
        Integer code = resultSet.getInt(s);
        return StudentAccountStatus.getStudentAccountStatusByCode(code);

    }

    @Override
    public StudentAccountStatus getResult(ResultSet resultSet, int i) throws SQLException {
        Integer code = resultSet.getInt(i);
        return StudentAccountStatus.getStudentAccountStatusByCode(code);


    }

    @Override
    public StudentAccountStatus getResult(CallableStatement callableStatement, int i) throws SQLException {
        Integer code = callableStatement.getInt(i);
        return StudentAccountStatus.getStudentAccountStatusByCode(code);

    }
}
