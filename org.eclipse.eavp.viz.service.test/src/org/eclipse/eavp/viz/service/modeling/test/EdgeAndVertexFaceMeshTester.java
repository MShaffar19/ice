/*******************************************************************************
 * Copyright (c) 2015-2016 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.service.modeling.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eavp.viz.service.modeling.AbstractView;
import org.eclipse.eavp.viz.service.modeling.EdgeAndVertexFaceMesh;
import org.eclipse.eavp.viz.service.modeling.EdgeController;
import org.eclipse.eavp.viz.service.modeling.IController;
import org.eclipse.eavp.viz.service.modeling.LinearEdgeMesh;
import org.eclipse.eavp.viz.service.modeling.MeshCategory;
import org.eclipse.eavp.viz.service.modeling.MeshProperty;
import org.eclipse.eavp.viz.service.modeling.VertexController;
import org.eclipse.eavp.viz.service.modeling.VertexMesh;
import org.junit.Test;

/**
 * A class that tests the functionality of the EdgeAndVertexFaceMesh.
 * 
 * @author Robert Smith
 *
 */
public class EdgeAndVertexFaceMeshTester {

	/**
	 * Tests that the face adds and removes all child entities in the correct
	 * categories.
	 */
	@Test
	public void checkEntities() {

		// Create the face
		ArrayList<IController> entities = new ArrayList<IController>();
		EdgeAndVertexFaceMesh face = new EdgeAndVertexFaceMesh(entities);

		// The children should start out empty.
		assertEquals(0, face.getEntities().size());

		// Create an edge and add it to the face
		VertexController vertex1 = new VertexController(new VertexMesh(0, 0, 0),
				new AbstractView());
		VertexController vertex2 = new VertexController(new VertexMesh(1, 1, 1),
				new AbstractView());
		EdgeController edge = new EdgeController(
				new LinearEdgeMesh(vertex1, vertex2), new AbstractView());
		face.addEntityByCategory(edge, MeshCategory.EDGES);

		// The Edges category should have one object, the edge
		assertTrue(
				face.getEntitiesByCategory(MeshCategory.EDGES).contains(edge));
		assertEquals(1, face.getEntitiesByCategory(MeshCategory.EDGES).size());

		// The Vertices category should have two objects, the vertices
		List<IController> vertices = face
				.getEntitiesByCategory(MeshCategory.VERTICES);
		assertTrue(vertices.contains(vertex1));
		assertTrue(vertices.contains(vertex2));
		assertEquals(2, vertices.size());

		// Add a clone of the edge to the face
		EdgeController edge2 = (EdgeController) edge.clone();
		edge2.setProperty(MeshProperty.ID, "2");
		edge2.getEntitiesByCategory(MeshCategory.VERTICES).get(0)
				.setProperty(MeshProperty.ID, "2");
		edge2.getEntitiesByCategory(MeshCategory.VERTICES).get(1)
				.setProperty(MeshProperty.ID, "3");
		face.addEntityByCategory(edge2, MeshCategory.EDGES);

		// Check that there are now two edges and four vertices in the map.
		assertEquals(2, face.getEntitiesByCategory(MeshCategory.EDGES).size());
		assertEquals(4,
				face.getEntitiesByCategory(MeshCategory.VERTICES).size());

		// Add a second edge, sharing a vertex with the first, to the face
		VertexController vertex3 = new VertexController(new VertexMesh(2, 2, 2),
				new AbstractView());
		EdgeController edge3 = new EdgeController(
				new LinearEdgeMesh(vertex2, vertex3), new AbstractView());
		face.addEntityByCategory(edge3, MeshCategory.EDGES);

		// The face should have three edges
		assertEquals(3, face.getEntitiesByCategory(MeshCategory.EDGES).size());

		// The face should have five vertices, as one of the vertices from the
		// new edge was already present
		assertEquals(5,
				face.getEntitiesByCategory(MeshCategory.VERTICES).size());

		// Try to remove a vertex. This should fail.
		face.removeEntity(vertex1);
		assertTrue(face.getEntitiesByCategory(MeshCategory.VERTICES)
				.contains(vertex1));

		// Remove an edge. The non-shared vertex should be taken off with it,
		// but the other should remain
		face.removeEntity(edge);
		assertFalse(
				face.getEntitiesByCategory(MeshCategory.EDGES).contains(edge));
		assertFalse(face.getEntitiesByCategory(MeshCategory.VERTICES)
				.contains(vertex1));
		assertTrue(face.getEntitiesByCategory(MeshCategory.VERTICES)
				.contains(vertex2));

		// Remove vertex2's other edge. This should remove both vertex2 and 3
		face.removeEntity(edge3);
		assertFalse(
				face.getEntitiesByCategory(MeshCategory.EDGES).contains(edge3));
		assertFalse(face.getEntitiesByCategory(MeshCategory.VERTICES)
				.contains(vertex2));
		assertFalse(face.getEntitiesByCategory(MeshCategory.VERTICES)
				.contains(vertex3));
	}

	/**
	 * Check that the part is cloned correctly.
	 */
	@Test
	public void checkClone() {

		// Create a face
		EdgeAndVertexFaceMesh face = new EdgeAndVertexFaceMesh();
		face.setProperty(MeshProperty.DESCRIPTION, "Property");

		// Clone it and check that they are identical
		EdgeAndVertexFaceMesh clone = (EdgeAndVertexFaceMesh) face.clone();
		assertTrue(face.equals(clone));
	}
}