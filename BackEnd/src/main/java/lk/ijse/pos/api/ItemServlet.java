package lk.ijse.pos.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.pos.bo.BoFactory;
import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dto.ItemDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(value = "/item")
public class ItemServlet extends HttpServlet {
    ItemBO itemBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.ITEM);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String info = req.getParameter("info");

        if ((info.equals("getall"))) {
            getAll(resp);
        } else if ((info.equals("search"))) {
            search(req,resp);
        }
    }
    private void search(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        String id = req.getParameter("itmCode");
        Jsonb jsonb = JsonbBuilder.create();
        try {
            ItemDTO item = itemBO.searchItem(id);
            if(item != null){
                jsonb.toJson(item,resp.getWriter());
                resp.setStatus(HttpServletResponse.SC_OK);
            }else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    private void getAll(HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        Jsonb jsonb = JsonbBuilder.create();
        try {
            ArrayList<ItemDTO> itemAr = itemBO.getAllItems();

            if(itemAr != null){
                jsonb.toJson(itemAr,resp.getWriter());
                resp.setStatus(HttpServletResponse.SC_OK);
            }else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
