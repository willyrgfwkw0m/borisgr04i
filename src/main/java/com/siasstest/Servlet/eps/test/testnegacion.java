/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siasstest.Servlet.eps.test;

import com.google.gson.Gson;
import com.siasstest.bussines.GestionEPS;
import com.siasstest.sdo.Autorizacion;
import com.siasstest.sdo.Negacion;
import com.siasstest.sdo.ServiciosCUPS;
import com.siasstest.sdo.ServiciosCUPSN;
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
@WebServlet(name = "testnegacion", urlPatterns = {"/testnegacion"})
public class testnegacion extends HttpServlet {

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
        //Leer Parametro 
        String codigo=request.getParameter("solicitud");
        //Leer POS
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
        
        Gson gson = new Gson();
        GestionEPS sbll= new GestionEPS();
        Negacion o = new Negacion();
        
        o.setEps_ide("EPS01");
        o.setIps_ide("IPS03");
        o.setPac_ide("7573361");
        
        o.setIdSol(codigo);
        List<ServiciosCUPSN> lcups= new ArrayList<ServiciosCUPSN>();
        lcups.add( new ServiciosCUPSN("j","f","a", "J09","10","RADIOGRAFIA") );
        lcups.add( new ServiciosCUPSN("j","f","a","J091","20","DOLEX") );
        o.setlServiciosCUPS(lcups);
                
        String Rpta=sbll.Negar(o);
        
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
