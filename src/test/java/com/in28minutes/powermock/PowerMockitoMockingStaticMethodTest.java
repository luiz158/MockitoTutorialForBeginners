package com.in28minutes.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UtilityClass.class})
//The class with static method to be mocked
public class PowerMockitoMockingStaticMethodTest {

	/*
        Mock static setup eg. @RunWith(PowerMockRunner.class)
		1) Specific Runner
		2) Initialize UtilityClass.class for mocking
		3) PrepareForTest to specify static class
		4) Mock
	 */

    @Mock
    Dependency dependencyMock;

    @InjectMocks
    SystemUnderTest systemUnderTest;

    @Test
    public void testRetriveTodosRelatedToSpring_Mock() {
        List<Integer> stats = Arrays.asList(1, 2, 3);

        when(dependencyMock.retrieveAllStats()).thenReturn(stats);

        // Init a static class in the test case
        mockStatic(UtilityClass.class);

        // Mock this method return value with stated argument
        when(UtilityClass.staticMethod(6)).thenReturn(6);

        int result = systemUnderTest.methodCallingAStaticMethod();

        assertThat(result, is(6));

        // To verify if staticMethod was called with the correct argument
        verifyStatic();
        UtilityClass.staticMethod(6);
    }

    @Test
    public void powerMockito_MockingAStaticMethodCall() {

        when(dependencyMock.retrieveAllStats()).thenReturn(
                Arrays.asList(1, 2, 3));

        PowerMockito.mockStatic(UtilityClass.class);

        when(UtilityClass.staticMethod(anyLong())).thenReturn(150);

        assertEquals(150, systemUnderTest.methodCallingAStaticMethod());

        //To verify a specific method call
        //First : Call PowerMockito.verifyStatic()
        //Second : Call the method to be verified
        PowerMockito.verifyStatic();
        UtilityClass.staticMethod(1 + 2 + 3);

        // verify exact number of calls
        //PowerMockito.verifyStatic(Mockito.times(1));

    }
}