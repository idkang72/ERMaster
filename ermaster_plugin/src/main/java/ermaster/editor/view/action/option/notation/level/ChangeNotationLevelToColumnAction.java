package ermaster.editor.view.action.option.notation.level;

import ermaster.editor.ERDiagramEditor;
import ermaster.editor.model.settings.Settings;

public class ChangeNotationLevelToColumnAction extends
		AbstractChangeNotationLevelAction {

	public static final String ID = ChangeNotationLevelToColumnAction.class
			.getName();

	public ChangeNotationLevelToColumnAction(ERDiagramEditor editor) {
		super(ID, editor);
	}

	@Override
	protected int getLevel() {
		return Settings.NOTATION_LEVLE_COLUMN;
	}

}
