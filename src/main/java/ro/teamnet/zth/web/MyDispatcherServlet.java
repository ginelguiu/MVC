package ro.teamnet.zth.web;

import ro.teamnet.zth.api.controllers.DepartmentController;
import ro.teamnet.zth.api.controllers.EmployeeController;
import ro.teamnet.zth.fmk.MethodAttributes;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Radu.Furculesteanu on 7/20/2017.
 */
public class MyDispatcherServlet extends HttpServlet{
    Map<String, MethodAttributes> allowedMethods = new HashMap<>();
    //List<Class> classList =

    @Override
    public void init() throws ServletException {
        EmployeeController ec = new EmployeeController();
        DepartmentController dc = new DepartmentController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodType = "GET";

        dispatchReply(req, resp, methodType);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodType = "POST";
        dispatchReply(req,resp,methodType);
    }

    private void dispatchReply(HttpServletRequest request, HttpServletResponse response, String methodType)
    {
        try
        {
            Object result = dispatch(request,methodType);
            reply(response, result);
        }
        catch(Exception e)
        {
            sendExceptionError(e);
        }
    }

    private Object dispatch(HttpServletRequest request, String methodType)
    {
        StringBuffer url = request.getRequestURL();
        String result = "Test";
        if(url.toString().contains("employees"))
        {
            if(url.toString().contains("all"))
            {
                EmployeeController ec = new EmployeeController();
                result = ec.getAllEmployees();
            }
            if(url.toString().contains("one"))
            {
                EmployeeController ec = new EmployeeController();
                result = ec.getOneEmployee();
            }
        }
        else if(url.toString().contains("departments"))
        {
            if(url.toString().contains("all"))
            {
                DepartmentController dc = new DepartmentController();
                result = dc.getAllDepartments();
            }
        }
        return result;
    }

    public void reply(HttpServletResponse response, Object result)
    {
        try
        {
            response.getWriter().write((String)result);
        }
        catch(IOException ioe)
        {
            sendExceptionError(ioe);
        }
    }

    public void sendExceptionError(Exception e)
    {
        System.out.println(e.toString());
    }



}
