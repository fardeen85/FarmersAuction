package com.example.landingpage;
public class model2 {
    String name, email, password, Company, phone, city, descript;

    public model2(String name, String email, String password, String Company, String phone1, String city, String descript) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.Company = Company;
        this.phone = phone;
        this.city = city;
        this.descript = descript;

    }
public model2(){

}
    public String getname() { return name; }

    public void setname(String name) { this.name = name; }

    public String getemail() { return email; }

    public void setemail(String email) { this.email = email; }

    public String getpassword() { return password;}

    public void setpassword(String password) { this.password = password; }

    public String getCompany() { return Company;}

    public void setCompany(String Company) { this.Company = Company; }

    public String getphone() { return phone;}

    public void setphone1(String phone) { this.phone = phone;}

    public String getcity() { return city;}

    public void setcity(String city) { this.city = city; }

    public String getdescript() { return descript;}

    public void setdescript(String descript) {this.descript = descript;}
}
