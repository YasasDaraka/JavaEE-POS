package lk.ijse.pos.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.pos.bo.BoFactory;
import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dto.CustomerDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(value = "/customer")
public class CustomerServlet extends HttpServlet {
    CustomerBO customerBO = (CustomerBO) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.CUSTOMER);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String info = req.getParameter("info");

        if ((info.equals("getall"))) {
            getAll(resp);
        } else if ((info.equals("search"))) {
            //search(req,resp);
        }

    }

   /* private void search(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        Connection con = null;
        String id = req.getParameter("cusId");
        System.out.println(id);
        try {
            con = DBConnection.getInstance().getConnection();
            String sql = "SELECT * FROM customer WHERE cusId = ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, id);
            ResultSet resultSet = pstm.executeQuery();
            Jsonb jsonb = JsonbBuilder.create();
            if (resultSet.next()) {
                Customer customer = new Customer(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getDouble(4));

                jsonb.toJson(customer, resp.getWriter());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to load the customer!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to load the customer!");
        }
    }
*/
    private void getAll(HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        Jsonb jsonb = JsonbBuilder.create();
        try {
            ArrayList<CustomerDTO> customerAr = customerBO.getAllCustomers();
            jsonb.toJson(customerAr,resp.getWriter());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

   /* @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Jsonb jsonb = JsonbBuilder.create();
        Customer customer = jsonb.fromJson(req.getReader(), Customer.class);
        String id = customer.getId();
        String name = customer.getName();
        String address = customer.getAddress();
        double salary = customer.getSalary();
        if(id==null || !id.matches("C00-(0*[1-9]\\d{0,2})")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID is empty or invalid");
            return;
        } else if (name == null || !name.matches("[A-Za-z ]{5,}")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Name is empty or invalid");
            return;
        } else if (address == null || address.length() < 3) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Address is empty or invalid");
            return;
        }else if (salary == 0 ) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Salary is empty or invalid");
            return;
        }
        System.out.println(customer);
        try {
            Connection con = DBConnection.getInstance().getConnection();
            String sql = "INSERT INTO customer(cusId, cusName, cusAddress, cusSalary) " +
                    "VALUES(?, ?, ?, ?)";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, id);
            pstm.setString(2, name);
            pstm.setString(3, address);
            pstm.setDouble(4, salary);

            if (pstm.executeUpdate() > 0){
                System.out.println("Customer added");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }
        } catch (SQLException throwables) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = null;
        String cusID = req.getParameter("cusId");
        if(cusID==null || !cusID.matches("C00-(0*[1-9]\\d{0,2})")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID is empty or invalid");
            return;
        }
        try {
            con = DBConnection.getInstance().getConnection();
            String sql = "DELETE FROM customer WHERE cusId = ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, cusID);
            if (pstm.executeUpdate() > 0){
                System.out.println("Customer Deleted");
                resp.setStatus(HttpServletResponse.SC_OK);
            }else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (SQLException throwables) {
            System.out.println("Sql error");
            throwables.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (ClassNotFoundException e) {
            System.out.println("Class error");
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }
    }


    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("put invoke");
        Jsonb jsonb = JsonbBuilder.create();
        Customer customer = jsonb.fromJson(req.getReader(), Customer.class);
        String id = customer.getId();
        String name = customer.getName();
        String address = customer.getAddress();
        double salary = customer.getSalary();
        System.out.println(id+name+address+salary);
        if(id==null || !id.matches("C00-(0*[1-9]\\d{0,2})")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID is empty or invalid");
            return;
        } else if (name == null || !name.matches("[A-Za-z ]{5,}")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Name is empty or invalid");
            return;
        } else if (address == null || address.length() < 3) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Address is empty or invalid");
            return;
        }else if (salary == 0 ) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Salary is empty or invalid");
            return;
        }
        try {
            Connection con = DBConnection.getInstance().getConnection();
            String sql = "UPDATE customer SET cusName = ?, cusAddress = ?, cusSalary = ? WHERE cusId = ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, name);
            pstm.setString(2, address);
            pstm.setDouble(3, salary);
            pstm.setString(4, id);

            boolean isSaved = pstm.executeUpdate() > 0;

            if (isSaved){
                System.out.println("Customer Update");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }else{
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (SQLException throwables) {
            System.out.println("Sql error");
            throwables.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (ClassNotFoundException e) {
            System.out.println("Class error");
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }*/
}
