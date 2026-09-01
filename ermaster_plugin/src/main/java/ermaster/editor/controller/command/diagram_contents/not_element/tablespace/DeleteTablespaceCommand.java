package ermaster.editor.controller.command.diagram_contents.not_element.tablespace;

import ermaster.editor.controller.command.AbstractCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.not_element.tablespace.Tablespace;
import ermaster.editor.model.diagram_contents.not_element.tablespace.TablespaceSet;

public class DeleteTablespaceCommand extends AbstractCommand {

	private TablespaceSet tablespaceSet;

	private Tablespace tablespace;

	public DeleteTablespaceCommand(ERDiagram diagram, Tablespace tablespace) {
		this.tablespaceSet = diagram.getDiagramContents().getTablespaceSet();
		this.tablespace = tablespace;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doExecute() {
		this.tablespaceSet.remove(this.tablespace);
		this.tablespaceSet.refresh();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doUndo() {
		this.tablespaceSet.addObject(this.tablespace);
		this.tablespaceSet.refresh();
	}
}
