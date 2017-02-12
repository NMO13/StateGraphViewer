/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package cgimodel.impl;

import cgimodel.CgimodelPackage;
import cgimodel.StateModel;
import cgimodel.StateModels;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>State Models</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link cgimodel.impl.StateModelsImpl#getStateModels <em>State Models</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StateModelsImpl extends EObjectImpl implements StateModels {
	/**
	 * The cached value of the '{@link #getStateModels() <em>State Models</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStateModels()
	 * @generated
	 * @ordered
	 */
	protected EList<StateModel> stateModels;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StateModelsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CgimodelPackage.Literals.STATE_MODELS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StateModel> getStateModels() {
		if (stateModels == null) {
			stateModels = new EObjectContainmentEList<StateModel>(StateModel.class, this, CgimodelPackage.STATE_MODELS__STATE_MODELS);
		}
		return stateModels;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CgimodelPackage.STATE_MODELS__STATE_MODELS:
				return ((InternalEList<?>)getStateModels()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CgimodelPackage.STATE_MODELS__STATE_MODELS:
				return getStateModels();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CgimodelPackage.STATE_MODELS__STATE_MODELS:
				getStateModels().clear();
				getStateModels().addAll((Collection<? extends StateModel>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CgimodelPackage.STATE_MODELS__STATE_MODELS:
				getStateModels().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CgimodelPackage.STATE_MODELS__STATE_MODELS:
				return stateModels != null && !stateModels.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //StateModelsImpl
