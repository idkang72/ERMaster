package ermaster.editor.controller.command.display.notation;

import ermaster.editor.controller.command.AbstractCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.settings.Settings;

public class ChangeNotationLevelCommand extends AbstractCommand {

	private ERDiagram diagram;

	private int oldNotationLevel;

	private int newNotationLevel;
	
	private Settings settings;

	public ChangeNotationLevelCommand(ERDiagram diagram, int notationLevel) {
		this.diagram = diagram;
		this.settings = diagram.getDiagramContents().getSettings();
		this.newNotationLevel = notationLevel;
		this.oldNotationLevel = this.settings.getNotationLevel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doExecute() {
		this.settings.setNotationLevel(this.newNotationLevel);
		this.diagram.refreshVisuals();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doUndo() {
		this.settings.setNotationLevel(this.oldNotationLevel);
		this.diagram.refreshVisuals();
	}
}
