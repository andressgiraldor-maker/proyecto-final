package com.example.demo.viewController;

import com.example.demo.controller.ModelFactoryController;
import com.example.demo.model.Administrador;
import com.example.demo.model.Cliente;
import com.example.demo.model.Operador;
import com.example.demo.model.Persona;
import com.example.demo.model.TipoCliente;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PersonasViewController
{

    private static final String ROL_CLIENTE = "CLIENTE";
    private static final String ROL_OPERADOR = "OPERADOR";
    private static final String ROL_ADMINISTRADOR = "ADMINISTRADOR";

    @FXML
    private TableView<Persona> tblPersonas;

    @FXML
    private TableColumn<Persona, String> colIdentificacion;

    @FXML
    private TableColumn<Persona, String> colNombre;

    @FXML
    private TableColumn<Persona, String> colRol;

    @FXML
    private TableColumn<Persona, String> colDetalle;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<String> cmbRol;

    @FXML
    private TextField txtGmail;

    @FXML
    private ComboBox<TipoCliente> cmbTipoCliente;

    @FXML
    private TextField txtCodigoEmpleado;

    @FXML
    private TextField txtCodigoAdministrador;

    @FXML
    private Button btnRegistrar;

    private ModelFactoryController modelFactoryController;
    private Persona personaSeleccionada;

    @FXML
    public void initialize()
    {
        modelFactoryController = ModelFactoryController.getInstance();

        colIdentificacion.setCellValueFactory(new PropertyValueFactory<>("identificacion"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colRol.setCellValueFactory(celda ->
        {
            SimpleStringProperty rol = new SimpleStringProperty(celda.getValue().obtenerRol());
            return rol;
        });
        colDetalle.setCellValueFactory(celda ->
        {
            SimpleStringProperty detalle = new SimpleStringProperty(construirDetalle(celda.getValue()));
            return detalle;
        });

        cmbRol.getItems().setAll(ROL_CLIENTE, ROL_OPERADOR, ROL_ADMINISTRADOR);
        cmbTipoCliente.getItems().setAll(TipoCliente.values());

        cmbRol.valueProperty().addListener((observable, anterior, nuevo) -> actualizarCamposPorRol());

        tblPersonas.getSelectionModel().selectedItemProperty().addListener((observable, anterior, nueva) ->
        {
            if (nueva != null)
            {
                seleccionarPersona(nueva);
            }
        });

        actualizarCamposPorRol();
        recargarTabla();
    }

    @FXML
    private void registrarPersona()
    {
        try
        {
            String rol = cmbRol.getValue();

            if (rol == null)
            {
                throw new IllegalArgumentException("Seleccione un rol");
            }

            if (rol.equals(ROL_CLIENTE))
            {
                modelFactoryController.registrarCliente(construirCliente());
            }
            else if (rol.equals(ROL_OPERADOR))
            {
                modelFactoryController.registrarOperador(construirOperador());
            }
            else
            {
                modelFactoryController.registrarAdministrador(construirAdministrador());
            }

            mostrarAlerta(Alert.AlertType.INFORMATION, "Persona registrada correctamente");
            deseleccionar();
            recargarTabla();
        }
        catch (IllegalArgumentException | IllegalStateException e)
        {
            mostrarAlerta(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    private void modificarPersona()
    {
        if (personaSeleccionada == null)
        {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleccione una persona de la tabla");
            return;
        }

        try
        {
            if (personaSeleccionada instanceof Cliente)
            {
                modelFactoryController.modificarCliente(construirCliente());
            }
            else if (personaSeleccionada instanceof Operador)
            {
                modelFactoryController.modificarOperador(construirOperador());
            }
            else
            {
                modelFactoryController.modificarAdministrador(construirAdministrador());
            }

            mostrarAlerta(Alert.AlertType.INFORMATION, "Persona modificada correctamente");
            deseleccionar();
            recargarTabla();
        }
        catch (IllegalArgumentException | IllegalStateException e)
        {
            mostrarAlerta(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    private void eliminarPersona()
    {
        if (personaSeleccionada == null)
        {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleccione una persona de la tabla");
            return;
        }

        try
        {
            modelFactoryController.eliminarPersona(personaSeleccionada.getIdentificacion());
            mostrarAlerta(Alert.AlertType.INFORMATION, "Persona eliminada correctamente");
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
        personaSeleccionada = null;
        tblPersonas.getSelectionModel().clearSelection();

        txtIdentificacion.clear();
        txtIdentificacion.setDisable(false);
        txtNombre.clear();
        cmbRol.setValue(null);
        cmbRol.setDisable(false);
        limpiarCamposDeRol();
        btnRegistrar.setDisable(false);
    }

    private void seleccionarPersona(Persona persona)
    {
        personaSeleccionada = persona;
        limpiarCamposDeRol();

        txtIdentificacion.setText(persona.getIdentificacion());
        txtIdentificacion.setDisable(true);
        txtNombre.setText(persona.getNombre());
        cmbRol.setValue(persona.obtenerRol());
        cmbRol.setDisable(true);

        if (persona instanceof Cliente cliente)
        {
            txtGmail.setText(cliente.getGmail());
            cmbTipoCliente.setValue(cliente.getTipoCliente());
        }
        else if (persona instanceof Operador operador)
        {
            txtCodigoEmpleado.setText(operador.getCodigoEmpleado());
        }
        else if (persona instanceof Administrador administrador)
        {
            txtCodigoAdministrador.setText(administrador.getCodigoAdministrador());
        }

        btnRegistrar.setDisable(true);
    }

    private void actualizarCamposPorRol()
    {
        String rol = cmbRol.getValue();

        boolean esCliente = ROL_CLIENTE.equals(rol);
        boolean esOperador = ROL_OPERADOR.equals(rol);
        boolean esAdministrador = ROL_ADMINISTRADOR.equals(rol);

        txtGmail.setDisable(!esCliente);
        cmbTipoCliente.setDisable(!esCliente);
        txtCodigoEmpleado.setDisable(!esOperador);
        txtCodigoAdministrador.setDisable(!esAdministrador);
    }

    private void limpiarCamposDeRol()
    {
        txtGmail.clear();
        cmbTipoCliente.setValue(null);
        txtCodigoEmpleado.clear();
        txtCodigoAdministrador.clear();
    }

    private Cliente construirCliente()
    {
        String identificacion = leerTexto(txtIdentificacion, "identificacion");
        String nombre = leerTexto(txtNombre, "nombre");
        String gmail = leerTexto(txtGmail, "gmail");
        TipoCliente tipoCliente = cmbTipoCliente.getValue();

        if (!gmail.contains("@"))
        {
            throw new IllegalArgumentException("El gmail no es valido");
        }

        if (tipoCliente == null)
        {
            throw new IllegalArgumentException("Seleccione el tipo de cliente");
        }

        Cliente cliente = new Cliente(identificacion, nombre, gmail, tipoCliente);
        return cliente;
    }

    private Operador construirOperador()
    {
        String identificacion = leerTexto(txtIdentificacion, "identificacion");
        String nombre = leerTexto(txtNombre, "nombre");
        String codigoEmpleado = leerTexto(txtCodigoEmpleado, "codigo de empleado");

        Operador operador = new Operador(identificacion, nombre, codigoEmpleado);
        return operador;
    }

    private Administrador construirAdministrador()
    {
        String identificacion = leerTexto(txtIdentificacion, "identificacion");
        String nombre = leerTexto(txtNombre, "nombre");
        String codigoAdministrador = leerTexto(txtCodigoAdministrador, "codigo de administrador");

        Administrador administrador = new Administrador(identificacion, nombre, codigoAdministrador);
        return administrador;
    }

    private String construirDetalle(Persona persona)
    {
        String detalle = "";

        if (persona instanceof Cliente cliente)
        {
            detalle = cliente.getGmail() + " | " + cliente.getTipoCliente();
        }
        else if (persona instanceof Operador operador)
        {
            detalle = "Codigo empleado: " + operador.getCodigoEmpleado();
        }
        else if (persona instanceof Administrador administrador)
        {
            detalle = "Codigo administrador: " + administrador.getCodigoAdministrador();
        }

        return detalle;
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

    private void recargarTabla()
    {
        tblPersonas.setItems(FXCollections.observableArrayList(modelFactoryController.consultarPersonas()));
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensaje)
    {
        Alert alerta = new Alert(tipo, mensaje);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }
}
