package ermaster.editor.view.action.option.notation.design;

import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Event;
import ermaster.ResourceString;
import ermaster.editor.ERDiagramEditor;
import ermaster.editor.controller.command.display.ChangeDesignCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.view.action.AbstractBaseAction;

public class AbstractChangeDesignAction extends AbstractBaseAction {

	private String type;

	public AbstractChangeDesignAction(String ID, String type,
			ERDiagramEditor editor) {
		super(ID, ResourceString
				.getResourceString("action.title.change.design." + type),
				IAction.AS_RADIO_BUTTON, editor);

		this.type = type;
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

		ChangeDesignCommand command = new ChangeDesignCommand(diagram, type);

		this.execute(command);
	}

}
