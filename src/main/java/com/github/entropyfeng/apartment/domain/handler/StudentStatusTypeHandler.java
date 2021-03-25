package com.github.entropyfeng.apartment.domain.handler;



import com.github.entropyfeng.apartment.domain.StudentStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(StudentStatus.class)
public class StudentStatusTypeHandler implements TypeHandler<StudentStatus> {


    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, StudentStatus studentStatus, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, studentStatus.getCode());

    }

    @Override
    public StudentStatus getResult(ResultSet resultSet, String s) throws SQLException {
        Integer code = resultSet.getInt(s);
        return StudentStatus.getStudentStatusByCode(code);

    }

    @Override
    public StudentStatus getResult(ResultSet resultSet, int i) throws SQLException {
        Integer code = resultSet.getInt(i);
        return StudentStatus.getStudentStatusByCode(code);


    }

    @Override
    public StudentStatus getResult(CallableStatement callableStatement, int i) throws SQLException {
       Integer code = callableStatement.getInt(i);
        return StudentStatus.getStudentStatusByCode(code);

    }

}
