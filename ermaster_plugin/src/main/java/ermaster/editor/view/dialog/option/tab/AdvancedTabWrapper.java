package ermaster.editor.view.dialog.option.tab;

import org.eclipse.swt.widgets.TabFolder;
import ermaster.common.dialog.ValidatableTabWrapper;
import ermaster.common.exception.InputException;
import ermaster.db.EclipseDBManagerFactory;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.diagram_contents.element.node.table.properties.TableProperties;
import ermaster.editor.model.settings.Settings;
import ermaster.editor.view.dialog.element.table_view.tab.AdvancedComposite;
import ermaster.editor.view.dialog.option.OptionSettingDialog;

public class AdvancedTabWrapper extends ValidatableTabWrapper {

	private Settings settings;

	private ERDiagram diagram;

	private AdvancedComposite composite;

	public AdvancedTabWrapper(OptionSettingDialog dialog, TabFolder parent,
			Settings settings, ERDiagram diagram) {
		super(dialog, parent, "label.advanced.settings");

		this.diagram = diagram;
		this.settings = settings;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validatePage() throws InputException {
		this.composite.validate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initComposite() {
		if (this.composite != null) {
			this.composite.dispose();
		}

		this.composite = EclipseDBManagerFactory.getEclipseDBManager(
				this.settings.getDatabase()).createAdvancedComposite(this);
		this.composite.initialize(this.dialog,
				(TableProperties) this.settings.getTableViewProperties(),
				this.diagram, null);

		this.pack();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setInitFocus() {
		this.composite.setInitFocus();
	}

	@Override
	public void reset() {
		this.init();
	}

	@Override
	public void perfomeOK() {
	}
}
