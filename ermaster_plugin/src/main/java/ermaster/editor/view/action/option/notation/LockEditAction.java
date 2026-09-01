package ermaster.editor.view.action.option.notation;

import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Event;
import ermaster.ResourceString;
import ermaster.editor.ERDiagramEditor;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.view.action.AbstractBaseAction;

public class LockEditAction extends AbstractBaseAction {

	public static final String ID = LockEditAction.class.getName();

	public LockEditAction(ERDiagramEditor editor) {
		super(ID, null, IAction.AS_CHECK_BOX, editor);
		this
				.setText(ResourceString
						.getResourceString("action.title.lock.edit"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(Event event) {
		ERDiagram diagram = this.getDiagram();

		diagram.setDisableSelectColumn(this.isChecked());
	}
}
