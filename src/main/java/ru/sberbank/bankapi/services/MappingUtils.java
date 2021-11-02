package ru.sberbank.bankapi.services;

import org.springframework.stereotype.Service;
import ru.sberbank.bankapi.model.Card;
import ru.sberbank.bankapi.model.dto.ResponseCardDTO;

/**Формирование данных для отклика*/
@Service
public class MappingUtils {

    public ResponseCardDTO mapToResponseCardDto(Card card) {
        ResponseCardDTO responseCardDTO = new ResponseCardDTO();
        responseCardDTO.setID(card.getID());
        responseCardDTO.setNumber(card.getNumber());
        return responseCardDTO;
    }

}
