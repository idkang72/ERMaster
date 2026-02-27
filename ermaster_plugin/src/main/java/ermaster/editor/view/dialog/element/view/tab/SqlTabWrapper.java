package ermaster.editor.view.dialog.element.view.tab;

import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;
import ermaster.common.dialog.ValidatableTabWrapper;
import ermaster.common.exception.InputException;
import ermaster.common.widgets.CompositeFactory;
import ermaster.editor.model.diagram_contents.element.node.view.View;
import ermaster.editor.view.dialog.element.view.ViewDialog;
import ermaster.util.Format;

public class SqlTabWrapper extends ValidatableTabWrapper {

	private View copyData;

	private Text sqlText;

	public SqlTabWrapper(ViewDialog viewDialog, TabFolder parent, View copyData) {
		super(viewDialog, parent, "label.sql");

		this.copyData = copyData;
	}

	@Override
	public void initComposite() {
		this.sqlText = CompositeFactory.createTextArea(this.dialog, this,
				"label.sql", -1, 400, 1, true, false);

		// Eclipse 설정(Basic > Text Font)에 지정된 글꼴을 그대로 사용합니다.
		if (this.sqlText != null && !this.sqlText.isDisposed()) {
			this.sqlText.setFont(org.eclipse.jface.resource.JFaceResources.getFont(org.eclipse.jface.resource.JFaceResources.TEXT_FONT));
		}

		this.sqlText.setText(Format.null2blank(copyData.getSql()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validatePage() throws InputException {
		String text = sqlText.getText().trim();

		if (text.equals("")) {
			throw new InputException("error.view.sql.empty");
		}

		this.copyData.setSql(text);
	}

	@Override
	public void setInitFocus() {
		this.sqlText.setFocus();
	}

	@Override
	public void perfomeOK() {
	}

}
