/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author brainplusplus
 */
@MappedSuperclass
public class BaseModel implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    protected String id;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
