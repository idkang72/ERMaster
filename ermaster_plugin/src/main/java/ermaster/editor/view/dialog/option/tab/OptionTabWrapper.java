package ermaster.editor.view.dialog.option.tab;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TabFolder;
import ermaster.common.dialog.ValidatableTabWrapper;
import ermaster.common.exception.InputException;
import ermaster.common.widgets.CompositeFactory;
import ermaster.editor.model.settings.Settings;
import ermaster.editor.view.dialog.option.OptionSettingDialog;

public class OptionTabWrapper extends ValidatableTabWrapper {

	private Button autoImeChangeCheck;

	private Button validatePhysicalNameCheck;

	private Button useBezierCurveCheck;

	private Button suspendValidatorCheck;

	private Settings settings;

	public OptionTabWrapper(OptionSettingDialog dialog, TabFolder parent,
			Settings settings) {
		super(dialog, parent, "label.option");

		this.settings = settings;
	}

	@Override
	public void initComposite() {
		this.autoImeChangeCheck = CompositeFactory.createCheckbox(this.dialog,
				this, "label.auto.ime.change", false);
		this.validatePhysicalNameCheck = CompositeFactory.createCheckbox(
				this.dialog, this, "label.validate.physical.name", false);
		this.useBezierCurveCheck = CompositeFactory.createCheckbox(this.dialog,
				this, "label.use.bezier.curve", false);
		this.suspendValidatorCheck = CompositeFactory.createCheckbox(
				this.dialog, this, "label.suspend.validator", false);
	}

	@Override
	public void setData() {
		this.autoImeChangeCheck.setSelection(this.settings.isAutoImeChange());
		this.validatePhysicalNameCheck.setSelection(this.settings
				.isValidatePhysicalName());
		this.useBezierCurveCheck.setSelection(this.settings.isUseBezierCurve());
		this.suspendValidatorCheck.setSelection(this.settings
				.isSuspendValidator());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validatePage() throws InputException {
		this.settings.setAutoImeChange(this.autoImeChangeCheck.getSelection());
		this.settings.setValidatePhysicalName(this.validatePhysicalNameCheck
				.getSelection());
		this.settings
				.setUseBezierCurve(this.useBezierCurveCheck.getSelection());
		this.settings.setSuspendValidator(this.suspendValidatorCheck
				.getSelection());
	}

	@Override
	public void setInitFocus() {
		this.autoImeChangeCheck.setFocus();
	}

	@Override
	public void perfomeOK() {
	}
}
