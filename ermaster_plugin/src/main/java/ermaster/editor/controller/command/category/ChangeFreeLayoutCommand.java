package ermaster.editor.controller.command.category;

import ermaster.editor.controller.command.AbstractCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.settings.CategorySetting;

public class ChangeFreeLayoutCommand extends AbstractCommand {

	private ERDiagram diagram;

	private boolean oldFreeLayout;

	private boolean newFreeLayout;

	private CategorySetting categorySettings;

	public ChangeFreeLayoutCommand(ERDiagram diagram, boolean isFreeLayout) {
		this.diagram = diagram;
		this.categorySettings = this.diagram.getDiagramContents().getSettings()
				.getCategorySetting();

		this.newFreeLayout = isFreeLayout;
		this.oldFreeLayout = this.categorySettings.isFreeLayout();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doExecute() {
		this.categorySettings.setFreeLayout(this.newFreeLayout);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doUndo() {
		this.categorySettings.setFreeLayout(this.oldFreeLayout);
	}
}
