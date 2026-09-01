package ermaster.editor.model.dbexport.excel.sheet_generator;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ermaster.editor.model.ERDiagram;
import ermaster.editor.model.ObjectModel;
import ermaster.editor.model.dbexport.excel.ExportToExcelManager.LoopDefinition;
import ermaster.editor.model.diagram_contents.element.node.table.ERTable;
import ermaster.editor.model.diagram_contents.element.node.table.TableSet;
import ermaster.editor.model.progress_monitor.ProgressMonitor;
import ermaster.util.POIUtils;

public class AllTablesSheetGenerator extends TableSheetGenerator {

	@Override
	public void generate(ProgressMonitor monitor, XSSFWorkbook workbook,
			int sheetNo, boolean useLogicalNameAsSheetName,
			Map<String, Integer> sheetNameMap,
			Map<String, ObjectModel> sheetObjectMap, ERDiagram diagram,
			Map<String, LoopDefinition> loopDefinitionMap)
			throws InterruptedException {
		this.clear();

		LoopDefinition loopDefinition = loopDefinitionMap.get(this
				.getTemplateSheetName());

		XSSFSheet newSheet = createNewSheet(workbook, sheetNo,
				loopDefinition.sheetName, sheetNameMap);

		String sheetName = workbook.getSheetName(workbook
				.getSheetIndex(newSheet));

		sheetObjectMap.put(sheetName, new TableSet());

		XSSFSheet oldSheet = workbook.getSheetAt(sheetNo);

		List<ERTable> tableContents = null;

		if (diagram.getCurrentCategory() != null) {
			tableContents = diagram.getCurrentCategory().getTableContents();
		} else {
			tableContents = diagram.getDiagramContents().getContents()
					.getTableSet().getList();
		}

		boolean first = true;

		for (ERTable table : tableContents) {
			monitor.subTaskWithCounter(sheetName + " - " + table.getName());

			if (first) {
				first = false;

			} else {
				POIUtils.copyRow(oldSheet, newSheet,
						loopDefinition.startLine - 1, oldSheet.getLastRowNum(),
						newSheet.getLastRowNum() + loopDefinition.spaceLine + 1);
			}

			this.setTableData(workbook, newSheet, table);

			newSheet.setRowBreak(newSheet.getLastRowNum()
					+ loopDefinition.spaceLine);

			monitor.worked(1);
		}

		if (first) {
			for (int i = loopDefinition.startLine - 1; i <= newSheet
					.getLastRowNum(); i++) {
				XSSFRow row = newSheet.getRow(i);
				if (row != null) {
					newSheet.removeRow(row);
				}
			}
		}
	}

	@Override
	public String getTemplateSheetName() {
		return "all_tables_template";
	}

	@Override
	public int count(ERDiagram diagram) {
		return diagram.getDiagramContents().getContents().getTableSet()
				.getList().size();
	}

}
