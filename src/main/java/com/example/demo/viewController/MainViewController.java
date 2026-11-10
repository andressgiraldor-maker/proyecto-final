package com.example.demo.viewController;

import com.example.demo.controller.ModelFactoryController;
import com.example.demo.model.Funcion;
import com.example.demo.model.Pelicula;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainViewController
{

    @FXML
    private Label lblNombreCine;

    @FXML
    private ListView<String> lstPeliculas;

    @FXML
    private ListView<String> lstFunciones;

    private ModelFactoryController modelFactoryController;

    @FXML
    public void initialize()
    {
        modelFactoryController = ModelFactoryController.getInstance();

        lblNombreCine.setText(modelFactoryController.getNombreCine());
        recargarListas();
    }

    @FXML
    private void abrirGestionPeliculas()
    {
        abrirVentana("/com/example/demo/view/peliculas-view.fxml", "Gestion de peliculas");
    }

    @FXML
    private void abrirGestionSalas()
    {
        abrirVentana("/com/example/demo/view/salas-view.fxml", "Gestion de salas");
    }

    @FXML
    private void abrirGestionPersonas()
    {
        abrirVentana("/com/example/demo/view/personas-view.fxml", "Gestion de personas");
    }

    private void abrirVentana(String rutaFxml, String titulo)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFxml));
            Scene scene = new Scene(loader.load());

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            recargarListas();
        }
        catch (IOException e)
        {
            Alert alerta = new Alert(Alert.AlertType.ERROR, "No se pudo abrir la ventana: " + e.getMessage());
            alerta.showAndWait();
        }
    }

    private void recargarListas()
    {
        lstPeliculas.getItems().clear();
        lstFunciones.getItems().clear();
        cargarPeliculas();
        cargarFunciones();
    }

    private void cargarPeliculas()
    {
        List<Pelicula> peliculas = modelFactoryController.consultarPeliculasDisponibles();

        for (Pelicula pelicula : peliculas)
        {
            String texto = pelicula.getNombre() + " | " + pelicula.getGenero() + " | "
                    + pelicula.getDuracionMinutos() + " min | " + pelicula.getClasificacion();
            lstPeliculas.getItems().add(texto);
        }
    }

    private void cargarFunciones()
    {
        List<Funcion> funciones = modelFactoryController.consultarFunciones();

        for (Funcion funcion : funciones)
        {
            String texto = funcion.getPelicula().getNombre() + " | " + funcion.getSala().getNombre() + " | "
                    + funcion.getFecha() + " " + funcion.getHora() + " | libres: "
                    + funcion.contarAsientosDisponibles();
            lstFunciones.getItems().add(texto);
        }
    }
}
