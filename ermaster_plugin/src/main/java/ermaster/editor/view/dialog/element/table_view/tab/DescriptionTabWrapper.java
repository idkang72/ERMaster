package ermaster.editor.view.dialog.element.table_view.tab;

import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;
import ermaster.common.dialog.AbstractTabbedDialog;
import ermaster.common.dialog.ValidatableTabWrapper;
import ermaster.common.exception.InputException;
import ermaster.common.widgets.CompositeFactory;
import ermaster.editor.model.diagram_contents.element.node.table.TableView;
import ermaster.util.Format;

public class DescriptionTabWrapper extends ValidatableTabWrapper {

	private TableView copyData;

	private Text descriptionText;

	public DescriptionTabWrapper(AbstractTabbedDialog dialog, TabFolder parent,
			TableView copyData) {
		super(dialog, parent, "label.table.description");

		this.copyData = copyData;
	}

	@Override
	public void initComposite() {
		this.descriptionText = CompositeFactory.createTextArea(null, this,
				"label.table.description", -1, 400, 1, true, false);

		this.descriptionText.setText(Format.null2blank(copyData
				.getDescription()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validatePage() throws InputException {
		String text = descriptionText.getText().trim();
		this.copyData.setDescription(text);
	}

	@Override
	public void setInitFocus() {
		this.descriptionText.setFocus();
	}

	@Override
	public void perfomeOK() {
	}

}
