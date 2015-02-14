/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.faces.context.FacesContext;

public class LeilaoDAO{

    private static String fileName;

    static {
        try {
        	FacesContext ctx = FacesContext.getCurrentInstance();
        	String temp = ctx.getExternalContext().getInitParameter("arquivoConf");
        	String pathConfProp = ctx.getExternalContext().getRealPath(temp);
        	
            FileInputStream fileInput = new FileInputStream(new File(pathConfProp));
            Properties properties = new Properties();
            properties.load(fileInput);
            fileName = properties.getProperty("leiloesStorageFile");
            fileInput.close();
        } catch (IOException ex) {
            System.err.println("[CONFIG] Could not read the configuration file");
        }
    }

    public static boolean save(Serializable object) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();

        } catch (FileNotFoundException ex) {
            System.err.println("[PERSIST] File not found");
            return false;
        } catch (IOException ex) {
            System.err.println("[PERSIST] I/O Error");
            return false;
        }
        return true;
    }

    public static Serializable load() {
        Serializable serializable = null;
        try {
            FileInputStream fin = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fin);
            serializable = (Serializable) ois.readObject();
            ois.close();

        } catch (Exception ex) {
            System.err.println("[PERSIST] Error");
        }
        return serializable;
    }
    
    public static boolean checkFile() {
        try {
            FileInputStream fin = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fin);
            ois.readObject();
            ois.close();

        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
