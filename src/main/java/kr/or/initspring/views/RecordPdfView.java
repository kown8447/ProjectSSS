package kr.or.initspring.views;
/*
 * @Class : RecordPdfView
 * @Date : 2016.11.30
 * @Author : 최준호
 * @Desc
 * 학생의 성적 정보를 받아 PDF파일을 구성해 학생에게 다운로드를 제공하는 view 클래스
 * 
*/
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;
import com.sun.prism.paint.Color;

import kr.or.initspring.dto.collegeRegister.StudentInfoDTO;
import kr.or.initspring.dto.collegeRegister.StudentMajorDTO;
import kr.or.initspring.dto.collegeRegister.StudentRecordDTO;
import kr.or.initspring.dto.collegeRegister.StudentStateDTO;

public class RecordPdfView extends AbstractPdfView {

	private String fontPath = "C:\\windows\\Fonts\\malgun.ttf";
	/*
	 * @method Name : buildPdfDocument
	 * @Author : 최준호
	 * @description
	 * 학생의 성적정보를 가지고 PDF파일을 구성하는 메서드
	 */
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		StudentInfoDTO student = (StudentInfoDTO) model.get("student");
		StudentMajorDTO major = (StudentMajorDTO) model.get("mainMajor");
		List<StudentRecordDTO> recordList = (List<StudentRecordDTO>) model.get("recordList");
		int majorCredit = (Integer) model.get("majorCredit");
		int liberalCredit = (Integer) model.get("liberalCredit");
		int doubleCredit = (Integer) model.get("doubleCredit");
		int totalCredit = (Integer) model.get("totalCredit");
		float inF = (Float) model.get("inF");
		float outF = (Float) model.get("outF");

		BaseFont bfKorean = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

		Font font = new Font(bfKorean);
		font.setSize(20);
		Paragraph title = new Paragraph("                              성적증명서", font);
		document.add(title);
		document.add(new Paragraph("  ", font));
		font.setSize(10);

		document.addTitle(major.getCollege_name() + " " + major.getDepartment_name() + "학과 -" + student.getMember_name()
				+ "- 성적증명서");

		Table studentTable = new Table(4, 2);
		int[] studentsWidths = { 100, 150, 100, 150 };
		studentTable.setWidths(studentsWidths);
		studentTable.setPadding(5);
		studentTable.addCell(new Cell(new Paragraph("학번", font)));
		studentTable.addCell(new Cell(new Paragraph(student.getStudent_code(), font)));
		studentTable.addCell(new Cell(new Paragraph("이름", font)));
		studentTable.addCell(new Cell(new Paragraph(student.getMember_name(), font)));
		studentTable.addCell(new Cell(new Paragraph("전공", font)));
		Cell majorcell = new Cell(
				new Paragraph(major.getCollege_name() + " " + major.getDepartment_name(), font));
		majorcell.setColspan(3);
		studentTable.addCell(majorcell);

		document.add(studentTable);

		Table recordtable = new Table(6, recordList.size() + 1);
		int[] recordswidths = { 100, 100, 200, 50, 50, 100 };
		recordtable.setWidths(recordswidths);
		recordtable.setPadding(5);

		Cell cell = new Cell(new Paragraph("구분", font));
		recordtable.addCell(cell);
		cell = new Cell(new Paragraph("과목코드", font));
		recordtable.addCell(cell);
		cell = new Cell(new Paragraph("과목명", font));
		recordtable.addCell(cell);
		cell = new Cell(new Paragraph("학점", font));
		recordtable.addCell(cell);
		cell = new Cell(new Paragraph("성적", font));
		recordtable.addCell(cell);
		cell = new Cell(new Paragraph("재수강", font));
		recordtable.addCell(cell);

		for (int i = 0; i < recordList.size(); i++) {
			recordtable.addCell(new Cell(new Paragraph(recordList.get(i).getStringtype(), font)));
			recordtable.addCell(new Cell(new Paragraph(recordList.get(i).getSubject_code(), font)));
			recordtable.addCell(new Cell(new Paragraph(recordList.get(i).getSubject_name(), font)));
			int credit = recordList.get(i).getSubject_credit();
			recordtable.addCell(new Cell(new Paragraph(String.valueOf(credit), font)));
			recordtable.addCell(new Cell(new Paragraph(recordList.get(i).getRecord_level(), font)));
			if (recordList.get(i).getRetake_check() == 0) {
				recordtable.addCell(new Cell(new Paragraph("", font)));
			} else {
				recordtable.addCell(new Cell(new Paragraph("재수강(학점 무효)", font)));
			}

		}
		document.add(recordtable);

		Table calDataTable = new Table(8, 2);
		int[] calDataWidths = { 75, 75, 75, 75, 75, 75, 75, 75 };
		calDataTable.setWidths(calDataWidths);
		calDataTable.setPadding(5);
		calDataTable.addCell(new Cell(new Paragraph("전공", font)));
		calDataTable.addCell(new Cell(new Paragraph(String.valueOf(majorCredit), font)));
		calDataTable.addCell(new Cell(new Paragraph("교양", font)));
		calDataTable.addCell(new Cell(new Paragraph(String.valueOf(liberalCredit), font)));
		calDataTable.addCell(new Cell(new Paragraph("복수전공", font)));
		calDataTable.addCell(new Cell(new Paragraph(String.valueOf(doubleCredit), font)));
		calDataTable.addCell(new Cell(new Paragraph("이수학점", font)));
		calDataTable.addCell(new Cell(new Paragraph(String.valueOf(totalCredit), font)));
		calDataTable.addCell(new Cell(new Paragraph("평점(F포함 계산)", font)));
		Cell inFcell = new Cell(new Paragraph(String.valueOf(inF), font));
		inFcell.setColspan(3);
		calDataTable.addCell(inFcell);
		calDataTable.addCell(new Cell(new Paragraph("평점(F제외 계산)", font)));
		Cell outFcell = new Cell(new Paragraph(String.valueOf(outF), font));
		outFcell.setColspan(3);
		calDataTable.addCell(outFcell);

		document.add(calDataTable);

		writer.getDirectContentUnder().addImage(
				Image.getInstance(request.getSession().getServletContext().getRealPath("/images") + "/pdfLogo3.png"),
				500, 0, 0, 350, 25, 290);

	}

	public String getFontPath() {
		return fontPath;
	}

	public void setFontPath(String fontPath) {
		this.fontPath = fontPath;
	}

}
