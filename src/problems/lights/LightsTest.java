package problems.lights;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class LightsTest {
	ILightsProblem lightsProblem;
	int numberOfLights, numberOfPresses;
	Integer [] onLights, offLights;
	Collection<Boolean[]> expectedResult;
	
	
	public LightsTest (int numberOfLights, int numberOfPresses, Integer [] onLights, Integer [] offLights, Collection<Boolean[]> expectedResult) {
			this.numberOfLights = numberOfLights;
			this.numberOfPresses = numberOfPresses;
			this.onLights = onLights;
			this.offLights = offLights;
			this.expectedResult = expectedResult;
	}
	
	 // creates the test data
	@Parameters
	public static Collection<Object[]> data() {
		Collection<Boolean[]> result1 = new LinkedList<Boolean[]> () {{	add(new Boolean[] {false,false,false,false,false,false,false,false,false,false});
																		add(new Boolean[] {false,true ,false,true ,false,true ,false,true ,false,true });
																		add(new Boolean[] {false,true ,true ,false,true ,true ,false,true ,true ,false});
																		add(new Boolean[] {true ,false,true ,false,true ,false,true ,false,true ,false});
																	}};
		Collection<Boolean[]> result2 = new LinkedList<Boolean[]> () {{	add(new Boolean[] {false,false,false,false,false,false,false,false,false,false});
																	add(new Boolean[] {false,true ,false,true ,false,true ,false,true ,false,true });
																	add(new Boolean[] {false,true ,true ,false,true ,true ,false,true ,true ,false});
																}};
		Collection<Boolean[]> result3 = new LinkedList<Boolean[]> () {{	add(new Boolean[] {false,true,true,false,true,true,false,true,true,false,true,true,false,true,true,false,true,true,false,true,true,false,true,true,false,true,true,false,true,true,false,true,true,false,true,true,false,true,true,false,true,true,false,true,true,false,true,true,false,true,true,false,true,true,false,true,true,false,true,true,false,true,true,false,true,true,false,true,true,false});
																add(new Boolean[] {true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false});
					
															}};
		Object[][] data = new Object[][] { 	{10,1,new Integer[] {},new Integer[] {},result1},
											{10,1,new Integer[] {},new Integer[] {7},result2},
											{70,99999,new Integer[] {51},new Integer[] {4},result2}};
						

	    return Arrays.asList(data);
	  }

	
	@Test
	public void testContactProblem() {
		lightsProblem = new LightsProblem();
		lightsProblem.setData(numberOfLights,numberOfPresses,onLights,offLights);
		lightsProblem.run();
		Collection<Boolean[]> result = lightsProblem.getResult();
		// checks if the mappings are equal.
		Assert.assertEquals("lights: ["+numberOfLights+"], presses:["+numberOfPresses+"]: "
							, expectedResult.size(), result.size() );
		// now we check that the values are correct for every key
		for (Boolean[] resultArray: result) {
			boolean exists = false;
			Iterator<Boolean[]> itb = expectedResult.iterator();
			while(itb.hasNext() && !exists) {
				Boolean[] expectedArray = itb.next();
				Assert.assertEquals("lights: ["+numberOfLights+"], presses:["+numberOfPresses+"]: ", expectedArray.length, resultArray.length);
				exists = true;
				for (int pos = 0; pos < expectedArray.length && exists; pos++)
					if (expectedArray[pos] != resultArray[pos])
						exists = false;
			}
			Assert.assertTrue("lights: ["+numberOfLights+"], presses:["+numberOfPresses+"]: ", exists);
		}
	}
}
