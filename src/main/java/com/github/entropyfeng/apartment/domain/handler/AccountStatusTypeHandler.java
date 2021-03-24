package com.github.entropyfeng.apartment.domain.handler;


import com.github.entropyfeng.apartment.domain.AccountStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(AccountStatus.class)
public class AccountStatusTypeHandler implements TypeHandler<AccountStatus> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, AccountStatus accStatus, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, accStatus.getCode());

    }


    @Override
    public AccountStatus getResult(ResultSet resultSet, String s) throws SQLException {
        Integer code = resultSet.getInt(s);
        return AccountStatus.getAccountStatusByCode(code);

    }

    @Override
    public AccountStatus getResult(ResultSet resultSet, int i) throws SQLException {
        Integer code = resultSet.getInt(i);
        return AccountStatus.getAccountStatusByCode(code);


    }

    @Override
    public AccountStatus getResult(CallableStatement callableStatement, int i) throws SQLException {
        Integer code = callableStatement.getInt(i);
        return AccountStatus.getAccountStatusByCode(code);

    }

}
