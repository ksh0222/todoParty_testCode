package hello.todoparty.controller;


import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import hello.todoparty.service.TodoService;

//수정 + 추가 필요
@WebMvcTest(TodoController.class)
public class TodoController {

    @MockBean //service 가짜 생성
    private TodoService todoService;

    @DisplayName("할일 생성 요청")
    @Test
    void postTodo() throws Exception {
        // given

        // when
        var action = mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(TEST_TODO_REQUEST_DTO)));

        // then
        action.andExpect(status().isCreated());
        verify(todoService, times(1)).createTodo(any(TodoRequestDTO.class), eq(TEST_USER));
    }


}
