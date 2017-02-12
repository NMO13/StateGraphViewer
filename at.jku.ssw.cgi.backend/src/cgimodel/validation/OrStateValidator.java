/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package cgimodel.validation;

import cgimodel.BaseState;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link cgimodel.OrState}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface OrStateValidator {
	boolean validate();

	boolean validateStates(EList<BaseState> value);
}
