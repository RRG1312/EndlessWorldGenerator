/*******************************************************************************
 * Copyright (c) 2013 Borja Molina Zea.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Borja Molina Zea - initial API and implementation
 ******************************************************************************/
/**
 * All the 'random' numbers used for create the world.
 * Starting from a seed-string, like "hola", it generates 2^18 different numbers.
 * Different strings generates different numbers.
 * The numbers are stored in a integer array.
 * v 0.1
 * Hi Borja from 2013, I am Borja from 2020, I am testing pull request
 */
package world;

import java.io.IOException;

public class GlobalSeed {
	public String sSeed;
	public int nSeed;
	public int size = 262144;
	public int amplitudeIndex = 0;
	public int amplitudeSize = 128;
	public int periodIndex = amplitudeIndex + amplitudeSize;
	public int periodSize = 128;
	public int xPhaseIndex = periodIndex + periodSize;
	public int xPasheSize = 128;
	public int zPhaseIndex = xPhaseIndex + xPasheSize;
	public int zPhaseSize = 128;

	public int[] seeds = new int[size] ;
		
	public GlobalSeed(String seed){
		this.sSeed = seed;
		this.nSeed = this.generateNumberSeed();
		this.generateNumberSeed();
		this.generateSeeds();
	}
	
	private int generateNumberSeed(){
		return this.sSeed.hashCode();
	}
	
	private void generateSeeds(){
		utilities.RNG nRand = new utilities.RNG(this.nSeed);
		for(int i=0; i<this.size; i++){
			this.seeds[i] = nRand.randi();
		}
	}
	
	public int getGlobal(int index){
		return this.seeds[index];
	}
	
	public int getAmplitude(int index){
		index%=this.amplitudeSize;
		index+=this.amplitudeIndex;
		return this.seeds[index];
	}
	
	public int getPeriod(int index){
		index%=this.periodSize;
		index+=this.periodIndex;
		return this.seeds[index];
	}

	public int getxPhase(int index){
		index%=this.xPasheSize;
		index+=this.xPhaseIndex;
		return this.seeds[index];
	}
	
	public int getzPhase(int index){
		index%=this.zPhaseSize;
		index+=this.zPhaseIndex;
		return this.seeds[index];
	}
	
	public static void main(String[] args){
		String seed = "ana";
		GlobalSeed a = new GlobalSeed(seed);
		System.out.println("Semillas numericas para semilla: "+seed);
		for(int i=0; i<a.size; i++){
			if(a.getGlobal(i)<10000 && a.getGlobal(i)>-10000){
			System.out.println("pos="+i+" seed="+a.getGlobal(i));
			}
		}
		System.out.println(5*Math.sin(((1.0/3)*2+3)));
	}
}
