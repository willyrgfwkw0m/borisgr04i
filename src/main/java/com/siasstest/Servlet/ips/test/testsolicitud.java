/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.Servlet.ips.test;

import com.google.gson.Gson;
import com.siasstest.bussines.GestionIPS;
import com.siasstest.sdo.DiagnosticoCIE10;
import com.siasstest.sdo.ServiciosCUPS;
import com.siasstest.sdo.Solicitud;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author borisgr04
 */
@WebServlet(name = "testsolicitud", urlPatterns = {"/testsolicitud"})
public class testsolicitud extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Leer Parametro //String json=request.getParameter("solicitud");
        //Leer POS
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
        //Mapear JSON -> SDO
        final Gson gson = new Gson();
        //final SolicitudesSDO sol = gson.fromJson(json, SolicitudesSDO.class);
        final Solicitud sol = new Solicitud();
        sol.setEps_ide("EPS01");
        sol.setIps_ide("IPS03");
        sol.setPac_ide("7573361");
        
        List<DiagnosticoCIE10> lcie10= new ArrayList<DiagnosticoCIE10>();
        lcie10.add( new DiagnosticoCIE10("J09","GRIPA") );
        lcie10.add( new DiagnosticoCIE10("J091","QWERTY") );
        sol.setlDiagnosticoCIE10(lcie10);
        
        //
        List<ServiciosCUPS> lcups= new ArrayList<ServiciosCUPS>();
        lcups.add( new ServiciosCUPS("J09","10","RADIOGRAFIA") );
        lcups.add( new ServiciosCUPS("J091","20","DOLEX") );
        sol.setlServiciosCUPS(lcups);
        
        GestionIPS sbll= new GestionIPS();
        String Rpta=sbll.Crear(sol);
        String jsonRpta = gson.toJson(Rpta);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        out.println(jsonRpta);      
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
