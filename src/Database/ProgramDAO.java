package Database;

import Domain.*;
import Domain.Program;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProgramDAO {

    private Connection connection;
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    /**
     * Default constructor for the ProgramDAO
     */
    public ProgramDAO() { this.connection = databaseConnection.getConn(); }

    /**
     * get a Program object for a given ProgramId
     * @param ProgramId
     * @return the Program object
     */
    public Program getProgram(int ProgramId){
        try{
            PreparedStatement pdo = connection.prepareStatement(
                    "SELECT Title,Genre,Language,Age,Playtime " +
                            "FROM Programma WHERE ProgramId=?"
            );
            pdo.setInt(1, ProgramId);
            ResultSet rs = pdo.executeQuery();

            Object[] arr = new Object[5];

            while (rs.next()) {         // Th is result data is untested
                arr[0] = rs.getString(1); // title
                arr[1] = rs.getString(2); // genre
                arr[2] = rs.getString(3); // language
                arr[3] = rs.getInt(4); // age
                arr[4] = rs.getString(5);    // playTime
            }
            return new Program(
                    ProgramId,
                    (String)arr[0],
                    (String)arr[1],
                    (String)arr[2],
                    (int)arr[3],
                    (String)arr[4]
            );

        } catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Get an ArrayList all the programs from programma table
     * @return the ArrayList with Program objects
     */
    public ArrayList<Program> getAllPrograms(){
        try{
            ArrayList<Program> programList = new ArrayList<>();
            PreparedStatement pdo = connection.prepareStatement(
                    "SELECT * FROM Programma " +
                            "LEFT JOIN Episode on Episode.ProgramId = Programma.ProgramId"
            );
            ResultSet rs = pdo.executeQuery();

            Object[] arr = new Object[4];

            while (rs.next()) {
                int programId = rs.getInt(1);    //Program id

                programList.add(new Episode(programId, rs.getString(2), rs.getString("Genre"), rs.getString("Language"), rs.getString("Playtime"), rs.getString("Vol"),  rs.getInt("Age")));

            }
            return programList;

        } catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
