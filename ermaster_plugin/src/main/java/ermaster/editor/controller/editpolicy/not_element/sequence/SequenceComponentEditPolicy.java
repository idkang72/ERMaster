package ermaster.editor.controller.editpolicy.not_element.sequence;

import org.eclipse.gef.commands.Command;
import ermaster.editor.controller.command.diagram_contents.not_element.sequence.DeleteSequenceCommand;
import ermaster.editor.controller.editpolicy.not_element.NotElementComponentEditPolicy;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.not_element.sequence.Sequence;

public class SequenceComponentEditPolicy extends NotElementComponentEditPolicy {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command createDeleteCommand(ERDiagram diagram, Object model) {
		return new DeleteSequenceCommand(diagram, (Sequence) model);
	}
}
