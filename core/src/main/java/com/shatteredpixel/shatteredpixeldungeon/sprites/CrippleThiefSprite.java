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

package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.watabou.noosa.TextureFilm;

public class CrippleThiefSprite extends MobSprite {

	public CrippleThiefSprite() {
		super();
		
		texture( Assets.Sprites.THIEF );
		TextureFilm film = new TextureFilm( texture, 12, 13 );
		
		idle = new Animation( 1, true );
		idle.frames( film, 84, 84, 84, 85, 84, 84, 84, 84, 85 );
		
		run = new Animation( 15, true );
		run.frames( film, 84, 84, 86, 87, 87, 88 );
		
		die = new Animation( 10, false );
		die.frames( film, 89, 90, 91, 92, 93 );
		
		attack = new Animation( 12, false );
		attack.frames( film, 94, 95, 96, 84 );
		
		idle();
	}
}
