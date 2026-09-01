package ermaster.editor.controller.command.category;

import java.util.List;

import ermaster.editor.controller.command.AbstractCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.element.connection.ConnectionElement;
import ermaster.editor.model.diagram_contents.element.node.NodeElement;
import ermaster.editor.model.settings.CategorySetting;

public class ChangeShowReferredTablesCommand extends AbstractCommand {

	private ERDiagram diagram;

	private boolean oldShowReferredTables;

	private boolean newShowReferredTables;

	private CategorySetting categorySettings;

	public ChangeShowReferredTablesCommand(ERDiagram diagram,
			boolean isShowReferredTables) {
		this.diagram = diagram;
		this.categorySettings = this.diagram.getDiagramContents().getSettings()
				.getCategorySetting();

		this.newShowReferredTables = isShowReferredTables;
		this.oldShowReferredTables = this.categorySettings.isFreeLayout();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doExecute() {
		this.categorySettings.setShowReferredTables(this.newShowReferredTables);
		this.refreshReferredNodeElementList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doUndo() {
		this.categorySettings.setShowReferredTables(this.oldShowReferredTables);
		this.refreshReferredNodeElementList();
	}

	private void refreshReferredNodeElementList() {
		List<NodeElement> nodeElementList = this.diagram.getCurrentCategory()
				.getContents();

		for (NodeElement nodeElement : nodeElementList) {
			for (ConnectionElement connection : nodeElement.getIncomings()) {
				NodeElement referredNodeElement = connection.getSource();

				if (nodeElementList.contains(referredNodeElement)) {
					continue;
				}

				referredNodeElement.refreshVisuals();
				connection.refreshVisuals();
			}
		}
	}
}
