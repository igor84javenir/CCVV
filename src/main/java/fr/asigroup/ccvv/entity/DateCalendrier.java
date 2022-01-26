package fr.asigroup.ccvv.entity;

public class DateCalendrier {
    private String texte;
    private String classeCSS;
    private boolean cliquable;

    public DateCalendrier() {
    }

    public DateCalendrier(String texte, String classeCSS, boolean cliquable) {
        this.texte = texte;
        this.classeCSS = classeCSS;
        this.cliquable = cliquable;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getClasseCSS() {
        return classeCSS;
    }

    public void setClasseCSS(String classeCSS) {
        this.classeCSS = classeCSS;
    }

    public boolean isCliquable() {
        return cliquable;
    }

    public void setCliquable(boolean cliquable) {
        this.cliquable = cliquable;
    }

    @Override
    public String toString() {
        return "DateCalendrier{" +
                "texte='" + texte + '\'' +
                ", classeCSS='" + classeCSS + '\'' +
                ", cliquable=" + cliquable +
                '}';
    }
}
