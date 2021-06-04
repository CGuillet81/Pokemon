package fr.eni.papeterie.dal.jdbc;

import java.util.Properties;

public class Settings {
    private static Properties propriete;

    static {
        try{
            propriete = new Properties();
            propriete.load(Settings.class.getResourceAsStream("setting.properties"));
        } catch (Exception e){
            System.err.println(e.getMessage());       }
    }

    public static String getPropriete(String cle) {
        return propriete.getProperty(cle, null);
    }
}
