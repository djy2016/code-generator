package com.ouyeel.generator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 连接数据库，将表信息导出；同时使用Velocity引擎将数据加载到模板
 *
 * @author dujiayong
 * @create 2017-08-29 10:17
 */
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void run(String projectPackage, String tables) throws Exception {
        Config config = new Config();
        //1.加载数据库驱动
        Class.forName(config.getDatabaseDriver()).newInstance();
        Properties p = new Properties();
        p.setProperty(Config.USERNAME,Config.properties.get(Config.USERNAME));
        p.setProperty("password",Config.properties.get(Config.PASSWORD));
        p.setProperty("remarksReporting","true");
        //2.获取数据库连接
        Connection connection = DriverManager.getConnection(config.getDatabaseUrl(),p);
        DatabaseMetaData metaData = connection.getMetaData();
        if (StringUtils.isNotEmpty(tables)) {
            //目前先测试：单个表代码生成
            ResultSet resultSet = metaData.getTables(null, config.getDatabaseSchema(), tables, new String[]{"TABLE"});
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                TableMetaData tableMetaData = new TableMetaData(tableName);
                ResultSet primaryKeyRs = metaData.getPrimaryKeys(null,config.getDatabaseSchema(),tableName);
                //主键列表
                List<String> primaryKeyCols = new ArrayList<String>();
                while (primaryKeyRs.next()){
                    String primaryKeyCol = primaryKeyRs.getString("COLUMN_NAME");
                    primaryKeyCols.add(primaryKeyCol);
                }
                logger.info("表名称为:{}", tableName);
                ResultSet rs = metaData.getColumns(null, config.getDatabaseSchema(), tableName.toUpperCase(), "%");
                List<ColumnMetaData> columnMetaDatas = new ArrayList<ColumnMetaData>();
                while (rs.next()){
                    String colName = rs.getString("COLUMN_NAME");
                    String remarks = rs.getString("REMARKS");
                    String colType = rs.getString("TYPE_NAME");
                    boolean pkFlag = false;
                    //判断列是否在主键列表中
                    for(String primaryKey:primaryKeyCols){
                        if(primaryKey.equals(colName)){
                            pkFlag = true;
                            break;
                        }
                    }
                    ColumnMetaData columnMetaData = new ColumnMetaData(colName,colType,remarks,pkFlag);
                    columnMetaDatas.add(columnMetaData);
                }
                tableMetaData.setCols(columnMetaDatas);
                Properties properties = new Properties();

            }
            resultSet.close();
        }
        connection.close();
    }
}
