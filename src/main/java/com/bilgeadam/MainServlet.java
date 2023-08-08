package com.bilgeadam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistenceUnitName");
	private EntityManager manager = factory.createEntityManager();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Ogrenci ogrenci = new Ogrenci("Ali", "Veli", 234, (byte) 2, new Date());
		ogrenci.setId(1);

		Ogrenci ogrenci2 = new Ogrenci("Fatma", "Şen", 698, (byte) 3, new Date());
		ogrenci2.setId(2);

		List<Ogrenci> ogrenciler = new ArrayList<>();
		// ogrenciler.add(ogrenci);
		// ogrenciler.add(ogrenci2);

		ogrenciler = Arrays.asList(ogrenci, ogrenci2);

		Query q = manager.createQuery("select o from Ogrenci as o");

		List<Ogrenci> liste = q.getResultList();

		String json = gson.toJson(liste);
		outputResponse(response, json, 200);

	private void outputResponse(HttpServletResponse response, String payload, int status) {

		response.setStatus(status);

		if (payload != null) {
			try {
				OutputStream outputStream = response.getOutputStream();
				outputStream.write(payload.getBytes());
				outputStream.flush();
			} catch (IOException e) {
				response.setStatus(status);
				e.printStackTrace();
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StringBuilder sb = new StringBuilder();

		BufferedReader reader = request.getReader();

		String line = "";

		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}

		Ogrenci yeniOgrenci = gson.fromJson(sb.toString(), Ogrenci.class);

		manager.getTransaction().begin();

		manager.persist(yeniOgrenci);

		manager.getTransaction().commit();

		doGet(request, response);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		Ogrenci ogrenci = manager.getReference(Ogrenci.class, Integer.valueOf(id));
		
		manager.getTransaction().begin();
		
		manager.remove(ogrenci);
		
		manager.getTransaction().commit();
		
	}

}
