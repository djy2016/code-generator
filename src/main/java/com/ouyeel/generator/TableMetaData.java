package com.ouyeel.generator;

import java.util.List;

/**
 * 保存表对应生成接口和类信息
 *
 * @author dujiayong
 * @create 2017-08-29 21:08
 */
public class TableMetaData {

    /**
     * domain类名
     */
    private String domainClassName;
    /**
     * service接口名
     */
    private String serviceInterfaceName;
    /**
     * service接口实现类名
     */
    private String serviceImplName;
    /**
     * mapper文件名
     */
    private String mapperInterfaceName;
    /**
     * mapper接口对应xml文件名
     */
    private String mapperXmlName;
    /**
     * 表对应的列信息
     */
    private List<ColumnMetaData> cols;

    public TableMetaData(String tableName){
        tableName = format(tableName);
        domainClassName = tableName + ".java";
        serviceInterfaceName = tableName + "Interface.java";
        serviceImplName = tableName + "InterfaceImpl.java";
        mapperInterfaceName = tableName + "Mapper.java";
        mapperXmlName = tableName + "Mapper.xml";
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
     * @return 格式化的表名
     */
    private String format(String tableName){
        StringBuffer buffer = new StringBuffer();
        String[] names = tableName.split("_");
        for(int i=0;i<names.length;i++){
            String name = names[i];
            name = name.replace(name.substring(0,1),name.substring(0,1).toUpperCase());
            buffer.append(name);
        }
        return buffer.toString();
    }
}
