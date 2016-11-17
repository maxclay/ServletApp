package com.maxclay.api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.maxclay.dao.impl.RowMaterialDaoImpl;
import com.maxclay.dao.impl.VendorDaoImpl;
import com.maxclay.dto.RowMaterialDto;
import com.maxclay.exception.ResourceNotFoundException;
import com.maxclay.model.RowMaterial;
import com.maxclay.service.RowMaterialService;
import com.maxclay.service.impl.RowMaterialServiceImpl;
import com.maxclay.exception.InvalidURIException;
import com.maxclay.util.RestRequestUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import static com.maxclay.util.HttpStatus.BAD_REQUEST;
import static com.maxclay.util.HttpStatus.CREATED;
import static com.maxclay.util.HttpStatus.NOT_FOUND;

/**
 * @author maxclay
 */
public class RowMaterialsServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Gson gson = new Gson();
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        RowMaterialService rowMaterialsService =
                new RowMaterialServiceImpl(new RowMaterialDaoImpl(), new VendorDaoImpl());
        try {

            Long rowMaterialId = RestRequestUtil.getId(request);
            Object result = (rowMaterialId != null) ? rowMaterialsService.get(rowMaterialId) : rowMaterialsService.getAll();
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
        RowMaterialService rowMaterialsService =
                new RowMaterialServiceImpl(new RowMaterialDaoImpl(), new VendorDaoImpl());
        try {

            RowMaterialDto rowMaterialDto = gson.fromJson(reader, RowMaterialDto.class);
            RowMaterial rowMaterial = rowMaterialsService.save(rowMaterialDto);
            out.println(gson.toJson(rowMaterial));
            response.setStatus(CREATED);
        } catch (JsonSyntaxException ex) {

            System.err.println("Invalid JSON format: " + ex);
            response.setStatus(BAD_REQUEST);
        } catch (NullPointerException ex) {

            System.err.println("Empty request body: " + ex);
            response.setStatus(BAD_REQUEST);
        } catch (IllegalArgumentException ex) {

            System.err.println("Invalid request body: " + ex);
            response.setStatus(BAD_REQUEST);
        } catch (ResourceNotFoundException ex) {

            System.err.println("Resource not found: " + ex);
            response.setStatus(NOT_FOUND);
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Gson gson = new Gson();
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        BufferedReader reader = request.getReader();
        RowMaterialService rowMaterialsService =
                new RowMaterialServiceImpl(new RowMaterialDaoImpl(), new VendorDaoImpl());
        try {

            Long pathVariableId = RestRequestUtil.getId(request);
            RowMaterialDto rowMaterialDto = gson.fromJson(reader, RowMaterialDto.class);
            RowMaterial rowMaterial = rowMaterialsService.save(pathVariableId, rowMaterialDto);
            out.println(gson.toJson(rowMaterial));

        } catch (InvalidURIException ex) {

            System.err.println("Invalid URI: " + ex);
            response.setStatus(BAD_REQUEST);
        } catch (NullPointerException ex) {

            System.err.println("Empty request body: " + ex);
            response.setStatus(BAD_REQUEST);
        } catch (IllegalArgumentException ex) {

            System.err.println("Invalid request body: " + ex);
            response.setStatus(BAD_REQUEST);
        } catch (ResourceNotFoundException ex) {

            System.err.println("Resource not found: " + ex);
            response.setStatus(NOT_FOUND);
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
        RowMaterialService rowMaterialsService =
                new RowMaterialServiceImpl(new RowMaterialDaoImpl(), new VendorDaoImpl());
        try {

            Long rowMaterialId = RestRequestUtil.getId(request);
            if (rowMaterialId != null) {

                //TODO move this checking to service, throw ResourceNotFoundException
                if (!rowMaterialsService.exists(rowMaterialId)) {
                    response.setStatus(NOT_FOUND);
                    return;
                }
                rowMaterialsService.delete(rowMaterialId);

            } else {
                rowMaterialsService.deleteAll();
            }

        } catch (InvalidURIException ex) {

            System.err.println("Invalid URI: " + ex);
            response.setStatus(BAD_REQUEST);
        }
    }

}
