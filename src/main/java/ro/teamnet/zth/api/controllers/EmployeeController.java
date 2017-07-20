package ro.teamnet.zth.api.controllers;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;

/**
 * Created by Ginel.Guiu on 7/20/2017.
 */
@MyController(urlPath = "/employees")
public class EmployeeController
{
    @MyRequestMethod(urlPath = "/all", methodType = "GET")
    public String getAllEmployees()
    {
        return "allEmployees";
}

    @MyRequestMethod(urlPath = "/one", methodType = "GET")
    public String getOneEmployee()
    {
        return "oneRandomEmployee";
    }

}
