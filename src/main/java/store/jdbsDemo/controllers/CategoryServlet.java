package store.jdbsDemo.controllers;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import store.jdbsDemo.domain.entity.Category;
import store.jdbsDemo.domain.entity.ObjectMapperFactory;
import store.jdbsDemo.domain.entity.dto.CategoryDto;
import store.jdbsDemo.domain.validator.CategoryValidator;
import store.jdbsDemo.domain.validator.ValidationException;
import store.jdbsDemo.service.CategoryService;
import store.jdbsDemo.service.impl.CategoryServiceSingleton;

@WebServlet(name = "CategoryServlet", urlPatterns = "/category")
public class CategoryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final CategoryService categoryService = CategoryServiceSingleton.getInstance();
	private final CategoryValidator categoryValidator = new CategoryValidator();
	private static final String CONTENT_TYPE = "application/json";
	private static final String ENCODING = "UTF-8";
	private static final String PARAMETER_ID = "id";
	private final ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
	public CategoryServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType(CONTENT_TYPE);
		resp.setCharacterEncoding(ENCODING);
		String id = req.getParameter(PARAMETER_ID);
		try {

			if (id != null) {
				if (Long.valueOf(id) > 0) {
					resp.getWriter().write(mapper.writeValueAsString(categoryService.read(Long.valueOf(id))));
					resp.setStatus(HttpServletResponse.SC_OK);
				} else {
					resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
				}
			} else {
				resp.getWriter()
						.write(mapper.registerModule(new JavaTimeModule()).writeValueAsString(categoryService.get()));
				resp.setStatus(HttpServletResponse.SC_OK);
			}
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setCharacterEncoding(ENCODING);
			resp.setContentType(CONTENT_TYPE);
			String jsonString = req.getReader().lines().collect(Collectors.joining());
			CategoryDto dto = mapper.readValue(jsonString, CategoryDto.class);
			try {
				categoryValidator.validate(dto);
			} catch (ValidationException e) {
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
			CategoryDto category = categoryService.create(dto);
			resp.getWriter().write(mapper.writeValueAsString(category));
			resp.setStatus(HttpServletResponse.SC_CREATED);

		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.err.println(e);
		}
	}
}
