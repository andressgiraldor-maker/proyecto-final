package com.example.demo.viewController;

import com.example.demo.controller.ModelFactoryController;
import com.example.demo.model.Asiento;
import com.example.demo.model.EstadoAsiento;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AsientosViewController
{

    @FXML
    private Label lblSala;

    @FXML
    private TableView<Asiento> tblAsientos;

    @FXML
    private TableColumn<Asiento, String> colCodigo;

    @FXML
    private TableColumn<Asiento, Character> colFila;

    @FXML
    private TableColumn<Asiento, Integer> colNumero;

    @FXML
    private TableColumn<Asiento, EstadoAsiento> colEstado;

    @FXML
    private TextField txtFila;

    @FXML
    private TextField txtNumero;

    @FXML
    private Button btnRegistrar;

    private ModelFactoryController modelFactoryController;
    private String codigoSala;
    private Asiento asientoSeleccionado;

    @FXML
    public void initialize()
    {
        modelFactoryController = ModelFactoryController.getInstance();

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colFila.setCellValueFactory(new PropertyValueFactory<>("fila"));
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        tblAsientos.getSelectionModel().selectedItemProperty().addListener((observable, anterior, nuevo) ->
        {
            if (nuevo != null)
            {
                seleccionarAsiento(nuevo);
            }
        });
    }

    public void setSala(String codigoSala, String nombreSala)
    {
        this.codigoSala = codigoSala;
        lblSala.setText("Asientos de " + nombreSala);
        recargarTabla();
    }

    @FXML
    private void registrarAsiento()
    {
        try
        {
            char fila = leerFila();
            int numero = leerNumero();

            modelFactoryController.agregarAsiento(codigoSala, fila, numero);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Asiento registrado correctamente");
            deseleccionar();
            recargarTabla();
        }
        catch (IllegalArgumentException | IllegalStateException e)
        {
            mostrarAlerta(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    private void modificarAsiento()
    {
        if (asientoSeleccionado == null)
        {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleccione un asiento de la tabla");
            return;
        }

        try
        {
            char filaNueva = leerFila();
            int numeroNuevo = leerNumero();

            modelFactoryController.modificarAsiento(codigoSala, asientoSeleccionado.getFila(),
                    asientoSeleccionado.getNumero(), filaNueva, numeroNuevo);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Asiento modificado correctamente");
            deseleccionar();
            recargarTabla();
        }
        catch (IllegalArgumentException | IllegalStateException e)
        {
            mostrarAlerta(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    private void eliminarAsiento()
    {
        if (asientoSeleccionado == null)
        {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleccione un asiento de la tabla");
            return;
        }

        try
        {
            modelFactoryController.eliminarAsiento(codigoSala, asientoSeleccionado.getFila(), asientoSeleccionado.getNumero());
            mostrarAlerta(Alert.AlertType.INFORMATION, "Asiento eliminado correctamente");
            deseleccionar();
            recargarTabla();
        }
        catch (IllegalArgumentException | IllegalStateException e)
        {
            mostrarAlerta(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    private void deseleccionar()
    {
        asientoSeleccionado = null;
        tblAsientos.getSelectionModel().clearSelection();

        txtFila.clear();
        txtNumero.clear();
        btnRegistrar.setDisable(false);
    }

    private void seleccionarAsiento(Asiento asiento)
    {
        asientoSeleccionado = asiento;

        txtFila.setText(String.valueOf(asiento.getFila()));
        txtNumero.setText(String.valueOf(asiento.getNumero()));
        btnRegistrar.setDisable(true);
    }

    private char leerFila()
    {
        String texto = txtFila.getText().trim().toUpperCase();

        if (texto.length() != 1 || !Character.isLetter(texto.charAt(0)))
        {
            throw new IllegalArgumentException("La fila debe ser una sola letra");
        }

        char fila = texto.charAt(0);
        return fila;
    }

    private int leerNumero()
    {
        String texto = txtNumero.getText().trim();
        int numero;

        try
        {
            numero = Integer.parseInt(texto);
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("El numero debe ser un numero entero");
        }

        return numero;
    }

    private void recargarTabla()
    {
        tblAsientos.setItems(FXCollections.observableArrayList(modelFactoryController.consultarAsientosSala(codigoSala)));
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensaje)
    {
        Alert alerta = new Alert(tipo, mensaje);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }
}
