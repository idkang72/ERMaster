package ermaster.editor.controller.command.diagram_contents.element.node.model_properties;

import java.util.List;

import ermaster.editor.controller.command.AbstractCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.element.node.model_properties.ModelProperties;
import ermaster.util.NameValue;

public class ChangeModelPropertiesCommand extends AbstractCommand {

	private List<NameValue> oldProperties;

	private List<NameValue> newProperties;

	private ModelProperties modelProperties;

	public ChangeModelPropertiesCommand(ERDiagram diagram,
			ModelProperties properties) {
		this.modelProperties = diagram.getDiagramContents().getSettings()
				.getModelProperties();

		this.oldProperties = this.modelProperties.getProperties();
		this.newProperties = properties.getProperties();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doExecute() {
		this.modelProperties.setProperties(newProperties);
		this.modelProperties.refresh();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doUndo() {
		this.modelProperties.setProperties(oldProperties);
		this.modelProperties.refresh();
	}

}
