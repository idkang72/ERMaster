package ermaster.editor.controller.command.diagram_contents.not_element.tablespace;

import ermaster.editor.controller.command.AbstractCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.not_element.tablespace.Tablespace;
import ermaster.editor.model.diagram_contents.not_element.tablespace.TablespaceSet;

public class EditTablespaceCommand extends AbstractCommand {

	private TablespaceSet tablespaceSet;

	private Tablespace tablespace;

	private Tablespace oldTablespace;

	private Tablespace newTablespace;

	public EditTablespaceCommand(ERDiagram diagram, Tablespace tablespace,
			Tablespace newTablespace) {
		this.tablespaceSet = diagram.getDiagramContents().getTablespaceSet();
		this.tablespace = tablespace;
		this.oldTablespace = (Tablespace) this.tablespace.clone();
		this.newTablespace = newTablespace;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doExecute() {
		this.newTablespace.copyTo(this.tablespace);
		this.tablespaceSet.refresh();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doUndo() {
		this.oldTablespace.copyTo(this.tablespace);
		this.tablespaceSet.refresh();
	}
}
