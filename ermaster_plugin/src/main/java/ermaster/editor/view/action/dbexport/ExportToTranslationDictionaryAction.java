package ermaster.editor.view.action.dbexport;

import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.PlatformUI;
import ermaster.ResourceString;
import ermaster.editor.ERDiagramEditor;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.view.action.AbstractBaseAction;
import ermaster.editor.view.dialog.dbexport.ExportToTranslationDictionaryDialog;

public class ExportToTranslationDictionaryAction extends AbstractBaseAction {

	public static final String ID = ExportToTranslationDictionaryAction.class
			.getName();

	public ExportToTranslationDictionaryAction(ERDiagramEditor editor) {
		super(
				ID,
				ResourceString
						.getResourceString("action.title.export.translation.dictionary"),
				editor);
	}

	@Override
	public void execute(Event event) {
		ERDiagram diagram = this.getDiagram();

		ExportToTranslationDictionaryDialog dialog = new ExportToTranslationDictionaryDialog(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				diagram);

		dialog.open();
	}

}
