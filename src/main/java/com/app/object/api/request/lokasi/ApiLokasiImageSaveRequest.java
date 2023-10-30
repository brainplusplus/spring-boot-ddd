/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.object.api.request.lokasi;

import com.app.entity.LokasiImages;
import com.app.object.MultiPartObject;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author brainplusplus
 */
public class ApiLokasiImageSaveRequest {
    public String token;
    public String idLokasi;
    public String appId;
    public MultiPartObject image;
    public Integer type;
    public LokasiImages lokasiImage;
}
