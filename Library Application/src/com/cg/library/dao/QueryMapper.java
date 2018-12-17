package com.cg.library.dao;


public interface QueryMapper
{
	//String bookId = null;
	public static final String INSERT_QUERY = "insert into Library values(bookId_sequence.nextval,?,?,?,?)";
	public static final String EXECUTE_QUERY = "Select * from Library order by book_id";
	public static final String EXECUTE_QUERY_WITH_CONDITION = "select * from Library where book_id = bookId";
	public static final String ISSUE_QUERY = " update Library set quantity "
					+ "= (select quantity-1 from Library where book_id = bookId)where book_id = bookId and quantity <> 0";
	public static final String ACCEPT_QUERY = " update Library set quantity "
					+ "= (select quantity+1 from Library where book_id = bookId)where book_id = bookId";
}
