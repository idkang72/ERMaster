package ermaster.editor.controller.editpart.outline.group;

import java.util.Collections;
import java.util.List;

import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.tools.SelectEditPartTracker;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.ui.PlatformUI;
import ermaster.ERDiagramActivator;
import ermaster.ImageKey;
import ermaster.ResourceString;
import ermaster.editor.controller.command.diagram_contents.not_element.group.ChangeGroupCommand;
import ermaster.editor.controller.editpart.outline.AbstractOutlineEditPart;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.not_element.group.ColumnGroup;
import ermaster.editor.model.diagram_contents.not_element.group.CopyGroup;
import ermaster.editor.model.diagram_contents.not_element.group.GroupSet;
import ermaster.editor.view.dialog.group.GroupManageDialog;

public class GroupSetOutlineEditPart extends AbstractOutlineEditPart {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List getModelChildren() {
		GroupSet columnGroupSet = (GroupSet) this.getModel();

		List<ColumnGroup> columnGroupList = columnGroupSet.getGroupList();

		Collections.sort(columnGroupList);

		return columnGroupList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void refreshOutlineVisuals() {
		this.setWidgetText(ResourceString
				.getResourceString("label.column.group")
				+ " ("
				+ this.getModelChildren().size() + ")");
		this.setWidgetImage(ERDiagramActivator.getImage(ImageKey.DICTIONARY));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void performRequest(Request request) {
		ERDiagram diagram = this.getDiagram();

		GroupSet groupSet = diagram.getDiagramContents().getGroups();

		if (request.getType().equals(RequestConstants.REQ_OPEN)) {
			GroupManageDialog dialog = new GroupManageDialog(PlatformUI
					.getWorkbench().getActiveWorkbenchWindow().getShell(),
					groupSet, diagram, false, -1);

			if (dialog.open() == IDialogConstants.OK_ID) {
				List<CopyGroup> newColumnGroups = dialog.getCopyColumnGroups();

				Command command = new ChangeGroupCommand(diagram, groupSet,
						newColumnGroups);

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
