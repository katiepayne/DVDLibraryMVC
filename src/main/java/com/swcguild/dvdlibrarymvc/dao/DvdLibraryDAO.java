/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibrarymvc.dao;

import com.swcguild.dvdlibrarymvc.model.Dvd;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 *
 * @author apprentice
 */
public interface DvdLibraryDAO {
    
    // CREATE
    public Dvd addDvd(Dvd dvd);
    
    // READ
    public Dvd getDvdById(int dvdId);
    public List<Dvd> getAllDvds();
    
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria);
    public List<Dvd> searchDvds(Predicate<Dvd> filter);
            
    
    // UPDATE
    public void updateDvd(Dvd dvd);
    
    // DELETE
    public Dvd removeDvd(int dvdId);
    
}
