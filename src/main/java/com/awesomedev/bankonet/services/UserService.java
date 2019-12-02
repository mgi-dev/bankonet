package com.awesomedev.bankonet.services;

import com.awesomedev.bankonet.exceptions.ClientNotFoundException;
import com.awesomedev.bankonet.repositories.UserRepository;
import com.awesomedev.bankonet.utils.ObjectTools;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.awesomedev.bankonet.DTO.Client;
import org.springframework.validation.annotation.Validated;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Iterable<Client> findAll(){
        ArrayList<Client> userList = new ArrayList<>();

        for (com.awesomedev.bankonet.models.Client client : userRepository.findAll()){
            userList.add(new Client(client));
        }
        return userList;
    }

    public Client find(Integer id) throws ClientNotFoundException {
        return new Client(userRepository.findById(id).orElseThrow(ClientNotFoundException::new));
    }

    public Client save(Client client){
        return new Client(userRepository.save(new com.awesomedev.bankonet.models.Client(client)));
    }

    public Client update(Client client) throws Exception {
        com.awesomedev.bankonet.models.Client clientModel = userRepository.findById(client.getId()).orElseThrow(Exception::new);
        copyNonNullProperties(client, clientModel);
        return new Client(userRepository.save(clientModel));
    }

    public void copyNonNullProperties(Object src, Object target) {

        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static /*String[]*/ void getNullPropertyNames2 (Object source) {
        System.out.println(source.getClass().getDeclaredFields());
        System.out.println(ObjectTools.stringifyObject(source));
        for (Field attribute : source.getClass().getDeclaredFields()){
            System.out.println(attribute);
            System.out.println(attribute);
            attribute.setAccessible(true);
            try {
                Object value = attribute.get(source);
                System.out.println(value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
        System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
        System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
        System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
        System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
        System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
        System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
        System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
        System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
        System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
        System.out.println("''''''''''''''''''''''''''''''''''''''''''''''''''");
        System.out.println(emptyNames.toArray(result));
        return emptyNames.toArray(result);
    }
}
