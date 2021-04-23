package com.demo.volvescalculator.serivce;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResponseServiceTest {

    @Mock
    private WordConventerService wordConventerServiceMock;
    @Mock
    private MapConventerServiceService MapConventerServiceServiceMock;
    @InjectMocks
    private ResponseService responseService;

    @Test
    void should() {
        // given
        String firstWord = "the";
        String secondWord = "woords";
        String joinedWords = String.join(" ", firstWord, secondWord);

        when(wordConventerServiceMock.convertWordsToArray(joinedWords))
                .thenReturn(new String[]{firstWord, secondWord});
        when(MapConventerServiceServiceMock.getVowelMap(firstWord))
                .thenReturn(Map.of('e', 1L));
        when(MapConventerServiceServiceMock.getVowelMap(secondWord))
                .thenReturn(Map.of('o', 2L));

        // when
        List<String> response = responseService.getProcessedResponse(joinedWords);

        // then
        assertAll(
                () -> assertEquals(response.get(0), "({e}, 3) -> 1.0"),
                () -> assertEquals(response.get(1), "({o}, 6) -> 2.0"));
    }
}