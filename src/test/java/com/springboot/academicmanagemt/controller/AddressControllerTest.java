package com.springboot.academicmanagemt.controller;

import com.springboot.academicmanagemt.entity.Address;
import com.springboot.academicmanagemt.rest.AddressController;
import com.springboot.academicmanagemt.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AddressControllerTest {

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAddress() {
        Address address = new Address(1L, "City", "Country");
        when(addressService.createAddress(address)).thenReturn(address);

        ResponseEntity<Address> result = addressController.createAddress(address);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("City", result.getBody().getCity());
        assertEquals("Country", result.getBody().getCountry());
        verify(addressService, times(1)).createAddress(address);
    }

    @Test
    void testGetAllAddresses() {
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address(1L, "City1", "Country1"));
        addresses.add(new Address(2L, "City2", "Country2"));
        when(addressService.getAllAddresses()).thenReturn(addresses);

        List<Address> result = addressController.getAllAddresses();

        assertEquals(2, result.size());
        assertEquals("City1", result.get(0).getCity());
        assertEquals("Country2", result.get(1).getCountry());
        verify(addressService, times(1)).getAllAddresses();
    }

    @Test
    void testGetAddressById() {
        Long addressId = 1L;
        Address address = new Address(1L, "City", "Country");
        when(addressService.getAddressById(addressId)).thenReturn(address);

        ResponseEntity<Address> result = addressController.getAddressById(addressId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("City", result.getBody().getCity());
        assertEquals("Country", result.getBody().getCountry());
        verify(addressService, times(1)).getAddressById(addressId);
    }

    @Test
    void testGetAddressById_NotFound() {
        Long addressId = 1L;
        when(addressService.getAddressById(addressId)).thenReturn(null);

        ResponseEntity<Address> result = addressController.getAddressById(addressId);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(addressService, times(1)).getAddressById(addressId);
    }
}
