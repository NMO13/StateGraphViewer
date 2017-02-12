/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package cgimodel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Or State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cgimodel.OrState#getStates <em>States</em>}</li>
 * </ul>
 * </p>
 *
 * @see cgimodel.CgimodelPackage#getOrState()
 * @model
 * @generated
 */
public interface OrState extends BaseState {
	/**
	 * Returns the value of the '<em><b>States</b></em>' containment reference list.
	 * The list contents are of type {@link cgimodel.BaseState}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>States</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>States</em>' containment reference list.
	 * @see cgimodel.CgimodelPackage#getOrState_States()
	 * @model containment="true"
	 * @generated
	 */
	EList<BaseState> getStates();

} // OrState
