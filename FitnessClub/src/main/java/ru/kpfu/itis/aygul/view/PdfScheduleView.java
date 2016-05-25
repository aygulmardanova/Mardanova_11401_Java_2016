package ru.kpfu.itis.aygul.view;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.CFFFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import ru.kpfu.itis.aygul.model.Schedule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by aygulmardanova on 06.05.16.
 */
public class PdfScheduleView extends AbstractPdfView {

    private Cell prepareCell(String msg) {
        Cell cell = new Cell(msg);
        cell.setRowspan(1);
        cell.setColspan(1);
        cell.setVerticalAlignment("middle");
        cell.setHorizontalAlignment("center");
        cell.setWidth(100);
        return cell;
    }

    @Override
    protected void buildPdfDocument(Map model, Document document, PdfWriter pdfWriter,
                                    HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        Map<String, Object> scheduleData = (Map<String, Object>) model.get("scheduleData");

        List<String> startTimes = (List<String>) scheduleData.get("startTimes");

        Map<String, Schedule> monday = (Map<String, Schedule>) scheduleData.get("monday");
        Map<String, Schedule> tuesday = (Map<String, Schedule>) scheduleData.get("tuesday");
        Map<String, Schedule> wednesday = (Map<String, Schedule>) scheduleData.get("wednesday");
        Map<String, Schedule> thursday = (Map<String, Schedule>) scheduleData.get("thursday");
        Map<String, Schedule> friday = (Map<String, Schedule>) scheduleData.get("friday");
        Map<String, Schedule> saturday = (Map<String, Schedule>) scheduleData.get("saturday");
        Map<String, Schedule> sunday = (Map<String, Schedule>) scheduleData.get("sunday");


        String[] days = new String[]{"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};

        Table table = new Table(8);

        document.addAuthor("Mardanova Aygul");
        document.addTitle("Schedule");

        table.addCell(prepareCell("Time and Weekday"));

        for (int i = 0; i < 7; i ++) {
            table.addCell(prepareCell(days[i]));
        }

        Cell empty = prepareCell("");

        for (String time : startTimes) {
            table.addCell(time + ".00-" + time + ".55");
            if (monday.get(time) != null) {
                table.addCell(prepareCell(monday.get(time).getClassByClassId().getName() + "\n" + monday.get(time).getInstructor().getUser().getName()));
            } else {
                table.addCell(empty);
            }
            if (tuesday.get(time) != null) {
            table.addCell(prepareCell(tuesday.get(time).getClassByClassId().getName() + "\n" + tuesday.get(time).getInstructor().getUser().getName()));
            } else {
                table.addCell(empty);
            }
            if (wednesday.get(time) != null) {
                table.addCell(prepareCell(wednesday.get(time).getClassByClassId().getName() + "\n" + wednesday.get(time).getInstructor().getUser().getName()));
            } else {
                table.addCell(empty);
            }
            if (thursday.get(time) != null) {
                table.addCell(prepareCell(thursday.get(time).getClassByClassId().getName() + "\n" + thursday.get(time).getInstructor().getUser().getName()));
            } else {
                table.addCell(empty);
            }
            if (friday.get(time) != null) {
                table.addCell(prepareCell(friday.get(time).getClassByClassId().getName() + "\n" + friday.get(time).getInstructor().getUser().getName()));
            } else {
                table.addCell(empty);
            }
            if (saturday.get(time) != null) {
                table.addCell(prepareCell(saturday.get(time).getClassByClassId().getName() + "\n" + saturday.get(time).getInstructor().getUser().getName()));
            } else {
                table.addCell(empty);
            }
            if (sunday.get(time) != null) {
                table.addCell(prepareCell(sunday.get(time).getClassByClassId().getName() + "\n" + sunday.get(time).getInstructor().getUser().getName()));
            } else {
                table.addCell(empty);
            }
        }

        document.add(table);
    }
}
