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

import cz.cvut.kbss.owl2query.model.QueryResult;
import cz.cvut.kbss.owl2query.model.ResultBinding;
import cz.cvut.kbss.owl2query.model.Variable;

import java.util.*;

class QueryResultImpl<G> implements QueryResult<G> {

	private final Collection<ResultBinding<G>> bindings;

	private final List<Variable<G>> resultVars;

	private final InternalQuery<G> query;

	private int offsetCount = 0;

	public QueryResultImpl(final InternalQuery<G> query) {
		this.query = query;
		this.resultVars = new ArrayList<>(query
				.getResultVars());

		if (query.isDistinct())
			bindings = new LinkedHashSet<>();
		else
			bindings = new ArrayList<>();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean add(ResultBinding<G> binding) {
		if (bindings.size() >= query.getLimit()) {
			return false;
		}
		if (offsetCount >= query.getOffset()) {
			bindings.add(binding);
		}
		offsetCount++;
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final QueryResultImpl<G> other = (QueryResultImpl<G>) obj;
		if (bindings == null) {
			if (other.bindings != null)
				return false;
		} else if (!bindings.equals(other.bindings))
			return false;
		if (resultVars == null) {
			return other.resultVars == null;
		} else return resultVars.equals(other.resultVars);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Variable<G>> getResultVars() {
		return Collections.unmodifiableList(resultVars);
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result
				+ ((bindings == null) ? 0 : bindings.hashCode());
		result = PRIME * result
				+ ((resultVars == null) ? 0 : resultVars.hashCode());
		return result;
	}

	public boolean isDistinct() {
		return (bindings instanceof Set);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public Iterator<ResultBinding<G>> iterator() {
		return bindings.iterator();
	}

	/**
	 * {@inheritDoc}
	 */
	public int size() {
		return bindings.size();
	}

	@Override
	public String toString() {
		return bindings.toString();
	}
}
