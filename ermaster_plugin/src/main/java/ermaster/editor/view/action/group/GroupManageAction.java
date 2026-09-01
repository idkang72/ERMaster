package ermaster.editor.view.action.group;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.PlatformUI;
import ermaster.ResourceString;
import ermaster.editor.ERDiagramEditor;
import ermaster.editor.controller.command.diagram_contents.not_element.group.ChangeGroupCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.not_element.group.CopyGroup;
import ermaster.editor.model.diagram_contents.not_element.group.GroupSet;
import ermaster.editor.view.action.AbstractBaseAction;
import ermaster.editor.view.dialog.group.GroupManageDialog;

public class GroupManageAction extends AbstractBaseAction {

	public static final String ID = GroupManageAction.class.getName();

	public GroupManageAction(ERDiagramEditor editor) {
		super(ID,
				ResourceString.getResourceString("action.title.manage.group"),
				editor);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(Event event) {
		ERDiagram diagram = this.getDiagram();
		GroupSet groupSet = diagram.getDiagramContents().getGroups();

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
}
