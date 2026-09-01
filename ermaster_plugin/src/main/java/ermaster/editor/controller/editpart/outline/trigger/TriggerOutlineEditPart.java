package ermaster.editor.controller.editpart.outline.trigger;

import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.tools.SelectEditPartTracker;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.ui.PlatformUI;
import ermaster.ERDiagramActivator;
import ermaster.ImageKey;
import ermaster.editor.controller.command.diagram_contents.not_element.trigger.EditTriggerCommand;
import ermaster.editor.controller.editpart.DeleteableEditPart;
import ermaster.editor.controller.editpart.outline.AbstractOutlineEditPart;
import ermaster.editor.controller.editpolicy.not_element.trigger.TriggerComponentEditPolicy;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.not_element.trigger.Trigger;
import ermaster.editor.view.dialog.outline.trigger.TriggerDialog;

public class TriggerOutlineEditPart extends AbstractOutlineEditPart implements
		DeleteableEditPart {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void refreshOutlineVisuals() {
		Trigger trigger = (Trigger) this.getModel();

		this.setWidgetText(this.getDiagram().filter(trigger.getName()));
		this.setWidgetImage(ERDiagramActivator.getImage(ImageKey.TRIGGER));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void performRequest(Request request) {
		Trigger trigger = (Trigger) this.getModel();
		ERDiagram diagram = (ERDiagram) this.getRoot().getContents().getModel();

		if (request.getType().equals(RequestConstants.REQ_OPEN)) {
			TriggerDialog dialog = new TriggerDialog(PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getShell(), trigger);

			if (dialog.open() == IDialogConstants.OK_ID) {
				EditTriggerCommand command = new EditTriggerCommand(diagram,
						trigger, dialog.getResult());
				this.getViewer().getEditDomain().getCommandStack()
						.execute(command);
			}
		}

		super.performRequest(request);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createEditPolicies() {
		this.installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new TriggerComponentEditPolicy());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DragTracker getDragTracker(Request req) {
		return new SelectEditPartTracker(this);
	}

	public boolean isDeleteable() {
		return true;
	}
}
