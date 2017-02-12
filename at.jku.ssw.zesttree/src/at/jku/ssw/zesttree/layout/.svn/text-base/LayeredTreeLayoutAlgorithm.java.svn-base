/*******************************************************************************
 * Copyright 2005, CHISEL Group, University of Victoria, Victoria, BC, Canada.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     The Chisel Group, University of Victoria
 *******************************************************************************/
package at.jku.ssw.zesttree.layout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.AbstractLayoutAlgorithm;
import org.eclipse.zest.layouts.dataStructures.DisplayIndependentPoint;
import org.eclipse.zest.layouts.dataStructures.DisplayIndependentRectangle;
import org.eclipse.zest.layouts.dataStructures.InternalNode;
import org.eclipse.zest.layouts.dataStructures.InternalRelationship;
import org.eclipse.zest.layouts.exampleStructures.SimpleRelationship;

/**
 * The LayeredTreeLayoutAlgorithm class implements a simple algorithm to arrange
 * graph nodes in a layered vertical tree-like layout.
 * 
 * @version 3.0
 * @author Kölbleitner Florian (version 2.0 by Casey Best and Rob Lintern,
 *         version 1.0 by Jingwei Wu)
 */
public class LayeredTreeLayoutAlgorithm extends AbstractLayoutAlgorithm {

	private final static double DEFAULT_WEIGHT = 0;
	private final static boolean DEFAULT_MARKED = false;

	private final static boolean AS_DESTINATION = false;
	private final static boolean AS_SOURCE = true;

	private final static int NUM_DESCENDENTS_INDEX = 0;
	private final static int NUM_LEVELS_INDEX = 1;

	private ArrayList<InternalNode> treeRoots;

	private double boundsX;
	private double boundsY;
	private double boundsWidth;
	private double boundsHeight;
	private DisplayIndependentRectangle layoutBounds = null;

	private List<InternalNode>[] parentLists;
	private List<InternalNode>[] childrenLists;
	private double[] weights;
	private boolean[] markedArr;

	// ==== NEW FIELDS FOR LAYERING ============================================

	//the minimal distance between any two nodes
	private final static double MIN_NODE_GAP = 5;

	//the maximum movement which is not considered as regular shifting
	private final static double MAX_JITTER = 1;

	//the standard gap between two nodes one level apart
	private final static double STANDARD_LEVEL_GAP = 30;

	//the multiplier applied to the minimal distance of weakly related nodes 
	private final static double RELATION_LEVEL_MULT = 1;

	// the index height from where to start the y-coordinate calculation
	private final static double INDEX_HEIGHT = 20;

	// A List containing all nodes' info sorted first by level, then by count
	private List<LvlCtInfo> sortedNodes;

	// A List of leaf nodes
	private List<LvlCtInfo> leaves;

	// the maximal depth of the tree (is only known after the first run)
	private double maxLevel;

	// ==== NEW FIELDS FOR HOLDING THE INFO ====================================

	private Map<InternalNode, List<LvlCtInfo>> parentsMap;
	private Map<InternalNode, List<LvlCtInfo>> childrenMap;

	private Map<String, DisplayIndependentPoint> lastLocations;

	// ==== NEW FIELDS FOR LEAFING ==================================

	private boolean leafingStyle = false;

	// a mainChild is a child directly under the root
	private int mainChildrenSize = 0;
	private int indexOfCurMainChild = 0;

	// ///////////////////////////////////////////////////////////////////////
	// /// Constructors /////
	// ///////////////////////////////////////////////////////////////////////

	/**
	 * Constructs a new TreeLayoutAlgorithm object, also setting the view style
	 * which can be either normal (false) or leafing (true). Leafing means that
	 * only one subtree is fully visible, the others are grouped
	 */
	public LayeredTreeLayoutAlgorithm(int styles, boolean leafingStyle) {

		super(styles);
		this.leafingStyle = leafingStyle;
		lastLocations = new HashMap<String, DisplayIndependentPoint>();
		parentsMap = new HashMap<InternalNode, List<LvlCtInfo>>();
		childrenMap = new HashMap<InternalNode, List<LvlCtInfo>>();
	}

	/**
	 * Constructs a new TreeLayoutAlgorithm object.
	 */
	public LayeredTreeLayoutAlgorithm(int styles) {

		this(styles, false);
	}

	/**
	 * Tree layout algorithm Constructor with NO Style
	 * 
	 */
	public LayeredTreeLayoutAlgorithm() {

		this(LayoutStyles.NONE);
	}

	// ///////////////////////////////////////////////////////////////////////
	// /// Public Methods /////
	// ///////////////////////////////////////////////////////////////////////

	public void setLayoutArea(double x, double y, double width, double height) {

		throw new RuntimeException();
	}

	protected int getCurrentLayoutStep() {

		return 0;
	}

	protected int getTotalNumberOfLayoutSteps() {

		return 5;
	}

	/**
	 * Executes this TreeLayoutAlgorithm layout algorithm by referencing the
	 * data stored in the repository system. Once done, the result will be saved
	 * to the data repository.
	 * 
	 * @param entitiesToLayout Apply the algorithm to these entities
	 * @param relationshipsToConsider Only consider these relationships when
	 *        applying the algorithm.
	 * @param boundsX The left side of the bounds in which the layout can place
	 *        the entities.
	 * @param boundsY The top side of the bounds in which the layout can place
	 *        the entities.
	 * @param boundsWidth The width of the bounds in which the layout can place
	 *        the entities.
	 * @param boundsHeight The height of the bounds in which the layout can
	 *        place the entities.
	 * @throws RuntimeException Thrown if entitiesToLayout doesn't contain all
	 *         of the endpoints for each relationship in relationshipsToConsider
	 */
	protected void preLayoutAlgorithm(InternalNode[] entitiesToLayout,
			InternalRelationship[] relationshipsToConsider, double x, double y,
			double width, double height) {

		// Filter unwanted entities and relationships
		// super.applyLayout (entitiesToLayout, relationshipsToConsider,
		// boundsX,
		// boundsY, boundsWidth, boundsHeight);

		parentLists = new List[entitiesToLayout.length];
		childrenLists = new List[entitiesToLayout.length];
		weights = new double[entitiesToLayout.length];
		markedArr = new boolean[entitiesToLayout.length];
		for(int i = 0; i < entitiesToLayout.length; i++) {
			parentLists[i] = new ArrayList<InternalNode>();
			childrenLists[i] = new ArrayList<InternalNode>();
			weights[i] = DEFAULT_WEIGHT;
			markedArr[i] = DEFAULT_MARKED;
		}

		this.boundsHeight = height;
		this.boundsWidth = width;
		this.boundsX = x;
		this.boundsY = y;
		layoutBounds = new DisplayIndependentRectangle(boundsX, boundsY,
				boundsWidth, boundsHeight);
	}

	protected void applyLayoutInternal(InternalNode[] entitiesToLayout,
			InternalRelationship[] relationshipsToConsider, double boundsX,
			double boundsY, double boundsWidth, double boundsHeight) {

		if(entitiesToLayout.length > 0) {
			int totalProgress = 4;
			fireProgressEvent(1, totalProgress);

			leaves = new ArrayList<LvlCtInfo>();
			sortedNodes = new ArrayList<LvlCtInfo>();
			maxLevel = 0;

			treeRoots = new ArrayList<InternalNode>();
			buildForest(treeRoots, entitiesToLayout, relationshipsToConsider);

			fireProgressEvent(2, totalProgress);

			computePositions(treeRoots, entitiesToLayout);

			defaultFitWithinBounds(entitiesToLayout, layoutBounds);

			fireProgressEvent(3, totalProgress);

			//new parts ===============================================
			if(entitiesToLayout.length > 1) {
				doLayering(entitiesToLayout);
			}

			adjustHeights(entitiesToLayout);

			centerTree(entitiesToLayout, boundsWidth);

			dejitter();

			if(leafingStyle) {
				doLeafing(entitiesToLayout, treeRoots);
			}

			fireProgressEvent(4, totalProgress);
		}
	}

	protected void postLayoutAlgorithm(InternalNode[] entitiesToLayout,
			InternalRelationship[] relationshipsToConsider) {

		updateLayoutLocations(entitiesToLayout);

		for(LvlCtInfo info : sortedNodes) {
			lastLocations.put(info.node.toString(),
					new DisplayIndependentPoint(info.node.getInternalX(),
							info.node.getInternalY()));
		}

		fireProgressEvent(5, 5);
	}

	/**
	 * Returns the last found roots
	 */
	public List<InternalNode> getRoots() {

		return treeRoots;
	}

	/**
	 * Finds all the relationships in which the node <code>obj<code>
	 * plays the specified <code>role</code>.
	 * 
	 * @param entity The node that concerns the relations to be found.
	 * @param role The role played by the <code>obj</code>. Its type must be of
	 *        <code>ACTOR_ROLE</code> or <code>ACTEE_ROLE</code>.
	 * @see SimpleRelationship
	 */
	private Collection<InternalRelationship> findRelationships(Object entity,
			boolean objectAsSource,
			InternalRelationship[] relationshipsToConsider) {

		Collection<InternalRelationship> foundRels = new ArrayList<InternalRelationship>();
		for(int i = 0; i < relationshipsToConsider.length; i++) {
			InternalRelationship rel = relationshipsToConsider[i];
			if(objectAsSource && rel.getSource().equals(entity)) {
				foundRels.add(rel);
			} else if(!objectAsSource && rel.getDestination().equals(entity)) {
				foundRels.add(rel);
			}
		}
		return foundRels;
	}

	/**
	 * Finds the relation that has the lowest index in the relation repository
	 * in which the node <code>obj<code> plays the specified
	 * <code>role</code>.
	 * 
	 * @param obj The node that concerns the relations to be found.
	 * @param role The role played by the <code>obj</code>. Its type must be of
	 *        <code>ACTOR_ROLE</code> or <code>ACTEE_ROLE</code>.
	 * @see SimpleRelationship
	 * @see SimpleRelationship#ACTOR_ROLE
	 * @see SimpleRelationship#ACTEE_ROLE
	 */
	private InternalRelationship findRelationship(Object entity,
			boolean objectAsSource,
			InternalRelationship[] relationshipsToConsider) {

		InternalRelationship relationship = null;
		for(int i = 0; i < relationshipsToConsider.length
				&& relationship == null; i++) {
			InternalRelationship possibleRel = relationshipsToConsider[i];
			if(objectAsSource && possibleRel.getSource().equals(entity)) {
				relationship = possibleRel;
			} else if(!objectAsSource
					&& possibleRel.getDestination().equals(entity)) {
				relationship = possibleRel;
			}
		}
		return relationship;
	}

	// ///////////////////////////////////////////////////////////////////////
	// /// Private Methods /////
	// ///////////////////////////////////////////////////////////////////////

	/**
	 * Builds the tree forest that is used to calculate positions for each node
	 * in this TreeLayoutAlgorithm.
	 */
	private void buildForest(List<InternalNode> roots, InternalNode[] entities,
			InternalRelationship[] relationships) {

		List<InternalNode> unplacedEntities = new ArrayList<InternalNode>(
				Arrays.asList(entities));
		buildForestRecursively(roots, unplacedEntities, entities, relationships);
	}

	/**
	 * Builds the forest recursively. All entities will be placed somewhere in
	 * the forest.
	 */
	private void buildForestRecursively(List<InternalNode> roots,
			List<InternalNode> unplacedEntities, InternalNode[] entities,
			InternalRelationship[] relationships) {

		if(unplacedEntities.size() == 0) {
			return; // no more entities to place
		}

		// get the first entity in the list of unplaced entities, find its root,
		// and build this root's tree
		InternalNode layoutEntity = unplacedEntities.get(0);
		InternalNode rootEntity = findRootObjectRecursive(layoutEntity,
				new HashSet<InternalNode>(), relationships);
		int rootEntityIndex = indexOfInternalNode(entities, rootEntity);
		buildTreeRecursively(rootEntity, rootEntityIndex, 0, entities,
				relationships);
		roots.add(rootEntity);

		// now see which nodes are left to be placed in a tree somewhere
		List<InternalNode> unmarkedCopy = new ArrayList<InternalNode>(
				unplacedEntities);
		for(InternalNode tmpEntity : unmarkedCopy) {
			int tmpEntityIndex = indexOfInternalNode(entities, tmpEntity);
			boolean isMarked = markedArr[tmpEntityIndex];
			if(isMarked) {
				unplacedEntities.remove(tmpEntity);
			}
		}
		buildForestRecursively(roots, unplacedEntities, entities, relationships);
	}

	/**
	 * Finds the root node that can be treated as the root of a tree. The found
	 * root node should be one of the unmarked nodes.
	 */
	private InternalNode findRootObjectRecursive(InternalNode currentEntity,
			Set<InternalNode> seenAlready,
			InternalRelationship[] relationshipsToConsider) {

		InternalNode rootEntity = null;
		InternalRelationship rel = findRelationship(currentEntity,
				AS_DESTINATION, relationshipsToConsider);
		if(rel == null) {
			rootEntity = currentEntity;
		} else {
			InternalNode parentEntity = rel.getSource();
			if(!seenAlready.contains(parentEntity)) {
				seenAlready.add(parentEntity);
				rootEntity = findRootObjectRecursive(parentEntity, seenAlready,
						relationshipsToConsider);
			} else {
				rootEntity = currentEntity;
			}
		}
		return rootEntity;
	}

	/**
	 * Builds a tree of the passed in entity. The entity will pass a weight
	 * value to all of its children recursively.
	 */
	private void buildTreeRecursively(InternalNode layoutEntity, int i,
			double weight, InternalNode[] entities,
			final InternalRelationship[] relationships) {

		// No need to do further computation!
		if(layoutEntity == null) {
			return;
		}

		LvlCtInfo entityInfo = new LvlCtInfo(layoutEntity, weight, i);

		// A marked entity means that it has been added to the
		// forest, and its weight value needs to be modified.
		if(markedArr[i]) {
			// modifyWeightRecursively(layoutEntity, i, weight, new HashSet(),
			// entities, relationships);
			return; // No need to do further computation.
		}

		// Mark this entity, set its weight value and create a new tree node.
		markedArr[i] = true;
		weights[i] = weight;

		// collect the children of this entity and put them in order
		Collection<InternalRelationship> rels = findRelationships(layoutEntity,
				AS_SOURCE, relationships);
		List<InternalNode> children = new ArrayList<InternalNode>();
		for(InternalRelationship layoutRel : rels) {
			InternalNode childEntity = layoutRel.getDestination();
			children.add(childEntity);
		}
		if(comparator != null) {
			// TODO Elements must implement comparable!!
			Collections.sort(children, comparator);
		} else {
			// TODO added a new sorting method: simply compare elems
			Collections.sort(children, new Comparator<Object>() {

				public int compare(Object o1, Object o2) {

					return ((Comparable<Comparable>) o1)
							.compareTo((Comparable) o2);
				}
			});
		}

		List<LvlCtInfo> chInfo = new ArrayList<LvlCtInfo>();

		// map children to this parent, and vice versa
		for(InternalNode childEntity : children) {
			int childEntityIndex = indexOfInternalNode(entities, childEntity);
			if(!childrenLists[i].contains(childEntity)) {
				childrenLists[i].add(childEntity);
			}

			LvlCtInfo ch = new LvlCtInfo(childEntity, weight + 1,
					childEntityIndex);
			chInfo.add(ch);
			parentsMap.put(childEntity, Collections.singletonList(entityInfo));

			if(!parentLists[childEntityIndex].contains(layoutEntity)) {
				parentLists[childEntityIndex].add(layoutEntity);
			}
		}

		childrenMap.put(layoutEntity, chInfo);

		for(InternalNode childEntity : children) {
			int childEntityIndex = indexOfInternalNode(entities, childEntity);
			buildTreeRecursively(childEntity, childEntityIndex, weight + 1,
					entities, relationships);
		}
	}

	// private int getNumChildren(InternalNode layoutEntity,
	// InternalRelationship[] relationships) {
	//
	// return findRelationships(layoutEntity, AS_SOURCE, relationships).size();
	// }
	//
	// private void getNumDescendentsAndLevel(InternalNode layoutEntity,
	// InternalRelationship[] relationships, int[] numDescendentsAndLevel) {
	//
	// getNumDescendentsAndLevelRecursive(layoutEntity, relationships,
	// new HashSet<InternalNode>(), numDescendentsAndLevel, 0);
	// }
	//
	// private void getNumDescendentsAndLevelRecursive(InternalNode
	// layoutEntity,
	// InternalRelationship[] relationships,
	// Set<InternalNode> seenAlready, int[] numDescendentsAndLevel,
	// int currentLevel) {
	//
	// if (seenAlready.contains(layoutEntity)) {
	// return;
	// }
	// seenAlready.add(layoutEntity);
	// numDescendentsAndLevel[NUM_LEVELS_INDEX] = Math.max(
	// numDescendentsAndLevel[NUM_LEVELS_INDEX], currentLevel);
	// Collection rels = findRelationships(layoutEntity, AS_SOURCE,
	// relationships);
	// for (Iterator iter = rels.iterator(); iter.hasNext();) {
	// InternalRelationship layoutRel = (InternalRelationship) iter.next();
	// InternalNode childEntity = layoutRel.getDestination();
	// numDescendentsAndLevel[NUM_DESCENDENTS_INDEX]++;
	// getNumDescendentsAndLevelRecursive(childEntity, relationships,
	// seenAlready, numDescendentsAndLevel, currentLevel + 1);
	//
	// }
	// }

	// /**
	// * Modifies the weight value of the marked node recursively.
	// */
	// private void modifyWeightRecursively(InternalNode layoutEntity, int i,
	// double weight, Set descendentsSeenSoFar, InternalNode [] entities,
	// InternalRelationship [] relationships) {
	// // No need to do further computation!
	// if (layoutEntity == null) {
	// return;
	// }
	//
	// if (descendentsSeenSoFar.contains(layoutEntity)) {
	// return; //No need to do further computation.
	// }
	//		
	// descendentsSeenSoFar.add(layoutEntity);
	// // No need to do further computation!
	// if (weight < weights[i]) {
	// return;
	// }
	//
	// weights[i] = weight;
	// Collection rels = findRelationships(layoutEntity, AS_SOURCE,
	// relationships);
	//		
	//		
	// for (Iterator iter = rels.iterator(); iter.hasNext();) {
	// InternalRelationship tmpRel = (InternalRelationship) iter.next();
	// InternalNode tmpEntity = tmpRel.getDestination();
	// int tmpEntityIndex = indexOfInternalNode(entities, tmpEntity);
	// modifyWeightRecursively(tmpEntity, tmpEntityIndex, weight + 1,
	// descendentsSeenSoFar, entities, relationships);
	// }
	// }

	/**
	 * Gets the maxium weight of a tree in the forest of this
	 * TreeLayoutAlgorithm.
	 */
	private double getMaxiumWeightRecursive(InternalNode layoutEntity, int i,
			Set<InternalNode> seenAlready, InternalNode[] entities) {

		double result = 0;
		if(seenAlready.contains(layoutEntity)) {
			return result;
		}
		seenAlready.add(layoutEntity);
		List<InternalNode> children = childrenLists[i];
		if(children.isEmpty()) {
			result = weights[i];
		} else {
			// TODO: SLOW
			for(InternalNode childEntity : children) {
				int childEntityIndex = indexOfInternalNode(entities,
						childEntity);
				result = Math.max(result, getMaxiumWeightRecursive(childEntity,
						childEntityIndex, seenAlready, entities));
			}
		}
		return result;
	}

	/**
	 * Computes positions for each node in this TreeLayoutAlgorithm by
	 * referencing the forest that holds those nodes.
	 */
	private void computePositions(List<InternalNode> roots,
			InternalNode[] entities) {

		// No need to do further computation!
		if(roots.size() == 0) {
			return;
		}

		int totalLeafCount = 0;
		double maxWeight = 0;
		for(int i = 0; i < roots.size(); i++) {
			InternalNode rootEntity = roots.get(i);
			int rootEntityIndex = indexOfInternalNode(entities, rootEntity);
			totalLeafCount = totalLeafCount
					+ getNumberOfLeaves(rootEntity, rootEntityIndex, entities);
			maxWeight = Math.max(maxWeight, getMaxiumWeightRecursive(
					rootEntity, rootEntityIndex, new HashSet<InternalNode>(),
					entities) + 1.0);
		}

		double width = 1.0 / totalLeafCount;
		double height = 1.0 / maxWeight;

		int leafCountSoFar = 0;

		// TODO: SLOW!
		for(int i = 0; i < roots.size(); i++) {
			InternalNode rootEntity = roots.get(i);
			int rootEntityIndex = indexOfInternalNode(entities, rootEntity);
			computePositionRecursively(rootEntity, rootEntityIndex,
					leafCountSoFar, width, height, new HashSet<InternalNode>(),
					entities);
			leafCountSoFar = leafCountSoFar
					+ getNumberOfLeaves(rootEntity, rootEntityIndex, entities);
		}
	}

	/**
	 * Computes positions recursively until the leaf nodes are reached.
	 */
	private void computePositionRecursively(InternalNode layoutEntity, int i,
			int relativePosition, double width, double height,
			Set<InternalNode> seenAlready, InternalNode[] entities) {

		if(seenAlready.contains(layoutEntity)) {
			return;
		}

		seenAlready.add(layoutEntity);
		double level = getLevel(layoutEntity, i, entities);

		if(level > maxLevel) {
			maxLevel = level;
		}
		int breadth = getNumberOfLeaves(layoutEntity, i, entities);

		// breadth = number of reserved "columns"

		double absHPosition = relativePosition + breadth / 2.0;
		double absVPosition = (level + 0.5);

		double posx = absHPosition;
		double posy = absVPosition * height;

		double weight = weights[i];
		posy = posy + height * (weight - level);

		layoutEntity.setInternalLocation(posx, posy);

		//addition to sortedNodes
		LvlCtInfo actInfo = new LvlCtInfo(layoutEntity, level, i);
		sortedNodes.add(actInfo);

		int relativeCount = 0;
		List<InternalNode> children = childrenLists[i];

		//addition to leaves
		if(children.isEmpty()) {
			leaves.add(actInfo);
		}
		// TODO: Slow
		for(InternalNode childEntity : children) {

			int childEntityIndex = indexOfInternalNode(entities, childEntity);

			computePositionRecursively(childEntity, childEntityIndex,
					relativePosition + relativeCount, width, height,
					seenAlready, entities);
			relativeCount = relativeCount
					+ getNumberOfLeaves(childEntity, childEntityIndex, entities);
		}

	}

	private int getNumberOfLeaves(InternalNode layoutEntity, int i,
			InternalNode[] entities) {

		return getNumberOfLeavesRecursive(layoutEntity, i,
				new HashSet<InternalNode>(), entities);
	}

	private int getNumberOfLeavesRecursive(InternalNode layoutEntity, int i,
			Set<InternalNode> seen, InternalNode[] entities) {

		int numLeaves = 0;
		List<InternalNode> children = childrenLists[i];
		if(children.size() == 0) {
			numLeaves = 1;
		} else {
			// TODO: SLOW!
			for(InternalNode childEntity : children) {
				if(!seen.contains(childEntity)) {
					seen.add(childEntity);
					int childEntityIndex = indexOfInternalNode(entities,
							childEntity);
					numLeaves += getNumberOfLeavesRecursive(childEntity,
							childEntityIndex, seen, entities);
				} else {
					numLeaves = 1;
				}
			}
		}
		return numLeaves;
	}

	private int getLevel(InternalNode layoutEntity, int i,
			InternalNode[] entities) {

		return getLevelRecursive(layoutEntity, i, new HashSet<InternalNode>(),
				entities);
	}

	private int getLevelRecursive(InternalNode layoutEntity, int i,
			Set<InternalNode> seen, InternalNode[] entities) {

		if(seen.contains(layoutEntity)) {
			return 0;
		}
		seen.add(layoutEntity);
		List<InternalNode> parents = parentLists[i];
		int maxParentLevel = 0;
		for(InternalNode parentEntity : parents) {
			int parentEntityIndex = indexOfInternalNode(entities, parentEntity);
			int parentLevel = getLevelRecursive(parentEntity,
					parentEntityIndex, seen, entities) + 1;
			maxParentLevel = Math.max(maxParentLevel, parentLevel);
		}
		return maxParentLevel;
	}

	/**
	 * Note: Use this as little as possible! TODO limit the use of this method
	 * 
	 * @param nodes
	 * @param nodeToFind
	 * @return
	 */
	private int indexOfInternalNode(InternalNode[] nodes,
			InternalNode nodeToFind) {

		for(int i = 0; i < nodes.length; i++) {
			InternalNode node = nodes[i];
			if(node.equals(nodeToFind)) {
				return i;
			}
		}
		throw new RuntimeException("Couldn't find index of internal node: "
				+ nodeToFind);
	}

	protected boolean isValidConfiguration(boolean asynchronous,
			boolean continueous) {

		return !continueous;
	}

	// ===================== LAYERING ALGORITHM METHODS ========================

	/**
	 * Applies a layering algorithm to minimize the space usage of the tree <BR>
	 * <BR>
	 * N = actual leaf<BR>
	 * L, R = Left and Right subtree neighbors of N<BR>
	 * <BR>
	 * 1.) take the N with highest level and lowest count -> lowest left node<BR>
	 * 2.) identify L and R, if existing<BR>
	 * 3.) calculate the minimal distance L and R must have<BR>
	 * 4.) shift N and R, so R is at minimal distance to L with N in the center<BR>
	 * 5.) shift the upper right rest of the tree to fill the new space<BR>
	 * 6.) repeat 1.) with all other leaves<BR>
	 * 
	 * @param entitiesToLayout An Array of all considered nodes
	 */
	private void doLayering(InternalNode[] entitiesToLayout) {

		// sort the lists
		prepareDataStructure();

		// each nonbottom-leaf's free space gets used up, if possible ==========

		for(LvlCtInfo nInfo : leaves) {

			int indexOfN = getIndex(sortedNodes, nInfo.node);
			InternalNode n = nInfo.node;

			LvlCtInfo lInfo = null, rInfo = null;
			List<Double> rVals = new ArrayList<Double>();
			List<Double> lVals = new ArrayList<Double>();

			// get left- and rightside x-vals for each level
			if(indexOfN > 0
					&& sortedNodes.get(indexOfN - 1).level == nInfo.level) {
				lInfo = sortedNodes.get(indexOfN - 1);
			}
			if(indexOfN < entitiesToLayout.length - 1
					&& sortedNodes.get(indexOfN + 1).level == nInfo.level) {
				rInfo = sortedNodes.get(indexOfN + 1);
			}

// calculate the optimal distance for the L and R subtree and perform shift ====

			// newPos = new X value of shifted node
			// nShift = shift of N, rShift = shift of R
			double newPos = 0, nShift = 0, rShift = 0;

			// shift the middle and right node to match the new distances
			if(lInfo != null && rInfo != null) {

				lVals = getSideXVals(lInfo, entitiesToLayout, true);
				rVals = getSideXVals(rInfo, entitiesToLayout, false);

				// calculate the minimal possible distance of R and L
				double minDist = calcMinDist(n, lVals, rVals);

				newPos = lVals.get(0) + minDist / 2 - getLayoutWidth(n) / 2
						+ calcRelationDist(nInfo, lInfo, entitiesToLayout);
				nShift = newPos - n.getInternalX();

				shiftNodeX(n, nShift, entitiesToLayout);

				newPos = n.getInternalX() + getLayoutWidth(n) / 2 + minDist / 2
						+ calcRelationDist(nInfo, rInfo, entitiesToLayout);
				rShift = newPos - rInfo.node.getInternalX();

				shiftNodeX(rInfo.node, rShift, entitiesToLayout);

			} else if(lInfo != null && rInfo == null) {

				newPos = lInfo.node.getInternalX() + getLayoutWidth(lInfo.node)
						+ MIN_NODE_GAP
						+ calcRelationDist(nInfo, lInfo, entitiesToLayout);
				nShift = newPos - n.getInternalX();
				shiftNodeX(n, nShift, entitiesToLayout);

			} else if(lInfo == null && rInfo != null) {

				newPos = n.getInternalX() + getLayoutWidth(n) + MIN_NODE_GAP
						+ calcRelationDist(nInfo, rInfo, entitiesToLayout);
				rShift = newPos - rInfo.node.getInternalX();
				shiftNodeX(rInfo.node, rShift, entitiesToLayout);
			}

// shift the rest of the tree to use the free space ============================

			// if N is the last node, there is nothing to shift
			if(rInfo != null) {
				InternalNode nParent = getParent(n, entitiesToLayout);
				InternalNode rParent = getParent(rInfo.node, entitiesToLayout);
				if(nParent == rParent) {
					shiftRestTree(entitiesToLayout, rShift, rInfo.node);
				} else {
					InternalNode stopParent = getFirstSameParent(n, rInfo.node,
							entitiesToLayout);
					shiftOnlyOwnParents(entitiesToLayout, stopParent, nShift, n);
					shiftRestTree(entitiesToLayout, rShift, rInfo.node);
				}
			} else {
				if(lInfo != null) {
					InternalNode stopParent = getFirstSameParent(n, lInfo.node,
							entitiesToLayout);
					shiftOnlyOwnParents(entitiesToLayout, stopParent, nShift, n);
				} else {
					shiftOnlyOwnParents(entitiesToLayout, treeRoots.get(0),
							nShift, n);
				}
			}
		}// for
	}

	private double calcRelationDist(LvlCtInfo i1, LvlCtInfo i2,
			InternalNode[] entitiesToLayout) {

		InternalNode sameP = getFirstSameParent(i1.node, i2.node,
				entitiesToLayout);
		double relLevel = i1.level - getInfo(sortedNodes, sameP).level - 1;
		double newDist = MIN_NODE_GAP * RELATION_LEVEL_MULT * relLevel;
		return newDist;
	}

// ==== PRE-LAYERING TASKS =====================================================

	private void prepareDataStructure() {

		// sort the sortedNodes list first by level, then by count
		Collections.sort(sortedNodes, new Comparator<LvlCtInfo>() {

			@Override
			public int compare(LvlCtInfo a, LvlCtInfo b) {

				if(a.level == b.level) {
					return a.count - b.count;
				} else {
					return (int) (b.level - a.level);
				}
			}
		});

		// sort the nonbottom leaves the same way
		Collections.sort(leaves, new Comparator<LvlCtInfo>() {

			@Override
			public int compare(LvlCtInfo n0, LvlCtInfo n1) {

				if(n0.level == n1.level) {
					return n0.count - n1.count;
				} else {
					return (int) (n1.level - n0.level);
				}
			}
		});
	}

// ==== METHODS FOR L, N and R NODE SHIFTING ===================================

	/**
	 * Gets all left- or rightmost X-Values next to N. The scan for X-Values
	 * goes along the neighbors children or, if none, looks further to the
	 * left/right side
	 * 
	 * @param info The actual ode Info
	 * @param entitiesToLayout
	 * @param left
	 * @return
	 */
	private List<Double> getSideXVals(final LvlCtInfo info,
			InternalNode[] entitiesToLayout, boolean left) {

		List<Double> vals = new ArrayList<Double>();

		getSideXValsRecursive(info, vals, left, entitiesToLayout);

		return vals;
	}

	private void getSideXValsRecursive(final LvlCtInfo info, List<Double> vals,
			boolean left, InternalNode[] entitiesToLayout) {

		vals.add(left ? info.node.getInternalX() + getLayoutWidth(info.node)
				: info.node.getInternalX());

		InternalNode next;
		List<InternalNode> children = getChildren(info.node, entitiesToLayout);

		if(children != null) {
			//look at the next relevant child below
			if(children.size() == 1) {
				next = children.get(0);
			} else {
				next = left ? children.get(children.size() - 1) : children
						.get(0);
			}

			getSideXValsRecursive(getInfo(sortedNodes, next), vals, left,
					entitiesToLayout);
		} else {
			//look at the neighbor nodes children
			int index = getIndex(sortedNodes, info.node);

			LvlCtInfo neighbor = getNeighbor(info, left, index,
					entitiesToLayout);
			if(neighbor != null) {
				List<InternalNode> nCh = getChildren(neighbor.node,
						entitiesToLayout);
				if(nCh != null) {
					next = left ? nCh.get(nCh.size() - 1) : nCh.get(0);
					getSideXValsRecursive(getInfo(sortedNodes, next), vals,
							left, entitiesToLayout);
				}
			}
		}
	}

	private LvlCtInfo getNeighbor(LvlCtInfo info, boolean left, int index,
			InternalNode[] entitiesToLayout) {

		if(left && index > 0 || !left && index < sortedNodes.size() - 1) {
			LvlCtInfo neighbor = left ? sortedNodes.get(index - 1)
					: sortedNodes.get(index + 1);
			if(neighbor.level == info.level) {
				if(getChildren(neighbor.node, entitiesToLayout) != null) {
					return neighbor;
				} else {
					return getNeighbor(neighbor, left, getIndex(sortedNodes,
							neighbor.node), entitiesToLayout);
				}
			}
		}
		return null;
	}

	/**
	 * Calculates the minimal possible distance between L, N and R which is the
	 * larger of<BR>
	 * => <u>the minimal distance of the two subtrees</u> or<BR>
	 * => <u>the minimal distance considering the node width</u><BR>
	 * note: this method is only applied when L and R both exist
	 * 
	 * @param n The middle node
	 * @param lVals The rightmost-X values of the left subtree
	 * @param rVals The leftmost-X values of the right subtree
	 * @return The minimum distance between the right side of L and the left
	 *         side of R
	 */
	private double calcMinDist(InternalNode n, List<Double> lVals,
			List<Double> rVals) {

		// calc actual distance between left and right neighbor of N in same lvl
		double rLDistance = rVals.get(0) - lVals.get(0);

		// calculate actual distance between left and right subtree
		double minRLSubtDist = Integer.MAX_VALUE;
		for(int i = 1; i < Math.min(rVals.size(), lVals.size()); i++) {
			double horzLRdist = rVals.get(i) - lVals.get(i);
			if(horzLRdist < minRLSubtDist) {
				minRLSubtDist = horzLRdist;
			}
		}

		// absolute minimum is the breadth of N plus 2 MIN_NODE_GAPs
		double minNodeDist = getLayoutWidth(n) + 2 * MIN_NODE_GAP;

		double erg = rLDistance + MIN_NODE_GAP - minRLSubtDist;

		return Math.max(rLDistance + MIN_NODE_GAP - minRLSubtDist, minNodeDist);
	}

	/**
	 * Shifts a subtree by dX
	 * 
	 * @param node The root of the subtree
	 * @param dX The shift in pixels
	 * @param entitiesToLayout All exisiting nodes to find the children
	 */
	private void shiftNodeX(InternalNode node, double dX,
			InternalNode[] entitiesToLayout) {

		node.setInternalLocation((int) (node.getInternalX() + dX), (int) (node
				.getInternalY()));
		List<InternalNode> children = getChildren(node, entitiesToLayout);
		if(children != null) {
			for(InternalNode n : children) {
				shiftNodeX(n, dX, entitiesToLayout);
			}
		}
	}

// ==== METHODS FOR TREE AND PARENT SHIFTING ===================================

	/**
	 * Shifts the entire tree to the right of R to take up the newly freed space
	 * <ul>
	 * 1.) get current nodes parent and thus the siblings of the current node<BR>
	 * 2.) shift each right-side sibling of the current node<BR>
	 * 3.) repeat with the parent of the current node<BR>
	 * 
	 * @param entitiesToLayout An Array of all considered Nodes
	 * @param rShift The shift in Pixels
	 * @param cur The current node
	 */
	private void shiftRestTree(InternalNode[] entitiesToLayout, double rShift,
			InternalNode cur) {

		InternalNode curParent = getParent(cur, entitiesToLayout);
		if(curParent != null) {

			// get parent of cur
			curParent = getParent(cur, entitiesToLayout);

			// get siblings of cur through parent
			List<InternalNode> curSiblings = getChildren(curParent,
					entitiesToLayout);

			// shift all siblings on the right side of cur to the left
			if(curSiblings.size() > 1) {
				int placeOfCur = curSiblings.indexOf(cur);
				for(int i = placeOfCur + 1; i < curSiblings.size(); i++) {
					shiftNodeX(curSiblings.get(i), rShift, entitiesToLayout);
				}
			}

			// center the parent above the shifted children
			centerParent(entitiesToLayout, curParent);

			// one level up
			shiftRestTree(entitiesToLayout, rShift, curParent);
		}
	}

	/**
	 * Similar to shiftRestTree, this Method shifts a nodes parents to match the
	 * shifted node, but only until a certain parent is reached. The main
	 * difference is that only parents are shifted and not the rest tree
	 * 
	 * @see LayeredTreeLayoutAlgorithm#shiftRestTree
	 * @param entitiesToLayout An Array of all considered Nodes
	 * @param stopParent The first parent not to be shifted by this method
	 * @param shift The shift in pixels
	 * @param n The node whose parents have to be shifted
	 */
	private void shiftOnlyOwnParents(InternalNode[] entitiesToLayout,
			InternalNode stopParent, double shift, InternalNode n) {

		InternalNode nParent = getParent(n, entitiesToLayout);
		if(nParent != stopParent) {
			// center parent
			centerParent(entitiesToLayout, nParent);
			// one level up
			shiftOnlyOwnParents(entitiesToLayout, stopParent, shift, nParent);
		}
	}

	/**
	 * Gets the first same parent of two nodes
	 * 
	 * @param n1 Node number one
	 * @param n2 Node number two
	 * @param entitiesToLayout An Array of all considered nodes
	 * @return The first same parent of <b>n1</b> and <b>n2</b>
	 */
	private InternalNode getFirstSameParent(InternalNode n1, InternalNode n2,
			InternalNode[] entitiesToLayout) {

		InternalNode parent1 = getParent(n1, entitiesToLayout);
		InternalNode parent2 = getParent(n2, entitiesToLayout);

		return parent1 == parent2 ? parent1 : getFirstSameParent(parent1,
				parent2, entitiesToLayout);
	}

	/**
	 * Centers a parent node above its children to maintain the tree
	 * 
	 * @param entitiesToLayout An Array of all considered Nodes
	 * @param parent The parent node
	 * @param childrenMap The parents children
	 */
	private void centerParent(InternalNode[] entitiesToLayout,
			InternalNode parent) {

		List<InternalNode> children = getChildren(parent, entitiesToLayout);

		if(children != null) {
			if(children.size() == 1) {
				InternalNode child = children.get(0);
				parent.setInternalLocation(child.getInternalX()
						+ (getLayoutWidth(child) - getLayoutWidth(parent)) / 2,
						parent.getInternalY());
			} else {
				parent.setInternalLocation(getNewParentPos(parent, children,
						entitiesToLayout), parent.getInternalY());
			}
		}
	}

	/**
	 * Centers a parents Position above its newly shifted children
	 * 
	 * @param curParent The actual parent node
	 * @param curSiblings Curparent's siblings
	 * @param entitiesToLayout An Array of all considered Nodes
	 * @return The centered X-Value
	 */
	private double getNewParentPos(InternalNode curParent,
			List<InternalNode> curSiblings, InternalNode[] entitiesToLayout) {

		int size = curSiblings.size();
		double leftX = curSiblings.get(0).getInternalX();
		double rightX = curSiblings.get(size - 1).getInternalX()
				+ getLayoutWidth(curSiblings.get(size - 1));

		return leftX + (rightX - leftX) / 2 - getLayoutWidth(curParent) / 2;
	}

	/**
	 * Centers the whole tree on the screen for better visualization
	 * 
	 * @param entitiesToLayout An Array of all considered Nodes
	 * @param boundsWidth
	 */
	private void centerTree(InternalNode[] entitiesToLayout, double boundsWidth) {

		InternalNode root = treeRoots.get(0);
		centerParent(entitiesToLayout, root);
		double shift = (boundsWidth / 2 - getLayoutWidth(root) / 2)
				- root.getInternalX();

		if(shift > MAX_JITTER) {
			shiftNodeX(root, shift, entitiesToLayout);
		}
	}

	/**
	 * tiny x-movements occurring because of double-coordinate rounding are
	 * found out and removed
	 */
	private void dejitter() {

		double xJitter = 0, yJitter = 0;
		DisplayIndependentPoint lastPos = null;

		for(LvlCtInfo n : sortedNodes) {
			lastPos = lastLocations.get(n.node.toString());

			if(lastPos != null) {
				xJitter = n.node.getInternalX() - lastPos.x;
				yJitter = n.node.getInternalY() - lastPos.y;
				if(xJitter != 0d && Math.abs(xJitter) < MAX_JITTER) {
					n.node.setInternalLocation(n.node.getInternalX() - xJitter,
							n.node.getInternalY());
				}
				if(yJitter != 0d && Math.abs(yJitter) < MAX_JITTER) {
					n.node.setInternalLocation(n.node.getInternalX(), n.node
							.getInternalY()
							- yJitter);
				}
			}
		}
	}

	/**
	 * Adjusts the height of each node to a fixed value based on root height and
	 * level
	 * 
	 * @param entitiesToLayout An Array of all considered Nodes
	 * @param height The height of the graph window
	 */
	private void adjustHeights(InternalNode[] entitiesToLayout) {

		InternalNode root = treeRoots.get(0);
		root.setInternalLocation(root.getInternalX(), INDEX_HEIGHT);
		adjustHeightRecursive(entitiesToLayout, root);
	}

	private void adjustHeightRecursive(InternalNode[] entitiesToLayout,
			InternalNode parent) {

		List<InternalNode> children = getChildren(parent, entitiesToLayout);
		if(children != null) {
			for(InternalNode n : children) {
				n.setInternalLocation(n.getInternalX(), parent.getInternalY()
						+ getLayoutHeight(parent) + STANDARD_LEVEL_GAP);
				adjustHeightRecursive(entitiesToLayout, n);
			}
		}
	}

// ==== METHODS FOR CONVENIENT DATA ACCESS =====================================

	private int getIndex(List<LvlCtInfo> nodes, InternalNode n) {

		for(int i = 0; i < nodes.size(); i++) {
			if(nodes.get(i).node.equals(n)) {
				return i;
			}
		}
		return -1;
	}

	private LvlCtInfo getInfo(List<LvlCtInfo> nodes, InternalNode n) {

		for(int i = 0; i < nodes.size(); i++) {
			if(nodes.get(i).node.equals(n)) {
				return nodes.get(i);
			}
		}
		return null;
	}

	/**
	 * Convenience method to get a nodes parent
	 * 
	 * @param n The node
	 * @param entitiesToLayout An Array of all considered Nodes
	 * @return The parent of <b>n</b>
	 */
	private InternalNode getParent(InternalNode n,
			InternalNode[] entitiesToLayout) {

		List<InternalNode> parent = parentLists[indexOfInternalNode(
				entitiesToLayout, n)];
		return parent.isEmpty() ? null : parent.get(0);
	}

	/**
	 * Convenience method to get a nodes children
	 * 
	 * @param parent The node
	 * @param entitiesToLayout An Array of all considered Nodes
	 * @return The children of <b>parent</b>
	 */
	private List<InternalNode> getChildren(InternalNode parent,
			InternalNode[] entitiesToLayout) {

		List<InternalNode> children = childrenLists[indexOfInternalNode(
				entitiesToLayout, parent)];
		return children.isEmpty() ? null : children;
	}

	/**
	 * returns the relevant layout width, which is:<BR>
	 * <BR>
	 * - vertical layout: node width<BR>
	 * - horizontal layout: node height
	 * 
	 * @param n The node
	 * @return The layout relevant width
	 */
	protected double getLayoutWidth(InternalNode n) {

		return n.getWidthInLayout();
	}

	/**
	 * returns the relevant layout height, which is:<BR>
	 * <BR>
	 * - vertical layout: node height<BR>
	 * - horizontal layout: node width
	 * 
	 * @param n The node
	 * @return The layout relevant width
	 */
	protected double getLayoutHeight(InternalNode n) {

		return n.getHeightInLayout();
	}

// ==== LEAFING ALGORTIHM METHODS ==============================================

	// TODO: up() and down() are for test purposes only!!
	public void up() {

		if(indexOfCurMainChild < mainChildrenSize - 1) {
			indexOfCurMainChild++;
		}
	}

	public void down() {

		if(indexOfCurMainChild > 0) {
			indexOfCurMainChild--;
		}
	}

	private void doLeafing(InternalNode[] entitiesToLayout,
			ArrayList<InternalNode> treeRoots) {

		List<InternalNode> mainChildren = getChildren(treeRoots.get(0),
				entitiesToLayout);

		if(mainChildren != null) {

			mainChildrenSize = mainChildren.size();
			InternalNode curMainChild = mainChildren.get(indexOfCurMainChild);

			double leftX = boundsX + 200;
			double rightX = boundsWidth - 200
					- getLayoutWidth(mainChildren.get(0));

			double mainChildShift = treeRoots.get(0).getInternalX()
					- curMainChild.getInternalX();
			shiftNodeX(curMainChild, mainChildShift, entitiesToLayout);

			for(InternalNode n : mainChildren) {
				if(n != curMainChild) {
					if(mainChildren.indexOf(n) < mainChildren
							.indexOf(curMainChild)) {
						shiftNodeX(n, leftX - n.getInternalX(),
								entitiesToLayout);
					} else if(mainChildren.indexOf(n) > mainChildren
							.indexOf(curMainChild)) {
						shiftNodeX(n, rightX - n.getInternalX(),
								entitiesToLayout);
					}
					shiftAllChildrenToPos(n, entitiesToLayout);
				}
			}
		}
	}

	private void shiftAllChildrenToPos(InternalNode n,
			InternalNode[] entitiesToLayout) {

		List<InternalNode> children = getChildren(n, entitiesToLayout);
		if(children != null) {
			for(InternalNode c : children) {
				c.setInternalLocation(n.getInternalX(), n.getInternalY());
				shiftAllChildrenToPos(c, entitiesToLayout);
			}
		}
	}

	// ==== PRIVATE CLASS LVLCTINFO ============================================

	class LvlCtInfo {

		InternalNode node;
		double level;
		int count;

		public LvlCtInfo(InternalNode node, double level, int count) {

			this.node = node;
			this.level = level;
			this.count = count;
		}

		public String toString() {

			return String.format("%s(lvl %1.1f|ct %d.)", node, level, count);
		}
	}
}
