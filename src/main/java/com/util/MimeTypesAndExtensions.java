package com.util;

import java.util.HashMap;

public class MimeTypesAndExtensions {

    static HashMap<String, String> TheHashMap = new HashMap<String, String>();

    public MimeTypesAndExtensions() {
        super();
    }

    static {
        CreateHashMap();
    }

    private static void CreateHashMap() {

        TheHashMap.put("application/pdf", ".pdf");
        TheHashMap.put("application/vnd.ms-excel", ".xls");
        TheHashMap.put("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", ".xlsx");
        TheHashMap.put("application/msword", ".doc");
        TheHashMap.put("application/vnd.openxmlformats-officedocument.wordprocessingml.document", ".docx");
        TheHashMap.put("application/vnd.ms-powerpoint", ".ppt");
        TheHashMap.put("application/vnd.openxmlformats-officedocument.presentationml.presentation", ".pptx");
        TheHashMap.put("text/plain", ".txt");
        TheHashMap.put("audio/mp4", ".mp4a");
        TheHashMap.put("video/mp4", ".mp4");
        TheHashMap.put("application/mp4", ".mp4");
        TheHashMap.put("audio/webm", ".weba");
        TheHashMap.put("video/webm", ".webm");
        TheHashMap.put("image/jpeg", ".jpg");
        TheHashMap.put("image/x-citrix-jpeg", ".jpg");
        TheHashMap.put("video/x-m4v", ".m4v");
        TheHashMap.put("video/x-ms-wm", ".wm");
        TheHashMap.put("audio/x-ms-wma", ".wma");
        TheHashMap.put("audio/x-ms-wax", ".wax");
        TheHashMap.put("image/png", ".png");
        TheHashMap.put("image/x-citrix-png", ".png");
        TheHashMap.put("image/x-png", ".png");
        TheHashMap.put("image/x-portable-pixmap", ".ppm");
        TheHashMap.put("application/xml", ".xml");
        TheHashMap.put("application/zip", ".zip");
        TheHashMap.put("application/x-rar-compressed", ".rar");
        TheHashMap.put("text/css", ".css");
        TheHashMap.put("text/html", ".html");
        TheHashMap.put("application/java-archive", ".jar");
        TheHashMap.put("application/java-vm", ".class");
        TheHashMap.put("application/javascript", ".js");
        TheHashMap.put("application/json", ".json");
        TheHashMap.put("application/vnd.ms-excel.addin.macroenabled.12", ".xlam");
        TheHashMap.put("application/vnd.ms-excel.sheet.binary.macroenabled.12", ".xlsb");
        TheHashMap.put("application/vnd.ms-excel.template.macroenabled.12", ".xltm");
        TheHashMap.put("application/vnd.ms-excel.sheet.macroenabled.12", ".xlsm");
        TheHashMap.put("video/x-ms-wmx", ".wmx");
        TheHashMap.put("video/x-ms-wvx", ".wvx");
        TheHashMap.put("application/octet-stream", ".exe");
        TheHashMap.put("audio/mpeg", ".mp3");
        TheHashMap.put("audio/mp3", ".mp3");
        TheHashMap.put("video/x-msvideo", ".avi");
        TheHashMap.put("video/x-matroska", ".mkv");
        TheHashMap.put("video/x-ms-vob", ".vob");
        TheHashMap.put("video/x-sgi-movie", ".movie");
        TheHashMap.put("audio/vnd.wave", ".wav");
        TheHashMap.put("video/3gpp", ".3gp");
        TheHashMap.put("video/quicktime", ".mov");
        TheHashMap.put("video/x-ms-wmv", ".wmv");
        TheHashMap.put("image/gif", ".gif");
        TheHashMap.put("image/bmp", ".bmp");
        TheHashMap.put("image/x-ms-bmp", ".bmp");
        TheHashMap.put("image/tiff", ".tiff");
        TheHashMap.put("image/vnd.adobe.photoshop", ".psd");
        TheHashMap.put("video/x-flv", ".flv");
        TheHashMap.put("video/mpeg", ".mpg");
        TheHashMap.put("video/x-ms-asf", ".asf");
        TheHashMap.put("audio/x-flac", ".flac");
        TheHashMap.put("audio/x-aiff", ".aiff");
    }
    public static String getExtensionFromMimeType(String TheMimeType) {

        String TheExtension = "Extension Not Found";
        for(String key: TheHashMap.keySet()){
            if(key.toLowerCase().equals(TheMimeType.toLowerCase())) {
                TheExtension = TheHashMap.get(key);
                return TheExtension;
            }
        }

        return TheExtension;
    }

    public static String getMimeTypeFromExtension(String TheExtension) {

        StringBuilder TheMimeTypes = new StringBuilder();
        for(String key: TheHashMap.keySet()){
            if(TheHashMap.get(key.toLowerCase()).equals(TheExtension.toLowerCase())) {
                TheMimeTypes.append(key.concat("\n"));
            }
        }

        if(TheMimeTypes.length() == 0)
            return "MimeType Not Found";

        return TheMimeTypes.toString();
    }

    public static String getMimeTypesAndExtensions(){

        StringBuilder TheMimeTypesAndExtensions = new StringBuilder();
        for(String key: TheHashMap.keySet()){
            TheMimeTypesAndExtensions.append(TheHashMap.get(key).concat("   ----->   ").concat(key).concat("\n"));
        }

        return TheMimeTypesAndExtensions.toString();
    }
}
