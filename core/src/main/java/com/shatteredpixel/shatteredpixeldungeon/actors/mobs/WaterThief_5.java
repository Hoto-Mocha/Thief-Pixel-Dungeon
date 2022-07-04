/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2022 Evan Debenham
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

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.GeyserTrap;
import com.shatteredpixel.shatteredpixeldungeon.sprites.WaterThiefSprite;

public class WaterThief_5 extends Thief_5 {
	
	public Item item;
	
	{
		spriteClass = WaterThiefSprite.class;

		//guaranteed first drop, then 1/3, 1/9, etc.
		lootChance = 1f;
		loot = Generator.Category.POTION;
	}
	
	@Override
	protected boolean steal( Hero hero ) {
		if (super.steal( hero )) {

			GeyserTrap geyser = new GeyserTrap();
			geyser.pos = hero.pos;
			geyser.activate();
			Dungeon.observe();
			
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void die(Object cause) {
		super.die(cause);
		GeyserTrap geyser = new GeyserTrap();
		geyser.pos = this.pos;
		geyser.activate();
		Dungeon.observe();
	}
	
}
