package hello.todoparty.controller;

import hello.todoparty.dto.*;
import hello.todoparty.security.UserDetailsImpl;
import hello.todoparty.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;

@RequestMapping("/api/todos")
@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponseDto> postTodo(@RequestBody TodoRequestDto todoRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        TodoResponseDto responseDTO = todoService.createTodo(todoRequestDTO, userDetails.getUser());

        return ResponseEntity.status(201).body(responseDTO);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<CommonResponseDto> getTodo(@PathVariable Long todoId) {
        try {
            TodoResponseDto responseDto = todoService.getTodoDto(todoId);
            return ResponseEntity.ok().body(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @GetMapping
    public ResponseEntity<List<TodoListResponseDto>> getTodoList() {
        List<TodoListResponseDto> response = new ArrayList<>();

        Map<UserDto, List<TodoResponseDto>> responseDTOMap = todoService.getUserTodoMap();

        responseDTOMap.forEach((key, value) -> response.add(new TodoListResponseDto(key, value)));

        return ResponseEntity.ok().body(response);
    }


    @PutMapping("/{todoId}")
    public ResponseEntity<TodoResponseDto> putTodo(@PathVariable Long todoId, @RequestBody TodoRequestDto todoRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            TodoResponseDto responseDTO = todoService.updateTodo(todoId, todoRequestDTO, userDetails.getUser());
            return ResponseEntity.ok().body(responseDTO);
        } catch (RejectedExecutionException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new TodoResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }


    @PatchMapping("/{todoId}/complete")
    public ResponseEntity<TodoResponseDto> completeTodo(@PathVariable Long todoId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            TodoResponseDto responseDTO = todoService.completeTodo(todoId, userDetails.getUser());
            return ResponseEntity.ok().body(responseDTO);
        } catch (RejectedExecutionException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new TodoResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

}
