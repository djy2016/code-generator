package com.ouyeel.generator;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
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

    public static void run(String projectPackage, String tables,String outPath) throws Exception {
        Config config = new Config();
        String filePath = Main.class.getClassLoader().getResource("generator").getPath();
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
                TableMetaData tableMetaData = new TableMetaData(tableName,projectPackage);
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
//                String[] templates = new String[]{"domain.vm","service.vm","serviceImpl.vm","mapper.vm","mapperXml.vm"};
                String[] templates = new String[]{"domain.vm"};
//                String[] files = new String[]{
//                        getFilePath(tableMetaData.getDomainPackageName()) + tableMetaData.getDomainClassName() + ".java",
//                        getFilePath(tableMetaData.getServicePackageName()) + tableMetaData.getServiceInterfaceName() + ".java",
//                        getFilePath(tableMetaData.getServicePackageName()) + tableMetaData.getServiceImplName() + ".java",
//                        getFilePath(tableMetaData.getMapperPackageName()) + tableMetaData.getMapperInterfaceName() + ".java",
//                        getFilePath(tableMetaData.getMapperPackageName()) + tableMetaData.getMapperXmlName() + ".xml"
//                };
                if(StringUtils.isEmpty(outPath)){
                    outPath = "D:\\temp\\" + getFilePath(projectPackage);
                }else{
                    outPath = outPath.replace("/","\\") + "\\" + getFilePath(projectPackage);
                }
                File file = new File(outPath);
                if(!file.exists()){
                    file.mkdirs();
                }
                String[] files = new String[]{
                        outPath + tableMetaData.getDomainClassName() + ".java"
                };
//                String[] templates = new String[]{"domain.vm"};
                for (int i = 0; i < templates.length; i++) {
                    //初始化模板引擎
                    VelocityEngine engine = new VelocityEngine();
//                    engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
                    engine.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH,filePath + "\\");
//                    engine.setProperty("file.resource.loader.class", ClasspathResourceLoader.class.getName());
                    engine.init();
                    //获取模板文件
                    Template template = engine.getTemplate(templates[i]);
                    template.setEncoding("UTF-8");
                    //设置变量，将表和列信息封装到map中
                    VelocityContext context = new VelocityContext();
                    context.put("meta",tableMetaData);
                    //输出
                    FileWriter fileWriter = new FileWriter(files[i],false);
                    template.merge(context,fileWriter);
                    fileWriter.flush();
                    fileWriter.close();
                }
            }
            resultSet.close();
        }
        connection.close();
    }

    private static String getFilePath(String packageName){
        return packageName.replace(".","\\") + "\\";
    }
}
