/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibrarymvc.dao;

import com.swcguild.dvdlibrarymvc.model.Dvd;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class DvdLibraryDAOInMemImpl implements DvdLibraryDAO {

    private ArrayList<Dvd> dvds = new ArrayList<>();
    private static int dvdIdCounter = 0;

    @Override
    public Dvd addDvd(Dvd dvd) {

        // Make sure that new dvds have an id
        dvd.setDvdId(dvdIdCounter);
        dvdIdCounter++; // Then make sure that you increment
        // So that the NEXT dvd you add will have a new number
        // Add the dvd to the list
        dvds.add(dvd);
        return dvd;
    }

    @Override
    public Dvd getDvdById(int dvdId) {

        Optional<Dvd> dvd = dvds.stream()
                .filter(bob -> bob.getDvdId() == dvdId)
                .findFirst();

        if (dvd.isPresent()) {
            return dvd.get();
        }

        return null;
    }

    @Override
    public List<Dvd> getAllDvds() {
        return dvds;
    }

    @Override
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Dvd> searchDvds(Predicate<Dvd> filter) {

        return dvds.stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    @Override
    public void updateDvd(Dvd dvd) {
        Dvd x = this.getDvdById(dvd.getDvdId());
        if (x != null) {
            dvds.remove(x);
            dvds.add(dvd);
        }
    }

    @Override
    public Dvd removeDvd(int dvdId) {
        Dvd x = this.getDvdById(dvdId);
        if (x != null) {
            dvds.remove(x);
        }
        return x;
    }
}
