package ermaster.editor.view.action.testdata;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.PlatformUI;
import ermaster.ERDiagramActivator;
import ermaster.ImageKey;
import ermaster.ResourceString;
import ermaster.editor.ERDiagramEditor;
import ermaster.editor.controller.command.testdata.ChangeTestDataCommand;
import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.testdata.TestData;
import ermaster.editor.view.action.AbstractBaseAction;
import ermaster.editor.view.dialog.testdata.TestDataManageDialog;

public class TestDataCreateAction extends AbstractBaseAction {

	public static final String ID = TestDataCreateAction.class.getName();

	public TestDataCreateAction(ERDiagramEditor editor) {
		super(ID, ResourceString
				.getResourceString("action.title.testdata.create"), editor);

		this.setImageDescriptor(ERDiagramActivator
				.getImageDescriptor(ImageKey.TEST_DATA));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(Event event) {
		ERDiagram diagram = this.getDiagram();

		List<TestData> testDataList = diagram.getDiagramContents()
				.getTestDataList();

		List<TestData> copyTestDataList = new ArrayList<TestData>();
		for (TestData testData : testDataList) {
			copyTestDataList.add(testData.clone());
		}

		TestDataManageDialog dialog = new TestDataManageDialog(PlatformUI
				.getWorkbench().getActiveWorkbenchWindow().getShell(), diagram,
				copyTestDataList);

		if (dialog.open() == IDialogConstants.OK_ID) {
			ChangeTestDataCommand command = new ChangeTestDataCommand(diagram,
					copyTestDataList);
			this.execute(command);
		}
	}

}
