package com.tobeto.spring.b;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/persons") // Bu controllerın kontrol edeceği alt routeları tanımlamak
public class PersonsController {
    List<Person> inMemoryList = new ArrayList<>();

    // In Memory DB
    // CRUD => Create,Read,Update,Delete


    @GetMapping // api/persons
    public List<Person> get(){
        return inMemoryList;
    }
    //@GetMapping("getById")
    // query string, path variable
    // path => https://localhost:8080/api/persons/30/halit/19
    // query string => https://localhost:8080/api/persons/getById?id=1&name=halit&age=19
    @GetMapping("{id}")
    public Person getById(@PathVariable int id){
        // Lambda Expressions
        // Stream API
        Person person = inMemoryList
                .stream()
                // Lambda Expressions
                .filter((p) -> p.getId() == id) // 1 eleman [ {} ]
                .findFirst() // {}
                .orElseThrow();
        return person;
    }


    @PostMapping
    public void add(@RequestBody Person person)
    {
        inMemoryList.add(person);
    }
    @PutMapping("{id}")
    public String update(@PathVariable int id, @RequestBody Person person){
        Person person1 = getById(id);
        if (person1 != null) {
            person1.setName(person.getName());
            person1.setSurname(person.getSurname());
            person1.setAge(person.getAge());
            return "Kullanıcı güncellendi: ID: " +id+", İsim: " +inMemoryList.get(id).getName()+", Soyisim: "+ inMemoryList.get(id).getSurname()+", Yaş: " +inMemoryList.get(id).getAge();
        }else {
            return "Kullanıcı bulunamadı";
        }

        /*if (inMemoryList.get(id) != null) {
            inMemoryList.get(id).setName(person.getName());
            inMemoryList.get(id).setSurname(person.getSurname());
            inMemoryList.get(id).setAge(person.getAge());
            return "Kullanıcı güncellendi: ID: " +id+", İsim: " +inMemoryList.get(id).getName()+", Soyisim: "+ inMemoryList.get(id).getSurname()+", Yaş: " +inMemoryList.get(id).getAge();
        }else {
            return "Kullanıcı bulunamadı";
        }*/

    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        inMemoryList.remove(id);
    }
}
