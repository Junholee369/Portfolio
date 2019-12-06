/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author junho
 */
public class DvdLibraryDaoFileImpl implements DvdLibraryDao {

    String path;

    public DvdLibraryDaoFileImpl(String path) {

        this.path = path;
    }

    @Override
    public List<Dvd> getAll() throws DvdLibraryDaoException {

        List<Dvd> toReturn = new ArrayList<Dvd>();

        FileReader reader = null;
        try {
            reader = new FileReader(path);
            Scanner scn = new Scanner(reader);

            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                if (line.length() > 0) {
                    String[] cells = line.split("::");

                    Dvd toAdd = new Dvd();
                    toAdd.setId(Integer.parseInt(cells[0]));
                    toAdd.setTitle(cells[1]);
                    toAdd.setReleaseDate(LocalDate.parse(cells[2]));
                    toAdd.setMpaaRating(cells[3]);
                    toAdd.setDirectorName(cells[4]);
                    toAdd.setStudio(cells[5]);
                    toAdd.setUserRating(cells[6]);
                    
                    toReturn.add(toAdd);
                }
            }

        } catch (FileNotFoundException ex) {

        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                throw new DvdLibraryDaoException("Could not close reader for " + path, ex);
            }
        }

        return toReturn;
    }

    
     @Override
    public Dvd getById(int id) throws DvdLibraryDaoException {
        Dvd toReturn = null;

        List<Dvd> allDvd = getAll();

        for (Dvd toCheck : allDvd) {
            if (toCheck.getId() == id) {
                toReturn = toCheck;
                break;
            }
        }

        return toReturn;
    }

 
    @Override
    public void removeById(int id) throws DvdLibraryDaoException {

        List<Dvd> allDvd = getAll();

        int matchIndex = -1;

        for (int i = 0; i < allDvd.size(); i++) {
            Dvd toCheck = allDvd.get(i);

            if (toCheck.getId() == id) {
                matchIndex = i;
                break;
            }
        }

        if (matchIndex == -1) { //didn't find a match
            throw new DvdLibraryDaoException("ERROR: could not remove Dvd Title " + id);
        }

        allDvd.remove(matchIndex);

        writeFile(allDvd);
    }

    
    @Override
    public Dvd addDvd(Dvd toAdd) throws DvdLibraryDaoException {
        List<Dvd> allDvd = getAll();

        int newId = 0;

        for (Dvd toCheck : allDvd) {
            if (toCheck.getId() > newId) {
                newId = toCheck.getId();
            }
        }

        newId++;
        toAdd.setId(newId);

        allDvd.add(toAdd);

        writeFile(allDvd);

        return toAdd;
    }

    @Override
    public void editDvd(int oldId, Dvd edited) throws DvdLibraryDaoException {

        List<Dvd> allDvd = getAll();

        int matchIndex = -1;

        for (int i = 0; i < allDvd.size(); i++) {
            Dvd toCheck = allDvd.get(i);

            if (toCheck.getId() == oldId) {
                matchIndex = i;
                break;
            }
        }

        if (matchIndex == -1) { //we didn't find a match

            throw new DvdLibraryDaoException("ERROR: could not edit Dvd with title " + oldId);
        }

        allDvd.remove(matchIndex);
        allDvd.add(edited);

        writeFile(allDvd);
    }

    private void writeFile(List<Dvd> allDvd) throws DvdLibraryDaoException {

        FileWriter writer = null;
        try {
            writer = new FileWriter(path);
            PrintWriter pw = new PrintWriter(writer);

            for (Dvd toWrite : allDvd) {
                String line = convertToLine(toWrite);
                pw.println(line);
            }
        } catch (IOException ex) {
            throw new DvdLibraryDaoException("ERROR: could not write to " + path, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                throw new DvdLibraryDaoException("ERROR: could not close writer for " + path, ex);
            }
        }
    }

    private String convertToLine(Dvd toWrite) {

        String line
                = toWrite.getId() + "::"
                + toWrite.getTitle() + "::"
                + toWrite.getReleaseDate() + "::"
                + toWrite.getMpaaRating() + "::"
                + toWrite.getDirectorName() + "::"
                + toWrite.getStudio() + "::"
                + toWrite.getUserRating();

        return line;
    }

  
}
