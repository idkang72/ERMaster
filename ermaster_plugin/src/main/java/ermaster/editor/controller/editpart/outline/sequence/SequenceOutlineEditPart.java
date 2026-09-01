package ermaster.editor.controller.editpart.outline.sequence;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.tools.SelectEditPartTracker;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.PlatformUI;
import ermaster.ERDiagramActivator;
import ermaster.ImageKey;
import ermaster.db.DBManager;
import ermaster.db.DBManagerFactory;
import ermaster.editor.controller.command.diagram_contents.not_element.sequence.EditSequenceCommand;
import ermaster.editor.controller.editpart.DeleteableEditPart;
import ermaster.editor.controller.editpart.outline.AbstractOutlineEditPart;
import ermaster.editor.controller.editpolicy.not_element.sequence.SequenceComponentEditPolicy;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.not_element.sequence.Sequence;
import ermaster.editor.view.dialog.outline.sequence.SequenceDialog;

public class SequenceOutlineEditPart extends AbstractOutlineEditPart implements
		DeleteableEditPart {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void refreshOutlineVisuals() {
		Sequence sequence = (Sequence) this.getModel();

		if (!DBManagerFactory.getDBManager(this.getDiagram()).isSupported(
				DBManager.SUPPORT_SEQUENCE)) {
			((TreeItem) getWidget()).setForeground(ColorConstants.lightGray);

		} else {
			((TreeItem) getWidget()).setForeground(ColorConstants.black);
		}

		this.setWidgetText(this.getDiagram().filter(sequence.getName()));
		this.setWidgetImage(ERDiagramActivator.getImage(ImageKey.SEQUENCE));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void performRequest(Request request) {
		try {
			Sequence sequence = (Sequence) this.getModel();
			ERDiagram diagram = this.getDiagram();

			if (request.getType().equals(RequestConstants.REQ_OPEN)) {
				SequenceDialog dialog = new SequenceDialog(PlatformUI
						.getWorkbench().getActiveWorkbenchWindow().getShell(),
						sequence, diagram);

				if (dialog.open() == IDialogConstants.OK_ID) {
					EditSequenceCommand command = new EditSequenceCommand(
							diagram, sequence, dialog.getResult());
					this.execute(command);
				}
			}

			super.performRequest(request);

		} catch (Exception e) {
			ERDiagramActivator.showExceptionDialog(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createEditPolicies() {
		this.installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new SequenceComponentEditPolicy());
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
