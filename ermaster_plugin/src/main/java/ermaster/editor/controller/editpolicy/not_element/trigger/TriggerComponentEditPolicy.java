package ermaster.editor.controller.editpolicy.not_element.trigger;

import org.eclipse.gef.commands.Command;
import ermaster.editor.controller.command.diagram_contents.not_element.trigger.DeleteTriggerCommand;
import ermaster.editor.controller.editpolicy.not_element.NotElementComponentEditPolicy;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.not_element.trigger.Trigger;

public class TriggerComponentEditPolicy extends NotElementComponentEditPolicy {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command createDeleteCommand(ERDiagram diagram, Object model) {
		return new DeleteTriggerCommand(diagram, (Trigger) model);
	}

}
