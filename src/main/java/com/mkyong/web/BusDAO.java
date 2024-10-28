package com.mkyong.web;

// import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusDAO {
      private String jdbcURL;
      private String jdbcUsername;
      private String jdbcPassword;
      private Connection jdbcConnection;

    public BusDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    protected void connect() throws SQLException{
        if(jdbcConnection == null || jdbcConnection.isClosed())
           jdbcConnection=DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void disconnect() throws SQLException{
        if(jdbcConnection!=null && !jdbcConnection.isClosed()){
            jdbcConnection.close();
        }
    }

    public boolean insert(Bus bus) throws SQLException {
        String query="insert into bus_details(bus_no,Bus_name,avail_seats,customer_name) values(?,?,?,?)";
        connect();

        PreparedStatement pst = jdbcConnection.prepareStatement(query);
        // pst.setString(1,bus.getBooking_date());
        pst.setInt(1, bus.getBus_no());
        pst.setString(2, bus.getBus_name());
        pst.setInt(3, bus.getAvail_seats());
        pst.setString(4, bus.getCustomer_name());

        boolean rowInserted = pst.executeUpdate()>0;
        pst.close();
        disconnect();
        return rowInserted;
}

public List<Bus> listAllCustomers() throws SQLException {
        List<Bus> customerList = new ArrayList<Bus>();         
        String sql = "SELECT * FROM bus_details";         
        connect();         
       PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
         
        while (resultSet.next()) {
            Bus b=new Bus();
        //   b.setBooking_date(resultSet.getString(1));
          b.setBus_no(resultSet.getInt(1));
          b.setBus_name(resultSet.getString(2));
          b.setAvail_seats(resultSet.getInt(3));
          b.setCustomer_name(resultSet.getString(4));
          b.setId(resultSet.getInt(5));
          customerList.add(b);

        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return customerList;
    }

    public void delete(int id) throws SQLException{
        connect();
        String query="Delete from bus_details where id=?";
        PreparedStatement pst=jdbcConnection.prepareStatement(query);
        pst.setInt(1,id);
        // pst.setInt(2,id);
        pst.executeUpdate();

        pst.close();
        disconnect();

    }
   

    public void update(Bus bus) throws SQLException{
        String query="Update bus_details set bus_no= ? , bus_name=?,avail_seats=?,customer_name=? where id=?";
        connect();
        PreparedStatement pst=jdbcConnection.prepareStatement(query);
          pst.setInt(1, bus.getBus_no());
          pst.setString(2, bus.getBus_name());
          pst.setInt(3, bus.getAvail_seats());
          pst.setString(4, bus.getCustomer_name());
          pst.setInt(5, bus.getId());

          pst.executeUpdate();

          pst.close();
          disconnect();
    }
      
      
}
