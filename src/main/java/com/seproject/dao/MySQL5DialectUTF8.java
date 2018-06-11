package com.seproject.dao;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * 说明：覆盖了原hibernate自动建表的方言，在最后面建表最后加上了utf8
 */
public class MySQL5DialectUTF8 extends MySQL5InnoDBDialect{

    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}