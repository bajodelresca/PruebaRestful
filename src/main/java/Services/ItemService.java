/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Exceptions.RecordNotFoundException;
import Model.Item;
import Repositories.ItemRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author espin
 */
@Service
public class ItemService {
    @Autowired
    ItemRepository repository;
    
     public List<Item> getAllItems()
    {
        List<Item> itemList = repository.findAll();
         
        if(itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<Item>();
        }
    }
     
    public Item getItemById(Long id) throws RecordNotFoundException
    {
        Optional<Item> item = repository.findById(id);
         
        if(item.isPresent()) {
            return item.get();
        } else {
            throw new RecordNotFoundException("No item record exist for given id",id);
        }
    }
    public Item createItem(Item entity){
        entity = repository.save(entity);
        return entity;
    }
    public Item UpdateItem(Item entity) throws RecordNotFoundException
    {
    	    	
    	if(entity.getId()!=null)
    	{
    	  Optional<Item> item = repository.findById(entity.getId());
        
            if(item.isPresent())
            {
                Item newEntity = item.get();
                //newEntity.setId(entity.getId());
                newEntity.setTitle(entity.getTitle());
                newEntity.setDescription(entity.getDescription());

                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Item not found",entity.getId());
            }
        }else{
    		throw new RecordNotFoundException("No id of item given",0l);
    	}	    
 }
     
    public void deleteItemById(Long id) throws RecordNotFoundException
    {
        Optional<Item> item = repository.findById(id);
         
        if(item.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No item record exist for given id",id);
        }
    }

  
}
