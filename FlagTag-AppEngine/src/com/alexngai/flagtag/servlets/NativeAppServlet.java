package com.alexngai.flagtag.servlets;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;



public class NativeAppServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();



		out.print("hello");
		out.flush();

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();



		Object weightObject = req.getParameter("weight");
		if( weightObject == null){
			String json = "{ problem : no weight proveded }";
			out.print(json);
			out.flush();
			return;
		}
		
		double weight = ( Double.parseDouble((String)weightObject));
		
		
		Object glucoseObject = req.getParameter("glucose");
		if( glucoseObject == null){
			String json = "{ problem : no glucose proveded }";
			out.print(json);
			out.flush();
			return;
		}
		
		double glucose = ( Double.parseDouble((String)glucoseObject));
		
		
		Object cholesterolObject = req.getParameter("cholesterol");
		if( cholesterolObject == null){
			String json = "{ problem : no cholesterol proveded }";
			out.print(json);
			out.flush();
			return;
		}
		
		double cholesterol = ( Double.parseDouble((String)cholesterolObject));
		
		Object calc_blood_pressureObject = req.getParameter("calc_blood_pressure");
		if( calc_blood_pressureObject == null){
			String json = "{ problem : no calc_blood_pressure proveded }";
			out.print(json);
			out.flush();
			return;
		}
		
		double calc_blood_pressure = ( Double.parseDouble((String)calc_blood_pressureObject));


		weight = weight + 29;
		out.print(workingMessage("you sent me : " + weight));
		out.flush();
		
		
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();


		Entity dataentry = new Entity("DataEntry");

		dataentry.setProperty("emailAddress", "cabooserwar@gmail.com");

		Date inputDate = new Date();
		dataentry.setProperty("date", inputDate);
		
		dataentry.setProperty("id", inputDate.toString() + "cabooserwar@gmail.com");

		dataentry.setProperty("calc_blood_pressure", calc_blood_pressure);
		dataentry.setProperty("cholesterol", cholesterol);
		dataentry.setProperty("glucose", glucose);
		dataentry.setProperty("weight", weight);

		datastore.put(dataentry);


		/*
	public static Airport jsonToAirport(HttpServletRequest req){

        Airport a = new Airport( (String)(req.getParameter("name")),
                                 Integer.parseInt((String)(req.getParameter("openRunways"))),
                                 Integer.parseInt((String)(req.getParameter("maxRunways"))),
                                (String)( req.getParameter("iata")),
                                 Integer.parseInt(req.getParameter("time")) );
        return a;
    }*/

		

	}
	
	public static String unworkingMessage(String reason){
		String json = "{\"Worked\": \"0\", \"Reason\": \"" + reason + "\"}";
		return json;
	}
	public static String workingMessage(String reason){
		String json = "{\"Worked\": \"1\", \"Reason\": \"" + reason + "\"}";
		return json;
	}
	
}

