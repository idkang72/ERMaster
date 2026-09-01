package ermaster.editor.view.dialog.word.column.real;

import org.eclipse.swt.widgets.Shell;
import ermaster.editor.model.ERDiagram;

public class GroupColumnDialog extends AbstractRealColumnDialog {

	public GroupColumnDialog(Shell parentShell, ERDiagram diagram) {
		super(parentShell, diagram);
	}

	protected int getStyle(int style) {
		return style;
	}

	@Override
	protected String getTitle() {
		return "dialog.title.group.column";
	}

}
