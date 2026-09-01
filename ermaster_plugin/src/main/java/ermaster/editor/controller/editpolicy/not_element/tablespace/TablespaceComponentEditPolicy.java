package ermaster.editor.controller.editpolicy.not_element.tablespace;

import org.eclipse.gef.commands.Command;
import ermaster.editor.controller.command.diagram_contents.not_element.tablespace.DeleteTablespaceCommand;
import ermaster.editor.controller.editpolicy.not_element.NotElementComponentEditPolicy;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.not_element.tablespace.Tablespace;

public class TablespaceComponentEditPolicy extends NotElementComponentEditPolicy {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command createDeleteCommand(ERDiagram diagram, Object model) {
		return new DeleteTablespaceCommand(diagram, (Tablespace) model);
	}

}
