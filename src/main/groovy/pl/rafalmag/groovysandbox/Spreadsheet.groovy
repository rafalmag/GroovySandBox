package pl.rafalmag.groovysandbox

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory

/**
 * Created by Rafal on 2014-07-01.
 */
class Spreadsheet {

    Map<String, List<String>> map = new TreeMap<>()

    Map<String, List<String>> getMap(){
        InputStream inp = getClass().getResourceAsStream("/test.xlsx");
        Workbook wb = WorkbookFactory.create(inp);
        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> rowIt = sheet.rowIterator()
        rowIt.each { row -> processRow(row) }
        map
    }

    def processRow(Row cells) {
        String id = "noId";
        for (Cell cell : cells) {
            switch (cell.getColumnIndex()) {
                case 0:
                    id = cell.getRichStringCellValue().getString()
                    map.put(id, new ArrayList<String>())
                    break
                case 1..2:
                    break
                default:
                    def value = cell.getRichStringCellValue().getString()
                    map.get(id)?.add(value)
            }
        }
        println(id + map.get(id))
    }

}
