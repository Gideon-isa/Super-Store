package com.ltp.globalsuperstore.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltp.globalsuperstore.Constants;
import com.ltp.globalsuperstore.Item;
import com.ltp.globalsuperstore.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public Item getFormData(String id) {
        int index = getIndex(id);
        if (index == Constants.NOT_FOUND) {
            return itemRepository.getNewItem();
        }
        return itemRepository.getItem(index);
    }

    public List<Item> getItems() {
        return itemRepository.getItems();
    }

    
    private Item getItem(int index) {
        return itemRepository.getItem(index);
    }


    private void updateItem( Item item, int index) {
        itemRepository.updateItem(item, index);
    }

    private void addItem(Item item) {
        itemRepository.addItem(item);
    }

    public Integer getIndex(String id) {
        for (int i = 0; i < itemRepository.getItems().size(); i++) {
            if (itemRepository.getItems().get(i).getId().equals(id)) {
                return i;
            }
        }

        return Constants.NOT_FOUND;
        
    }

    private boolean within5Days(Date newDate, Date oldDate) {
        long diff = Math.abs(newDate.getTime() - oldDate.getTime());

        return (int)TimeUnit.MILLISECONDS.toDays(diff) <= 5;

    }

    public String handleSubmit(Item item) {
        int index = getIndex(item.getId());
        String status = Constants.SUCCESS_STATUS;
        if (index == Constants.NOT_FOUND) {
            addItem(item);
        }else if (within5Days(item.getDate(), getItem(index).getDate())){
            updateItem(item, index);
        }else{
            status = Constants.FAILED_STATUS;
        }

        return status;
    }

    
}
