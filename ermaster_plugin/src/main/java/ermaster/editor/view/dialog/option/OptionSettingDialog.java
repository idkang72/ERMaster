package ermaster.editor.view.dialog.option;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import ermaster.common.dialog.AbstractTabbedDialog;
import ermaster.common.dialog.ValidatableTabWrapper;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.settings.Settings;
import ermaster.editor.view.dialog.option.tab.AdvancedTabWrapper;
import ermaster.editor.view.dialog.option.tab.DBSelectTabWrapper;
import ermaster.editor.view.dialog.option.tab.EnvironmentTabWrapper;
import ermaster.editor.view.dialog.option.tab.OptionTabWrapper;

public class OptionSettingDialog extends AbstractTabbedDialog {

	private Settings settings;

	private ERDiagram diagram;

	public OptionSettingDialog(Shell parentShell, Settings settings,
			ERDiagram diagram) {
		super(parentShell);

		this.diagram = diagram;
		this.settings = settings;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initialize(Composite composite) {
		this.createTabFolder(composite);
	}

	@Override
	protected String getTitle() {
		return "dialog.title.option";
	}

	@Override
	protected List<ValidatableTabWrapper> createTabWrapperList(
			TabFolder tabFolder) {
		List<ValidatableTabWrapper> list = new ArrayList<ValidatableTabWrapper>();

		list.add(new DBSelectTabWrapper(this, tabFolder, this.settings));
		list.add(new EnvironmentTabWrapper(this, tabFolder, this.settings));
		list.add(new AdvancedTabWrapper(this, tabFolder, this.settings,
				this.diagram));
		list.add(new OptionTabWrapper(this, tabFolder, this.settings));

		return list;
	}
}
