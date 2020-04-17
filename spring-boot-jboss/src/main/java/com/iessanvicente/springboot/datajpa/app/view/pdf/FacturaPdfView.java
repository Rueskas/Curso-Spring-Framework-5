package com.iessanvicente.springboot.datajpa.app.view.pdf;

import java.awt.Color;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.iessanvicente.springboot.datajpa.app.models.entity.Factura;
import com.iessanvicente.springboot.datajpa.app.models.entity.ItemFactura;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("factura/ver")
public class FacturaPdfView extends AbstractPdfView {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private LocaleResolver localeResolver;

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Factura factura = (Factura) model.get("factura");
		Locale locale = localeResolver.resolveLocale(request);
		
		PdfPTable table = new PdfPTable(1);
		table.setSpacingAfter(20);
		PdfPCell cellTitle = new PdfPCell(new Phrase(messageSource.getMessage("text.factura.ver.datos.cliente", null, locale)));
		cellTitle.setBackgroundColor(new Color(184, 218, 255));
		cellTitle.setPadding(8f);
		
		table.addCell(cellTitle);
		table.addCell(factura.getCliente().getNombre() + " " + factura.getCliente().getApellido());
		table.addCell(factura.getCliente().getEmail());

		PdfPTable table2 = new PdfPTable(1);
		
		PdfPCell cellTitle2 = new PdfPCell(new Phrase(messageSource.getMessage("text.factura.ver.datos.factura", null, locale)));
		cellTitle2.setBackgroundColor(new Color(195, 230, 203));
		cellTitle2.setPadding(8f);
		
		table2.setSpacingAfter(20);
		table2.addCell(cellTitle2);
		table2.addCell(messageSource.getMessage("text.cliente.factura.folio", null, locale) + ": " + factura.getId());
		table2.addCell(messageSource.getMessage("text.cliente.factura.descripcion", null, locale) + ": "
				+ factura.getDescripcion());
		table2.addCell(
				messageSource.getMessage("text.cliente.factura.fecha", null, locale) + ": " + factura.getCreateAt());
		
		PdfPTable table3 = new PdfPTable(4);
		table3.setWidths(new float[] {3.5f, 1,1,1});
		PdfPCell cellNombre = new PdfPCell(new Phrase(messageSource.getMessage("text.factura.form.item.nombre", null, locale)));
		cellNombre.setPadding(4);
		cellNombre.setBackgroundColor(new Color(255, 165, 165));
		table3.addCell(cellNombre);
		PdfPCell cellPrecio = new PdfPCell(new Phrase(messageSource.getMessage("text.factura.form.item.precio", null, locale)));
		cellPrecio.setPadding(4);
		cellPrecio.setBackgroundColor(new Color(255, 165, 165));
		table3.addCell(cellPrecio);
		PdfPCell cellCantidadProducto = new PdfPCell(new Phrase(messageSource.getMessage("text.factura.form.item.cantidad", null, locale)));
		cellCantidadProducto.setPadding(4);
		cellCantidadProducto.setBackgroundColor(new Color(255, 165, 165));
		table3.addCell(cellCantidadProducto);
		PdfPCell cellTotal = new PdfPCell(new Phrase(messageSource.getMessage("text.factura.form.item.total", null, locale)));
		cellTotal.setPadding(4);
		cellTotal.setBackgroundColor(new Color(255, 165, 165));
		table3.addCell(cellTotal);
		for(ItemFactura item : factura.getItems()){
			table3.addCell(item.getProducto().getNombre());
			table3.addCell(item.getProducto().getPrecio() +" $");
			PdfPCell cellCantidad = new PdfPCell(new Phrase(item.getCantidad()+""));
			cellCantidad.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table3.addCell(cellCantidad);
			table3.addCell(item.calcularImporte()+" $");
		}
		PdfPCell cell = new PdfPCell(new Phrase(messageSource.getMessage("text.factura.form.total", null, locale)+": "));
		cell.setColspan(3);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		cell.setBackgroundColor(new Color(255, 212, 128));
		PdfPCell total = new PdfPCell(new Phrase(factura.getTotal()+" $"));		
		total.setBackgroundColor(new Color(255, 212, 128));
		table3.addCell(cell);
		table3.addCell(total);

		Phrase title = new Phrase(String.format(messageSource.getMessage("text.factura.ver.titulo", null, locale),
				factura.getDescripcion()));
		Paragraph paragraph = new Paragraph();
		paragraph.setFont(new Font(Font.HELVETICA, 22, Font.BOLDITALIC));
		paragraph.add(title);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		paragraph.setSpacingAfter(40);
		document.add(paragraph);
		document.add(table);
		document.add(table2);
		document.add(table3);
	}

}
