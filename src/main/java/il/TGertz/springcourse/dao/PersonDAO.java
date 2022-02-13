package il.TGertz.springcourse.dao;

import il.TGertz.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
//    // Working with PostgreSQL DB using pure JDBC API
//    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "your postgre DB password";
//
//    // setting Connection
//    private static Connection connection;
//
//    static {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    private final JdbcTemplate jdbcTemplate;
    int rowsCount;

    // Jdbc Template
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        // Jdbc Template implementation
        //return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
        // or
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));

//    // (pure JDBC API implementation) creating Statement for DB and getting ResultSet from DB
//        List<Person> people = new ArrayList<>();
//
//        try {
//            Statement statement = connection.createStatement();
//            String SQL = "SELECT * FROM Person";
//            ResultSet resultSet = statement.executeQuery(SQL);
//
//            while (resultSet.next()){
//                Person person = new Person();
//
//                person.setId(resultSet.getInt("id"));
//                person.setName(resultSet.getString("name"));
//                person.setAge(resultSet.getInt("age"));
//                person.setEmail(resultSet.getString("email"));
//
//                people.add(person);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return people;
//    }
}
    public Person show(int id) {
        // Jdbc Template implementation
        //return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new PersonMapper()).stream().findAny().orElse(null);
        // or
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
//        Person person = null;
//    // (pure JDBC API implementation)
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Person WHERE id=?");
//
//            preparedStatement.setInt(1, id);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.next(); // will get the first person with id match
//
//            person = new Person();
//
//            person.setId(resultSet.getInt("id"));
//            person.setName(resultSet.getString("name"));
//            person.setEmail(resultSet.getString("email"));
//            person.setAge(resultSet.getInt("age"));
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        return person;
    }

    public void save(Person person){
        // Jdbc Template implementation
        rowsCount = jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class)).size();
        jdbcTemplate.update("INSERT INTO Person VALUES(?, ?, ?, ?)",person.getId(), person.getName(), person.getAge(), person.getEmail());
//    // (pure JDBC API implementation)
//        try {
//            // using Statement is vulnerable for SQL Injections
//            // in this Statement using case inserting the next value: test@gmail.com'); DROP TABLE Person; --
//            // in email input will DROP TABLE Person !!!!
////            Statement statement = connection.createStatement();
////            String SQL = "INSERT INTO Person VALUES(" + 1 + ",'"
////                    + person.getName() + "'," + person.getAge() + ",'"
////                    + person.getEmail() + "')";
////            statement.executeUpdate(SQL);
//                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Person VALUES(1, ?, ?, ?)");
//
//                preparedStatement.setString(1, person.getName());
//                preparedStatement.setInt(2, person.getAge());
//                preparedStatement.setString(3, person.getEmail());
//
//                preparedStatement.executeUpdate();
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
    }

    public void update(int id, Person updatedPerson){
        // Jdbc Template implementation
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?", updatedPerson.getName(),
                                updatedPerson.getAge(), updatedPerson.getEmail(), id);
//    // (pure JDBC API implementation)
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Person SET name=?, age=?, email=? WHERE id=?");
//
//            preparedStatement.setString(1, updatedPerson.getName());
//            preparedStatement.setInt(2, updatedPerson.getAge());
//            preparedStatement.setString(3, updatedPerson.getEmail());
//            preparedStatement.setInt(4, id);
//
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public void delete(int id) {
        // Jdbc Template implementation
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
//    // (pure JDBC API implementation)
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Person WHERE id=?");
//
//            preparedStatement.setInt(1, id);
//
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    }
}
