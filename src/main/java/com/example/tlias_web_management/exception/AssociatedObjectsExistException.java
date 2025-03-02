package com.example.tlias_web_management.exception;

public class AssociatedObjectsExistException extends RuntimeException {
    
    private String objectName;
    private String associatedObjectName;

    public AssociatedObjectsExistException(String objectName, String associatedObjectName) {
        super("对不起, 当前" + objectName + "下有" + associatedObjectName + ", 不能直接删除!");
        this.objectName = objectName;
        this.associatedObjectName = associatedObjectName;
    }

    public String getObjectName() {
        return objectName;
    }

    public String getAssociatedObjectName() {
        return associatedObjectName;
    }
}
