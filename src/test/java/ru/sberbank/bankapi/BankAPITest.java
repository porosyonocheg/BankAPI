package ru.sberbank.bankapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import ru.sberbank.bankapi.controllers.CardController;
import ru.sberbank.bankapi.repository.AccountRepository;
import ru.sberbank.bankapi.services.CardServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith({TestExtension.class})
public class BankAPITest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private CardController cardController;

    @Autowired
    private CardServiceImpl cardService;

    @Test
    public void controllerTest() {
        assertThat(cardController).isNotNull();
    }

    @Test
    public void testGetCardsGoodOneCard() throws Exception {
        mockMvc.perform(get("/api/cards/list?accountNumber=98765.432.1.0123.4567890"))
                .andExpect(status().isOk())
                .andExpect(content().json(getExpectedJson("getCard.json")))
                .andDo(print());
    }

    @Test
    public void testGetCardsGoodSeveralCards() throws Exception {
        mockMvc.perform(get("/api/cards/list?accountNumber=11111.222.3.4444.5555555"))
                .andExpect(status().isOk())
                .andExpect(content().json(getExpectedJson("getCards.json")))
                .andDo(print());
    }

    @Test
    public void testGetCardsNoCard() throws Exception {
        mockMvc.perform(get("/api/cards/list?accountNumber=61234.432.1.2230.4567210"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void testAddCard() {

    }

    @Test
    public void testGetBalanceGood() throws Exception {
        mockMvc.perform(get("/api/cards/balance?cardNumber=4256123456780123"))
                .andExpect(status().isOk())
                .andExpect(content().json(getExpectedJson("balance.json")))
                .andDo(print());
    }

    @Test
    public void testGetBalanceNoCard() throws Exception {
        mockMvc.perform(get("/api/cards/balance?cardNumber=123456"))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andDo(print());
    }

    private String getExpectedJson(String fileName) throws IOException {
        return String.join("", Files.readAllLines(
                Paths.get(
                        this.getClass().getClassLoader()
                                .getResource(fileName).getPath())));
    }


}
