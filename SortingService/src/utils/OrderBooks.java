package utils;

import java.util.Comparator;
import com.google.common.collect.ComparisonChain;

import objects.Book;
import objects.Config;

public class OrderBooks implements Comparator<Book> {
	
	String configPath;

	public OrderBooks(String configPath) {
		this.configPath = configPath;
	}
	
	@Override
	public int compare(Book book, Book anotherBook) {
		ComparisonChain cc = null;
		ConfigReader configReader = new ConfigReader();
		configReader.read(configPath);

		for (Config config : configReader.getConfigs()) {
			if (config.getActive()) {
				if ("author".equals(config.getName())) {
					cc = config.getDescending() ? author(cc, anotherBook, book) : author(cc, book, anotherBook);
				}
				if ("title".equals(config.getName())) {
					cc = config.getDescending() ? title(cc, anotherBook, book) : title(cc, book, anotherBook);
				}
				if ("year".equals(config.getName())) {
					cc = config.getDescending() ? year(cc, anotherBook, book) : year(cc, book, anotherBook);
				}
			}
		}
		return cc.result();
	
	}
	
	
	protected ComparisonChain author(ComparisonChain cc, Book book, Book anotherBook) {
		if(cc != null){
			return cc.compare(book.getAuthor(), anotherBook.getAuthor());
		} else {
			return ComparisonChain.start().compare(book.getAuthor(), anotherBook.getAuthor());
		}
	}

	protected ComparisonChain title(ComparisonChain cc, Book book, Book anotherBook) {
		if(cc != null){
			return cc.compare(book.getTitle(), anotherBook.getTitle());
		} else {
			return ComparisonChain.start().compare(book.getTitle(), anotherBook.getTitle());
		}
	}

	protected ComparisonChain year(ComparisonChain cc, Book book, Book anotherBook) {
		if(cc != null) {
			return cc.compare(book.getYear(), anotherBook.getYear());
		} else {
			return ComparisonChain.start().compare(book.getYear(), anotherBook.getYear());
		}
	}
}