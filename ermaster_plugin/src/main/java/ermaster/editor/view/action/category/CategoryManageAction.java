package ermaster.editor.view.action.category;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.PlatformUI;
import ermaster.ResourceString;
import ermaster.editor.ERDiagramEditor;
import ermaster.editor.controller.command.settings.ChangeSettingsCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.settings.Settings;
import ermaster.editor.view.action.AbstractBaseAction;
import ermaster.editor.view.dialog.category.CategoryManageDialog;

public class CategoryManageAction extends AbstractBaseAction {

	public static final String ID = CategoryManageAction.class.getName();

	public CategoryManageAction(ERDiagramEditor editor) {
		super(ID, ResourceString
				.getResourceString("action.title.category.manage"), editor);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(Event event) {
		ERDiagram diagram = this.getDiagram();

		Settings settings = (Settings) diagram.getDiagramContents()
				.getSettings().clone();

		CategoryManageDialog dialog = new CategoryManageDialog(PlatformUI
				.getWorkbench().getActiveWorkbenchWindow().getShell(),
				settings, diagram);

		if (dialog.open() == IDialogConstants.OK_ID) {
			ChangeSettingsCommand command = new ChangeSettingsCommand(diagram,
					settings, true);
			this.execute(command);
		}
	}

}
