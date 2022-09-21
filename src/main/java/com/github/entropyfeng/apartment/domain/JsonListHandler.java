package com.github.entropyfeng.apartment.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@MappedJdbcTypes(JdbcType.BLOB)
public class JsonListHandler<T> extends BaseTypeHandler<List<T>> {


    private static final Logger logger = LoggerFactory.getLogger(JsonListHandler.class);
    protected static final ObjectMapper objectMapper;
    protected Class<T> type;

    private ObjectReader reader;
    private ObjectWriter writer;
    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public JsonListHandler(Class<T> type) {
        if (logger.isTraceEnabled()) {
            logger.trace("JacksonTypeHandler(" + type + ")");
        }
        if (null == type) {
            throw new PersistenceException("Type argument cannot be null");
        }
        CollectionType arrayType=objectMapper.getTypeFactory().constructCollectionType(List.class,type);
        ObjectReader reader=objectMapper.readerFor(arrayType);
        ObjectWriter writer=objectMapper.writerFor(arrayType);
        this.reader=reader;
        this.writer=writer;
        this.type = type;
    }

    private List<T> parse(String json) {
        try {
            if (json == null || json.length() == 0) {
                return null;
            }
            return reader.readValue(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String toJsonString(List<T> obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<T> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, toJsonString(parameter));
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return parse(rs.getString(columnName));
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return parse(rs.getString(columnIndex));
    }

    @Override
    public List<T> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return parse(cs.getString(columnIndex));
    }


}
