package ermaster.editor.controller.editpolicy.not_element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import ermaster.editor.controller.editpart.DeleteableEditPart;
import ermaster.editor.model.ERDiagram;

public abstract class NotElementComponentEditPolicy extends ComponentEditPolicy {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command createDeleteCommand(GroupRequest request) {
		if (this.getHost() instanceof DeleteableEditPart) {
			DeleteableEditPart editPart = (DeleteableEditPart) this.getHost();

			if (!editPart.isDeleteable()) {
				return null;
			}

		} else {
			return null;
		}

		ERDiagram diagram = (ERDiagram) this.getHost().getRoot().getContents()
				.getModel();

		return this.createDeleteCommand(diagram, this.getHost().getModel());
	}

	protected abstract Command createDeleteCommand(ERDiagram diagram,
			Object model);
}
