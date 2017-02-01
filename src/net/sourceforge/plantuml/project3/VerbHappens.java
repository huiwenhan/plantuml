/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2017, Arnaud Roques
 *
 * Project Info:  http://plantuml.com
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
 *
 */
package net.sourceforge.plantuml.project3;

import java.util.Arrays;
import java.util.Collection;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.regex.IRegex;
import net.sourceforge.plantuml.command.regex.RegexLeaf;
import net.sourceforge.plantuml.command.regex.RegexResult;

public class VerbHappens implements VerbPattern {

	public Collection<ComplementPattern> getComplements() {
		return Arrays.<ComplementPattern> asList(new ComplementBeforeOrAfterOrAtTaskStartOrEnd());
	}

	public IRegex toRegex() {
		return new RegexLeaf("happens");
	}

	public Verb getVerb(GanttDiagram project, RegexResult arg) {
		return new Verb() {
			public CommandExecutionResult execute(Subject subject, Complement complement) {
				final Task task = (Task) subject;
				final TaskInstant when = (TaskInstant) complement;
				task.setDuration(new DurationDay(1));
				task.setStart(when.getInstantTheorical());
				return CommandExecutionResult.ok();
			}

		};
	}

}