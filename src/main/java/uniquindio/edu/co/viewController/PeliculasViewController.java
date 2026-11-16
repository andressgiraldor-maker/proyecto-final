package uniquindio.edu.co.viewController;

import uniquindio.edu.co.controller.ModelFactoryController;
import model.ClasificacionPelicula;
import model.EstadoPelicula;
import model.GeneroPelicula;
import model.Pelicula;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PeliculasViewController
{

    @FXML
    private TableView<Pelicula> tblPeliculas;

    @FXML
    private TableColumn<Pelicula, String> colCodigo;

    @FXML
    private TableColumn<Pelicula, String> colNombre;

    @FXML
    private TableColumn<Pelicula, GeneroPelicula> colGenero;

    @FXML
    private TableColumn<Pelicula, Integer> colDuracion;

    @FXML
    private TableColumn<Pelicula, ClasificacionPelicula> colClasificacion;

    @FXML
    private TableColumn<Pelicula, EstadoPelicula> colEstado;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<GeneroPelicula> cmbGenero;

    @FXML
    private TextField txtDuracion;

    @FXML
    private ComboBox<ClasificacionPelicula> cmbClasificacion;

    @FXML
    private Button btnRegistrar;

    private ModelFactoryController modelFactoryController;
    private Pelicula peliculaSeleccionada;

    @FXML
    public void initialize()
    {
        modelFactoryController = ModelFactoryController.getInstance();

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("generoPelicula"));
        colDuracion.setCellValueFactory(new PropertyValueFactory<>("duracionMinutos"));
        colClasificacion.setCellValueFactory(new PropertyValueFactory<>("clasificacionPelicula"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estadoPelicula"));

        cmbGenero.getItems().setAll(GeneroPelicula.values());
        cmbClasificacion.getItems().setAll(ClasificacionPelicula.values());

        tblPeliculas.getSelectionModel().selectedItemProperty().addListener((observable, anterior, nueva) ->
        {
            if (nueva != null)
            {
                seleccionarPelicula(nueva);
            }
        });

        recargarTabla();
    }

    @FXML
    private void registrarPelicula()
    {
        try
        {
            Pelicula pelicula = construirPeliculaDesdeFormulario();
            modelFactoryController.registrarPelicula(pelicula);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Pelicula registrada correctamente");
            deseleccionar();
            recargarTabla();
        }
        catch (Exception e)
        {
            mostrarAlerta(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    private void modificarPelicula()
    {
        if (peliculaSeleccionada == null)
        {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleccione una pelicula de la tabla");
            return;
        }

        try
        {
            Pelicula pelicula = construirPeliculaDesdeFormulario();
            pelicula.setEstadoPelicula(peliculaSeleccionada.getEstadoPelicula());
            modelFactoryController.modificarPelicula(pelicula);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Pelicula modificada correctamente");
            deseleccionar();
            recargarTabla();
        }
        catch (Exception e)
        {
            mostrarAlerta(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    private void deshabilitarPelicula()
    {
        if (peliculaSeleccionada == null)
        {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleccione una pelicula de la tabla");
            return;
        }

        try
        {
            modelFactoryController.deshabilitarPelicula(peliculaSeleccionada.getCodigo());
            mostrarAlerta(Alert.AlertType.INFORMATION, "Pelicula deshabilitada correctamente");
            deseleccionar();
            recargarTabla();
        }
        catch (Exception e)
        {
            mostrarAlerta(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    private void habilitarPelicula()
    {
        if (peliculaSeleccionada == null)
        {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleccione una pelicula de la tabla");
            return;
        }

        try
        {
            modelFactoryController.habilitarPelicula(peliculaSeleccionada.getCodigo());
            mostrarAlerta(Alert.AlertType.INFORMATION, "Pelicula habilitada correctamente");
            deseleccionar();
            recargarTabla();
        }
        catch (Exception e)
        {
            mostrarAlerta(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    private void eliminarPelicula()
    {
        if (peliculaSeleccionada == null)
        {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleccione una pelicula de la tabla");
            return;
        }

        try
        {
            modelFactoryController.eliminarPelicula(peliculaSeleccionada.getCodigo());
            mostrarAlerta(Alert.AlertType.INFORMATION, "Pelicula eliminada correctamente");
            deseleccionar();
            recargarTabla();
        }
        catch (Exception e)
        {
            mostrarAlerta(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    private void deseleccionar()
    {
        peliculaSeleccionada = null;
        tblPeliculas.getSelectionModel().clearSelection();

        txtCodigo.clear();
        txtCodigo.setDisable(false);
        txtNombre.clear();
        cmbGenero.setValue(null);
        txtDuracion.clear();
        cmbClasificacion.setValue(null);
        btnRegistrar.setDisable(false);
    }

    private void seleccionarPelicula(Pelicula pelicula)
    {
        peliculaSeleccionada = pelicula;

        txtCodigo.setText(pelicula.getCodigo());
        txtCodigo.setDisable(true);
        txtNombre.setText(pelicula.getNombre());
        cmbGenero.setValue(pelicula.getGeneroPelicula());
        txtDuracion.setText(String.valueOf(pelicula.getDuracionMinutos()));
        cmbClasificacion.setValue(pelicula.getClasificacionPelicula());
        btnRegistrar.setDisable(true);
    }

    private Pelicula construirPeliculaDesdeFormulario()
    {
        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        GeneroPelicula genero = cmbGenero.getValue();
        ClasificacionPelicula clasificacion = cmbClasificacion.getValue();
        String textoDuracion = txtDuracion.getText().trim();

        if (codigo.isEmpty() || nombre.isEmpty() || genero == null || clasificacion == null || textoDuracion.isEmpty())
        {
            throw new IllegalArgumentException("Complete todos los campos");
        }

        int duracion;
        try
        {
            duracion = Integer.parseInt(textoDuracion);
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("La duracion debe ser un numero entero");
        }

        if (duracion <= 0)
        {
            throw new IllegalArgumentException("La duracion debe ser mayor a cero");
        }

        Pelicula pelicula = new Pelicula(codigo, nombre, genero, duracion, clasificacion, EstadoPelicula.DISPONIBLE);
        return pelicula;
    }

    private void recargarTabla()
    {
        tblPeliculas.setItems(FXCollections.observableArrayList(modelFactoryController.consultarPeliculas()));
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensaje)
    {
        Alert alerta = new Alert(tipo, mensaje);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }
}