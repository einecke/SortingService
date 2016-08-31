package sortingService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;
import junit.framework.Assert;
import objects.Book;
import utils.OrderBooks;

@SuppressWarnings("deprecation")
public class SortingServiceTest {
	
	List<Book> books = new ArrayList<Book>();
	
	private void prepareTest(){
		books.add(new Book("Java How To Program", "Deitel & Deitel", 2007));
		books.add(new Book("Patterns Of Enterprise Application Architecture", "Martin Fowler", 2002));
		books.add(new Book("Head First Design Patterns", "Elisabeth Freeman", 2004));
		books.add(new Book("Internet & World Wide Web How To Program", "Deitel & Deitel", 2007));
	}
	
	private String expected(String path){		
		try {
			return Files.toString(new File(path), Charsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	@Test
	public void test01_titleAsc() {
		prepareTest();
		Gson gson = new Gson(); 
		OrderBooks comparator = new OrderBooks("tests/configs/test01.xml");
        Collections.sort(books,comparator);
        Assert.assertEquals(expected("tests/configs/test01_out.txt"), gson.toJson(books));
	}
	
	@Test
	public void test02_authorAsc_titleDesc(){
		prepareTest();
		Gson gson = new Gson(); 
		OrderBooks comparator = new OrderBooks("tests/configs/test02.xml");
        Collections.sort(books,comparator);
        Assert.assertEquals(expected("tests/configs/test02_out.txt"), gson.toJson(books));
	}
	
	@Test
	public void test03_editionAsc_authorDesc_titleAsc(){
		prepareTest();
		Gson gson = new Gson(); 
		OrderBooks comparator = new OrderBooks("tests/configs/test03.xml");
        Collections.sort(books,comparator);
        Assert.assertEquals(expected("tests/configs/test03_out.txt"), gson.toJson(books));
	}
	
	@Test
	public void test04_void(){
		Gson gson = new Gson(); 
		OrderBooks comparator = new OrderBooks("tests/configs/test03.xml");
        Collections.sort(books,comparator);
        Assert.assertEquals("[]", gson.toJson(books));
	}

}
