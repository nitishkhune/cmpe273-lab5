package edu.sjsu.cmpe.library.ui.resources;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.yammer.dropwizard.jersey.params.LongParam;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Book.Status;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;
import edu.sjsu.cmpe.library.ui.views.HomeView;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HomeResource {
    private final BookRepositoryInterface bookRepository;

    public HomeResource(BookRepositoryInterface bookRepository) {
	this.bookRepository = bookRepository;
    }

    @GET
    public HomeView getHome() {
	return new HomeView(bookRepository.getAllBooks());
    }
	@PUT
	@Path("v1/books/{isbn}")
	public void updateStatus(@PathParam("isbn") LongParam isbn , @QueryParam("status") Status status){
		
		Book book = bookRepository.getBookByISBN(isbn.get());
		book.setStatus(status);
		
		bookRepository.updateLibrary(book);		
	}
    
}