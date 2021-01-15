# Library-Management
Repository created for assessment, used Java, Spring Boot, REST web services

# About The Project
Library managament system prvides abilty to user to get aviable books and borrow them, return them as per predefined criteria.

# Built With
- Java 1.8 
- Spring Boot 2
- Maven
- Eclipse IDE

# Testing approach
- Junits (TDD)
- Integration Test (TDD)
- PostMan Testing (Manual)

# Flow description : Story 1
     - Requirement
     User should be able to view available books in the library. If no book is avaible in library then empty list should be returned.
     
     - Technical flow.
     REST web sevice is exposed using Spring REST controller annotation driven @RestController. 
     Project is divided into three layer 
       a. Controller Layer
           1. BooksController.java 
       b. Service layer
           1. BookService.java (Interface)
           2. BookServiceImplementor.java (Service implementor)
       c. DAO layer (Data access). 
           1. BooksListDAO.java (Interface)
           2. BooksReadOnlyDAOImplementor.java (DAO implementor- for read only database operation)
       
       Depedencies are injected using annotation based constuctor parameter injection. 
       This provided loose coupling and each class was indepedently testable as dependancies can be externally injected. 
       
     - Development/Testing Stratergy 
     A.  1. com.hexad.library.managment.service.TestBookServiceImplementor.testGetAvailableBooks_NoBooksAvailableCondition() 
            was written and it was  failing as there was no application logic was exist.
            With flow implementation test case was passed for empty list.
         2. Then com.hexad.library.managment.service.TestBookServiceImplementor.testGetAvailableBooks_booksAvailableCondition()
	    was written and as it was  failing as we had only empty book list. It passsed over time when DataHelper class was 
	    implemeted to return dummy data.
	 3. com.hexad.library.managment.service.TestBookServiceImplementor.testGetAvailableBooks_booksAvailableCondition_ForZeroCopies()
            was written. Intially it failed as BookServiceImplementor.getLibraryData() method was not checking for number of copies available. 
	    Check was added in method and test case passed.
        
     B. Integration Test case.
        SpringBoot Test was written to call REST service and check if book list is written. 
        com.hexad.library.managment.webtest.HttpWebTest.restShouldReturnBooks()
        
        Sample code was refred from Spring documentation.
        https://spring.io/guides/gs/testing-web/.
        
     c. Postman Test
        URL: http://localhost:8080/library/getAvailableBooks
        Method: get
        Headers: No additional headers were provided. 
        Sample output: 
              {
                  "availableBooks": [
                 {
                    "bookId": 102,
                    "bookName": "Head First Java",
                    "bookAthour": "Brain",
                    "numberOfCopiesAvailable": 1
                },
                {
                    "bookId": 101,
                    "bookName": "Lets C",
                    "bookAthour": "Kanitkar",
                    "numberOfCopiesAvailable": 2
                }
            ]
           }
           
     - Exceptional Handling
      Very basic Exception handling was provided using com.hexad.library.managment.advice.GlobalExceptionHandler
      If any runtime error occurace the Status 500 with appropriate error message is returned in response. 
      
      - Database 
      From DAO layer we can use Hibernate/jpa to fetch data from database. But I have created DataHelper class which 
      returns static dummy data. 
	  
# Flow description : Story 2
    - Requirement
     User should be able to borrow books from library.  Each User has a borrowing limit of 2 books at any point of time. 
     the book will be added to users borrowed list and removed from available list. 

    - Assumption
     User has availabe book list from the REST I developed in Story 1. So frontend system will pass ids of user selected books along 
     with user id to borrower REST. 
      
    - Development/Testing Stratergy 
    A. 1. com.hexad.library.managment.service.TestUserBookBorrowServiceImplementor.testBorrowBook_BoookIssuccefullyBorrowed() tests 
          if book is succefully addded in borrowed list.
       2. com.hexad.library.managment.service.TestUserBookBorrowServiceImplementor.testBorrowBook_UserTriedBorrowingThirdBook() checks 
          for MaximumAllowedBooksExceededException when user tries to borrow third book. 
     
    B. Integration Test case.
       1. com.hexad.library.managment.webtest.HttpWebTest.testBorrowBook_BookisAddedInBorrowerList() checks 
          if book is added into borrwedBooks list
       2. com.hexad.library.managment.webtest.HttpWebTest.testBorrowBook_BookIsRemovedFromLibrary() checks 
          if book is removed from available book list
       3. com.hexad.library.managment.webtest.HttpWebTest.testBorrowBook_InValidUserIdProvided() checks 
          for 422 http response code is retuned when incorrect userId is given. 
	  UserNotFoundException is mapped to  422 Unprocessable Entity
       4. com.hexad.library.managment.webtest.HttpWebTest.testBorrowBook_InValidBookIdProvided() 
    
    C. Postamn test
       
       1. Success scenario: User is able to borrow book
          URL: http://localhost:8080/library/borrowBook/user/1/book/101
          Method: post
          Headers: No additional headers were provided. 
          Sample output: 
              {
   		 "userId": 1,
    		 "userName": "Martin",
    		 "borrowedBooks": [
       		 {
           	  "bookId": 101,
            	  "bookName": "Lets C",
           	  "bookAthour": "Kanitkar",
           	 "numberOfCopiesAvailable": 1
    		    }
   		]
	   }
   
 	 2. Invalid input : incorrect user id is provided
	    URL: http://localhost:8080/library/borrowBook/user/5/book/101
      	    Method: post
            Headers: No additional headers were provided.
	    Sample output:    
	    {
    		"timestamp": "2021-01-13T23:01:42.932+00:00",
   		 "status": 422,
    		"error": "Unprocessable Entity",
    		"message": "",
   		 "path": "/library/borrowBook/user/5/book/101"
	    }
	    
	    Improvement: it showed show meaning full message in case of 422 status
	    
    - Exceptional Handling
        Newly added cutomize exception
	1. BookNotFoundException
	2. UserNotFoundException
	2. MaximumAllowedBooksExceededException
	Error mapping has done for above exception in GlobalExceptionHandler.
	
     - Newly added validator service classes
	1. UserDataValidationService : Validates If valid userId provided and If user has already borrowed two books
	2. BookDataValidationServiceImplementor : Validates if valid bookId provided
	
	
# Flow description : Story 3
    - Requirement
     There are more than one copy of a book in the library, user should be able to borrow one copy of book.
     then one copy of the book is added to my borrowed list
     If there is only one copy of a book in the library, user should be able to borrow one copy of book
     then the book is removed from the library. 
     
     - Development/Testing Stratergy 
    A. com.hexad.library.managment.service.TestUserBookBorrowServiceImplementor.testBorrowBook_UserTriedBorrowAnotherCopyOfBook() checks for    		
      MaximumAllowedCopyOfBookExceededException when user try to borrow anther copy of book which he has already borrowed previously
      
    B. Integration Test case.
      com.hexad.library.managment.webtest.HttpWebTest.testBorrowBook_BuyOneCopyOfBook() covers following scenarios in given order
      1.  Initially two copies of Book "Lets C" bookId=101 & one copy of "Head First Java" bookId=102 are available
      2.  borrow "Lets C" bookId=101 book for user userId=1
      3.  available books still show "Lets C" bookId=101 as it still has one copy left and one copy of "Head First Java" bookId=102
      4.  again borrow "Lets C" bookId=101 for user userId=1 we should get exception
           MaximumAllowedCopyOfBookExceededException is mapped to HttpStatus.UNPROCESSABLE_ENTITY 422
      5.  try to borrow "Head First Java" bookId=102 user  userId=1
      6.  Now only "Lets C" bookId=101 should be there in available list and has one copy available
      7.  try to borrow "Lets C" bookId=101 for different user userId=2
      8.  Now no book should be there in available list
      
    - Exceptional Handling
         Newly added cutomize exception 
        1. MaximumAllowedCopyOfBookExceededException
	Error mapping has done for above exception in GlobalExceptionHandler.
	
    - Newly added validator service classes/Method
    com.hexad.library.managment.validation.user.UserDataValidationService.checkIfUserHasAlreadyBorrowedCopyOfBook(int, Book) : 
    Validates if user has already borrowed copy Of book
        
        
 # Flow description : Story 4
    -Requirement
    User should be able to return book one or two boks and accordingly stocks should be updated.
    
     - Development/Testing Stratergy 
     A. 1. .hexad.library.managment.service.TestUserBookReturnServiceImplementor.testBorrowBook_BoookReturnedSuccessful_NoBookInBorrowedList() 
           user returns one book and borrowed book list is empty.
	2. com.hexad.library.managment.service.TestUserBookReturnServiceImplementor.testBorrowBook_BoookReturnedSuccessful_OneBookInBorrowedList()
	   user returns one book and borrowed book list has one book. 
	   
	   
     B. Integration test cases 	
     
     com.hexad.library.managment.webtest.HttpWebTest.testBorrowBook_BookIsReturned() covers following scenarios in given order
    1. borrow book "Lets C" bookId=101
    2. borrow book "Head First Java" bookId=102
    3. get availalble books list
       only one copy of "Lets C" bookId=101 should be available and "Head First Java" bookId=102 is not available
    4. return "Lets C" bookId=101    
    5. get availalble books list
      two copies of book "Lets C" bookId=101 are available and "Head First Java" bookId=102 is not available
    6. return "Head First Java" bookId=101
       both books returned borrowed list should be empty
    7. get availalble books list.
       two copies of book "Lets C" bookId=101 are available and "Head First Java" bookId=102 is available
      
     
  
