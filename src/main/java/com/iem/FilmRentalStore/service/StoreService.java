package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.entity.Store;

import java.util.List;
import java.util.Map;

public interface StoreService {
    List<Store> getAllStores();
    List<Store> getStoresByFields(Map<String, String> searchParams);
    Store getStoreById(Integer id);
    Store createStore(Store store);
    Store updateStore(Integer id, Store storeDetails);
    Store patchStore(Integer id, Map<String, Object> updates);
    void deleteStore(Integer id);
}