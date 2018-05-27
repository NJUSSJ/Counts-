package com.seproject.domain.testDomain;

import com.seproject.common.Searchable;

import javax.persistence.*;

@Entity
@Table(name="TestH")
public class TestH {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @Searchable(varName="id")
    private String id;

    public TestH() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }
}
