package com.example.demo.demo1.controller.api;

import com.example.demo.demo1.dto.AddressDto;
import com.example.demo.demo1.entity.Address;
import com.example.demo.demo1.entity.Customer;
import com.example.demo.demo1.entity.Product;
import com.example.demo.demo1.mapper.AddressMapper;
import com.example.demo.demo1.mapper.CustomerMapper;
import com.example.demo.demo1.repository.AddressRepository;
import com.example.demo.demo1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api/addresses")
@CrossOrigin(origins = "*")

public class AddressRestController {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AddressMapper addressMapper;

    @GetMapping(value = {"/", ""})
    public ResponseEntity<List<Address>> showAddresses(){
        List<Address> addresses = (List<Address>) addressRepository.findAll();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }


    @PostMapping(value = "/get")
    public ResponseEntity<List<AddressDto>> getAddresses(@RequestBody Long customer_id){
        List<Address> addresses = addressRepository.findByCustomerrId(customer_id);

        List<AddressDto> addressesDto = new ArrayList<>();

        for(Address a : addresses){
            addressesDto.add(addressMapper.toDto(a));
        }

        return new ResponseEntity<>(addressesDto, HttpStatus.OK);
    }





   /* @GetMapping(value = {"/{id}"})
    public ResponseEntity<Address> show(@PathVariable("id") long id, Model model){
        Optional<Address> address = addressRepository.findById(id);
        if(address.isPresent()) {
            return new ResponseEntity<Address>(address.get(), HttpStatus.OK);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }




    @PostMapping(value = {"/", ""})
    public Address create(@RequestBody Address newAddress){
        return addressRepository.save(newAddress);
    }

    @PutMapping(value = {"/{id}"})
    public Address update(@PathVariable("id") long id, @RequestBody Address newAddress){

        return addressRepository.findById(id).map(
                address -> {
                    address.setCity(newAddress.getCity());
                    address.setStreetAddress(newAddress.getStreetAddress());
                    address.setCountry(newAddress.getCountry());
                    address.setZipcode(newAddress.getZipcode());
                    address.setCustomer(newAddress.getCustomer());
                    address.setAddressType(newAddress.getAddressType());
                    return addressRepository.save(address);
                }
        ).orElseGet(() -> {
            newAddress.setId(id);
            return addressRepository.save(newAddress);
        });
    }

    @DeleteMapping(value = {"/{id}"})
    public void delete(@PathVariable("id") long id) {
        addressRepository.deleteById(id);
    }*/

}
