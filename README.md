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
     A.  1. com.hexad.library.managment.service.TestBookServiceImplementor.testGetAvailableBooks_NoBooksAvailableCondition() was written and it was  failing 
            as there was no application logic was exist.
            With flow implementation test case was passed for empty list.
         2. Then com.hexad.library.managment.service.TestBookServiceImplementor.testGetAvailableBooks_booksAvailableCondition() was written and as it was  failing 
            as we had only empty book list. It passsed over time when DataHelper class was implemeted to return dummy data.
		 3. com.hexad.library.managment.service.TestBookServiceImplementor.testGetAvailableBooks_booksAvailableCondition_ForZeroCopies() was written. Intially it failed
		    as BookServiceImplementor.getLibraryData() method was not checking for number of ccpies available. Check was added in method and test case passed.
        
     B. Integration Test case.
        SpringBoot Test was written to call REST service and check if book list is written. 
        com.hexad.library.managment.webtest.HttpWebTest.restShouldReturnBooks()
        
        Sample code was refred from Spring documentation.
        https://spring.io/guides/gs/testing-web/.
        
     c. Postman Test
        URL: http://localhost:8080/getAvailableBooks
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
      From DAO layer we can use Hibernate/jpa to fetch data from database. But I have created DataHelper class which returns static dummy data. 
	  
# Flow description : Story 2
    - Requirement
     User should be able to borrow books from library.  Each User has a borrowing limit of 2 books at any point of time. 
     the book will be added to users borrowed list and removed from available list. 

    - Assumption
     User has availabe book list from the REST I developed in Story 1. So frontend system will pass ids of user selected books along with user id to borrower REST. 
      
      
   
 

             
        
        
        
        
        
        
        
     
     
     
  
