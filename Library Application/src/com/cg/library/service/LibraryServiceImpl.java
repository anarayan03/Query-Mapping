package com.cg.library.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.library.bean.LibraryBean;
import com.cg.library.dao.ILibraryDao;
import com.cg.library.dao.LibraryDaoImpl;
import com.cg.library.exception.LibraryException;

public class LibraryServiceImpl implements IlibraryService {

	ILibraryDao ilibrarydao  = new LibraryDaoImpl();
	@Override
	public String addBook(LibraryBean libraryBean)
			throws LibraryException, SQLException, ClassNotFoundException, IOException {
		String bookseq;
		bookseq = ilibrarydao.addBook(libraryBean);
		return bookseq;
	}

	@Override
	public LibraryBean viewBook(String bookId)
			throws LibraryException, ClassNotFoundException, IOException, SQLException {
		LibraryBean libraryBean = ilibrarydao.viewBook(bookId);
		return libraryBean;
	}

	@Override
	public LibraryBean issueBook(String bookId)
			throws LibraryException, ClassNotFoundException, IOException, SQLException {
		LibraryBean libraryBean = ilibrarydao.issueBook(bookId);
		return libraryBean;
	}

	@Override
	public LibraryBean acceptBook(String bookId) throws LibraryException, ClassNotFoundException, IOException, SQLException {
		LibraryBean libraryBean = ilibrarydao.acceptBook(bookId);
		return libraryBean;
	}

	public void validateLibraryAttribute(LibraryBean libraryBean) throws LibraryException
	{
		
		List <String> validationError  = new ArrayList<String>();
		
		if(!(isValidName(libraryBean.getBookName())))
		{
			
			validationError.add("\n Book name should be in alphabet and minimum 1 character. ");
		}
		
		if(!(isValidAuthorName(libraryBean.getAuthorName())))
		{
			
			validationError.add("\n Author name should be in alphabet and minimum 5 characters. ");
		}
		
		if(!(isValidPrice(libraryBean.getPrice())))
		{
			validationError.add("\n Price should be positive. ");
		}
		
		if(!(isValidQuantity(libraryBean.getQuantity())))
		{
			validationError.add("\n Quantity should be positive. ");
		}
		
		if(!(validationError.isEmpty()))
		{
			throw new LibraryException(validationError+" ");
		}
		
	}

	private boolean isValidQuantity(long quantity) {

		return quantity>0;
	}

	private boolean isValidPrice(double price) {
		
		return price > 0;
	}

	private boolean isValidAuthorName(String authorName) {
		
		Pattern apattern = Pattern.compile("^[A-Za-z]{2,}$");
		Matcher amatcher = apattern.matcher(authorName);
		return amatcher.matches();
	}

	private boolean isValidName(String bookName) {
		Pattern bpattern = Pattern.compile("^[A-Za-z]{1,}$");
		Matcher bmatcher = bpattern.matcher(bookName);
		return bmatcher.matches();
	}
	
	
	public boolean isValidBookId(String bookId)
	{

		Pattern pattern = Pattern.compile("[0-9]{3}");
		Matcher matcher = pattern.matcher(bookId);
		if(matcher.matches())
		{
			return true;
		}
		else
			return false;
	}

	@Override
	public List<LibraryBean> retriveAll() throws LibraryException, ClassNotFoundException, SQLException, IOException {
		
		List<LibraryBean> list = new ArrayList<>();
		list=ilibrarydao.retriveAll();
		return list;
		
	}

	
}
