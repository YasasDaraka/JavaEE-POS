package lk.ijse.pos.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.pos.bo.BoFactory;
import lk.ijse.pos.bo.custom.OrderBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.OrderDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(value = "/order")
public class OrderServlet extends HttpServlet {
    OrderBO orderBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.ORDER);

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
        String id = req.getParameter("oid");
        System.out.println(id);
        Jsonb jsonb = JsonbBuilder.create();
        try {
            OrderDTO dto = orderBO.searchOrder(id);
            if(dto != null){
                jsonb.toJson(dto,resp.getWriter());
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
            ArrayList<OrderDTO> orderDto = orderBO.getAllOrder();

            if (orderDto != null) {
                jsonb.toJson(orderDto, resp.getWriter());
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
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
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("post method log");

        try {
            Jsonb jsonb = JsonbBuilder.create();
            OrderDTO orderDTO = jsonb.fromJson(req.getReader(), OrderDTO.class);
            System.out.println(orderDTO);
            if (orderDTO == null){
                System.out.println("null");
            }
            else {System.out.println("not null");}
        } catch (Exception e) {
            e.printStackTrace();
        }



        /*if(id==null || !id.matches("C00-(0*[1-9]\\d{0,2})")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID is empty or invalid");
            return;
        } else if (name == null || !name.matches("[A-Za-z ]{5,}")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Name is empty or invalid");
            return;
        } else if (address == null || address.length() < 3) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Address is empty or invalid");
            return;
        }
        System.out.println(customer);
        try {
            boolean isSaved = customerBO.saveCustomer(new CustomerDTO(id,name,address));
            if (isSaved){
                System.out.println("Customer added");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }
        } catch (SQLException throwables) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            e.printStackTrace();
        }*/
    }
}
