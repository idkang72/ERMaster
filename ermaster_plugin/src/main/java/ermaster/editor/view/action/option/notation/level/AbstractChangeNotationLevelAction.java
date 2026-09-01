package ermaster.editor.view.action.option.notation.level;

import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Event;
import ermaster.ResourceString;
import ermaster.editor.ERDiagramEditor;
import ermaster.editor.controller.command.display.notation.ChangeNotationLevelCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.view.action.AbstractBaseAction;

public abstract class AbstractChangeNotationLevelAction extends
		AbstractBaseAction {

	public AbstractChangeNotationLevelAction(String id, ERDiagramEditor editor) {
		super(id, null, IAction.AS_RADIO_BUTTON, editor);
		this.setText(ResourceString
				.getResourceString("action.title.change.notation.level."
						+ this.getLevel()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(Event event) {
		if (!this.isChecked()) {
			return;
		}

		ERDiagram diagram = this.getDiagram();

		ChangeNotationLevelCommand command = new ChangeNotationLevelCommand(
				diagram, this.getLevel());

		this.execute(command);
	}

	protected abstract int getLevel();
}
