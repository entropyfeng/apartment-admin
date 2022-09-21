package com.github.entropyfeng.apartment.domain.handler;

import com.github.entropyfeng.apartment.domain.DormitoryDirection;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(DormitoryDirection.class)
public class DormitoryDirectionTypeHandler implements TypeHandler<DormitoryDirection> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, DormitoryDirection dormitoryDirection, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, dormitoryDirection.getCode());

    }

    @Override
    public DormitoryDirection getResult(ResultSet resultSet, String s) throws SQLException {
        Integer code = resultSet.getInt(s);
        return DormitoryDirection.getDormitoryDirectionByCode(code);

    }

    @Override
    public DormitoryDirection getResult(ResultSet resultSet, int i) throws SQLException {
        Integer code = resultSet.getInt(i);
        return DormitoryDirection.getDormitoryDirectionByCode(code);


    }

    @Override
    public DormitoryDirection getResult(CallableStatement callableStatement, int i) throws SQLException {
        Integer code = callableStatement.getInt(i);
        return DormitoryDirection.getDormitoryDirectionByCode(code);

    }

}
