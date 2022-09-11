package com.flo.tennis.core;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class DataSourceProvider {

    private static MysqlDataSource singleDataSource;

    public static DataSource getSingleDataSourceInstance (){
        if (singleDataSource == null){
            singleDataSource = new MysqlDataSource();

            singleDataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");
            singleDataSource.setUser("root");
            singleDataSource.setPassword("root");
        }
        return singleDataSource;
    }
}
