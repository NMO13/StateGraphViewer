/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package cgimodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cgimodel.State#isSet <em>Set</em>}</li>
 *   <li>{@link cgimodel.State#getExpr <em>Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see cgimodel.CgimodelPackage#getState()
 * @model
 * @generated
 */
public interface State extends BaseState {
	/**
	 * Returns the value of the '<em><b>Set</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Set</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Set</em>' attribute.
	 * @see #setSet(boolean)
	 * @see cgimodel.CgimodelPackage#getState_Set()
	 * @model
	 * @generated
	 */
	boolean isSet();

	/**
	 * Sets the value of the '{@link cgimodel.State#isSet <em>Set</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Set</em>' attribute.
	 * @see #isSet()
	 * @generated
	 */
	void setSet(boolean value);

	/**
	 * Returns the value of the '<em><b>Expr</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expr</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expr</em>' containment reference.
	 * @see #setExpr(Expr)
	 * @see cgimodel.CgimodelPackage#getState_Expr()
	 * @model containment="true"
	 * @generated
	 */
	Expr getExpr();

	/**
	 * Sets the value of the '{@link cgimodel.State#getExpr <em>Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expr</em>' containment reference.
	 * @see #getExpr()
	 * @generated
	 */
	void setExpr(Expr value);

} // State
