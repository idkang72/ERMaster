package ermaster.editor.controller.command.display;

import ermaster.editor.controller.command.AbstractCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.settings.Settings;

public class ChangeCapitalCommand extends AbstractCommand {

	private ERDiagram diagram;

	private boolean oldCapital;

	private boolean newCapital;

	private Settings settings;

	public ChangeCapitalCommand(ERDiagram diagram, boolean isCapital) {
		this.diagram = diagram;
		this.settings = this.diagram.getDiagramContents().getSettings();
		this.newCapital = isCapital;
		this.oldCapital = this.settings.isCapital();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doExecute() {
		this.settings.setCapital(this.newCapital);
		this.diagram.refreshVisuals();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doUndo() {
		this.settings.setCapital(this.oldCapital);
		this.diagram.refreshVisuals();
	}
}
