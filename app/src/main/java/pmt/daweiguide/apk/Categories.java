package pmt.daweiguide.apk;

public class Categories {
    String CateName;
    int CateImage;
    String CName;

    public Categories (String Name, int Image, String CName) {
        this.CateName = Name;
        this.CateImage = Image;
        this.CName=CName;
    }

    public String getCateName() {
        return CateName;
    }

    public int getCateImage() {
        return CateImage;
    }

    public String getCName() {
        return CName;
    }

}
