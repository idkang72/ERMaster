package ermaster.editor.view.dialog.category;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import ermaster.common.dialog.AbstractDialog;
import ermaster.common.exception.InputException;
import ermaster.common.widgets.CompositeFactory;
import ermaster.editor.model.diagram_contents.element.node.category.Category;

public class CategoryNameChangeDialog extends AbstractDialog {

	private Text categoryNameText = null;

	private Category targetCategory;

	private String categoryName;

	public CategoryNameChangeDialog(Shell parentShell, Category category) {
		super(parentShell);
		this.targetCategory = category;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initialize(Composite composite) {
		this.categoryNameText = CompositeFactory.createText(this, composite,
				"label.category.name", true, true);
	}

	@Override
	protected String getTitle() {
		return "dialog.title.change.category.name";
	}

	@Override
	protected void perfomeOK() throws InputException {
	}

	@Override
	protected void setData() {
		this.categoryNameText.setText(this.targetCategory.getName());
	}

	@Override
	protected String getErrorMessage() {
		String text = categoryNameText.getText().trim();

		if ("".equals(text)) {
			return "error.category.name.empty";
		}

		this.categoryName = text;

		return null;
	}

	public String getCategoryName() {
		return this.categoryName;
	}
}
