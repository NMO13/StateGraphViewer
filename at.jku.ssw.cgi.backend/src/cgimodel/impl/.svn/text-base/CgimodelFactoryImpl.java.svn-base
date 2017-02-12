/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package cgimodel.impl;

import cgimodel.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CgimodelFactoryImpl extends EFactoryImpl implements CgimodelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CgimodelFactory init() {
		try {
			CgimodelFactory theCgimodelFactory = (CgimodelFactory)EPackage.Registry.INSTANCE.getEFactory("http://Cgimodel/1.0"); 
			if (theCgimodelFactory != null) {
				return theCgimodelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CgimodelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CgimodelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CgimodelPackage.STATE_MODEL: return createStateModel();
			case CgimodelPackage.STATE: return createState();
			case CgimodelPackage.OR_STATE: return createOrState();
			case CgimodelPackage.TRANSITION: return createTransition();
			case CgimodelPackage.EXPR: return createExpr();
			case CgimodelPackage.STATE_MODELS: return createStateModels();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateModel createStateModel() {
		StateModelImpl stateModel = new StateModelImpl();
		return stateModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State createState() {
		StateImpl state = new StateImpl();
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrState createOrState() {
		OrStateImpl orState = new OrStateImpl();
		return orState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Transition createTransition() {
		TransitionImpl transition = new TransitionImpl();
		return transition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expr createExpr() {
		ExprImpl expr = new ExprImpl();
		return expr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateModels createStateModels() {
		StateModelsImpl stateModels = new StateModelsImpl();
		return stateModels;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CgimodelPackage getCgimodelPackage() {
		return (CgimodelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CgimodelPackage getPackage() {
		return CgimodelPackage.eINSTANCE;
	}

} //CgimodelFactoryImpl
