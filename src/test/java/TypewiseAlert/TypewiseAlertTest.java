
package TypewiseAlert;

import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.never;

import org.junit.Before;
import org.junit.Test;

import TypewiseAlert.Constants.BreachType;
import TypewiseAlert.Constants.CoolingType;
import TypewiseAlert.Constants.AlertTarget;
import TypewiseAlert.FormatOutput;
import TypewiseAlert.TypewiseAlert;

public class TypewiseAlertTest {
	
	  private FormatOutput mockOutput;
    private TypewiseAlert.BatteryCharacter batteryCharacter;

    @Before
    public void setUpPrinter() throws Exception {
        mockOutput = mock(FormatOutput.class);
        Field outputField = TypewiseAlert.class.getDeclaredField("output");
        outputField.setAccessible(true);
        outputField.set(null, mockOutput);
        batteryCharacter = new TypewiseAlert().new BatteryCharacter();
    }

    @Test
    public void testSendToController() {
        batteryCharacter.coolingType = CoolingType.PASSIVE_COOLING;
        TypewiseAlert.checkAndAlert(AlertTarget.TO_CONTROLLER, batteryCharacter, 50);
        verify(mockOutput).printf("%x : %s\n", 0xfeed, BreachType.TOO_HIGH);
    }

  

}
