package LibraryProject.librarydemo.Controller;
import LibraryProject.librarydemo.model.Book;
import LibraryProject.librarydemo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.UUID;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;


// generate object
    Book setBook(Book book, int number) {
        book.setId(number);
        book.setName("name" + number);
        book.setAuthor("author" + number);
        book.setCategory("category" + number);
        book.setLanguage("language" + number);
        book.setPublicationDate(1970 + number);
        book.setIsbn("isbn" + number);
        return book;

    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(1)
    void canSaveBook () throws Exception {
            Book book1 = new Book();
            setBook(book1, 1);
            mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .content(asJsonString(book1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
        }

    @Test
    @Order(2)
    void canGetAllBooks () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books")
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.[0].author").value("author1"));

    }
    @Test
    @Order(3)
    void canGetBookById () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/1")
                        .accept(MediaType.ALL_VALUE))
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andExpect(jsonPath("$.author").value("author1"));
    }

    @Test
    @Order(4)
    void canSaveUser () throws Exception {
        User user1 = new User();
        user1.setUserName("Dwight");
        user1.setId(1);
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(asJsonString(user1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @Order(6)
    void canSearchByAuthor () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/search/author?author=author1")
                        .accept(MediaType.ALL_VALUE))
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andExpect(jsonPath("$.[0].author").value("author1"));

    }
    @Test
    @Order(7)
    void canSearchByName () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/search/name?name=name1")
                        .accept(MediaType.ALL_VALUE))
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andExpect(jsonPath("$.[0].name").value("name1"));

    }
    @Test
    @Order(8)
    void canSearchByCategory () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/search/category?category=category1")
                        .accept(MediaType.ALL_VALUE))
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andExpect(jsonPath("$.[0].category").value("category1"));

    }

    @Test
    @Order(9)
    void canSearchByLanguage () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/search/language?language=language1")
                        .accept(MediaType.ALL_VALUE))
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andExpect(jsonPath("$.[0].language").value("language1"));

    }

    @Test
    @Order(10)
    void canSearchByIsbn () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/search/isbn?isbn=isbn1")
                        .accept(MediaType.ALL_VALUE))
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andExpect(jsonPath("$.[0].isbn").value("isbn1"));

    }

    @Test
    @Order(12)
    void canSaveSecondBook () throws Exception {
        Book book2 = new Book();
        setBook(book2, 2);
        book2.setGuid(UUID.fromString("89a527f3-8812-4b90-8687-7a0f3793dd86"));
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .content(asJsonString(book2))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isCreated());
    }

    @Test
    @Order(13)
    void canSearchByAvailable () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/search/isavailable?isavailable=true")
                        .accept(MediaType.ALL_VALUE))
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andExpect(jsonPath("$.[0].available").value(true));

    }


//    @Test
//    @Order(14)
//    void canAssignBookToUserById () throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.put("/books/1/user/1")
//                        .accept(MediaType.ALL_VALUE))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(content().string(org.hamcrest.Matchers.containsString("book taken by ")));
//
//    }
//
//
//    @Test
//    @Order(15)
//    void canAssignBookToUserByGuid () throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.put("/books/assignbyguid/89a527f3-8812-4b90-8687-7a0f3793dd86/1")
//                        .accept(MediaType.ALL_VALUE))
//                        .andExpect(status().isOk())
//                        .andDo(print())
//                        .andExpect(content().string(org.hamcrest.Matchers.containsString("book taken by ")));
//
//    }
//

    @Test
    @Order(16)
    void canSearchBookByGuid () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/findbyguid?guid=89a527f3-8812-4b90-8687-7a0f3793dd86")
                        .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.author").value("author2"));
    }

    @Test
    @Order(17)
    void canDeleteBookById () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/1")
                        .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(org.hamcrest.Matchers.equalTo("Book deleted")));

    }

//    @Test
//    @Order(18)
//    void canExtendBook () throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.put("/books/2/extendbook")
//                        .accept(MediaType.ALL_VALUE))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(content().string(org.hamcrest.Matchers.equalTo("book extended")));
//
//    }

    @Test
    @Order(19)
    @Disabled("needs more work done")
    void canSearchByUnavailable () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/search/unavailable?unavailable=true")
                        .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.[0].available").value(false));

 }



}