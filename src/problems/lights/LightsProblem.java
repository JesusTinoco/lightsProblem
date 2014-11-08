package problems.lights;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class LightsProblem implements ILightsProblem {

	/*
	 * This method sets the input data for the problem.
	 * @param numberOfLights the number of lights in the strip.
	 * @param numberOfPresses the number of times the buttons are pressed.
	 * @param onLights a list of 0, 1 or 2 integer numbers indicating the lights that must be ON at the end (ligths are numbered from 1 to numberOfLights)
	 * @param offLights a list of 0, 1 or 2 integer numbers indicating the lights that must be OFF at the end (ligths are numbered from 1 to numberOfLights) 
	 * @throws IllegalArgumentException if any of the integer input parameters are zero or negative, 
	 * the maxLength is less than the minLength or the message is null.
	 */
	
	private Integer numberOfLights;
	private Integer numberOfPresses;
	private Integer[] onLights;
	private Integer[] offLights;
	private Set<Boolean[]> setResults;
	
	@Override
	public void setData(int numberOfLights, int numberOfPresses,
			Integer[] onLights, Integer[] offLights) {
		if(numberOfLights <= 0 || numberOfPresses <= 0)
			throw new IllegalArgumentException();
		this.numberOfLights = numberOfLights;
		this.numberOfPresses = numberOfPresses;
		this.onLights = onLights;
		this.offLights = offLights;
		this.setResults = new HashSet<Boolean[]>();
	}

	@Override
	public void run() {
		
		switch (numberOfPresses) {
	
		case 1:
			case1();
			break;
			
		case 2:
			case2();
			break;

		default:
			case3();
			break;
		}

	}
	
	public void case1(){
		setResults.add(
				applyB1(getBooleanArray(numberOfLights)));
		setResults.add(
				applyB2(getBooleanArray(numberOfLights)));
		setResults.add(
				applyB3(getBooleanArray(numberOfLights)));
		setResults.add(
				applyB4(getBooleanArray(numberOfLights)));
	}
	
	public void case2(){
		setResults.add(getBooleanArray(numberOfLights));
		setResults.add(
				applyB1(getBooleanArray(numberOfLights)));
		setResults.add(
				applyB2(getBooleanArray(numberOfLights)));
		setResults.add(
				applyB3(getBooleanArray(numberOfLights)));
		setResults.add(
				applyB2(
				applyB4(getBooleanArray(numberOfLights))));
		setResults.add(
				applyB3(
				applyB4(getBooleanArray(numberOfLights))));
		setResults.add(
				applyB4(
				applyB1(getBooleanArray(numberOfLights))));
	}
	
	public void case3(){
		case2();
		setResults.add(
				applyB4(getBooleanArray(numberOfLights)));
	}
	
	
	private Boolean[] applyB1(Boolean[] lights){
		Stream<Boolean> streamBoolean = Stream
				.of(lights)
				.map(n -> {
					if(n) 
						return false; 
					else 
						return true;
				});
		return streamBoolean.toArray(size -> new Boolean[size]);
	}
	
	private Boolean[] applyB2(Boolean[] lights){
		for(int i = 0; i<lights.length; i = i+2){
			if(lights[i])
				lights[i] = false;
			else
				lights[i] = true;
		}
		return lights;
	}
	
	private Boolean[] applyB3(Boolean[] lights){
		for(int i = 1; i<lights.length; i = i+2){
			if(lights[i])
				lights[i] = false;
			else
				lights[i] = true;
		}
		return lights;
	}
	
	private Boolean[] applyB4(Boolean[] lights){
		int j = 0;
		for(int i = 0; i<lights.length; i = 3*j){
			if(lights[i])
				lights[i] = false;
			else
				lights[i] = true;
			j++;
		}
		return lights;
	}
	
	private Boolean[] getBooleanArray(Integer nElements){
		Stream<Boolean> streamBoolean = Stream
				.of(new Boolean[nElements])
				.map(n -> true);
		return streamBoolean.toArray(size -> new Boolean[size]);
	}
	
	@Override
	public Collection<Boolean[]> getResult() {
		Set<Boolean[]> res = new HashSet<Boolean[]>();
		
		for(Boolean[] b: setResults){
			Boolean aux = true;
			for(Integer onLight: onLights){
				if(!b[onLight-1]){
					aux = false;
					break;
				}
			}
			if(!aux)
				continue;
			for(Integer offLight: offLights){
				if(b[offLight-1]){
					aux = false;
					break;
				}
			}
			if(!aux)
				continue;
			res.add(b);
		}
		
		return res;
	}

}
