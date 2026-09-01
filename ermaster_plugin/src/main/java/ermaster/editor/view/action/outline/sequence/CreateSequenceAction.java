package ermaster.editor.view.action.outline.sequence;

import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.PlatformUI;
import ermaster.ResourceString;
import ermaster.editor.controller.command.diagram_contents.not_element.sequence.CreateSequenceCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.view.action.outline.AbstractOutlineBaseAction;
import ermaster.editor.view.dialog.outline.sequence.SequenceDialog;

public class CreateSequenceAction extends AbstractOutlineBaseAction {

	public static final String ID = CreateSequenceAction.class.getName();

	public CreateSequenceAction(TreeViewer treeViewer) {
		super(ID, ResourceString
				.getResourceString("action.title.create.sequence"), treeViewer);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(Event event) {
		ERDiagram diagram = this.getDiagram();

		SequenceDialog dialog = new SequenceDialog(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(), null, diagram);

		if (dialog.open() == IDialogConstants.OK_ID) {
			CreateSequenceCommand command = new CreateSequenceCommand(diagram,
					dialog.getResult());
			this.execute(command);
		}
	}

}
