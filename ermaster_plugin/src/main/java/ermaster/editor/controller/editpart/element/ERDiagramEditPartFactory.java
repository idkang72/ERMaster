package ermaster.editor.controller.editpart.element;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import ermaster.editor.controller.editpart.element.connection.CommentConnectionEditPart;
import ermaster.editor.controller.editpart.element.connection.RelationEditPart;
import ermaster.editor.controller.editpart.element.node.CategoryEditPart;
import ermaster.editor.controller.editpart.element.node.ERTableEditPart;
import ermaster.editor.controller.editpart.element.node.InsertedImageEditPart;
import ermaster.editor.controller.editpart.element.node.ModelPropertiesEditPart;
import ermaster.editor.controller.editpart.element.node.NoteEditPart;
import ermaster.editor.controller.editpart.element.node.ViewEditPart;
import ermaster.editor.controller.editpart.element.node.column.GroupColumnEditPart;
import ermaster.editor.controller.editpart.element.node.column.NormalColumnEditPart;
import ermaster.editor.controller.editpart.element.node.removed.RemovedERTableEditPart;
import ermaster.editor.controller.editpart.element.node.removed.RemovedNoteEditPart;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.element.connection.CommentConnection;
import ermaster.editor.model.diagram_contents.element.connection.Relation;
import ermaster.editor.model.diagram_contents.element.node.category.Category;
import ermaster.editor.model.diagram_contents.element.node.image.InsertedImage;
import ermaster.editor.model.diagram_contents.element.node.model_properties.ModelProperties;
import ermaster.editor.model.diagram_contents.element.node.note.Note;
import ermaster.editor.model.diagram_contents.element.node.table.ERTable;
import ermaster.editor.model.diagram_contents.element.node.table.column.NormalColumn;
import ermaster.editor.model.diagram_contents.element.node.view.View;
import ermaster.editor.model.diagram_contents.not_element.group.ColumnGroup;
import ermaster.editor.model.tracking.RemovedERTable;
import ermaster.editor.model.tracking.RemovedNote;

public class ERDiagramEditPartFactory implements EditPartFactory {

	public ERDiagramEditPartFactory() {
	}

	public EditPart createEditPart(EditPart context, Object model) {
		EditPart editPart = null;

		if (model instanceof ERTable) {
			editPart = new ERTableEditPart();

		} else if (model instanceof View) {
			editPart = new ViewEditPart();

		} else if (model instanceof ERDiagram) {
			editPart = new ERDiagramEditPart();

		} else if (model instanceof Relation) {
			editPart = new RelationEditPart();

		} else if (model instanceof Note) {
			editPart = new NoteEditPart();

		} else if (model instanceof ModelProperties) {
			editPart = new ModelPropertiesEditPart();

		} else if (model instanceof CommentConnection) {
			editPart = new CommentConnectionEditPart();

		} else if (model instanceof Category) {
			editPart = new CategoryEditPart();

		} else if (model instanceof RemovedERTable) {
			editPart = new RemovedERTableEditPart();

		} else if (model instanceof RemovedNote) {
			editPart = new RemovedNoteEditPart();

		} else if (model instanceof NormalColumn) {
			editPart = new NormalColumnEditPart();

		} else if (model instanceof ColumnGroup) {
			editPart = new GroupColumnEditPart();

		} else if (model instanceof InsertedImage) {
			editPart = new InsertedImageEditPart();

		}

		if (editPart != null) {
			editPart.setModel(model);
		}

		return editPart;
	}
}
