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
    //Lista oggetti
    private ArrayList<ObjectSale> objectList = new ArrayList<ObjectSale>();
    private static ObjectSaleFactory singleton;
    
    //Costruttore
    private ObjectSaleFactory() {
        int i = 1;
        
        //Oggetto 1
        ObjectSale object1 = new ObjectSale();
        object1.setId(i++);
        object1.setCategoria("completo");
        object1.setNome("Hudora Skateboard, ABEC 5");
        object1.setUrlImmagine("img/hudora_skate.jpg");
        object1.setQuantita(5);
        object1.setPrezzo(25.46);
        objectList.add(object1);
        
        //Oggetto 2
        ObjectSale object2 = new ObjectSale();
        object2.setId(i++);
        object2.setCategoria("trucks");
        object2.setNome("Truck Independent: 149 Salazar "
                + "Doomsayers Matte Nero Std Stage 11");
        object2.setUrlImmagine("img/truck_independent.jpg");
        object2.setQuantita(12);
        object2.setPrezzo(40.71);
        objectList.add(object2);
    }
    
    //Metodi
    public static ObjectSaleFactory getInstance() {
        if(singleton == null)
            singleton = new ObjectSaleFactory();
        
        return singleton;
    }
    
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
    
    public int getCounterId() {
        return objectList.size();
    }
}

