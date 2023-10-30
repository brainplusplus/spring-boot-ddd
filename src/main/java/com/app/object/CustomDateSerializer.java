/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.object;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author brainplusplus
 */
public class CustomDateSerializer extends JsonSerializer<Date> {    
    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws 
            IOException, JsonProcessingException {      
        if(value!=null){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = formatter.format(value);

            gen.writeString(formattedDate);
        }else{
            gen.writeString("0000-00-00");
        }
    }
}
