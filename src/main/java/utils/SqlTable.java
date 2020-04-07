package main.java.utils;

public class SqlTable {

    private String tableName;
    private String[] tableFields;

    public SqlTable(String name, String[] fields) {
        this.setTableName(name);
        this.setTableFields(fields);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String[] getTableFields() {
        return tableFields;
    }

    public void setTableFields(String[] tableFields) {
        this.tableFields = tableFields;
    }

    public Integer getFieldIndex(String field) {
        for (int i = 0; i < tableFields.length; i++) {
            if(tableFields[i].equals(field)) return i;
        }
        return null;
    }

    public String getFieldsString() {
        return String.join(",", tableFields);
    }

    public String getFieldsSafeString() {
        String[] fields = tableFields;
        for (int i = 0; i < fields.length; i++) {
            fields[i] = "?";
        }
        return String.join(",", fields);
    }

    public String getFieldsSafeUpdateString() {
        String[] fields = tableFields;
        for (int i = 0; i < fields.length; i++) {
            fields[i] = String.format("%s = ?", fields[i]);
        }
        return String.join(",", fields);
    }

    public String buildInsert() {
        return String.format("INSERT INTO %s (%s) values (%s)", this.getTableName(), this.getFieldsString(), this.getFieldsSafeString());
    }

    public String buildUpdate() {
        return String.format("UPDATE %s SET %s", this.getTableName(), this.getFieldsSafeUpdateString());
    }

}