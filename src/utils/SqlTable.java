package utils;

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
        // System.out.println("setTableFields = " + String.join(",", tableFields));
    }

    public Integer getFieldIndex(String field) {
        for (int i = 0; i < tableFields.length; i++) {
            // System.out.println("getFieldIndex("+tableFields[i]+") = " + i +1);
            if(tableFields[i].equals(field)) return i + 1;
        }
        // System.out.println("getFieldIndex = null");
        return null;
    }

    public String getFieldsString() {
        return String.join(",", tableFields);
    }

    public String getFieldsSafeString() {
        String[] fields = this.getTableFields().clone();

        for (int i = 0; i < fields.length; i++) {
            fields[i] = "?";
        }
        // System.out.println("getFieldsSafeString = " + String.join(",", fields));
        return String.join(",", fields);
    }

    public String getFieldsSafeUpdateString() {
        String[] fields = this.getTableFields().clone();

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

    public String buildSelect(String fields) {
        return String.format("SELECT %s FROM %s", fields, this.getTableName());
    }

    public String buildSelect(String fields, String where) {
        return String.format("SELECT %s FROM %s WHERE %s", fields, this.getTableName(), where);
    }

}