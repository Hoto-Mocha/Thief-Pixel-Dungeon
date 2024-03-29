/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2023 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Arrays;

public class Bestiary {
	
	public static ArrayList<Class<? extends Mob>> getMobRotation( int depth ){
		ArrayList<Class<? extends Mob>> mobs = standardMobRotation( depth );
		addRareMobs(depth, mobs);
		swapMobAlts(mobs);
		Random.shuffle(mobs);
		return mobs;
	}
	
	//returns a rotation of standard mobs, unshuffled.
	private static ArrayList<Class<? extends Mob>> standardMobRotation( int depth ){
		switch(depth){

			// Sewers
			case 1: case 2: case 3: case 4: case 5: default:
				return new ArrayList<>(Arrays.asList(
						Thief_1.class, FireThief_1.class, ElectricThief_1.class,
						CrippleThief_1.class, WaterThief_1.class, BlindThief_1.class,
						IceThief_1.class, Bandit_1.class));
			// Prison
			case 6: case 7: case 8: case 9: case 10:
				return new ArrayList<>(Arrays.asList(
						Thief_2.class, FireThief_2.class, ElectricThief_2.class,
						CrippleThief_2.class, WaterThief_2.class, BlindThief_2.class,
						IceThief_2.class, Bandit_2.class));
			// Caves
			case 11: case 12: case 13: case 14: case 15:
				return new ArrayList<>(Arrays.asList(
						Thief_3.class, FireThief_3.class, ElectricThief_3.class,
						CrippleThief_3.class, WaterThief_3.class, BlindThief_3.class,
						IceThief_3.class, Bandit_3.class));
			// City
			case 16: case 17: case 18: case 19: case 20:
				return new ArrayList<>(Arrays.asList(
						Thief_4.class, FireThief_4.class, ElectricThief_4.class,
						CrippleThief_4.class, WaterThief_4.class, BlindThief_4.class,
						IceThief_4.class, Bandit_4.class));

			// Halls
			case 21: case 22: case 23: case 24: case 25: case 26:
				return new ArrayList<>(Arrays.asList(
						Thief_5.class, FireThief_5.class, ElectricThief_5.class,
						CrippleThief_5.class, WaterThief_5.class, BlindThief_5.class,
						IceThief_5.class, Bandit_5.class));
		}

	}
	
	//has a chance to add a rarely spawned mobs to the rotation
	public static void addRareMobs( int depth, ArrayList<Class<?extends Mob>> rotation ){
		
		switch (depth){
			
			// Sewers
			default:
				return;
			case 4:
				if (Random.Float() < 0.025f) rotation.add(Thief_2.class);
				return;
				
			// Prison
			case 9:
				if (Random.Float() < 0.025f) rotation.add(Thief_3.class);
				return;
				
			// Caves
			case 14:
				if (Random.Float() < 0.025f) rotation.add(Thief_4.class);
				return;
				
			// City
			case 19:
				if (Random.Float() < 0.025f) rotation.add(Thief_5.class);
				return;
		}
	}
	
	//switches out regular mobs for their alt versions when appropriate
	private static void swapMobAlts(ArrayList<Class<?extends Mob>> rotation){
		for (int i = 0; i < rotation.size(); i++){
			if (Random.Int( 50 ) == 0) {
				Class<? extends Mob> cl = rotation.get(i);
				if (cl == Rat.class) {
					cl = Albino.class;
				} else if (cl == Slime.class) {
					cl = CausticSlime.class;
				/*} else if (cl == Thief.class) {
					cl = Bandit.class;*/
				} else if (cl == Necromancer.class){
					cl = SpectralNecromancer.class;
				} else if (cl == Brute.class) {
					cl = ArmoredBrute.class;
				} else if (cl == DM200.class) {
					cl = DM201.class;
				} else if (cl == Monk.class) {
					cl = Senior.class;
				} else if (cl == Scorpio.class) {
					cl = Acidic.class;
				}
				rotation.set(i, cl);
			}
		}
	}
}
