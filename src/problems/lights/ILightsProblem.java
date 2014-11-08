package problems.lights;

import java.util.Collection;

public interface ILightsProblem {
	/*
	 * This method sets the input data for the problem.
	 * @param numberOfLights the number of lights in the strip.
	 * @param numberOfPresses the number of times the buttons are pressed.
	 * @param onLights a list of 0, 1 or 2 integer numbers indicating the lights that must be ON at the end (ligths are numbered from 1 to numberOfLights)
	 * @param offLights a list of 0, 1 or 2 integer numbers indicating the lights that must be OFF at the end (ligths are numbered from 1 to numberOfLights) 
	 * @throws IllegalArgumentException if any of the integer input parameters are zero or negative, 
	 * the maxLength is less than the minLength or the message is null.
	 */
	public abstract void setData(int numberOfLights, int numberOfPresses, Integer[] onLights, Integer[] offLights);
	
	/*
	 * Executes the problem if the data has been correctly set
	 */
	public abstract void run();
	
	/*
	 * @return a collection of lights configuration that satisfy the on/off constraints specified by onLights and offLights
	 * It return null if the problem has not been solved yet.
	 */
	public abstract Collection<Boolean[]> getResult();
}