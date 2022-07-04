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
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.sprites.IceThiefSprite;

public class IceThief_2 extends Thief_2 {
	
	public Item item;
	
	{
		spriteClass = IceThiefSprite.class;

		//guaranteed first drop, then 1/3, 1/9, etc.
		lootChance = 1f;
		loot = Generator.Category.ARMOR;
	}
	
	@Override
	protected boolean steal( Hero hero ) {
		if (super.steal( hero )) {

			if (enemy != null
					&& !(enemy instanceof Goo)
					&& !(enemy instanceof Tengu)
					&& !(enemy instanceof DM300)
					&& !(enemy instanceof DwarfKing)
					&& !(enemy instanceof YogDzewa)) {
				new FlavourBuff(){
					{actPriority = VFX_PRIO;}
					public boolean act() {
						Buff.affect( enemy, Frost.class, 8f);
						return super.act();
					}
				}.attachTo(enemy);
			}
			Dungeon.observe();
			
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int attackProc(Char enemy, int damage ) {
		damage = super.attackProc( enemy, damage );

		float durationToAdd = 3f;
		Chill existing = enemy.buff(Chill.class);
		if (existing != null){
			durationToAdd = Math.min(durationToAdd, 6f-existing.cooldown());
		}

		Buff.affect( enemy, Chill.class, durationToAdd );
		Splash.at( enemy.sprite.center(), 0xFFB2D6FF, 5);

		if (alignment == Alignment.ENEMY && item == null
				&& enemy instanceof Hero && steal( (Hero)enemy )) {
			state = FLEEING;
		}

		return damage;
	}


}
