package com.maxclay.api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.maxclay.dao.impl.VehicleDaoImpl;
import com.maxclay.model.Vehicle;
import com.maxclay.service.VehicleService;
import com.maxclay.service.impl.VehicleServiceImpl;
import com.maxclay.exception.InvalidURIException;
import com.maxclay.util.RestRequestUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import static com.maxclay.util.HttpStatus.*;

/**
 * TODO review, add validation, add interceptor to do common things like setting content type, exceptions handling(?),
 * TODO get rid of boilerplate code
 *
 * @author maxclay
 */
public class VehiclesServlet extends HttpServlet {

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
                response.setStatus(NOT_FOUND);
            }

        } catch (InvalidURIException ex) {

            System.err.println("Invalid URI: " + ex);
            response.setStatus(BAD_REQUEST);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Gson gson = new Gson();
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        BufferedReader reader = request.getReader();
        VehicleService vehicleService = new VehicleServiceImpl(new VehicleDaoImpl());
        try {

            Vehicle vehicle = gson.fromJson(reader, Vehicle.class);
            if (vehicle.getId() != null) {
                response.setStatus(BAD_REQUEST);
                return;
            }

            vehicleService.save(vehicle);
            out.println(gson.toJson(vehicle));
            response.setStatus(CREATED);
        } catch (JsonSyntaxException ex) {

            System.err.println("Invalid JSON format: " + ex);
            response.setStatus(BAD_REQUEST);
        } catch (NullPointerException ex) {

            System.err.println("Empty request body: " + ex);
            response.setStatus(BAD_REQUEST);
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Gson gson = new Gson();
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        BufferedReader reader = request.getReader();
        VehicleService vehicleService = new VehicleServiceImpl(new VehicleDaoImpl());
        try {

            Long pathVariableId = RestRequestUtil.getId(request);
            Vehicle vehicle = gson.fromJson(reader, Vehicle.class);
            boolean identifiersDontMatch =
                    (pathVariableId != null && vehicle.getId() != null && !pathVariableId.equals(vehicle.getId()));

            if (pathVariableId == null || identifiersDontMatch) {
                response.setStatus(BAD_REQUEST);
                return;
            }

            if (!vehicleService.exists(pathVariableId)) {
                response.setStatus(NOT_FOUND);
                return;
            }

            if (vehicle.getId() == null) {
                vehicle.setId(pathVariableId);
            }

            vehicleService.save(vehicle);
            out.println(gson.toJson(vehicle));

        } catch (InvalidURIException ex) {

            System.err.println("Invalid URI: " + ex);
            response.setStatus(BAD_REQUEST);
        } catch (NullPointerException ex) {

            System.err.println("Empty request body: " + ex);
            response.setStatus(BAD_REQUEST);
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
        VehicleService vehicleService = new VehicleServiceImpl(new VehicleDaoImpl());
        try {

            Long vehicleId = RestRequestUtil.getId(request);
            if (vehicleId != null) {

                if (!vehicleService.exists(vehicleId)) {
                    response.setStatus(NOT_FOUND);
                    return;
                }
                vehicleService.delete(vehicleId);

            } else {
                vehicleService.deleteAll();
            }

        } catch (InvalidURIException ex) {

            System.err.println("Invalid URI: " + ex);
            response.setStatus(BAD_REQUEST);
        }
    }

}
