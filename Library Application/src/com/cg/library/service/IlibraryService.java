package com.cg.library.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.cg.library.bean.LibraryBean;
import com.cg.library.exception.LibraryException;

public interface IlibraryService 
{
	public String addBook(LibraryBean libraryBean) throws LibraryException, SQLException, ClassNotFoundException, IOException;
	public LibraryBean viewBook(String bookId) throws LibraryException, ClassNotFoundException, IOException, SQLException;
	public LibraryBean issueBook(String bookId) throws LibraryException, ClassNotFoundException, IOException, SQLException;
	public LibraryBean acceptBook(String bookId) throws LibraryException, ClassNotFoundException, IOException, SQLException;
	public List<LibraryBean> retriveAll()throws LibraryException, ClassNotFoundException, SQLException, IOException;
}
