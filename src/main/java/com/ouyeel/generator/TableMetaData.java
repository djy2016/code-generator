package com.ouyeel.generator;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 保存表对应生成接口和类信息
 *
 * @author dujiayong
 * @create 2017-08-29 21:08
 */
public class TableMetaData {

    /**
     * 表名称
     */
    private String dbTableName;
    /**
     * domain类名
     */
    private String domainClassName;
    /**
     * domain所在包全路径
     */
    private String domainPackageName;
    /**
     * service接口名
     */
    private String serviceInterfaceName;
    /**
     * service接口和实现类全路径
     */
    private String servicePackageName;
    /**
     * service接口实现类名
     */
    private String serviceImplName;
    /**
     * mapper文件名
     */
    private String mapperInterfaceName;
    /**
     * mapper接口所在包名
     */
    private String mapperPackageName;
    /**
     * mapper接口对应xml文件名
     */
    private String mapperXmlName;
    /**
     * 表对应的列信息
     */
    private List<ColumnMetaData> cols;

    public TableMetaData(String tableName,String projectPackage){
        dbTableName = tableName;
        tableName = format(tableName);

        domainClassName = tableName;
        domainPackageName = projectPackage + ".common.domain";

        serviceInterfaceName = tableName + "Service";
        serviceImplName = tableName + "ServiceImpl";
        servicePackageName = projectPackage + ".service";

        mapperInterfaceName = tableName + "Mapper";
        mapperXmlName = tableName + "Mapper";
        mapperPackageName = projectPackage + ".common.mapper";

    }

    public String getDbTableName() {
        return dbTableName;
    }

    public void setDbTableName(String dbTableName) {
        this.dbTableName = dbTableName;
    }

    public String getDomainClassName() {
        return domainClassName;
    }

    public void setDomainClassName(String domainClassName) {
        this.domainClassName = domainClassName;
    }

    public String getServiceInterfaceName() {
        return serviceInterfaceName;
    }

    public void setServiceInterfaceName(String serviceInterfaceName) {
        this.serviceInterfaceName = serviceInterfaceName;
    }

    public String getServiceImplName() {
        return serviceImplName;
    }

    public void setServiceImplName(String serviceImplName) {
        this.serviceImplName = serviceImplName;
    }

    public String getMapperInterfaceName() {
        return mapperInterfaceName;
    }

    public void setMapperInterfaceName(String mapperInterfaceName) {
        this.mapperInterfaceName = mapperInterfaceName;
    }

    public String getMapperXmlName() {
        return mapperXmlName;
    }

    public void setMapperXmlName(String mapperXmlName) {
        this.mapperXmlName = mapperXmlName;
    }

    public String getDomainPackageName() {
        return domainPackageName;
    }

    public void setDomainPackageName(String domainPackageName) {
        this.domainPackageName = domainPackageName;
    }

    public String getServicePackageName() {
        return servicePackageName;
    }

    public void setServicePackageName(String servicePackageName) {
        this.servicePackageName = servicePackageName;
    }

    public String getMapperPackageName() {
        return mapperPackageName;
    }

    public void setMapperPackageName(String mapperPackageName) {
        this.mapperPackageName = mapperPackageName;
    }

    public List<ColumnMetaData> getCols() {
        return cols;
    }

    public void setCols(List<ColumnMetaData> cols) {
        this.cols = cols;
    }

    /**
     * 格式化数据库表名，规则：
     * 单个名词：首字母大写
     * 多个单词组成，每个单词首字母大写
     * @param tableName 需格式化的表名
     * @see StringUtils#capitalize(String) 字符串首字母大写
     * @return 格式化的表名
     */
    private String format(String tableName){
        StringBuffer buffer = new StringBuffer();
        String[] names = tableName.split("_");
        Arrays.stream(names).forEach(name -> {
            //name首字母大写
            buffer.append(StringUtils.capitalize(name));
        });
        return buffer.toString();
    }
}
