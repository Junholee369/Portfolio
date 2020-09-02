/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import java.util.List;

/**
 *
 * @author junho
 */
/**
 *
 * @author dsmelser
 */
public interface DvdLibraryDao {

    List<Dvd> getAll()
            throws DvdLibraryDaoException;

    Dvd getById(int id)
            throws DvdLibraryDaoException;
    
    void removeById(int id)
            throws DvdLibraryDaoException;

    
    Dvd addDvd(Dvd toAdd)
            throws DvdLibraryDaoException;

    
    void editDvd(int oldId, Dvd toEdit)
            throws DvdLibraryDaoException;
    

}
