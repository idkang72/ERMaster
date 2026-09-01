package ermaster.editor.view.dialog.option.tab;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.ui.PlatformUI;
import ermaster.ResourceString;
import ermaster.common.dialog.ValidatableTabWrapper;
import ermaster.common.exception.InputException;
import ermaster.common.widgets.CompositeFactory;
import ermaster.db.DBManagerFactory;
import ermaster.editor.model.settings.Settings;
import ermaster.editor.view.dialog.option.OptionSettingDialog;

public class DBSelectTabWrapper extends ValidatableTabWrapper {

	private Combo databaseCombo;

	private Settings settings;

	public DBSelectTabWrapper(OptionSettingDialog dialog, TabFolder parent,
			Settings settings) {
		super(dialog, parent, "label.database");

		this.settings = settings;
	}

	@Override
	protected void initLayout(GridLayout layout) {
		super.initLayout(layout);
		layout.numColumns = 2;
	}

	@Override
	public void initComposite() {

		this.databaseCombo = CompositeFactory.createReadOnlyCombo(null, this,
				"label.database");
		this.databaseCombo.setVisibleItemCount(10);

		for (String db : DBManagerFactory.getAllDBList()) {
			this.databaseCombo.add(db);
		}

		this.databaseCombo.setFocus();
	}

	@Override
	protected void addListener() {
		super.addListener();

		this.databaseCombo.addSelectionListener(new SelectionAdapter() {

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeDatabase();
			}
		});
	}

	@Override
	public void setData() {
		for (int i = 0; i < this.databaseCombo.getItemCount(); i++) {
			String database = this.databaseCombo.getItem(i);
			if (database.equals(this.settings.getDatabase())) {
				this.databaseCombo.select(i);
				break;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validatePage() throws InputException {
		this.settings.setDatabase(this.databaseCombo.getText());
	}

	private void changeDatabase() {
		MessageBox messageBox = new MessageBox(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(), SWT.ICON_QUESTION
				| SWT.OK | SWT.CANCEL);
		messageBox.setText(ResourceString
				.getResourceString("dialog.title.change.database"));
		messageBox.setMessage(ResourceString
				.getResourceString("dialog.message.change.database"));

		if (messageBox.open() == SWT.OK) {
			String database = this.databaseCombo.getText();
			this.settings.setDatabase(database);

			this.dialog.resetTabs();

		} else {
			this.setData();
		}
	}

	@Override
	public void setInitFocus() {
		this.databaseCombo.setFocus();
	}

	@Override
	public void perfomeOK() {
	}
}
