package com.ouyeel.generator;

/**
 * 保存表列名和列对应属性信息
 *
 * @author dujiayong
 * @create 2017-08-29 21:25
 */
public class ColumnMetaData {
    /**
     * 列名
     */
    private String colName;
    /**
     * 列类型
     */
    private String colType;
    /**
     * 列描述
     */
    private String colDesc;
    /**
     * 列对应属性名
     */
    private String fieldName;
    /**
     * set方法名
     */
    private String setOpeName;
    /**
     * get方法名
     */
    private String getOpeName;
    /**
     * 是否为主键
     */
    private boolean pkFlag;

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
        this.fieldName = format(colName,false);
        this.setOpeName = "set" + format(colName,true);
        this.getOpeName = "get" + format(colName,true);
    }

    public String getColType() {
        return colType;
    }

    public void setColType(String colType) {
        this.colType = colType;
    }

    public String getColDesc() {
        return colDesc;
    }

    public void setColDesc(String colDesc) {
        this.colDesc = colDesc;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getSetOpeName() {
        return setOpeName;
    }

    public void setSetOpeName(String setOpeName) {
        this.setOpeName = setOpeName;
    }

    public String getGetOpeName() {
        return getOpeName;
    }

    public void setGetOpeName(String getOpeName) {
        this.getOpeName = getOpeName;
    }

    public boolean isPkFlag() {
        return pkFlag;
    }

    public void setPkFlag(boolean pkFlag) {
        this.pkFlag = pkFlag;
    }

    /**
     * 将列名格式化为属性名
     * @param colName 需要格式化的列名
     * @param firstUpper 格式化后字符串首字符是否大写
     * @return 格式化的属性名
     */
    private String format(String colName,boolean firstUpper){
        StringBuffer buffer = new StringBuffer();
        String[] splitColNames = colName.split("_");
        for(int i=0;i<splitColNames.length;i++){
            if(i==0&&firstUpper){
                String name = splitColNames[i].toLowerCase();
                buffer.append(name);
            }else{
                String name = splitColNames[i];
                String before = name.substring(0,1).toUpperCase();
                String after = name.substring(1).toLowerCase();
                buffer.append(before).append(after);
            }
        }
        return buffer.toString();
    }
}
