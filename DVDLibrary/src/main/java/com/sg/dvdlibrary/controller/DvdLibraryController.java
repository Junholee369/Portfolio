/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryDaoException;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import java.util.List;

/**
 *
 * @author junho
 */
public class DvdLibraryController {

    DvdLibraryDao dao;
    DvdLibraryView view;

    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listDvd();
                        break;
                    case 2:
                        addDvd();
                        break;
                    case 3:
                        removeDvd();
                        break;
                    case 4:
                        viewDvd();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (DvdLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void listDvd() throws DvdLibraryDaoException {
        view.displayListAllBanner();
        List<Dvd> dvdList = dao.getAll();
        view.displayDvdList(dvdList);
    }

    private void addDvd() throws DvdLibraryDaoException {
        view.displayAddDvdBanner();
        Dvd dvdId = view.getNewDvdInfo();
        dao.addDvd(dvdId);
        view.displayAddDvdBanner();
    }

    private void removeDvd() throws DvdLibraryDaoException {
        view.displayRemoveDvdBanner();
        int dvdId = view.getDvdIdChoice();
        dao.removeById(dvdId);
        view.displayRemoveDvdSuccessBanner();

    }

    private void viewDvd() throws DvdLibraryDaoException {
      
        int dvdId = view.getDvdIdChoice();
        Dvd dvd = dao.getById(dvdId);
        view.displayDvd(dvd);
    }

    private void unknownCommand() throws DvdLibraryDaoException {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() throws DvdLibraryDaoException {
        view.displayExitBanner();
    }

}
