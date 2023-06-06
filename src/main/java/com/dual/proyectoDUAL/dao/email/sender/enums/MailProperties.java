package com.dual.proyectoDUAL.dao.email.sender.enums;

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

}