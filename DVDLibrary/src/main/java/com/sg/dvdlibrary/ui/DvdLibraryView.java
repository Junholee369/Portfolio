/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.Dvd;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author junho
 */
public class DvdLibraryView {

    public DvdLibraryView(UserIO io) {
        this.io = io;
    }
    private UserIO io;
    
    public void displayAddDvdBanner() {
        io.print("=== Add DVD ===");
    }

    public void displayAddSuccessBanner() {
        io.readString(
                "Dvd successfully added.  Please hit enter to continue");
    }

    public void displayListAllBanner() {
        io.print("=== List All DVD ===");
    }

    public int getDvdIdChoice() {
        return io.readInt("Please enter the DVD Id.");
    }

    public void displayRemoveDvdBanner() {
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveDvdSuccessBanner() {
        io.readString("DVD successfully removed. Please hit enter to continue.");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!");
    } 
    
    
  
   
    public void displayDvd(Dvd dvd) {
        if (dvd != null) {
            io.print(dvd.getId() + "");
            io.print(dvd.getTitle());
            io.print(dvd.getReleaseDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + "");
            io.print(dvd.getMpaaRating());
            io.print(dvd.getDirectorName());
            io.print(dvd.getStudio());
            io.print(dvd.getUserRating());
            
        } else {
            io.print("No such student.");

        }
        io.readString("Please hit enter to continue.");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List Dvd Title");
        io.print("2. Add Dvd");
        io.print("3. Remove Dvd");
        io.print("4. View Dvd");
        io.print("5. Exit");

        return io.readInt("Please select from the above choices.", 1, 5);

    }

    public int printMenuSelection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Dvd getNewDvdInfo() {
        int dvdId = io.readInt("Please Enter Dvd Id ");
        String dvdTitle = io.readString("Please Enter Dvd Title");
        LocalDate releaseDate = io.readDate("Please Enter Release Date", 1800, 2050);
        String mpaaRating = io.readString("Please Enter MPAA Rating");
        String directorName = io.readString("Please Enter Director Name");
        String studioName = io.readString("Please Enter Studio Name");
        String userRating = io.readString("Please Enter User Rating");
        
        Dvd currentDvd = new Dvd();
        currentDvd.setId(dvdId);
        currentDvd.setTitle(dvdTitle);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setMpaaRating(mpaaRating);
        currentDvd.setDirectorName(directorName);
        currentDvd.setStudio(studioName);
        currentDvd.setUserRating(userRating);
        
        return currentDvd;
    }

    public void displayDvdList(List<Dvd> dvdList) {
        for (Dvd currentDvd : dvdList) {
            io.print(currentDvd.getId() + ": "
                    + currentDvd.getTitle() + " "
                    + currentDvd.getReleaseDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + " "
                    + currentDvd.getMpaaRating() + " "
                    + currentDvd.getDirectorName() + " "
                    + currentDvd.getUserRating());
        
        }
        io.readString("Please hit enter to continue.");
    }


}