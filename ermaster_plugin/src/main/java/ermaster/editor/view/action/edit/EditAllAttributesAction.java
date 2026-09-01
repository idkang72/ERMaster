package ermaster.editor.view.action.edit;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.PlatformUI;
import ermaster.ERDiagramActivator;
import ermaster.ImageKey;
import ermaster.ResourceString;
import ermaster.editor.ERDiagramEditor;
import ermaster.editor.controller.command.edit.EditAllAttributesCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.DiagramContents;
import ermaster.editor.view.action.AbstractBaseAction;
import ermaster.editor.view.dialog.edit.EditAllAttributesDialog;

public class EditAllAttributesAction extends AbstractBaseAction {

	public static final String ID = EditAllAttributesAction.class.getName();

	public EditAllAttributesAction(ERDiagramEditor editor) {
		super(ID, ResourceString
				.getResourceString("action.title.edit.all.attributes"), editor);
		this.setImageDescriptor(ERDiagramActivator.getImageDescriptor(ImageKey.EDIT));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(Event event) {
		ERDiagram diagram = this.getDiagram();

		EditAllAttributesDialog dialog = new EditAllAttributesDialog(PlatformUI
				.getWorkbench().getActiveWorkbenchWindow().getShell(), diagram);

		if (dialog.open() == IDialogConstants.OK_ID) {
			DiagramContents newDiagramContents = dialog.getDiagramContents();
			EditAllAttributesCommand command = new EditAllAttributesCommand(
					diagram, newDiagramContents);
			this.execute(command);
		}
	}
}
