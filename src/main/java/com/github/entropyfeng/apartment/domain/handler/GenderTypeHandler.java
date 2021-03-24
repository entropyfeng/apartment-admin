package com.github.entropyfeng.apartment.domain.handler;

import com.github.entropyfeng.apartment.domain.Gender;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Gender.class)
public class GenderTypeHandler implements TypeHandler<Gender> {


    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Gender inGender, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, inGender.getCode());

    }

    @Override
    public Gender getResult(ResultSet resultSet, String s) throws SQLException {
        Integer code = resultSet.getInt(s);
        return Gender.getInGenderByCode(code);

    }

    @Override
    public Gender getResult(ResultSet resultSet, int i) throws SQLException {
        Integer code = resultSet.getInt(i);
        return Gender.getInGenderByCode(code);

    }

    @Override
    public Gender getResult(CallableStatement callableStatement, int i) throws SQLException {
       Integer code = callableStatement.getInt(i);
        return Gender.getInGenderByCode(code);

    }

}
