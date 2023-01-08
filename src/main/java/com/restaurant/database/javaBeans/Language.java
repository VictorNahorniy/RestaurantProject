package com.restaurant.database.javaBeans;

import java.util.Objects;

public class Language extends BaseEntity {
    private int id;
    private String langName;

    public Language(int id, String langName) {
        this.id = id;
        this.langName = langName;
    }

    public Language(String langName) {
        if(langName == null){
            this.langName = "en";
            this.id = 1;
            return;
        }
        if(langName.equals("ukr")) this.id = 2;
        if (langName.equals("en")) this.id = 1;
    }

    public int getId() {
        return id;
    }

    public String getLangName() {
        return langName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLangName(String langName) {
        this.langName = langName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Language language)) return false;
        return getId() == language.getId() && Objects.equals(getLangName(), language.getLangName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLangName());
    }

    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", langName='" + langName + '\'' +
                '}';
    }
}
