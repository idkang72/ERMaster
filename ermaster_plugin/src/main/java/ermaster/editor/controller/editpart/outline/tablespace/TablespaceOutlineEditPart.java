package ermaster.editor.controller.editpart.outline.tablespace;

import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.tools.SelectEditPartTracker;
import org.eclipse.jface.dialogs.IDialogConstants;
import ermaster.ERDiagramActivator;
import ermaster.ImageKey;
import ermaster.db.EclipseDBManagerFactory;
import ermaster.editor.controller.command.diagram_contents.not_element.tablespace.EditTablespaceCommand;
import ermaster.editor.controller.editpart.DeleteableEditPart;
import ermaster.editor.controller.editpart.outline.AbstractOutlineEditPart;
import ermaster.editor.controller.editpolicy.not_element.tablespace.TablespaceComponentEditPolicy;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.not_element.tablespace.Tablespace;
import ermaster.editor.view.dialog.outline.tablespace.TablespaceDialog;

public class TablespaceOutlineEditPart extends AbstractOutlineEditPart
		implements DeleteableEditPart {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void refreshOutlineVisuals() {
		Tablespace tablespace = (Tablespace) this.getModel();

		this.setWidgetText(this.getDiagram().filter(tablespace.getName()));
		this.setWidgetImage(ERDiagramActivator.getImage(ImageKey.TABLESPACE));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void performRequest(Request request) {
		Tablespace tablespace = (Tablespace) this.getModel();
		ERDiagram diagram = this.getDiagram();

		if (request.getType().equals(RequestConstants.REQ_OPEN)) {
			TablespaceDialog dialog = EclipseDBManagerFactory
					.getEclipseDBManager(diagram).createTablespaceDialog();

			if (dialog == null) {
				ERDiagramActivator
						.showMessageDialog("dialog.message.tablespace.not.supported");
			} else {
				dialog.init(tablespace, diagram);

				if (dialog.open() == IDialogConstants.OK_ID) {
					EditTablespaceCommand command = new EditTablespaceCommand(
							diagram, tablespace, dialog.getResult());
					this.execute(command);
				}
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
				new TablespaceComponentEditPolicy());
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
