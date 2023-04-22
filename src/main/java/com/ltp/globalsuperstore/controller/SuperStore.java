package com.ltp.globalsuperstore.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ltp.globalsuperstore.Constants;
import com.ltp.globalsuperstore.Item;
import com.ltp.globalsuperstore.service.ItemService;

@Controller
public class SuperStore {

    

    @Autowired
    ItemService itemService;
   
    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        //model.addAttribute("categories", Constants.CATEGORIES);

        //model.addAttribute("item", index == Constants.NOT_FOUND ? new Item() : itemList.get(index));
        model.addAttribute("item", itemService.getFormData(id));

        return "form";
    }

    @GetMapping("/inventory")
    public String getInventory(Model model) {
        model.addAttribute("items", itemService.getItems());
        return "inventory";
    }

    @PostMapping("/submitItem")
    // NOTE THE BINDINGRESULT VARIABLE MUST BE THE NEXT IMMEDIATELY AFTER THE ITEM
    public String submitForm(@Valid @ModelAttribute("item") Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        
        if (item.getPrice() < item.getDiscount()) {
            bindingResult.rejectValue("price", "", null, "Price cannot be less than discount");
        }
        if (bindingResult.hasErrors()) {
            return "form";
        }

        String status = itemService.handleSubmit(item);
     
        // No need to add the item to the model here as it will be added mapped in the getInventory handler
        // model.addAttribute("list", list); -- 

        // adding attributes to the redirects
        redirectAttributes.addFlashAttribute("status", status);

        return "redirect:/inventory";
    }

  
}
