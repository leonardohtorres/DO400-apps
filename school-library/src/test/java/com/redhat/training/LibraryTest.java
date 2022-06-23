package com.redhat.training;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.redhat.training.books.Book;
import com.redhat.training.books.BookNotAvailableException;
import com.redhat.training.inventory.InMemoryInventory;


public class LibraryTest {

    InMemoryInventory inventory;
    Library library;

    @BeforeEach
    public void setUp() {
        inventory = new InMemoryInventory();
        library = new Library(inventory);
    }

    // Add tests here...
    @Test
    public void checkingOutDecreasesNumberOfBookCopiesFromInventory()
        throws BookNotAvailableException {
            // Given
            inventory.add(new Book("book1"));
            inventory.add(new Book("book1"));
            
            // When
            library.checkOut("student1", "book1");
            library.checkOut("student2", "book1");

            final BookNotAvailableException exception = assertThrows(
                BookNotAvailableException.class,
                () -> {
                    library.checkOut("student3", "book1");
                }
            );
            
            assertTrue(exception.getMessage().matches("Book book1 is not available"));
            // Then
            //assertEquals(2, inventory.countCopies("book1"));
        }
}
