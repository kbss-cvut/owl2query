/*******************************************************************************
 * Copyright (C) 2011 Czech Technical University in Prague                                                                                                                                                        
 *                                                                                                                                                                                                                
 * This program is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free Software 
 * Foundation, either version 3 of the License, or (at your option) any 
 * later version. 
 *                                                                                                                                                                                                                
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more 
 * details. You should have received a copy of the GNU General Public License 
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package cz.cvut.kbss.owl2query.engine;

import java.util.Iterator;

import cz.cvut.kbss.owl2query.model.OWL2Ontology;
import cz.cvut.kbss.owl2query.model.ResultBinding;

public abstract class External<G> implements QueryAtom<G> {
	
	protected String name;
	
	@SuppressWarnings("unchecked")
	public External(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Ex-"+name+"()";
	}
	
	public abstract Iterator<ResultBinding<G>> eval(ResultBinding<G> binding, final OWL2Ontology<G> o);
}
