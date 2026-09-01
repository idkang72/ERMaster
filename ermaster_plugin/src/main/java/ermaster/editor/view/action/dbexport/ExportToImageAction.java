package ermaster.editor.view.action.dbexport;

import ermaster.ImageKey;
import ermaster.editor.ERDiagramEditor;
import ermaster.editor.view.dialog.dbexport.AbstractExportDialog;
import ermaster.editor.view.dialog.dbexport.ExportToImageDialog;

public class ExportToImageAction extends AbstractExportWithDialogAction {

	public static final String ID = ExportToImageAction.class.getName();

	public ExportToImageAction(ERDiagramEditor editor) {
		super(ID, "action.title.export.image", ImageKey.EXPORT_TO_IMAGE, editor);
	}

	@Override
	protected AbstractExportDialog getExportDialog() {
		return new ExportToImageDialog();
	}

}
