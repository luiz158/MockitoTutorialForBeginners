package com.in28minutes.business;

import com.in28minutes.data.api.TodoService;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class TodoBusinessImplMockitoTest {

    @Test
    public void usingMockito() {

        // mock the todoservice
        TodoService todoService = mock(TodoService.class);

        // create the content
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");

        // Mock the expected return type
        when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);

        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring("Ranga");
        assertEquals(2, todos.size());
    }

    @Test
    public void usingMockito_UsingBDD() {


        TodoService todoService = mock(TodoService.class);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);

        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");

        //given
        given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);

        //when
        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring("Ranga");

        //then
        assertThat(todos.size(), is(2));
    }

    @Test
    public void test_argument_passed_on_deletetodo_method() {

        // Declare argument captor with argument type to capture
        ArgumentCaptor stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        // [given]
        String userName = "prajesh";

        // Mock the todoService
        TodoService todoServiceMock = mock(TodoService.class);

        // Mock the values
        List<String> todoListItems = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");

        given(todoServiceMock.retrieveTodos(userName)).willReturn(todoListItems);

        // Do not mock the class we are testing
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceMock);

        // [when]
        boolean result = todoBusiness.deleteTodosNotRelatedToSpring(userName);

        // [then]
        then(todoServiceMock).should().deleteTodo((String) stringArgumentCaptor.capture());
        assertThat(stringArgumentCaptor.getValue(), is("Learn to Dance"));

        verify(todoServiceMock, atLeastOnce()).deleteTodo("Learn to Dance");
        verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");
        // atLeastOnce, atLeast
    }

    @Test
    public void letsTestDeleteNow() {

        // The mocking process takes place here
        TodoService todoService = mock(TodoService.class);

        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");

        when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);


        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);

        todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

        // Ensure the method with this argument is called
        verify(todoService).deleteTodo("Learn to Dance");

        verify(todoService, Mockito.never()).deleteTodo("Learn Spring MVC");

        verify(todoService, Mockito.never()).deleteTodo("Learn Spring");

        verify(todoService, Mockito.times(1)).deleteTodo("Learn to Dance");

    }

    @Test
    public void captureArgument() {

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        TodoService todoService = mock(TodoService.class);

        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        Mockito.when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

        Mockito.verify(todoService).deleteTodo(argumentCaptor.capture());

        assertEquals("Learn to Dance", argumentCaptor.getValue());
    }
}
