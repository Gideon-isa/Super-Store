package com.ltp.globalsuperstore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SuperStore {

    List<Item> itemList = new ArrayList<>();
   
    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        model.addAttribute("categories", Constants.CATEGORIES);
        int index = getIndex(id);
        //model.addAttribute("item", index == Constants.NOT_FOUND ? new Item() : itemList.get(index));
        if (index == Constants.NOT_FOUND) {
            model.addAttribute("item", new Item());     
        }else {
            model.addAttribute("item", itemList.get(index));
        }

        return "form";
    }

    @GetMapping("/inventory")
    public String getInventory(Model model) {
        model.addAttribute("items", itemList);
        return "inventory";
    }

    @PostMapping("/submitItem")
    public String submitForm(Item item, RedirectAttributes redirectAttributes) {
        int index = getIndex(item.getId());
        String status = Constants.SUCCESS_STATUS;
        if (index == Constants.NOT_FOUND) {
            itemList.add(item);
        }else if (within5Days(item.getDate(), itemList.get(index).getDate())){
            itemList.set(index, item);
        }else{
            status = Constants.FAILED_STATUS;
        }
        
        // No need to add the item to the model here as it will be added mapped in the getInventory handler
        // model.addAttribute("list", list); -- 

        // adding attributes to the redirects
        redirectAttributes.addFlashAttribute("status", status);

        return "redirect:/inventory";
    }

    private Integer getIndex(String id) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getId().equals(id)) {
                return i;
            }
        }

        return Constants.NOT_FOUND;
        
    }

    private boolean within5Days(Date newDate, Date oldDate) {
        long diff = Math.abs(newDate.getTime() - oldDate.getTime());

        return (int)TimeUnit.MILLISECONDS.toDays(diff) <= 5;

    }
}
