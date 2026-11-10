package com.example.demo.viewController;

import com.example.demo.controller.ModelFactoryController;
import com.example.demo.model.EstadoSala;
import com.example.demo.model.Sala;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SalasViewController
{

    @FXML
    private TableView<Sala> tblSalas;

    @FXML
    private TableColumn<Sala, String> colCodigo;

    @FXML
    private TableColumn<Sala, String> colNombre;

    @FXML
    private TableColumn<Sala, Integer> colCapacidad;

    @FXML
    private TableColumn<Sala, EstadoSala> colEstado;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtFilas;

    @FXML
    private TextField txtColumnas;

    @FXML
    private Button btnRegistrar;

    private ModelFactoryController modelFactoryController;
    private Sala salaSeleccionada;

    @FXML
    public void initialize()
    {
        modelFactoryController = ModelFactoryController.getInstance();

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        tblSalas.getSelectionModel().selectedItemProperty().addListener((observable, anterior, nueva) ->
        {
            if (nueva != null)
            {
                seleccionarSala(nueva);
            }
        });

        recargarTabla();
    }

    @FXML
    private void registrarSala()
    {
        try
        {
            String codigo = leerTexto(txtCodigo, "codigo");
            String nombre = leerTexto(txtNombre, "nombre");
            int filas = leerEntero(txtFilas, "filas");
            int columnas = leerEntero(txtColumnas, "columnas");

            Sala sala = new Sala(codigo, nombre);
            sala.generarAsientos(filas, columnas);

            modelFactoryController.registrarSala(sala);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sala registrada correctamente");
            deseleccionar();
            recargarTabla();
        }
        catch (IllegalArgumentException | IllegalStateException e)
        {
            mostrarAlerta(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    private void modificarSala()
    {
        if (salaSeleccionada == null)
        {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleccione una sala de la tabla");
            return;
        }

        try
        {
            String codigo = salaSeleccionada.getCodigo();
            String nombre = leerTexto(txtNombre, "nombre");

            Sala sala = new Sala(codigo, nombre);
            sala.setEstado(salaSeleccionada.getEstado());

            modelFactoryController.modificarSala(sala);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sala modificada correctamente");
            deseleccionar();
            recargarTabla();
        }
        catch (IllegalArgumentException | IllegalStateException e)
        {
            mostrarAlerta(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    private void deshabilitarSala()
    {
        if (salaSeleccionada == null)
        {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleccione una sala de la tabla");
            return;
        }

        try
        {
            modelFactoryController.deshabilitarSala(salaSeleccionada.getCodigo());
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sala deshabilitada correctamente");
            deseleccionar();
            recargarTabla();
        }
        catch (IllegalArgumentException | IllegalStateException e)
        {
            mostrarAlerta(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    private void eliminarSala()
    {
        if (salaSeleccionada == null)
        {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleccione una sala de la tabla");
            return;
        }

        try
        {
            modelFactoryController.eliminarSala(salaSeleccionada.getCodigo());
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sala eliminada correctamente");
            deseleccionar();
            recargarTabla();
        }
        catch (IllegalArgumentException | IllegalStateException e)
        {
            mostrarAlerta(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    private void gestionarAsientos()
    {
        if (salaSeleccionada == null)
        {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleccione una sala de la tabla");
            return;
        }

        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/view/asientos-view.fxml"));
            Scene scene = new Scene(loader.load());

            AsientosViewController controlador = loader.getController();
            controlador.setSala(salaSeleccionada.getCodigo(), salaSeleccionada.getNombre());

            Stage stage = new Stage();
            stage.setTitle("Gestion de asientos");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            deseleccionar();
            recargarTabla();
        }
        catch (IOException e)
        {
            mostrarAlerta(Alert.AlertType.ERROR, "No se pudo abrir la ventana: " + e.getMessage());
        }
    }

    @FXML
    private void deseleccionar()
    {
        salaSeleccionada = null;
        tblSalas.getSelectionModel().clearSelection();

        txtCodigo.clear();
        txtCodigo.setDisable(false);
        txtNombre.clear();
        txtFilas.clear();
        txtFilas.setDisable(false);
        txtColumnas.clear();
        txtColumnas.setDisable(false);
        btnRegistrar.setDisable(false);
    }

    private void seleccionarSala(Sala sala)
    {
        salaSeleccionada = sala;

        txtCodigo.setText(sala.getCodigo());
        txtCodigo.setDisable(true);
        txtNombre.setText(sala.getNombre());
        txtFilas.clear();
        txtFilas.setDisable(true);
        txtColumnas.clear();
        txtColumnas.setDisable(true);
        btnRegistrar.setDisable(true);
    }

    private String leerTexto(TextField campo, String nombreCampo)
    {
        String texto = campo.getText().trim();

        if (texto.isEmpty())
        {
            throw new IllegalArgumentException("El campo " + nombreCampo + " es obligatorio");
        }

        return texto;
    }

    private int leerEntero(TextField campo, String nombreCampo)
    {
        String texto = leerTexto(campo, nombreCampo);
        int valor;

        try
        {
            valor = Integer.parseInt(texto);
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("El campo " + nombreCampo + " debe ser un numero entero");
        }

        return valor;
    }

    private void recargarTabla()
    {
        tblSalas.setItems(FXCollections.observableArrayList(modelFactoryController.consultarSalas()));
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensaje)
    {
        Alert alerta = new Alert(tipo, mensaje);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }
}
