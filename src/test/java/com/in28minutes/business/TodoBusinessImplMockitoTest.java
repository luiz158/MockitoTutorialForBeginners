package com.in28minutes.business;

import com.in28minutes.data.api.TodoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplMockitoTest {

    @Mock
    TodoService todoServiceMock;
    // TodoService todoServiceMock = mock(TodoService.class)

    @InjectMocks
    TodoBusinessImpl todoBusinessImpl;
    // TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock)

    @Test
    public void usingMockito() {

        // create the content
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");

        // Mock the expected return type
        when(todoServiceMock.retrieveTodos("Ranga")).thenReturn(allTodos);

        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring("Ranga");
        assertEquals(2, todos.size());
    }

    @Test
    public void usingMockito_UsingBDD() {

        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");

        //given
        given(todoServiceMock.retrieveTodos("Ranga")).willReturn(allTodos);

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

        // Mock the values
        List<String> todoListItems = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");

        given(todoServiceMock.retrieveTodos(userName)).willReturn(todoListItems);

        // [when]
        boolean result = todoBusinessImpl.deleteTodosNotRelatedToSpring(userName);

        // [then]
        then(todoServiceMock).should().deleteTodo((String) stringArgumentCaptor.capture());
        assertThat(stringArgumentCaptor.getValue(), is("Learn to Dance"));

        verify(todoServiceMock, atLeastOnce()).deleteTodo("Learn to Dance");
        verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");
        // atLeastOnce, atLeast

        assertThat(result, is(true));
    }

    @Test
    public void letsTestDeleteNow() {

        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");

        when(todoServiceMock.retrieveTodos("Ranga")).thenReturn(allTodos);

        todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

        // Ensure the method with this argument is called
        verify(todoServiceMock).deleteTodo("Learn to Dance");

        verify(todoServiceMock, Mockito.never()).deleteTodo("Learn Spring MVC");

        verify(todoServiceMock, Mockito.never()).deleteTodo("Learn Spring");

        verify(todoServiceMock, Mockito.times(1)).deleteTodo("Learn to Dance");

    }
}
