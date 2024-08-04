package model;

public class contacts {



    private int contact_id;
    private String contact_Name;
    private String email;

    public contacts(int contact_id, String contact_Name, String email) {
        this.contact_id = contact_id;
        this.contact_Name = contact_Name;
        this.email = email;
    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public String getContact_Name() {

        return contact_Name;
    }

    public void setContact_Name(String contact_Name) {
         this.contact_Name = contact_Name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return ("#"+ Integer.toString(contact_id) + " " + contact_Name + " [" +email + "]");
    }
    //return ("#" + Integer.toString(customer_ID) + " " + customer_Name

}
