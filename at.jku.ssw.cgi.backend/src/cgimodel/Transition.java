/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package cgimodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cgimodel.Transition#getSource <em>Source</em>}</li>
 *   <li>{@link cgimodel.Transition#getTarget <em>Target</em>}</li>
 *   <li>{@link cgimodel.Transition#getCondition <em>Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @see cgimodel.CgimodelPackage#getTransition()
 * @model
 * @generated
 */
public interface Transition extends EObject {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(BaseState)
	 * @see cgimodel.CgimodelPackage#getTransition_Source()
	 * @model
	 * @generated
	 */
	BaseState getSource();

	/**
	 * Sets the value of the '{@link cgimodel.Transition#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(BaseState value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(State)
	 * @see cgimodel.CgimodelPackage#getTransition_Target()
	 * @model
	 * @generated
	 */
	State getTarget();

	/**
	 * Sets the value of the '{@link cgimodel.Transition#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(State value);

	/**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference.
	 * @see #setCondition(Expr)
	 * @see cgimodel.CgimodelPackage#getTransition_Condition()
	 * @model containment="true"
	 * @generated
	 */
	Expr getCondition();

	/**
	 * Sets the value of the '{@link cgimodel.Transition#getCondition <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' containment reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(Expr value);

} // Transition
