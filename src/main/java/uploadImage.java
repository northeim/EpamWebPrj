import by.gsu.epamlab.controllers.AbstractController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Enumeration;


@WebServlet(urlPatterns = "/uploadFile")
public class uploadImage extends AbstractController {
    protected void controllerLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        printEnumeration(req.getParameterNames());


        Part filePart = req.getPart("file");
        System.out.println("filePart = " + filePart);
        System.out.println("filePart = " + req.getParameter("file"));

    }

    void printEnumeration(Enumeration<String> enumeration) {
        System.out.println("Print Enumeration");
        while (enumeration.hasMoreElements()) {
           System.out.println(enumeration.nextElement());
        }
    }



}
