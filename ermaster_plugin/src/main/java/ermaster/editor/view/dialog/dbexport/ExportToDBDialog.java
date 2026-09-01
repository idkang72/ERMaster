package ermaster.editor.view.dialog.dbexport;

import java.lang.reflect.InvocationTargetException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import ermaster.ERDiagramActivator;
import ermaster.ResourceString;
import ermaster.common.dialog.AbstractDialog;
import ermaster.common.exception.InputException;
import ermaster.common.widgets.CompositeFactory;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.dbexport.db.ExportToDBManager;
import ermaster.editor.model.settings.DBSetting;
import ermaster.util.Check;

public class ExportToDBDialog extends AbstractDialog {

	private Text textArea;

	private DBSetting dbSetting;

	private String ddl;

	public ExportToDBDialog(Shell parentShell, ERDiagram diagram,
			DBSetting dbSetting, String ddl) {
		super(parentShell);

		this.dbSetting = dbSetting;
		this.ddl = ddl;
	}

	@Override
	protected void initLayout(GridLayout layout) {
		super.initLayout(layout);
		layout.numColumns = 1;
	}

	@Override
	protected void initialize(Composite composite) {
		this.textArea = CompositeFactory.createTextArea(null, composite,
				"dialog.message.export.db.sql", 600, 400, 1, false, false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		this.createButton(parent, IDialogConstants.OK_ID,
				ResourceString.getResourceString("label.button.execute"), true);
	}

	@Override
	protected String getErrorMessage() {
		if ("".equals(this.textArea.getText().trim())) {
			return "";
		}

		return null;
	}

	@Override
	protected String getTitle() {
		return "dialog.title.export.db";
	}

	@Override
	protected void perfomeOK() throws InputException {
		String executeDDL = this.textArea.getSelectionText();
		if (Check.isEmpty(executeDDL)) {
			executeDDL = this.textArea.getText();
		}

		if (!ERDiagramActivator.showConfirmDialog("dialog.message.export.db.confirm")) {
			return;
		}

		Connection con = null;

		try {
			con = this.dbSetting.connect();

			ProgressMonitorDialog dialog = new ProgressMonitorDialog(
					this.getShell());

			ExportToDBManager exportToDBManager = new ExportToDBManager();
			exportToDBManager.init(con, executeDDL);

			try {
				dialog.run(true, true, exportToDBManager);

				Exception e = exportToDBManager.getException();
				if (e != null) {
					ERDiagramActivator.showMessageDialog(e.getMessage());
					throw new InputException();

				} else {
//					Activator
//							.showMessageDialog("dialog.message.export.db.finish");
				}

			} catch (InvocationTargetException e) {
			} catch (InterruptedException e) {
			}

		} catch (InputException e) {
			throw e;

		} catch (Exception e) {
			Throwable cause = e.getCause();

			if (cause instanceof UnknownHostException) {
				throw new InputException("error.server.not.found");
			}

			ERDiagramActivator.log(e);

			ERDiagramActivator.showMessageDialog(e.getMessage());
			throw new InputException("error.database.not.found");

		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					ERDiagramActivator.showExceptionDialog(e);
				}
			}
		}

	}

	@Override
	protected void setData() {
		this.textArea.setText(this.ddl);
	}

}
