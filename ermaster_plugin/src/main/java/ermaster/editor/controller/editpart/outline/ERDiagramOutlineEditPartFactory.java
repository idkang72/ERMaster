package ermaster.editor.controller.editpart.outline;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import ermaster.editor.controller.editpart.outline.dictionary.DictionaryOutlineEditPart;
import ermaster.editor.controller.editpart.outline.dictionary.WordOutlineEditPart;
import ermaster.editor.controller.editpart.outline.group.GroupOutlineEditPart;
import ermaster.editor.controller.editpart.outline.group.GroupSetOutlineEditPart;
import ermaster.editor.controller.editpart.outline.index.IndexOutlineEditPart;
import ermaster.editor.controller.editpart.outline.index.IndexSetOutlineEditPart;
import ermaster.editor.controller.editpart.outline.sequence.SequenceOutlineEditPart;
import ermaster.editor.controller.editpart.outline.sequence.SequenceSetOutlineEditPart;
import ermaster.editor.controller.editpart.outline.table.RelationOutlineEditPart;
import ermaster.editor.controller.editpart.outline.table.TableOutlineEditPart;
import ermaster.editor.controller.editpart.outline.table.TableSetOutlineEditPart;
import ermaster.editor.controller.editpart.outline.tablespace.TablespaceOutlineEditPart;
import ermaster.editor.controller.editpart.outline.tablespace.TablespaceSetOutlineEditPart;
import ermaster.editor.controller.editpart.outline.trigger.TriggerOutlineEditPart;
import ermaster.editor.controller.editpart.outline.trigger.TriggerSetOutlineEditPart;
import ermaster.editor.controller.editpart.outline.view.ViewOutlineEditPart;
import ermaster.editor.controller.editpart.outline.view.ViewSetOutlineEditPart;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.element.connection.Relation;
import ermaster.editor.model.diagram_contents.element.node.table.ERTable;
import ermaster.editor.model.diagram_contents.element.node.table.TableSet;
import ermaster.editor.model.diagram_contents.element.node.table.index.Index;
import ermaster.editor.model.diagram_contents.element.node.table.index.IndexSet;
import ermaster.editor.model.diagram_contents.element.node.view.View;
import ermaster.editor.model.diagram_contents.element.node.view.ViewSet;
import ermaster.editor.model.diagram_contents.not_element.dictionary.Dictionary;
import ermaster.editor.model.diagram_contents.not_element.dictionary.Word;
import ermaster.editor.model.diagram_contents.not_element.group.ColumnGroup;
import ermaster.editor.model.diagram_contents.not_element.group.GroupSet;
import ermaster.editor.model.diagram_contents.not_element.sequence.Sequence;
import ermaster.editor.model.diagram_contents.not_element.sequence.SequenceSet;
import ermaster.editor.model.diagram_contents.not_element.tablespace.Tablespace;
import ermaster.editor.model.diagram_contents.not_element.tablespace.TablespaceSet;
import ermaster.editor.model.diagram_contents.not_element.trigger.Trigger;
import ermaster.editor.model.diagram_contents.not_element.trigger.TriggerSet;

public class ERDiagramOutlineEditPartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart context, Object model) {
		EditPart editPart = null;

		if (model instanceof ERTable) {
			editPart = new TableOutlineEditPart();

		} else if (model instanceof ERDiagram) {
			editPart = new ERDiagramOutlineEditPart();

		} else if (model instanceof Relation) {
			editPart = new RelationOutlineEditPart();

		} else if (model instanceof Word) {
			editPart = new WordOutlineEditPart();

		} else if (model instanceof Dictionary) {
			editPart = new DictionaryOutlineEditPart();

		} else if (model instanceof ColumnGroup) {
			editPart = new GroupOutlineEditPart();

		} else if (model instanceof GroupSet) {
			editPart = new GroupSetOutlineEditPart();

		} else if (model instanceof SequenceSet) {
			editPart = new SequenceSetOutlineEditPart();

		} else if (model instanceof Sequence) {
			editPart = new SequenceOutlineEditPart();

		} else if (model instanceof ViewSet) {
			editPart = new ViewSetOutlineEditPart();

		} else if (model instanceof View) {
			editPart = new ViewOutlineEditPart();

		} else if (model instanceof TriggerSet) {
			editPart = new TriggerSetOutlineEditPart();

		} else if (model instanceof Trigger) {
			editPart = new TriggerOutlineEditPart();

		} else if (model instanceof TablespaceSet) {
			editPart = new TablespaceSetOutlineEditPart();

		} else if (model instanceof Tablespace) {
			editPart = new TablespaceOutlineEditPart();

		} else if (model instanceof TableSet) {
			editPart = new TableSetOutlineEditPart();

		} else if (model instanceof IndexSet) {
			editPart = new IndexSetOutlineEditPart();

		} else if (model instanceof Index) {
			editPart = new IndexOutlineEditPart();

		}

		if (editPart != null) {
			editPart.setModel(model);
		}

		return editPart;
	}
}
