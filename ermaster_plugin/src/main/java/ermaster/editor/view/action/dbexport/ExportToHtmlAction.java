package ermaster.editor.view.action.dbexport;

import ermaster.ImageKey;
import ermaster.editor.ERDiagramEditor;
import ermaster.editor.view.dialog.dbexport.AbstractExportDialog;
import ermaster.editor.view.dialog.dbexport.ExportToHtmlDialog;

public class ExportToHtmlAction extends AbstractExportWithDialogAction {

	public static final String ID = ExportToHtmlAction.class.getName();

	public ExportToHtmlAction(ERDiagramEditor editor) {
		super(ID, "action.title.export.html", ImageKey.EXPORT_TO_HTML, editor);
	}

	@Override
	protected AbstractExportDialog getExportDialog() {
		return new ExportToHtmlDialog();
	}

}
