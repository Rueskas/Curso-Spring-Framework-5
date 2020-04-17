package com.iessanvicente.springboot.jwt.app.view.csv;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.view.AbstractView;
import org.supercsv.cellprocessor.ConvertNullTo;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.iessanvicente.springboot.jwt.app.models.entity.Cliente;

@Component("listar.csv")
public class ClienteCsvView extends AbstractView {
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private LocaleResolver localeResolver;

	public ClienteCsvView() {
		setContentType("text/csv");
	}

	@Override
	protected boolean generatesDownloadContent() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Page<Cliente> clientes = (Page<Cliente>) model.get("clientes");
		Locale locale = localeResolver.resolveLocale(request);
		response.setHeader(
				"Content-Disposition", "attachment; filename=\"clientes.csv\"");
		response.setContentType(getContentType());
		
		ICsvBeanWriter beanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		String[] header = {
				"id", "nombre", "apellido", "email", "createAt"
		};
		CellProcessor[] processors = new CellProcessor[] { new UniqueHashCode(), new NotNull(),
                new NotNull(), new ConvertNullTo("no response", new Optional()), new Optional() };
		beanWriter.writeHeader(header);

		for(Cliente cliente : clientes) {
			beanWriter.write(cliente, header, processors);
		}
		beanWriter.close();
	}
	


}
