/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2020, Arnaud Roques
 *
 * Project Info:  http://plantuml.com
 *
 * If you like this project or if you find it useful, you can support us at:
 *
 * http://plantuml.com/patreon (only 1$ per month!)
 * http://plantuml.com/paypal
 *
 * This file is part of PlantUML.
 *
 * PlantUML is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlantUML distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 *
 * Original Author:  Arnaud Roques
 *
 */
package net.sourceforge.plantuml.tim.stdlib;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sourceforge.plantuml.tim.EaterException;
import net.sourceforge.plantuml.tim.TContext;
import net.sourceforge.plantuml.tim.TFunction;
import net.sourceforge.plantuml.tim.TFunctionSignature;
import net.sourceforge.plantuml.tim.TFunctionType;
import net.sourceforge.plantuml.tim.TMemory;
import net.sourceforge.plantuml.tim.expression.TValue;

public class DateFunction implements TFunction {

	public TFunctionSignature getSignature() {
		return new TFunctionSignature("%date", 1);
	}

	public boolean canCover(int nbArg) {
		return nbArg == 0 || nbArg == 1;
	}

	public TFunctionType getFunctionType() {
		return TFunctionType.RETURN;
	}

	public void executeVoid(TContext context, String s, TMemory memory) throws EaterException {
		throw new UnsupportedOperationException();
	}

	public TValue executeReturn(TContext context, TMemory memory, List<TValue> args) throws EaterException {
		if (args.size() == 0) {
			return TValue.fromString(new Date().toString());
		}
		final String format = args.get(0).toString();
		try {
			return TValue.fromString(new SimpleDateFormat(format).format(new Date()));
		} catch (Exception e) {
			throw new EaterException("Bad date pattern");
		}
	}

	public boolean isUnquoted() {
		return false;
	}

}
