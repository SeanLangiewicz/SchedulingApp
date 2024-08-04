import DAO.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;


public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception{


        Parent root = FXMLLoader.load(getClass().getResource("/view_controllers/LoginScreen/LoginScreen.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();


    }

    /**
     *
     * @param args Not used
     * @throws SQLException Throws an error if there is an issue connecting to SQL.
     */
    public static void main(String[] args) throws SQLException {




        DBConnection.getConnection();





        //Launch Program
        launch(args);



        //Close DB Connection
        DBConnection.closeConnection();


    }
}
