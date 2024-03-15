package hello.todoparty.service;

import hello.todoparty.dto.TodoResponseDto;
import hello.todoparty.entity.Todo;
import hello.todoparty.repository.TodoRepository;
import hello.todoparty.test.TodoTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;


import static hello.todoparty.test.CommonTest.TEST_USER;
import static hello.todoparty.test.TodoTest.TEST_TODO;
import static hello.todoparty.test.TodoTest.TEST_TODO_REQUEST_DTO;
import static org.awaitility.Awaitility.given;
import static org.hamcrest.Matchers.any;


//수정 + 추가 필요

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {
    @InjectMocks
    TodoService todoService;

    @Mock
    TodoRepository todoRepository;

    @DisplayName("todo 생성")
    @Test
    void createTodo() {
        // given
        var testTodo = TodoTestUtils.get(TEST_TODO, TEST_USER);
        given(todoRepository.save(any(Todo.class))).willReturn(testTodo);

        // when
        var result = todoService.createTodo(TEST_TODO_REQUEST_DTO, TEST_USER);

        // then
        var expect = new TodoResponseDto(testTodo);
        assertThat(result).isEqualTo(expect);
    }

}
