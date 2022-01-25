package it.fai.be.controller;

import it.fai.be.utils.DbUtils;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractController {

    @Value("${fai.db.driver}") private String dbDriver;
    @Value("${fai.db.url}") private String dbUrl;
    @Value("${fai.db.username}") private String dbUser;
    @Value("${fai.db.password}") private String dbPass;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        return DbUtils.getDbConnection(dbDriver, dbUrl, dbUser, dbPass);
    }
}
