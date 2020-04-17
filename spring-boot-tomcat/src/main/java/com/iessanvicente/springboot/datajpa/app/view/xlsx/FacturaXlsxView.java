package com.iessanvicente.springboot.datajpa.app.view.xlsx;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.iessanvicente.springboot.datajpa.app.models.entity.Factura;
import com.iessanvicente.springboot.datajpa.app.models.entity.ItemFactura;

@Component("factura/ver.xlsx")
public class FacturaXlsxView extends AbstractXlsxView{
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private LocaleResolver localeResolver;
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Factura factura = (Factura) model.get("factura");
		Locale locale = localeResolver.resolveLocale(request);
		response.setHeader("Content-Disposition", "attachment; filename=\"factura.xlsx\"");
		Sheet sheet = workbook.createSheet("Factura Spring");
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue(messageSource.getMessage("text.factura.ver.datos.cliente", null, locale));
		
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue(factura.getCliente().getNombre() + " " + factura.getCliente().getApellido());
		
		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellValue(factura.getCliente().getEmail());
		
		sheet.createRow(4).createCell(0).setCellValue(
				messageSource.getMessage("text.factura.ver.datos.factura", null, locale));
		sheet.createRow(5).createCell(0).setCellValue(
				messageSource.getMessage("text.cliente.factura.folio", null, locale) + ": " + factura.getId());
		sheet.createRow(6).createCell(0).setCellValue(
				messageSource.getMessage("text.cliente.factura.descripcion", null, locale) + ": "
				+ factura.getDescripcion());
		sheet.createRow(7).createCell(0).setCellValue(
				messageSource.getMessage("text.cliente.factura.fecha", null, locale) + ": " + factura.getCreateAt());

		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setBorderBottom(BorderStyle.MEDIUM);
		headerStyle.setBorderLeft(BorderStyle.MEDIUM);
		headerStyle.setBorderRight(BorderStyle.MEDIUM);
		headerStyle.setBorderTop(BorderStyle.MEDIUM);
		headerStyle.setFillForegroundColor(IndexedColors.GOLD.index);
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		CellStyle bodyStyle = workbook.createCellStyle();
		bodyStyle.setBorderBottom(BorderStyle.THIN);
		bodyStyle.setBorderLeft(BorderStyle.THIN);
		bodyStyle.setBorderRight(BorderStyle.THIN);
		bodyStyle.setBorderTop(BorderStyle.THIN);
		bodyStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
		bodyStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		Row header = sheet.createRow(9);
		header.createCell(0).setCellValue(messageSource.getMessage("text.factura.form.item.nombre", null, locale));
		header.createCell(1).setCellValue(messageSource.getMessage("text.factura.form.item.precio", null, locale));
		header.createCell(2).setCellValue(messageSource.getMessage("text.factura.form.item.cantidad", null, locale));
		header.createCell(3).setCellValue(messageSource.getMessage("text.factura.form.item.total", null, locale));
		
		header.getCell(0).setCellStyle(headerStyle);
		header.getCell(1).setCellStyle(headerStyle);
		header.getCell(2).setCellStyle(headerStyle);
		header.getCell(3).setCellStyle(headerStyle);
		
		int rownum = 10;
		for(ItemFactura item : factura.getItems()) {
			Row fila = sheet.createRow(rownum++);
			fila.createCell(0).setCellValue(item.getProducto().getNombre());
			fila.createCell(1).setCellValue(item.getProducto().getPrecio());
			fila.createCell(2).setCellValue(item.getCantidad());
			fila.createCell(3).setCellValue(item.calcularImporte());
			fila.getCell(0).setCellStyle(bodyStyle);
			fila.getCell(1).setCellStyle(bodyStyle);
			fila.getCell(2).setCellStyle(bodyStyle);
			fila.getCell(3).setCellStyle(bodyStyle);
		}
		Row filaTotal = sheet.createRow(rownum);
		filaTotal.createCell(2).setCellValue(messageSource.getMessage("text.factura.form.total", null, locale)+": ");
		filaTotal.createCell(3).setCellValue(factura.getTotal());
		filaTotal.getCell(2).setCellStyle(bodyStyle);
		filaTotal.getCell(3).setCellStyle(bodyStyle);
	
	}
	
}
