package ermaster.editor.controller.editpart.outline.table;

import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.tools.SelectEditPartTracker;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.ui.PlatformUI;
import ermaster.ERDiagramActivator;
import ermaster.ImageKey;
import ermaster.editor.controller.command.diagram_contents.element.connection.relation.ChangeRelationPropertyCommand;
import ermaster.editor.controller.editpart.outline.AbstractOutlineEditPart;
import ermaster.editor.controller.editpolicy.element.connection.RelationEditPolicy;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.element.connection.Relation;
import ermaster.editor.model.diagram_contents.element.node.table.column.NormalColumn;
import ermaster.editor.model.settings.Settings;
import ermaster.editor.view.dialog.element.relation.RelationDialog;
import ermaster.util.Format;

public class RelationOutlineEditPart extends AbstractOutlineEditPart {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void refreshOutlineVisuals() {
		Relation model = (Relation) this.getModel();

		ERDiagram diagram = (ERDiagram) this.getRoot().getContents().getModel();

		int viewMode = diagram.getDiagramContents().getSettings()
				.getOutlineViewMode();

		boolean first = true;
		StringBuilder sb = new StringBuilder();

		for (NormalColumn foreignKeyColumn : model.getForeignKeyColumns()) {
			if (first) {
				first = false;
			} else {
				sb.append(", ");
			}

			if (viewMode == Settings.VIEW_MODE_PHYSICAL) {
				sb.append(Format.null2blank(foreignKeyColumn.getPhysicalName()));

			} else if (viewMode == Settings.VIEW_MODE_LOGICAL) {
				sb.append(Format.null2blank(foreignKeyColumn.getLogicalName()));

			} else {
				sb.append(Format.null2blank(foreignKeyColumn.getLogicalName()));
				sb.append("/");
				sb.append(Format.null2blank(foreignKeyColumn.getPhysicalName()));
			}
		}

		this.setWidgetText(sb.toString());
		this.setWidgetImage(ERDiagramActivator.getImage(ImageKey.FOREIGN_KEY));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createEditPolicies() {
		this.installEditPolicy(EditPolicy.CONNECTION_ROLE,
				new RelationEditPolicy());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void performRequest(Request request) {
		Relation relation = (Relation) this.getModel();

		if (request.getType().equals(RequestConstants.REQ_OPEN)) {
			Relation copy = relation.copy();

			RelationDialog dialog = new RelationDialog(PlatformUI
					.getWorkbench().getActiveWorkbenchWindow().getShell(), copy);

			if (dialog.open() == IDialogConstants.OK_ID) {
				ChangeRelationPropertyCommand command = new ChangeRelationPropertyCommand(
						relation, copy);
				this.execute(command);
			}
		}

		super.performRequest(request);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DragTracker getDragTracker(Request req) {
		return new SelectEditPartTracker(this);
	}
}
