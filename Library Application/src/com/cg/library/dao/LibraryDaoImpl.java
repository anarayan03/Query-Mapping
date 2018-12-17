package com.cg.library.dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.QualifiedNameable;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cg.library.bean.LibraryBean;
import com.cg.library.exception.LibraryException;
import com.cg.library.util.DbConnection;

public class LibraryDaoImpl implements ILibraryDao {

	@Override
	public String addBook(LibraryBean libraryBean)
			throws LibraryException, SQLException, ClassNotFoundException, IOException {
		
		Connection connection = DbConnection.getConnection();
		Statement statement = connection.createStatement();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		int quantity;
		String bookId = null;
		int queryResult = 0;
		
		try
		{
			preparedStatement = connection.prepareStatement(QueryMapper.INSERT_QUERY);
			//preparedStatement = connection.prepareStatement("insert into Library values(bookId_sequence.nextval,?,?,?,?)");
			preparedStatement.setString(1, libraryBean.getBookName());
			preparedStatement.setString(2, libraryBean.getAuthorName());
			preparedStatement.setDouble(3, libraryBean.getPrice());
			preparedStatement.setLong(4, libraryBean.getQuantity());
			
			preparedStatement.executeUpdate();
			
			resultSet = statement.executeQuery(QueryMapper.EXECUTE_QUERY);
			//resultSet = statement.executeQuery("Select * from Library");
			
			while(resultSet.next())
			{
				bookId = resultSet.getString(1);
								
			}
			return bookId;
			
		}catch (Exception e)
		{
			System.out.println(e);
		}
		
		return null;
	}

	@Override
	public LibraryBean viewBook(String bookId)
			throws LibraryException, ClassNotFoundException, IOException, SQLException {
		
		Connection connection = DbConnection.getConnection();
		Statement statement = connection.createStatement();
		LibraryBean libraryBean = new LibraryBean();

		ResultSet resultSet = null;
		
		//resultSet = statement.executeQuery("select * from Library where book_id='"+bookId+"'");
		resultSet = statement.executeQuery(QueryMapper.EXECUTE_QUERY_WITH_CONDITION);
		
		try
		{
		while(resultSet.next())
		{
			libraryBean.setBookId(resultSet.getString(1));
			libraryBean.setBookName(resultSet.getString(2));
			libraryBean.setAuthorName(resultSet.getString(3));
			libraryBean.setPrice(resultSet.getDouble(4));
			libraryBean.setQuantity(resultSet.getLong(5));
			
		}
		}catch (Exception e)
		{
			System.out.println(e);
		}
		
		return libraryBean;
	}

	@Override
	public LibraryBean issueBook(String bookId)
			throws LibraryException, ClassNotFoundException, IOException, SQLException {
		
		
		PreparedStatement preparedStatement = null;
		preparedStatement.setString(1,bookId);
		Connection connection = DbConnection.getConnection();
		Statement statement = connection.createStatement();
		LibraryBean libraryBean = new LibraryBean();
		
		ResultSet resultSet = null;
		
		try
		{
			
			preparedStatement = connection.prepareStatement(QueryMapper.ISSUE_QUERY);
			/*preparedStatement = connection.prepareStatement(" update Library set quantity "
					+ "= (select quantity-1 from Library where book_id ='"+bookId+"')where book_id ='"+bookId+"' and quantity <> 0");*/
			
			preparedStatement.executeUpdate();
			
			resultSet = statement.executeQuery(QueryMapper.EXECUTE_QUERY_WITH_CONDITION);
			//resultSet = statement.executeQuery("select * from Library where book_id='"+bookId+"'");
			
		while(resultSet.next())
		{
			
			if(resultSet.getLong(5)<=0)
			{
				System.out.println("Book out of stock");
				return null;
			}
			else
			{
				libraryBean.setBookId(resultSet.getString(1));
				libraryBean.setBookName(resultSet.getString(2));
				libraryBean.setAuthorName(resultSet.getString(3));
				libraryBean.setPrice(resultSet.getDouble(4));
				libraryBean.setQuantity(resultSet.getLong(5));
			}
			
		}
		}catch (Exception e)
		{
			System.out.println(e);
		}
		
		return libraryBean;
	}

	@Override
	public LibraryBean acceptBook(String bookId) throws LibraryException, ClassNotFoundException, IOException, SQLException {
		
		PreparedStatement preparedStatement = null;
		Connection connection = DbConnection.getConnection();
		Statement statement = connection.createStatement();
		LibraryBean libraryBean = new LibraryBean();
		
		ResultSet resultSet = null;
		
		try
		{
			
			preparedStatement = connection.prepareStatement(QueryMapper.ACCEPT_QUERY);
			/*preparedStatement = connection.prepareStatement(" update Library set quantity "
					+ "= (select quantity+1 from Library where book_id ='"+bookId+"')where book_id ='"+bookId+"'");*/
			preparedStatement.executeUpdate();
			
			resultSet = statement.executeQuery(QueryMapper.EXECUTE_QUERY_WITH_CONDITION);
			//resultSet = statement.executeQuery("select * from Library where book_id='"+bookId+"'");
			
			
		while(resultSet.next())
		{
			libraryBean.setBookId(resultSet.getString(1));
			libraryBean.setBookName(resultSet.getString(2));
			libraryBean.setAuthorName(resultSet.getString(3));
			libraryBean.setPrice(resultSet.getDouble(4));
			libraryBean.setQuantity(resultSet.getLong(5));
			//System.out.println("Quantity is now"+resultSet.getLong(5));
			
		}
		}catch (Exception e)
		{
			System.out.println(e);
		}
		
		return libraryBean;
	}

	@Override
	public List<LibraryBean> retriveAll() throws LibraryException, ClassNotFoundException, SQLException, IOException {
		Connection con= DbConnection.getConnection();
		Statement statement=con.createStatement();
		
		List<LibraryBean> list=null;
		ResultSet resultSet=null;
		
		resultSet = statement.executeQuery(QueryMapper.EXECUTE_QUERY);
		//resultSet=statement.executeQuery("select * from library order by book_id");
		list=new ArrayList<>();
		while(resultSet.next())
		{
			LibraryBean libraryBean =new LibraryBean();
			libraryBean.setBookId(resultSet.getString(1));
			libraryBean.setBookName(resultSet.getString(2));
			libraryBean.setAuthorName(resultSet.getString(3));
			libraryBean.setPrice(resultSet.getDouble(4));
			libraryBean.setQuantity(resultSet.getLong(5));
			
			list.add(libraryBean);
			
		}
		return list;

	}

}
