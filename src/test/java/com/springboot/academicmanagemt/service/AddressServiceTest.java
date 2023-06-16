package com.springboot.academicmanagemt.service;

import com.springboot.academicmanagemt.entity.Address;
import com.springboot.academicmanagemt.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAddress() {
        Address address = new Address(1L, "City", "Country");
        when(addressRepository.save(address)).thenReturn(address);

        Address result = addressService.createAddress(address);

        assertEquals(1L, result.getId());
        assertEquals("City", result.getCity());
        assertEquals("Country", result.getCountry());
        verify(addressRepository, times(1)).save(address);
    }

    @Test
    void testGetAllAddresses() {
        List<Address> addresses = Arrays.asList(
                new Address(1L, "City1", "Country1"),
                new Address(2L, "City2", "Country2")
        );
        when(addressRepository.findAll()).thenReturn(addresses);

        List<Address> result = addressService.getAllAddresses();

        assertEquals(2, result.size());
        assertEquals("City1", result.get(0).getCity());
        assertEquals("City2", result.get(1).getCity());
        verify(addressRepository, times(1)).findAll();
    }

    @Test
    void testGetAddressById() {
        Long addressId = 1L;
        Address address = new Address(1L, "City", "Country");
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));

        Address result = addressService.getAddressById(addressId);

        assertEquals(1L, result.getId());
        assertEquals("City", result.getCity());
        assertEquals("Country", result.getCountry());
        verify(addressRepository, times(1)).findById(addressId);
    }

    @Test
    void testGetAddressById_AddressNotFound() {
        Long addressId = 1L;
        when(addressRepository.findById(addressId)).thenReturn(Optional.empty());

        Address result = addressService.getAddressById(addressId);

        assertEquals(null, result);
        verify(addressRepository, times(1)).findById(addressId);
    }
}
