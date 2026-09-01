package ermaster.editor.view.action.option;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.PlatformUI;
import ermaster.ERDiagramActivator;
import ermaster.ImageKey;
import ermaster.ResourceString;
import ermaster.editor.ERDiagramEditor;
import ermaster.editor.controller.command.settings.ChangeSettingsCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.settings.Settings;
import ermaster.editor.view.action.AbstractBaseAction;
import ermaster.editor.view.dialog.option.OptionSettingDialog;

public class OptionSettingAction extends AbstractBaseAction {

	public static final String ID = OptionSettingAction.class.getName();

	public OptionSettingAction(ERDiagramEditor editor) {
		super(ID, ResourceString.getResourceString("action.title.option"),
				editor);
		this.setImageDescriptor(ERDiagramActivator.getImageDescriptor(ImageKey.OPTION));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(Event event) {
		ERDiagram diagram = this.getDiagram();

		Settings settings = (Settings) diagram.getDiagramContents()
				.getSettings().clone();

		OptionSettingDialog dialog = new OptionSettingDialog(PlatformUI
				.getWorkbench().getActiveWorkbenchWindow().getShell(),
				settings, diagram);

		if (dialog.open() == IDialogConstants.OK_ID) {
			ChangeSettingsCommand command = new ChangeSettingsCommand(diagram,
					settings, true);

			this.execute(command);
		}
	}

}
