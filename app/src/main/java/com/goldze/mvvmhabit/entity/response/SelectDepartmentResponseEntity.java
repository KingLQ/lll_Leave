package com.goldze.mvvmhabit.entity.response;

public class SelectDepartmentResponseEntity {


    /**
     * id : 2e29a3f9-b7c1-44af-882f-57121e421f44
     * departmentName : 技术部
     * createtime : 2019/08/27 15:35:49
     * createuser : 7d8c9638-6b69-4607-b9de-3eca2d44484c
     * modifytime : null
     * modifyuser : null
     * isdelete : false
     */

    private String id = "";
    private String departmentName = "";
    private String createtime = "";
    private String createuser = "";
    private String modifytime = "";
    private String modifyuser = "";
    private boolean isdelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }

    public String getModifyuser() {
        return modifyuser;
    }

    public void setModifyuser(String modifyuser) {
        this.modifyuser = modifyuser;
    }

    public boolean isIsdelete() {
        return isdelete;
    }

    public void setIsdelete(boolean isdelete) {
        this.isdelete = isdelete;
    }
}
