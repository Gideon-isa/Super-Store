package com.ltp.globalsuperstore.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ltp.globalsuperstore.Constants;
import com.ltp.globalsuperstore.Item;

@Repository
public class ItemRepository {
    
    private List<Item> itemList = new ArrayList<>();

    public Item getItem(int index) {
        return itemList.get(index);
    }
  
    public List<Item> getItems() {
        return itemList;
    }

    public Item getNewItem() {
        return new Item();
    }

    public void updateItem(Item item, int index) {
        itemList.set(index, item);
    }

    public void addItem(Item item) {
        itemList.add(item);
    }


}
