package ermaster.editor.view.action.option.notation.system;

import ermaster.editor.ERDiagramEditor;
import ermaster.editor.model.settings.Settings;

public class ChangeToIENotationAction extends AbstractChangeNotationAction {

	public static final String ID = ChangeToIENotationAction.class.getName();

	public ChangeToIENotationAction(ERDiagramEditor editor) {
		super(ID, "ie", editor);
	}

	@Override
	protected String getNotation() {
		return Settings.NOTATION_IE;
	}

}
