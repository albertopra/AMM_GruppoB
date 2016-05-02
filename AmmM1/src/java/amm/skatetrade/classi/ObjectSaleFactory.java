/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.skatetrade.classi;

import amm.skatetrade.classi.ObjectSale;
import java.util.ArrayList;

/**
 *
 * @author alber
 */
public class ObjectSaleFactory {
    private static ObjectSaleFactory singleton;
    public static ObjectSaleFactory getInstance() {
        if(singleton == null)
            singleton = new ObjectSaleFactory();
        
        return singleton;
    }
    
    //Lista oggetti
    private ArrayList<ObjectSale> objectList = new ArrayList<ObjectSale>();
    
    //Costruttore
    private ObjectSaleFactory() {
        ObjectSale object = new ObjectSale();
        int i = 1;
        
        //Oggetto 1
        object.setId(i++);
        object.setCategoria("completo");
        object.setNome("Hudora Skateboard, ABEC 5");
        object.setUrlFoto("img/hudora_skate.jpg");
        object.setQuantita(5);
        object.setPrezzo(25.46);
        objectList.add(object);
        
        //Oggetto 2
        object.setId(i++);
        object.setCategoria("trucks");
        object.setNome("Truck Independent: 149 Salazar "
                + "Doomsayers Matte Nero Std Stage 11");
        object.setUrlFoto("img/truck_independent.jpg");
        object.setQuantita(12);
        object.setPrezzo(40.71);
        objectList.add(object);
    }
    
    //Metodi
    public ArrayList<ObjectSale> getSellingObjectList() {
        return objectList;
    }
    
    public ArrayList<ObjectSale> getSellingObjectByCategory(String categoria) {
        ArrayList<ObjectSale> objectListbyCategory = new ArrayList<ObjectSale>();
        
        for (ObjectSale obj : objectList) {
            if(obj.getCategoria().equals(categoria))
                objectListbyCategory.add(obj);
        }
        
        return objectListbyCategory;
    }
    
    public ObjectSale getObjectSaleById(int id) {
        for (ObjectSale obj : objectList)
            if(obj.getId() == id)
                return obj;
        
        return null;
    }
}

