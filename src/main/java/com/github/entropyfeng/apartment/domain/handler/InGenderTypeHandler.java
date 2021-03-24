package com.github.entropyfeng.apartment.domain.handler;

import com.github.entropyfeng.apartment.domain.InGender;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(InGender.class)
public class InGenderTypeHandler implements TypeHandler<InGender> {


    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, InGender inGender, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, inGender.getCode());

    }

    @Override
    public InGender getResult(ResultSet resultSet, String s) throws SQLException {
        Integer code = resultSet.getInt(s);
        return InGender.getInGenderByCode(code);

    }

    @Override
    public InGender getResult(ResultSet resultSet, int i) throws SQLException {
        Integer code = resultSet.getInt(i);
        return InGender.getInGenderByCode(code);


    }

    @Override
    public InGender getResult(CallableStatement callableStatement, int i) throws SQLException {
       Integer code = callableStatement.getInt(i);
        return InGender.getInGenderByCode(code);

    }

}
