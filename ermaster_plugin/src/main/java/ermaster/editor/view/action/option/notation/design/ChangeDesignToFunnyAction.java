package ermaster.editor.view.action.option.notation.design;

import ermaster.editor.ERDiagramEditor;

public class ChangeDesignToFunnyAction extends AbstractChangeDesignAction {

	public static final String ID = ChangeDesignToFunnyAction.class.getName();

	public static final String TYPE = "funny";

	public ChangeDesignToFunnyAction(ERDiagramEditor editor) {
		super(ID, TYPE, editor);
	}

}
