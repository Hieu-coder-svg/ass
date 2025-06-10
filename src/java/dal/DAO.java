package dal;

import Models.Bookings;
import Models.Tours;
import Models.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;

public class DAO {

    public static DAO INSTANCE = new DAO();
    private static Connection con;

    public DAO() {
        if (INSTANCE == null) {
            con = new DBContext().connection;
        } else {
            INSTANCE = this;
        }
    }

    public static ArrayList<Users> getUser() {
        ArrayList<Users> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Users";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Users u = new Users();
                u.setId(rs.getInt("ID"));
                u.setName(rs.getString("Name"));
                u.setBirthday(rs.getDate("BirthDate"));
                u.setGender(rs.getBoolean("Gender"));
                u.setPhone(rs.getString("PhoneNumber"));
                u.setEmail(rs.getString("Email"));
                u.setAddress(rs.getString("Address"));
                u.setInname(rs.getString("InName"));
                u.setPassword(rs.getString("Password"));
                u.setRole(rs.getString("Role"));
                users.add(u);
            }

            rs.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    public static ArrayList<Tours> searchTours(String keywords, String destination, int duration, double minPrice, double maxPrice) {
        ArrayList<Tours> tours = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM Tours WHERE 1=1");
            ArrayList<Object> params = new ArrayList<>();

            if (keywords != null && !keywords.trim().isEmpty()) {
                sql.append(" AND (TourName LIKE ? OR Description LIKE ?)");
                params.add("%" + keywords.trim() + "%");
                params.add("%" + keywords.trim() + "%");
            }

            if (destination != null && !destination.trim().isEmpty()) {
                sql.append(" AND Destination = ?");
                params.add(destination.trim());
            }

            if (duration > 0) {
                sql.append(" AND Duration = ?");
                params.add(duration);
            }

            sql.append(" AND Price BETWEEN ? AND ?");
            params.add(minPrice);
            params.add(maxPrice);

            PreparedStatement statement = con.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Tours tour = new Tours();
                tour.setTourID(rs.getInt("TourID"));
                tour.setTourName(rs.getString("TourName"));
                tour.setDestination(rs.getString("Destination"));
                tour.setDuration(rs.getInt("Duration"));
                tour.setPrice(rs.getDouble("Price"));
                tour.setDescription(rs.getString("Description"));
                tour.setStartDate(rs.getDate("StartDate"));
                tour.setImages(rs.getString("Image"));
                tours.add(tour);
            }

            rs.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tours;
    }

    public static ArrayList<String> getUniqueDestinations() {
        ArrayList<String> destinations = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT Destination FROM Tours";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String destination = rs.getString("Destination");
                if (destination != null && !destination.trim().isEmpty()) {
                    destinations.add(destination);
                }
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return destinations;
    }

    public static void addUser(Users user) {
        try {
            String sql = "INSERT INTO Users (Name, BirthDate, Gender, PhoneNumber, Email, Address, InName, Password, Role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setDate(2, (Date) user.getBirthday());
            statement.setBoolean(3, user.isGender());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getAddress());
            statement.setString(7, user.getInname());
            statement.setString(8, user.getPassword());
            statement.setString(9, user.getRole());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Users getUserByInname(String inname) {
        Users user = null;
        try {
            String sql = "SELECT * FROM Users WHERE InName = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, inname);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                user = new Users();
                user.setId(rs.getInt("ID"));
                user.setName(rs.getString("Name"));
                user.setBirthday(rs.getDate("BirthDate"));
                user.setGender(rs.getBoolean("Gender"));
                user.setPhone(rs.getString("PhoneNumber"));
                user.setEmail(rs.getString("Email"));
                user.setAddress(rs.getString("Address"));
                user.setInname(rs.getString("InName"));
                user.setPassword(rs.getString("Password"));
                user.setRole(rs.getString("Role"));
            }

            rs.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public static Users getUserByPhone(String phone) {
        Users user = null;
        try {
            String sql = "SELECT * FROM Users WHERE PhoneNumber = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, phone);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                user = new Users();
                user.setId(rs.getInt("ID"));
                user.setName(rs.getString("Name"));
                user.setBirthday(rs.getDate("BirthDate"));
                user.setGender(rs.getBoolean("Gender"));
                user.setPhone(rs.getString("PhoneNumber"));
                user.setEmail(rs.getString("Email"));
                user.setAddress(rs.getString("Address"));
                user.setInname(rs.getString("InName"));
                user.setPassword(rs.getString("Password"));
                user.setRole(rs.getString("Role"));
            }

            rs.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public static boolean deleteUser(int id) {
        try {
            String sql = "DELETE FROM Users WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            statement.close();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false; 
        }
    }

    public static boolean updateUserRole(int id, String role) {
        try {
            String sql = "UPDATE Users SET Role = ? WHERE ID = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, role);
            statement.setInt(2, id);
            int rowsAffected = statement.executeUpdate();
            statement.close();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static Tours getTourById(int tourId) {
        Tours tour = null;
        try {
            String sql = "SELECT * FROM Tours WHERE TourID = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, tourId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                tour = new Tours();
                tour.setTourID(rs.getInt("TourID"));
                tour.setTourName(rs.getString("TourName"));
                tour.setDestination(rs.getString("Destination"));
                tour.setDuration(rs.getInt("Duration"));
                tour.setPrice(rs.getDouble("Price"));
                tour.setDescription(rs.getString("Description"));
                tour.setStartDate(rs.getDate("StartDate"));
                tour.setImages(rs.getString("Image"));
            }

            rs.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tour;
    }

    public static boolean bookTour(int userId, int tourId, int numberOfPeople, double totalPrice) {
        try {
            String sql = "INSERT INTO Bookings (UserID, TourID, BookingDate, NumberOfPeople, TotalPrice, Status) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, tourId);
            statement.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
            statement.setInt(4, numberOfPeople);
            statement.setDouble(5, totalPrice);
            statement.setString(6, "Pending");

            int rowsAffected = statement.executeUpdate();
            statement.close();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Thêm phương thức để cập nhật trạng thái booking
    public static boolean updateBookingStatus(int bookingId, String status) {
        try {
            String sql = "UPDATE Bookings SET Status = ? WHERE BookingID = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, status);
            statement.setInt(2, bookingId);
            int rowsAffected = statement.executeUpdate();
            statement.close();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public ArrayList<Bookings> getBookingsByUserId(int userId) {
        ArrayList<Bookings> bookings = new ArrayList<>();
        try {
            String sql = "SELECT b.BookingID, b.UserID, b.TourID, b.BookingDate, b.NumberOfPeople, b.TotalPrice, b.Status, " +
                         "t.TourName, t.Destination, t.Duration, t.Price, t.Description, t.StartDate, t.Image " +
                         "FROM Bookings b " +
                         "JOIN Tours t ON b.TourID = t.TourID " +
                         "WHERE b.UserID = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Bookings booking = new Bookings();
                booking.setBookingID(rs.getInt("BookingID"));
                booking.setUserID(rs.getInt("UserID"));
                booking.setTourID(rs.getInt("TourID"));
                booking.setBookingDate(rs.getDate("BookingDate"));
                booking.setNumberOfPeople(rs.getInt("NumberOfPeople"));
                booking.setTotalPrice(rs.getDouble("TotalPrice"));
                booking.setStatus(rs.getString("Status"));

                // Tạo đối tượng Tours để lưu thông tin tour
                Tours tour = new Tours();
                tour.setTourID(rs.getInt("TourID"));
                tour.setTourName(rs.getString("TourName"));
                tour.setDestination(rs.getString("Destination"));
                tour.setDuration(rs.getInt("Duration"));
                tour.setPrice(rs.getDouble("Price"));
                tour.setDescription(rs.getString("Description"));
                tour.setStartDate(rs.getDate("StartDate"));
                tour.setImages(rs.getString("Image"));

                booking.setTour(tour);
                bookings.add(booking);
            }

            rs.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bookings;
    }
    public boolean deleteBooking(int bookingId) {
        try {
            String sql = "DELETE FROM Bookings WHERE BookingID = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, bookingId);
            int rowsAffected = statement.executeUpdate();
            statement.close();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}