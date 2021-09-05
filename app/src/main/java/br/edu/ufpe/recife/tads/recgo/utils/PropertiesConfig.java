package br.edu.ufpe.recife.tads.recgo.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesConfig {
    private static final String BASE_URL = "http://192.168.0.113:1337";

    public static String getBaseUrl() {
        return BASE_URL;
    }
}
