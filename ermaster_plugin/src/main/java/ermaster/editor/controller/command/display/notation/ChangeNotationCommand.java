package ermaster.editor.controller.command.display.notation;

import ermaster.editor.controller.command.AbstractCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.settings.Settings;

public class ChangeNotationCommand extends AbstractCommand {

	private ERDiagram diagram;

	private String oldNotation;

	private String newNotation;

	private Settings settings;

	public ChangeNotationCommand(ERDiagram diagram, String notation) {
		this.diagram = diagram;
		this.settings = diagram.getDiagramContents().getSettings();
		this.newNotation = notation;
		this.oldNotation = this.settings.getNotation();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doExecute() {
		this.settings.setNotation(this.newNotation);
		this.diagram.refreshConnection();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doUndo() {
		this.settings.setNotation(this.oldNotation);
		this.diagram.refreshConnection();
	}
}
