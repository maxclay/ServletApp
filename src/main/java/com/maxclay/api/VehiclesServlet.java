package com.maxclay.api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.maxclay.dao.impl.VehicleDaoImpl;
import com.maxclay.model.Vehicle;
import com.maxclay.service.VehicleService;
import com.maxclay.service.impl.VehicleServiceImpl;
import com.maxclay.util.InvalidURIException;
import com.maxclay.util.RestRequestUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * TODO review, add validation
 *
 * @author maxclay
 */
public class VehiclesServlet extends HttpServlet {

    //TODO not found
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Gson gson = new Gson();
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        VehicleService vehicleService = new VehicleServiceImpl(new VehicleDaoImpl());
        try {

            Long vehicleId = RestRequestUtil.getId(request);
            Object result = (vehicleId != null) ? vehicleService.get(vehicleId) : vehicleService.getAll();
            if (result != null) {
                out.print(gson.toJson(result));
            } else {
                response.setStatus(404);
            }

        } catch (InvalidURIException ex) {
            System.err.println("Invalid URI: " + ex);
            response.setStatus(400);
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Gson gson = new Gson();
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        VehicleService vehicleService = new VehicleServiceImpl(new VehicleDaoImpl());

        BufferedReader reader = request.getReader();
        try {

            Vehicle vehicle = gson.fromJson(reader, Vehicle.class);
            vehicleService.save(vehicle);
            out.println(gson.toJson(vehicle));
            response.setStatus(201);
        } catch (JsonSyntaxException ex) {
            System.err.println("Invalid JSON format: " + ex);
            response.setStatus(400);
        }

    }

}
