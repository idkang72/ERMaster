package ermaster.editor.view.action.option.notation;

import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Event;
import ermaster.ResourceString;
import ermaster.editor.ERDiagramEditor;
import ermaster.editor.controller.command.display.ChangeCapitalCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.view.action.AbstractBaseAction;

public class ChangeCapitalAction extends AbstractBaseAction {

	public static final String ID = ChangeCapitalAction.class.getName();

	public ChangeCapitalAction(ERDiagramEditor editor) {
		super(ID, null, IAction.AS_CHECK_BOX, editor);
		this.setText(ResourceString
				.getResourceString("action.title.display.capital"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(Event event) {
		ERDiagram diagram = this.getDiagram();

		ChangeCapitalCommand command = new ChangeCapitalCommand(diagram, this
				.isChecked());

		this.execute(command);
	}
}
