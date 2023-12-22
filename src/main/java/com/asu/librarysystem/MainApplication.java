package com.asu.librarysystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


import java.util.ArrayList;

public class MainApplication extends Application {
    public static ArrayList<Book> books  = new ArrayList<Book>();

    public static Stage st;

    @Override
    public void start(Stage stage) throws IOException {


        /** Login
         

        
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

         */

//        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        Image icon =new Image("E:\\ibrahem\\oop\\project2\\library-system\\src\\main\\resources\\icon\\icons8-library-64.png");
//        stage.getIcons().add(icon);
//
//
//        stage.setScene(scene);
//        stage.show();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1280,720);
//        Image icon =new Image("");
//        stage.getIcons().add(icon);
        stage.setTitle("library");


        stage.setScene(scene);
        stage.setResizable(false);
        st = stage;
        stage.show();


//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main(String[] args) {
        /*Book book100 = new Book("Jane Eyre", "Charlotte Bronte", 1847, true, 8
                , 1, "Orphaned Jane is sent to work as a governess for brooding Mr. Rochester's daughter, Adele. Love begins to grow between Jane and her moody employer, but his mysterious first wife threatens to ruin their chance at happiness."
                , """", new Category[]{Category.HORROR, Category.DRAMA});*/
        //Book book100 = new Book("Harry Potter and the Prisoner of Azkaban","J. K. Rowling",2023,true,200,4,"A very interesting book", "src/main/resources/image/330px-BlackBeautyCoverFirstEd1877.jpeg", new Category[]{Category.HORROR});

//
//        Book book1 = new Book("Harry Potter and the Prisoner of Azkaban","J. K. Rowling",2023,true,200,4,"Harry Potter and the Prisoner of Azkaban is a fantasy novel written by British author J. K. Rowling and is the third in the Harry Potter series. The book follows Harry Potter, a young wizard, in his third year at Hogwarts School of Witchcraft and Wizardry. Along with friends Ronald Weasley and Hermione Granger, Harry investigates Sirius Black, an escaped prisoner from Azkaban, the wizard prison, believed to be one of Lord Voldemort's old allies.", "src/main/resources/image/Harry_Potter_and_the_Prisoner_of_Azkaban.jpg", new Category[]{Category.HORROR});
//        Book book2 = new Book("Harry Potter and the Chamber of Secrets","J. K. Rowling",2020,true,200,3,"Harry Potter and the Chamber of Secrets is a fantasy novel written by British author J. K. Rowling and the second novel in the Harry Potter series. The plot follows Harry's second year at Hogwarts School of Witchcraft and Wizardry, during which a series of messages on the walls of the school's corridors warn that the \"Chamber of Secrets\" has been opened and that the \"heir of Slytherin\" would kill all pupils who do not come from all-magical families. These threats are found after attacks that leave residents of the school petrified. Throughout the year, Harry and his friends Ron and Hermione investigate the attacks.", "src/main/resources/image/Harry_Potter_and_the_Chamber_of_Secrets.jpg", new Category[]{Category.ADVENTURE});
//        Book book3 = new Book("The Alchemist ","Paulo Coelho ",2021,true,200,2,"The Alchemist (Portuguese: O Alquimista) is a novel by Brazilian author Paulo Coelho which was first published in 1988. Originally written in Portuguese, it became a widely translated international bestseller.[1][2] The story follows the shepherd boy Santiago in his journey across northern Africa to the pyramids of Egypt after he dreams of finding a treasure there.", "src/main/resources/image/TheAlchemist.jpg", new Category[]{Category.ADVENTURE});
//        Book book4 = new Book("A Tale of Two Cities","Charles Dickens",2023,true,200,4,"A Tale of Two Cities is a historical novel published in 1859 by Charles Dickens, set in London and Paris before and during the French Revolution. The novel tells the story of the French Doctor Manette, his 18-year-long imprisonment in the Bastille in Paris, and his release to live in London with his daughter Lucie whom he had never met. The story is set against the conditions that led up to the French Revolution and the Reign of Terror.", "src/main/resources/image/Tales_serial.jpg", new Category[]{Category.ADVENTURE});
//        Book book5 = new Book("The Little Prince ","Antoine de Saint-ExupÃ©ry",2020,true,200,3,"The Little Prince (French: Le Petit Prince, pronounced [lə p(ə)ti pʁɛ̃s]) is a novella written and illustrated by French aristocrat, writer, and military pilot Antoine de Saint-Exupéry. It was first published in English and French in the United States by Reynal & Hitchcock in April 1943 and was published posthumously in France following liberation; Saint-Exupéry's works had been banned by the Vichy Regime. The story follows a young prince who visits various planets, including Earth, and addresses themes of loneliness, friendship, love, and loss. Despite its style as a children's book, The Little Prince makes observations about life, adults, and human nature.", "src/main/resources/image/Littleprince.jpeg", new Category[]{Category.ADVENTURE});
//        Book book6 = new Book("Harry Potter and the Philosopher's Stone","J. K. Rowling",2021,true,200,2,"Harry Potter and the Philosopher's Stone is a fantasy novel written by British author J. K. Rowling. The first novel in the Harry Potter series and Rowling's debut novel, it follows Harry Potter, a young wizard who discovers his magical heritage on his eleventh birthday, when he receives a letter of acceptance to Hogwarts School of Witchcraft and Wizardry. Harry makes close friends and a few enemies during his first year at the school and with the help of his friends, Ron Weasley and Hermione Granger, he faces an attempted comeback by the dark wizard Lord Voldemort, who killed Harry's parents, but failed to kill Harry when he was just 15 months old.", "src/main/resources/image/Harry_Potter_and_the_Philosopher's_Stone_Book_Cover.jpg", new Category[]{Category.ADVENTURE});
//        Book book7 = new Book("And Then There Were None","Agatha Christie",2023,true,200,4,"And Then There Were None is a mystery novel by the English writer Agatha Christie, who described it as the most difficult of her books to write.[2] It was first published in the United Kingdom by the Collins Crime Club on 6 November 1939, as Ten Little Niggers,[3] after an 1869 minstrel song that serves as a major plot element.[4][5] The US edition was released in January 1940 with the title And Then There Were None, taken from the last five words of the song.[6] Successive American reprints and adaptations use that title, though American Pocket Books paperbacks used the title Ten Little Indians between 1964 and 1986. UK editions continued to use the original title until 1985.[7]", "src/main/resources/image/And_Then_There_Were_None_First_Edition_Cover_1939.jpg", new Category[]{Category.ADVENTURE});
//        Book book8 = new Book("The Hobbit","J. R. R. Tolkien",2020,true,200,3,"The Hobbit, or There and Back Again is a children's fantasy novel by English author J. R. R. Tolkien. It was published in 1937 to wide critical acclaim, being nominated for the Carnegie Medal and awarded a prize from the New York Herald Tribune for best juvenile fiction. The book is recognized as a classic in children's literature and is one of the best-selling books of all time, with over 100 million copies sold.", "src/main/resources/image/TheHobbit_FirstEdition.jpg", new Category[]{Category.ADVENTURE, Category.HORROR});
//        Book book9 = new Book("She: A History of Adventure","H. Rider Haggard",2021,true,200,2,"She, subtitled A History of Adventure, is a novel by the English writer H. Rider Haggard, published in book form in 1887 following serialisation in The Graphic magazine between October 1886 and January 1887. She was extraordinarily popular upon its release and has never been out of print.", "src/main/resources/image/330px-SHE,_A_History_of_Adventure_(1st_Edition_Cover),_by_H._Rider_Haggard.jpg", new Category[]{Category.ADVENTURE});
//        Book book10 = new Book("The Da Vinci Code","Dan Brown",2021,true,200,2,"The Da Vinci Code is a 2003 mystery thriller novel by Dan Brown. It is Brown's second novel to include the character Robert Langdon: the first was his 2000 novel Angels & Demons. The Da Vinci Code follows symbologist Robert Langdon and cryptologist Sophie Neveu after a murder in the Louvre Museum in Paris causes them to become involved in a battle between the Priory of Sion and Opus Dei over the possibility of Jesus Christ and Mary Magdalene having had a child together.", "src/main/resources/image/DaVinciCode.jpg", new Category[]{Category.ADVENTURE});
//        Library.addBook(book1);
//        Library.addBook(book2);
//        Library. addBook(book3);
//        Library.addBook(book4);
//        Library.addBook(book5);
//        Library.addBook(book6);
//        Library.addBook(book7);
//        Library.addBook(book8);
//        Library.addBook(book9);
//        Library.addBook(book10);
//        Customer customer=new Customer("user1","user1","111");
//        Library.addCustomer(customer);
//        Borrower borrower = new Borrower("user2","user2","111");
//        Library.addBorrower(borrower);
//        //Library.logInByUserName("ib","123");
//
//        customer.addOrder(book1.getId(),2);
//        customer.addOrder(book2.getId(),2);
//        customer.addOrder(book3.getId(),1);
//        customer.addOrder(book5.getId(),2);
//        customer.addOrder(book7.getId(),2);
//
//        borrower.addTransaction(book3);
//        borrower.addTransaction(book5);
//        borrower.addTransaction(book9);

      // Library.writeLibrary();

        Library.readLibrary();
 
        /* Login
         Borrower B = new Borrower("Hossam", "123456", "123456");
        Book b = new Book("", "", 0, true, 1500, 4, "Any");
        Library.addBook(b);
        Book BB = new Book("", "", 0, true, 1500, 4, "Any");
        Library.addBook(BB);
        B.addTransaction(BB, 15, 20);
        B.addTransaction(b, 10, 15);
        Library.addBorrower(B);
        System.out.println(B.getNoOfBooks());
//       Library.logInByUserName("Ahmad" , "1234");


        launch();
         */

        //books.add(book100);
//        Library.addBorrower(new Borrower("arsany", "123", "01277535814"));
//        Library.logInByUserName("arsany", "123");


        launch();
        Library.writeLibrary();



    }
}
