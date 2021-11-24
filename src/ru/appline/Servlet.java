package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/math")
public class Servlet extends HttpServlet {
    Model model = Model.getInstanse();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        StringBuffer jb = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

        JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);
        double a = jobj.get("a").getAsDouble();
        double b = jobj.get("b").getAsDouble();
        String math =jobj.get("math").getAsString();
        double result=0;

        if (math.equals("+")){
            result=a+b;
        }
        if (math.equals("-")){
            result=a-b;
        }
        if (math.equals("*")){
            result=a*b;
        }
        if (math.equals("/")){
            result=a/b;
        }

        model.add(result);

        PrintWriter pw = response.getWriter();
        pw.print(gson.toJson(model.getResult()));
    }

}

//выполнила Маркина Ксения
