package org.jpa.mysql.domain;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

public class Foo {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String f_name;
    private String l_name;
    private Date dob;

    protected Foo() {}

    public Foo(String f_name, String l_name, Date dob) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.dob = dob;
    }

    @Override
    public String toString() {
        return String.format(
                "Character[id=%d, f_name='%s', l_name='%s', dob %s]",
                id, f_name, l_name, dob);
    }

}
