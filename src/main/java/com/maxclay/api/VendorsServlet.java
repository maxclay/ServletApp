package com.maxclay.api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.maxclay.dao.impl.VendorDaoImpl;
import com.maxclay.model.Vendor;
import com.maxclay.service.VendorService;
import com.maxclay.service.impl.VendorServiceImpl;
import com.maxclay.util.InvalidURIException;
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
public class VendorsServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Gson gson = new Gson();
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        VendorService vendorService = new VendorServiceImpl(new VendorDaoImpl());
        try {

            Long vendorId = RestRequestUtil.getId(request);
            Object result = (vendorId != null) ? vendorService.get(vendorId) : vendorService.getAll();
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
        VendorService vendorService = new VendorServiceImpl(new VendorDaoImpl());
        try {

            Vendor vendor = gson.fromJson(reader, Vendor.class);
            if (vendor.getId() != null) {
                response.setStatus(BAD_REQUEST);
                return;
            }

            vendorService.save(vendor);
            out.println(gson.toJson(vendor));
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
        VendorService vendorService = new VendorServiceImpl(new VendorDaoImpl());
        try {

            Long pathVariableId = RestRequestUtil.getId(request);
            Vendor vendor = gson.fromJson(reader, Vendor.class);
            boolean identifiersDontMatch =
                    (pathVariableId != null && vendor.getId() != null && !pathVariableId.equals(vendor.getId()));

            if (pathVariableId == null || identifiersDontMatch) {
                response.setStatus(BAD_REQUEST);
                return;
            }

            if (!vendorService.exists(pathVariableId)) {
                response.setStatus(NOT_FOUND);
                return;
            }

            if (vendor.getId() == null) {
                vendor.setId(pathVariableId);
            }

            vendorService.save(vendor);
            out.println(gson.toJson(vendor));

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
        VendorService vendorService = new VendorServiceImpl(new VendorDaoImpl());
        try {

            Long vendorId = RestRequestUtil.getId(request);
            if (vendorId != null) {

                if (!vendorService.exists(vendorId)) {
                    response.setStatus(NOT_FOUND);
                    return;
                }
                vendorService.delete(vendorId);

            } else {
                vendorService.deleteAll();
            }

        } catch (InvalidURIException ex) {

            System.err.println("Invalid URI: " + ex);
            response.setStatus(BAD_REQUEST);
        }
    }

}
