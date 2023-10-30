package com.app.entity.statik;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Agung Andika on 15/08/2016.
 */
public class JenisRealisasiStatik {

    private final String valueJenisRealisasi;
    private final String textJenisRealisasi;

    JenisRealisasiStatik(String value, String text) {
        this.valueJenisRealisasi = value;
        this.textJenisRealisasi = text;
    }

    public String getValueJenisRealisasi()
    {
        return valueJenisRealisasi;
    }

    public String getTextJenisRealisasi()
    {
        return textJenisRealisasi;
    }

    public static Map<String, String> getListJenisRealisasi() {
        Map<String, String> listJenisRealisasi = new HashMap<String, String>();
        listJenisRealisasi.put("UANG_MUKA", "Uang Muka");
        listJenisRealisasi.put("TERMIN1", "Termin 1");
        listJenisRealisasi.put("TERMIN2", "Termin 2");
        listJenisRealisasi.put("TERMIN3", "Termin 3");
        listJenisRealisasi.put("TERMIN4", "Termin 4");
        return listJenisRealisasi;
    }
}
