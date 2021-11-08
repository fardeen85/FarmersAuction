package com.example.landingpage;

public class model5 {
    String NAME,TYPE,DESCRIPTION,PRICE,CROP_ID,namet,email,image;
    public model5(String NAME, String TYPE, String DESCRIPTION, String PRICE,String CROP_ID,String namet,String email,String image) {
        this.NAME = NAME;
        this.TYPE = TYPE;
        this.DESCRIPTION = DESCRIPTION;
        this.PRICE = PRICE;
        this.CROP_ID = CROP_ID;
        this.namet = namet;
        this.email = email;
        this.image = image;
    }
    public model5() {

    }
    public String getName() {
        return NAME;
    }

    public void setName(String NAME) { this.NAME = NAME;}

    public String getType() {
        return TYPE;
    }

    public void setType(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getDESCRIPTION() { return DESCRIPTION; }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getPrice() {
        return PRICE;
    }

    public void setPrice(String PRICE) { this.PRICE = PRICE;}

    public String getCROP_ID() {
        return CROP_ID;
    }
    public void setCROP_ID(String CROP_ID) { this.CROP_ID = CROP_ID;}

    public void setnamet(String namet) {
        this.namet = namet;
    }
    public String getnamet() {
        return namet;
    }

    public void setemail(String email) {
        this.email = email;
    }
    public String getemail() {
        return email;
    }

    public void setimage(String image) {
        this.image = image;
    }
    public String getimage() {
        return image;
    }

}
