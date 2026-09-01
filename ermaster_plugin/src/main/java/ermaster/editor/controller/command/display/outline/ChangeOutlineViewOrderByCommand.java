package ermaster.editor.controller.command.display.outline;

import ermaster.editor.controller.command.AbstractCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.settings.Settings;

public class ChangeOutlineViewOrderByCommand extends AbstractCommand {

	private ERDiagram diagram;

	private int oldViewOrderBy;

	private int newViewOrderBy;

	private Settings settings;

	public ChangeOutlineViewOrderByCommand(ERDiagram diagram, int viewOrderBy) {
		this.diagram = diagram;
		this.settings = this.diagram.getDiagramContents().getSettings();
		this.newViewOrderBy = viewOrderBy;
		this.oldViewOrderBy = this.settings.getViewOrderBy();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doExecute() {
		this.settings.setViewOrderBy(this.newViewOrderBy);
		this.diagram.refreshOutline();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doUndo() {
		this.settings.setViewOrderBy(this.oldViewOrderBy);
		this.diagram.refreshOutline();
	}
}
