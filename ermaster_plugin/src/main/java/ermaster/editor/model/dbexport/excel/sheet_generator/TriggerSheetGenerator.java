package ermaster.editor.model.dbexport.excel.sheet_generator;

import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.ObjectModel;
import ermaster.editor.model.dbexport.excel.ExportToExcelManager.LoopDefinition;
import ermaster.editor.model.diagram_contents.not_element.trigger.Trigger;
import ermaster.editor.model.progress_monitor.ProgressMonitor;
import ermaster.util.POIUtils;

public class TriggerSheetGenerator extends AbstractSheetGenerator {

	private static final String KEYWORD_TRIGGER_NAME = "$PTGN";

	private static final String KEYWORD_TRIGGER_DESCRIPTION = "$TGDSC";

	private static final String KEYWORD_TRIGGER_SQL = "$SQL";

	public void setTriggerData(XSSFWorkbook workbook, XSSFSheet sheet,
			Trigger trigger) {
		POIUtils.replace(sheet, KEYWORD_TRIGGER_NAME, this.getValue(
				this.keywordsValueMap, KEYWORD_TRIGGER_NAME, trigger.getName()));

		POIUtils.replace(sheet, KEYWORD_TRIGGER_DESCRIPTION, this.getValue(
				this.keywordsValueMap, KEYWORD_TRIGGER_DESCRIPTION,
				trigger.getDescription()));

		POIUtils.replace(sheet, KEYWORD_TRIGGER_SQL, this.getValue(
				this.keywordsValueMap, KEYWORD_TRIGGER_SQL, trigger.getSql()));
	}

	@Override
	public void generate(ProgressMonitor monitor, XSSFWorkbook workbook,
			int sheetNo, boolean useLogicalNameAsSheetName,
			Map<String, Integer> sheetNameMap,
			Map<String, ObjectModel> sheetObjectMap, ERDiagram diagram,
			Map<String, LoopDefinition> loopDefinitionMap)
			throws InterruptedException {

		for (Trigger trigger : diagram.getDiagramContents().getTriggerSet()) {
			String name = trigger.getName();
			XSSFSheet newSheet = createNewSheet(workbook, sheetNo, name,
					sheetNameMap);

			String sheetName = workbook.getSheetName(workbook
					.getSheetIndex(newSheet));
			monitor.subTaskWithCounter("[Trigger] " + sheetName);

			sheetObjectMap.put(sheetName, trigger);

			this.setTriggerData(workbook, newSheet, trigger);
			monitor.worked(1);
		}
	}

	@Override
	public String getTemplateSheetName() {
		return "trigger_template";
	}

	@Override
	public String[] getKeywords() {
		return new String[] { KEYWORD_TRIGGER_NAME,
				KEYWORD_TRIGGER_DESCRIPTION, KEYWORD_TRIGGER_SQL };
	}

	@Override
	public int getKeywordsColumnNo() {
		return 16;
	}

	@Override
	public int count(ERDiagram diagram) {
		return diagram.getDiagramContents().getTriggerSet().getObjectList()
				.size();
	}

}
