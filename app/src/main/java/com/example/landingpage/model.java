package com.example.landingpage;

public class model {
    String NAME,TYPE,DESCRIPTION,PRICE,img1;

    public model(String NAME, String TYPE, String DESCRIPTION, String PRICE,String img1) {
        this.NAME = NAME;
        this.TYPE = TYPE;
        this.DESCRIPTION = DESCRIPTION;
        this.PRICE = PRICE;
        this.img1 = img1;
    }

    public model() {

    }

    public String getName() {
        return NAME;
    }

    public void setName(String NAME) {
        this.NAME = NAME;
    }

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
    public void setPrice(String PRICE) {
        this.PRICE = PRICE;
    }

    public String getimg1() {
        return img1;
    }
    public void setimg1(String img1) {
        this.img1 = img1;
    }
}
