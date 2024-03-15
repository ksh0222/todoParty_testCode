package hello.todoparty.test;

import hello.todoparty.entity.Todo;
import hello.todoparty.entity.User;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.SerializationUtils;

import java.time.LocalDateTime;

public class TodoTestUtils {
    public static Todo get(Todo todo, User user) {
        return get(todo, 1L, LocalDateTime.now(), user);
    }

    public static Todo get(Todo todo, Long id, LocalDateTime createDate, User user) {
        var newTodo = SerializationUtils.clone(todo);
        ReflectionTestUtils.setField(newTodo, Todo.class, "id", id, Long.class);
        ReflectionTestUtils.setField(newTodo, Todo.class, "createDate", createDate, LocalDateTime.class);
        newTodo.setUser(user);
        return newTodo;
    }
}
