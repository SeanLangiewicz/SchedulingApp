package model;

public class month_Type
{
    public month_Type(String month, String type, Integer count) {
        this.month = month;
        this.type = type;
        this.count = count;
    }

    String month;
    String type;
    Integer count;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
