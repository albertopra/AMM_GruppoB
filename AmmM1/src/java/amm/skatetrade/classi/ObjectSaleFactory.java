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
        
        //Oggetto 3
        ObjectSale object3 = new ObjectSale();
        object3.setId(i++);
        object3.setCategoria("tavola");
        object3.setNome("Tavola Element: Westgate Split 8");
        object3.setUrlImmagine("img/tavola_element.jpg");
        object3.setQuantita(7);
        object3.setPrezzo(58.70);
        objectList.add(object3);
        
        //Oggetto 4
        ObjectSale object4 = new ObjectSale();
        object4.setId(i++);
        object4.setCategoria("tavola");
        object4.setNome("Tavola Zero: Dead Presidents "
                + "Jamie Thomas 8.375");
        object4.setUrlImmagine("img/tavola_zero.jpg");
        object4.setQuantita(9);
        object4.setPrezzo(63.82);
        objectList.add(object4);
        
        //Oggetto 5
        ObjectSale object5 = new ObjectSale();
        object5.setId(i++);
        object5.setCategoria("ruote");
        object5.setNome("Spitfire - Kit di 4 "
                + "ruote per skateboard Bighead");
        object5.setUrlImmagine("img/ruote_spitfire.jpg");
        object5.setQuantita(13);
        object5.setPrezzo(46.57);
        objectList.add(object5);
        
        //Oggetto 6
        ObjectSale object6 = new ObjectSale();
        object6.setId(i++);
        object6.setCategoria("cuscinetti");
        object6.setNome("Cuscinetti Skateboard Enuff Black");
        object6.setUrlImmagine("img/cuscinetti_enuff_black.jpg");
        object6.setQuantita(11);
        object6.setPrezzo(14.99);
        objectList.add(object6);
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

