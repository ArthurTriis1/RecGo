package br.edu.ufpe.recife.tads.recgo.models.dto;

import java.io.Serializable;
import java.util.Date;

public class Image  implements Serializable {
    public int id;
    public String name;
    public String alternativeText;
    public String caption;
    public int width;
    public int height;
    public ImageGroup formats;
    public String hash;
    public String ext;
    public String mime;
    public double size;
    public String url;
    public String provider;
    public Date created_at;
    public Date updated_at;
}
