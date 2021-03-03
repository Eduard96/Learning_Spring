package org.example.dao;

import org.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.List;

/**
 * Данный паттерн используется для отделения объекта от
 * логики взаимодействия с Базой данных
 */

@Component
public class PersonDAO implements PersonDAOInterface{

    private static final String URL = "jdbc:mysql://localhost:3306/people_db";
    private static final String user = "user";
    private static final String password = "user";

    private static Connection connection;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    static {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            connection = DriverManager.getConnection(URL, user, password);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//    private final List<Person> people;
//
//    /**
//     * List.of() returns immutable object - REMEMBER IT ! ! ! <(-_-)>
//     * @return
//     */
//    private List<Person> initPeople() {
//        ArrayList<Person> arr = new ArrayList<>();
//        arr.add(new Person(ID_COUNTER,"Eduard", "Matveev", 24));
//        arr.add(new Person(++ID_COUNTER,"Artak", "Kirakosyan", 24));
//        arr.add(new Person(++ID_COUNTER,"Mkrtich", "Mkhitaryan", 23));
//        arr.add( new Person(++ID_COUNTER,"Andranik", "Nanagulyan", 24));
//        arr.add(new Person(++ID_COUNTER,"Tatyana", "Yudaeva", 50));
//        arr.add(new Person(++ID_COUNTER,"Zograb", "Matveev", 51));
//        arr.add( new Person(++ID_COUNTER,"Viktoria", "Matveeva", 28));
//
//        return arr;
//    }

    public PersonDAO() {
        //people = initPeople();
    }

    @Override
    public List<Person> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM Person;", new BeanPropertyRowMapper<>(Person.class));
//        List<Person> people = new ArrayList<>();
//        try {
//            Statement statement = connection.createStatement();
//            String SQL = "SELECT * FROM Person";
//            ResultSet resultSet = statement.executeQuery(SQL);
//
//            while (resultSet.next()) {
//                people.add(new Person(resultSet.getInt("id"),
//                        resultSet.getString("name"),
//                        resultSet.getString("surname"),
//                        resultSet.getInt("age")));
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return people;
    }

    @Override
    public Person getUserById(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?;", new BeanPropertyRowMapper<>(Person.class), id)
                .stream().findAny().orElse(null);
//        try {
////            Statement statement = connection.createStatement();
////            String SQL = String.format("SELECT * FROM Person WHERE id=%d;", id);
////            ResultSet resultSet = statement.executeQuery(SQL);
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("SELECT * FROM Person WHERE id=?;");
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            resultSet.next();
//            return new Person(resultSet.getInt("id"),
//                            resultSet.getString("name"),
//                            resultSet.getString("surname"),
//                            resultSet.getInt("age"));
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return null;
    }

    @Override
    public void persist(Person person) {
        //people.add(person);
        jdbcTemplate.update("INSERT INTO Person VALUES(?, ?, ?, ?);",
                 0, person.getName(), person.getSurname(), person.getAge());
//        try {
////            Statement statement = connection.createStatement();
////            String SQL = String.format("INSERT INTO Person VALUES(%d, '%s', '%s', %d);", person.getID(), person.getName(), person.getSurname(), person.getAge());
////            statement.executeUpdate(SQL);
//
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("INSERT INTO Person VALUES(?, ?, ?, ?);");
//            preparedStatement.setInt(1, 0);
//            preparedStatement.setString(2, person.getName());
//            preparedStatement.setString(3, person.getSurname());
//            preparedStatement.setInt(4, person.getAge());
//            preparedStatement.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
    }

    @Override
    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE Person SET name=?, surname=?, age=? WHERE id=?;",
                person.getName(), person.getSurname(), person.getAge(), id);
//        try {
////            Statement statement = connection.createStatement();
////            String SQL = String.format("UPDATE Person SET name='%s', surname='%s', age=%d WHERE id=%d;",
////                    person.getName(), person.getSurname(), person.getAge(), id);
////            statement.executeUpdate(SQL);
//
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("UPDATE Person SET name=?, surname=?, age=? WHERE id=?;");
//            preparedStatement.setString(1, person.getName());
//            preparedStatement.setString(2, person.getSurname());
//            preparedStatement.setInt(3, person.getAge());
//            preparedStatement.setInt(4, id);
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?;", id);
//        try {
////            Statement statement = connection.createStatement();
////            String SQL = String.format("DELETE FROM Person WHERE id=%d;", id);
////            statement.executeUpdate(SQL);
//
//            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Person WHERE id=?;");
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
    }
}
