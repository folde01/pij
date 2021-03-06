import org.junit.*;
import static org.junit.Assert.*;

public class LibraryTests {
  
  Book book;
  String myTitle1;
  String myAuthorName1;

  User u1; 
  User u2; 
  User u3; 
  User u4; 

  Users users;

  Library myLibrary;
  String myLibName;

  @Before
  public void buildUp() {

    u1 = new UserImpl("Jesse");
    u1.setID(123);
    u2 = new UserImpl("Sara");
    myTitle1 = "Huckleberry Finn";
    myAuthorName1 = "Mark Twain";
    book = new BookImpl(myTitle1, myAuthorName1);
    users = new UsersImpl();
    myLibName = "UCL Library";
    myLibrary = new MockLibrary(myLibName);
    
  }

  @Test
  public void testBookGetTitle() {
    System.out.println("\n\nTEST 1");
    assertEquals(book.getTitle(), myTitle1);
  }

  @Test
  public void testBookGetAuthorName() {
    System.out.println("\n\nTEST 2");
    assertEquals(book.getAuthorName(), myAuthorName1);
  }
  
  @Test
  public void testUsersUniqUserID() {
    System.out.println("\n\nTEST 3");

    assertEquals(true, users.add(u1));

    u2.setID(123);
    assertEquals(false, users.add(u2));

    u3 = new UserImpl("Craig");
    u3.setID(124);
    assertEquals(true, users.add(u3));

    u2 = new UserImpl("Sara");
    u2.setID(125);
    assertEquals(true, users.add(u2));

    /*
    u2.setID(124);
    assertEquals(124, u2.getID());
    assertEquals(true, users.add(u2));
    */
  } 

  @Test
  public void testUsersUniqUserName() {
    System.out.println("\n\nTEST 4");
    assertEquals(true, users.add(u1));
    u3 = new UserImpl("Jesse");
    u3.setID(126);
    assertEquals(false, users.add(u3));
    u4 = new UserImpl("Craig");
    assertEquals(true, users.add(u4));
  } 

  @Test 
  public void testLibraryGetID_NoUsers() {
    System.out.println("\n\nTEST 7");
    assertEquals(101, myLibrary.getID("Bobby")); 
  }
  @Test
  public void testLibraryGetID_OneUser_Found() {
    System.out.println("\n\nTEST 8");
    myLibrary.getID("Bobby"); 
    assertEquals(101, myLibrary.getID("Bobby")); 
  }
  @Test
  public void testLibraryGetID_OneUser_NotFound() {
    System.out.println("\n\nTEST 9");
    myLibrary.getID("Bobby"); 
    assertEquals(102, myLibrary.getID("Jane")); 
    assertEquals(101, myLibrary.getID("Bobby")); 
    assertEquals(102, myLibrary.getID("Jane")); 
  }
  @Test
  public void testLibraryGetID_TwoUsers_NotFound() {
    System.out.println("\n\nTEST 10");
    myLibrary.getID("Bobby"); 
    assertEquals(102, myLibrary.getID("Jane")); 
    assertEquals(101, myLibrary.getID("Bobby")); 
    assertEquals(102, myLibrary.getID("Jane")); 
    assertEquals(103, myLibrary.getID("Cooter")); 
    assertEquals(103, myLibrary.getID("Cooter")); 
  }
  @Test
  public void testLibraryFindBook_noBooks() {
    System.out.println("\n\nTEST 11");
    assertNull(myLibrary.findBook("Sabbath's Theater", "Philip Roth")); 
  }
  @Test
  public void testLibraryAddBook_toEmptyList() {
    System.out.println("\n\nTEST 12");
    myLibrary.addBook("Sabbath's Theater", "Philip Roth");
    Book myBook = myLibrary.findBook("Sabbath's Theater", "Philip Roth"); 
    assertEquals("Sabbath's Theater", myBook.getTitle());
  }
  @Test
  public void testLibraryAddBook_toListOfOne() {
    System.out.println("\n\nTEST 13");
    myLibrary.addBook("Title1", "Author1");
    myLibrary.addBook("Title2", "Author2");
    Book myBook = myLibrary.findBook("Title1", "Author1"); 
    assertEquals("Title1", myBook.getTitle());
    myBook = myLibrary.findBook("Title2", "Author2"); 
    assertEquals("Title2", myBook.getTitle());
  }
  @Test
  public void test_Library_Book_isTaken_false() {
    System.out.println("\n\nTEST 14");
    myLibrary.addBook("Title1", "Author1");
    Book myBook = myLibrary.findBook("Title1", "Author1"); 
    assertEquals(false, myBook.isTaken());
  }
  @Test
  public void test_Library_Book_isTaken_true() {
    System.out.println("\n\nTEST 15");
    myLibrary.addBook("Title1", "Author1");
    myLibrary.addBook("Title2", "Author2");
    myLibrary.takeBook("Title1");
    Book myBook = myLibrary.findBook("Title1", "Author1"); 
    Book myBook2 = myLibrary.findBook("Title1", "Author1"); 
    assertEquals(true, myBook.isTaken());
    assertEquals(true, myBook2.isTaken());
  }
  @Test
  public void test_Library_takeBook_alreadyTaken() {
    System.out.println("\n\nTEST 16");
    myLibrary.addBook("Title1", "Author1");
    Book myBook = myLibrary.takeBook("Title1");
    assertTrue(myBook.isTaken());
    assertNull(myLibrary.takeBook("Title1"));
  }
  @Test
  public void test_Library_returnBook() {
    System.out.println("\n\nTEST 17");
    myLibrary.addBook("Title1", "Author1");
    myLibrary.addBook("Title2", "Author2");
    myLibrary.addBook("Title3", "Author3");
    Book myBook = myLibrary.takeBook("Title2");
    assertTrue(myBook.isTaken());
    System.out.println("yoda");
    myLibrary.returnBook(myBook);
    assertFalse(myLibrary.findBook(myBook.getTitle()).isTaken());
  }
  @Test  
  public void test_Library_getReaderCount_OneUser() {
    System.out.println("\n\nTEST 18");
    myLibrary.getID("Jane"); 
    assertEquals(2, myLibrary.getReaderCount());
  }
  @Test  
  public void test_Library_getReaderCount_NoUsers() {
    System.out.println("\n\nTEST 18.1");
    assertEquals(1, myLibrary.getReaderCount());
  }
  @Test
  public void test_Library_getBookCount_noBooks() {
    System.out.println("\n\nTEST 19");
    assertEquals(0, myLibrary.getBookCount());
  }
  @Test
  public void test_Library_getBookCount_oneBook() {
    System.out.println("\n\nTEST 20");
    myLibrary.addBook("Title1", "Author1");
    assertEquals(1, myLibrary.getBookCount());
  }
  @Test
  public void test_Library_getBookCount_2Books() {
    System.out.println("\n\nTEST 21");
    myLibrary.addBook("Title1", "Author1");
    myLibrary.addBook("Title2", "Author2");
    assertEquals(2, myLibrary.getBookCount());
  }
  @Test
  public void test_Library_getBookBorrowedCount_noBooks() {
    System.out.println("\n\nTEST 22");
    assertEquals(0, myLibrary.getBookBorrowedCount());
  }
  @Test
  public void test_Library_getBookBorrowedCount_1Book() {
    System.out.println("\n\nTEST 23");
    myLibrary.addBook("Title1", "Author1");
    myLibrary.addBook("Title2", "Author2");
    myLibrary.takeBook("Title1");
    assertEquals(1, myLibrary.getBookBorrowedCount());
  }
  @Test
  public void test_Library_getBookBorrowedCount_2Books() {
    System.out.println("\n\nTEST 24");
    myLibrary.addBook("Title1", "Author1");
    myLibrary.addBook("Title2", "Author2");
    myLibrary.addBook("Title3", "Author3");
    myLibrary.addBook("Title4", "Author4");
    Book myBook = myLibrary.takeBook("Title1");
    myLibrary.takeBook("Title2");
    myLibrary.takeBook("Title3");
    myLibrary.returnBook(myBook);
    assertEquals(2, myLibrary.getBookBorrowedCount());
  }
  
  @Test
  public void test_Library_getUserByID_exists() {
    System.out.println("\n\nTEST 25.1");
    int janeID = myLibrary.getID("Jane"); 
    User jane = myLibrary.getUserByID(janeID);
    assertEquals(janeID,jane.getID());
  }

  @Test
  public void test_Book_getBorrower() {
    System.out.println("\n\nTEST 25.2");
    int janeID = myLibrary.getID("Jane"); 
    User jane = myLibrary.getUserByID(janeID);
    Book myBook = myLibrary.addBook("Title1", "Author1");
    myLibrary.lendBookToUser(myBook,jane);
    assertEquals(jane,myBook.getBorrower());
  }
    
  @Test
  public void test_User_getTitles_noTitles() {
    System.out.println("\n\nTEST 26");
    int janeID = myLibrary.getID("Jane"); 
    User jane = myLibrary.getUserByID(janeID);   
    assertNull(null, jane.getTitles()); 
  
  }

  @Test
  public void test_User_getTitles_1Title() {
    System.out.println("\n\nTEST 27");
    Book book1 = myLibrary.addBook("Title1", "Author1");
    int janeID = myLibrary.getID("Jane"); 
    User jane = myLibrary.getUserByID(janeID);   
    myLibrary.lendBookToUser(book1,jane);
    Title myTitleList = jane.getTitles();  // npe? 
    assertEquals(book1.getTitle(),myTitleList.getTitle());
  }

  @Test
  public void test_User_getTitles_2Titles() {
    System.out.println("\n\nTEST 28");
    int janeID = myLibrary.getID("Jane"); 
    User jane = myLibrary.getUserByID(janeID);   
    Book book1 = myLibrary.addBook("Title1", "Author1");
    Book book2 = myLibrary.addBook("Title2", "Author2");
    Book book3 = myLibrary.addBook("Title3", "Author3");
    myLibrary.lendBookToUser(book1,jane);
    myLibrary.lendBookToUser(book2,jane);
    Title myTitleList = jane.getTitles();  // npe? 
    assertEquals(book2.getTitle(),myTitleList.getTitle());
    assertEquals(book1.getTitle(),myTitleList.getNext().getTitle());
    assertNull(myTitleList.getNext().getNext());
  }

  @Test
  public void User_getTitles_AFTER_Library_returnBook() {
    System.out.println("\n\nTEST 29");
    int janeID = myLibrary.getID("Jane"); 
    User jane = myLibrary.getUserByID(janeID);   
    Book book1 = myLibrary.addBook("Title1", "Author1");
    myLibrary.lendBookToUser(book1,jane);
    myLibrary.returnBook(book1);
    Title myTitleList = jane.getTitles();  // npe? 
    assertNull(null, jane.getTitles()); 
  }

  // to do the above properly we need sorted lists

  @Test
  public void Library_getBorrowers_noBorrowers() {
    System.out.println("\n\nTEST 29.1");
    // to test this: getBorrowers returns list of Users borrowing. Need to compare this against all the users found to
    // actually be borrowing ie traverse the list of users and see who has a myTitleList which is not null. Or (if we're 
    // going to keep (eventually) all the borrow info in Book) traverse the Book list for all borrowers. (That debate is 
    // about where to keep borrowing info: in Book, in User, both, elsewhere, or all/some of the above?)
    assertEquals(null,myLibrary.getBorrowers());
  }

  @Test
  public void Library_getBorrowers_1Borrower() {
    System.out.println("\n\nTEST 30");
    int janeID = myLibrary.getID("Jane"); 
    User jane = myLibrary.getUserByID(janeID);   
    Book book1 = myLibrary.addBook("Title1", "Author1");
    myLibrary.lendBookToUser(book1,jane);
    assertEquals(jane.getName(),myLibrary.getBorrowers().getName());
  }

  @Test
  public void Library_getBorrowers_3Borrowers() {
    System.out.println("\n\nTEST 31");
    int janeID = myLibrary.getID("Jane"); 
    int jimID = myLibrary.getID("Jim"); 
    int fredID = myLibrary.getID("Fred"); 
    User jane = myLibrary.getUserByID(janeID);   
    User jim = myLibrary.getUserByID(jimID);   
    User fred = myLibrary.getUserByID(fredID);   
    Book book1 = myLibrary.addBook("Title1", "Author1");
    Book book2 = myLibrary.addBook("Title2", "Author2");
    Book book3 = myLibrary.addBook("Title3", "Author2");
    myLibrary.lendBookToUser(book1,jane);
    myLibrary.lendBookToUser(book2,jim);
    myLibrary.lendBookToUser(book3,fred);
    assertEquals(jane.getName(),myLibrary.getBorrowers().getName());
    assertEquals(jim.getName(),myLibrary.getBorrowers().getNext().getName());
    assertEquals(fred.getName(),myLibrary.getBorrowers().getNext().getNext().getName());
  }

  @Test
  public void Library_getUsers_2Users() {
    System.out.println("\n\nTEST 32");
    System.out.println("name of firstUser " + myLibrary.getUsers().getName());
    int janeID = myLibrary.getID("Jane"); 
    User jane = myLibrary.getUserByID(janeID);   
    int jimID = myLibrary.getID("Jim"); 
    User jim = myLibrary.getUserByID(jimID);   
    assertEquals(jim.getName(),myLibrary.getUsers().getName());
    assertEquals(jane.getName(),myLibrary.getUsers().getNext().getName());
  }

}
