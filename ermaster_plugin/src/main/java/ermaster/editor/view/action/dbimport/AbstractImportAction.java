package ermaster.editor.view.action.dbimport;

import java.util.List;

import ermaster.editor.ERDiagramEditor;
import ermaster.editor.controller.command.dbimport.ImportTableCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.element.node.NodeElement;
import ermaster.editor.model.diagram_contents.not_element.group.ColumnGroup;
import ermaster.editor.model.diagram_contents.not_element.sequence.Sequence;
import ermaster.editor.model.diagram_contents.not_element.tablespace.Tablespace;
import ermaster.editor.model.diagram_contents.not_element.trigger.Trigger;
import ermaster.editor.view.action.AbstractBaseAction;

public abstract class AbstractImportAction extends AbstractBaseAction {

	protected List<NodeElement> importedNodeElements;

	protected List<Sequence> importedSequences;

	protected List<Trigger> importedTriggers;

	protected List<Tablespace> importedTablespaces;

	protected List<ColumnGroup> importedColumnGroups;
	
	public AbstractImportAction(String id, String text, ERDiagramEditor editor) {
		super(id, text, editor);
	}

	protected void showData() {
		ERDiagram diagram = this.getDiagram();

		if (this.importedNodeElements != null) {
			ImportTableCommand command = new ImportTableCommand(diagram,
					this.importedNodeElements, this.importedSequences,
					this.importedTriggers, this.importedTablespaces,
					this.importedColumnGroups);

			this.execute(command);
		}
	}
}
