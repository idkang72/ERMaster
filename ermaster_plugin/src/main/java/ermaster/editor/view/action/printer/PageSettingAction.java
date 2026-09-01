package ermaster.editor.view.action.printer;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.PlatformUI;
import ermaster.ERDiagramActivator;
import ermaster.ImageKey;
import ermaster.ResourceString;
import ermaster.editor.ERDiagramEditor;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.view.action.AbstractBaseAction;
import ermaster.editor.view.dialog.printer.PageSettingDialog;

public class PageSettingAction extends AbstractBaseAction {

	public static final String ID = PageSettingAction.class.getName();

	public PageSettingAction(ERDiagramEditor editor) {
		super(ID,
				ResourceString.getResourceString("action.title.page.setting"),
				editor);

		this.setImageDescriptor(ERDiagramActivator.getImageDescriptor(ImageKey.PRINTER));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(Event event) {
		ERDiagram diagram = this.getDiagram();

		PageSettingDialog dialog = new PageSettingDialog(PlatformUI
				.getWorkbench().getActiveWorkbenchWindow().getShell(), diagram);

		if (dialog.open() == IDialogConstants.OK_ID) {

		}
	}

}
