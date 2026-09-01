package ermaster.editor.view.action.translation;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.PlatformUI;
import ermaster.ResourceString;
import ermaster.editor.ERDiagramEditor;
import ermaster.editor.controller.command.settings.ChangeSettingsCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.settings.Settings;
import ermaster.editor.view.action.AbstractBaseAction;
import ermaster.editor.view.dialog.translation.TranslationManageDialog;

public class TranslationManageAction extends AbstractBaseAction {

	public static final String ID = TranslationManageAction.class.getName();

	public TranslationManageAction(ERDiagramEditor editor) {
		super(ID, ResourceString
				.getResourceString("action.title.manage.translation"), editor);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(Event event) {
		ERDiagram diagram = this.getDiagram();

		Settings settings = (Settings) diagram.getDiagramContents()
				.getSettings().clone();

		TranslationManageDialog dialog = new TranslationManageDialog(PlatformUI
				.getWorkbench().getActiveWorkbenchWindow().getShell(),
				settings, diagram);

		if (dialog.open() == IDialogConstants.OK_ID) {
			ChangeSettingsCommand command = new ChangeSettingsCommand(diagram,
					settings, false);
			this.execute(command);
		}
	}

}
