package at.jku.ssw.stategraph.testmodel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cgimodel.BaseState;
import cgimodel.CgimodelFactory;
import cgimodel.Expr;
import cgimodel.OrState;
import cgimodel.State;
import cgimodel.StateModel;
import cgimodel.StateModels;
import cgimodel.Transition;

public class ModelBuilder {

	public static StateModelNode buildModel(InputStream s) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(s);
			Element topElem = doc.getDocumentElement();
			StateModels stateModels = CgimodelFactory.eINSTANCE.createStateModels();
			
			// iterate over statemodel nodes
			List<Object> l = collectStates(topElem);	
			stateModels.getStateModels().addAll((Collection<? extends StateModel>) l);
			StateModelNode root = createStateModelNodesRec(stateModels, null);
			setTransitions(root, stateModels);
			return root;
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	private static void setTransitions(StateModelNode root, Object parent) {
		if(parent instanceof StateModels)  {
			StateModels sms = (StateModels) parent;
			for(int i = 0; i < sms.getStateModels().size(); i++) {
				StateModel sm = sms.getStateModels().get(i);
				setTransitions(root, sm);
			}
		}
		if(parent instanceof StateModel) {
			// set transitions here
			StateModel sm = (StateModel) parent;
			for(Transition t : sm.getTransitions()) {
				State source = (State) t.getSource();
				State target = (State) t.getTarget();
				Expr condition = t.getCondition();
				StateModelNode smnSource = StateModelNodeImpl.findNode(root, source.getName());
				StateModelNode smnTarget = StateModelNodeImpl.findNode(root, target.getName());
				if(smnSource == null || smnTarget == null)
					System.out.println("Transition is not valid");
				else {
					int index = smnSource.setTarget(smnTarget);
					if(condition != null)
						smnSource.setTransitionName(condition.getValue(), index);
					smnTarget.setSource(smnSource);
				}
			}
		}
		
	}

	private static StateModelNode createStateModelNodesRec(Object parent, StateModelNode parentNode) {
		if(parent instanceof StateModels)  {
			StateModels sms = (StateModels) parent;
			StateModelNodeImpl smsNode = new StateModelNodeImpl("", null);
			for(int i = 0; i < sms.getStateModels().size(); i++) {
				StateModel sm = sms.getStateModels().get(i);
				StateModelNodeImpl smNode = new StateModelNodeImpl(sm.getName(), smsNode);
				smsNode.addChild(smNode);
				createStateModelNodesRec(sm, smNode);
			}
			return smsNode;
		}
		else if(parent instanceof StateModel) {
			StateModel sm = (StateModel) parent;
			for(int i = 0; i < sm.getStates().size(); i++) {
				BaseState bs = sm.getStates().get(i);
				StateModelNodeImpl bsNode = new StateModelNodeImpl(bs.getName(), parentNode);
				((StateModelNodeImpl)parentNode).addChild(bsNode);
				createStateModelNodesRec(bs, bsNode);
			}
		}
		else if(parent instanceof OrState) {
			// create new relation
			OrState orstate = (OrState) parent;
			for(int i = 0; i < orstate.getStates().size(); i++) {
				BaseState bs = orstate.getStates().get(i);
				StateModelNodeImpl bsNode = new StateModelNodeImpl(bs.getName(), parentNode);
				((StateModelNodeImpl)parentNode).addChild(bsNode);
				createStateModelNodesRec(bs, bsNode);
			}
		}
		return null;
	}
	
	private static List<Object> collectStates(Element parent) {
		List<Object> stateList = new ArrayList<Object>();
		NodeList nodeList = parent.getChildNodes();
		for(int i = 1; i < nodeList.getLength(); i += 2) {
			if(!(nodeList.item(i) instanceof Element))
				continue;
			Element element = (Element)nodeList.item(i);
			List<Object> childList = collectStates(element);
			if(element.getNodeName().equals("State")) {
				State st = CgimodelFactory.eINSTANCE.createState();
				st.setName(element.getAttribute("name"));
				stateList.add(st);
				
				// set expression
				if(childList.size() != 0) {
					Expr expr = CgimodelFactory.eINSTANCE.createExpr();
					expr.setValue((String)childList.get(0));
					st.setExpr(expr);
				}
			}
			else if(element.getNodeName().equals("OrState")) {
				OrState orst = CgimodelFactory.eINSTANCE.createOrState();
				orst.setName(element.getAttribute("name"));
				orst.getStates().addAll((Collection<? extends BaseState>) childList);
				stateList.add(orst);
			}
			else if(element.getNodeName().equals("Statemodel")) {
				StateModel sm = CgimodelFactory.eINSTANCE.createStateModel();
				sm.setName(element.getAttribute("name"));
				for(Object o : childList) {
					if(o instanceof BaseState)
						sm.getStates().add((BaseState) o);
					else if(o instanceof Transition)
						sm.getTransitions().add((Transition) o);
				}
				stateList.add(sm);
			}
			else if(element.getNodeName().equals("expr")) {
				stateList.add(element.getAttribute("value"));
			}
			else if(element.getNodeName().equals("source")) {
				stateList.add(childList.get(0));
			}
			else if(element.getNodeName().equals("target")) {
				stateList.add(childList.get(0));
			}
			else if(element.getNodeName().equals("Transition")) {
				Transition t = CgimodelFactory.eINSTANCE.createTransition();
				t.setSource((State) childList.get(0));
				t.setTarget((State) childList.get(1));
				if(childList.size() > 2) {
					Expr expr = CgimodelFactory.eINSTANCE.createExpr();
					expr.setValue((String)childList.get(2));
					t.setCondition(expr);
				}
				stateList.add(t);
			}
			else if(element.getNodeName().equals("condition")) {
				stateList.add(element.getAttribute("value"));
			}
		}
		return stateList;
	}
}
