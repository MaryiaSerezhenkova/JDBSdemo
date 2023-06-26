package store.jdbsDemo.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import store.jdbsDemo.domain.entity.ObjectMapperFactory;
import store.jdbsDemo.service.ProductService;
import store.jdbsDemo.service.RegionService;
import store.jdbsDemo.service.impl.ProductServiceSingleton;
import store.jdbsDemo.service.impl.RegionServiceSingleton;

@WebServlet(name = "StoreServlet", urlPatterns = "/store")
public class StoreServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final RegionService regionService = RegionServiceSingleton.getInstance();
	private final ProductService productService = ProductServiceSingleton.getInstance();
	private static final String CONTENT_TYPE = "application/json";
	private static final String ENCODING = "UTF-8";
	private static final String PARAMETER_ID = "id";
	private final ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType(CONTENT_TYPE);
		resp.setCharacterEncoding(ENCODING);
		String id = req.getParameter(PARAMETER_ID);
		try {

			if (id != null) {
				if (Long.valueOf(id) > 0) {
					resp.getWriter().write(mapper.writeValueAsString(regionService.getProductsByRegion(Long.valueOf(id))));
					resp.setStatus(HttpServletResponse.SC_OK);
				} else {
					resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
				}
			} else {
				resp.getWriter().write(
						mapper.registerModule(new JavaTimeModule()).writeValueAsString(regionService.getStore()));
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
			String productId = req.getParameter("productId");
			String regionId = req.getParameter("regionId");
			if (productId != null && regionId != null) {
				regionService.createStore(Long.parseLong(productId), Long.parseLong(regionId));
				resp.setStatus(HttpServletResponse.SC_OK);
			} else {
				resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}

		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
