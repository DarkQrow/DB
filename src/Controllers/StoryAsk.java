package Controllers;

import javax.net.ssl.SSLContext;

public class StoryAsk {
    private int id;
    private String tableName;
    private String tableId;
    private String operation;
    private String operationTime;

    public StoryAsk(int id,String tableName,String tableId, String operation,String operationTime){
        this.id = id;
        this.tableName = tableName;
        this.tableId = tableId;
        this.operation = operation;
        this.operationTime = operationTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getOperation() {
        return operation;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public String getTableId() {
        return tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public void setTableName(String tableName) {

        this.tableName = tableName;
    }
}
