package com.dual.proyectoDUAL.email.sender.enums;

import lombok.Getter;

@Getter
public enum MailProperties {
    USER(1,"mail.smtp.user"),
    PASSWORD(2,"mail.smtp.password");

    private final int id;
    private final String name;

    MailProperties(int id, String name){
        this.name=name;
        this.id = id;
    }

    public boolean compareValue(int id){
        return this.id == id;
    }

    public boolean compareValue(String valueToCompare){
        return this.name.equals(valueToCompare);
    }

    public static MailProperties getValueById(int id){
//        return Stream.of(values()).filter(data -> data.getId() == id).findFirst().orElse(null);
        for(MailProperties mailProp : values()){
            if(mailProp.getId() == id){
                return mailProp;
            }
        }
        return null;
    }

}