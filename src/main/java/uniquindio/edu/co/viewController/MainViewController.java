package uniquindio.edu.co.viewController;

import uniquindio.edu.co.controller.ModelFactoryController;
import uniquindio.edu.co.model.Funcion;
import uniquindio.edu.co.model.Pelicula;
import uniquindio.edu.co.model.Sala;
import uniquindio.edu.co.model.Tarifa;
import uniquindio.edu.co.model.TipoEntrada;
import uniquindio.edu.co.model.Venta;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MainViewController
{

    @FXML
    private Label lblNombreCine;

    @FXML
    private Label lblReportes;

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
        actualizarReportes();
    }

    @FXML
    private void abrirGestionPeliculas()
    {
        abrirVentana("/uniquindio/edu/co/view/peliculas-view.fxml", "Gestion de peliculas");
    }

    @FXML
    private void abrirGestionSalas()
    {
        abrirVentana("/uniquindio/edu/co/view/salas-view.fxml", "Gestion de salas");
    }

    @FXML
    private void abrirGestionPersonas()
    {
        abrirVentana("/uniquindio/edu/co/view/personas-view.fxml", "Gestion de personas");
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
            actualizarReportes();
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

    // ===== NUEVO: REPORTES / VENTAS / FUNCIONES / TARIFAS =====

    private void actualizarReportes()
    {
        if (lblReportes == null) return;
        lblReportes.setText("Ventas: " + modelFactoryController.contarEntradasVendidas()
                + " | Ingresos: $" + modelFactoryController.calcularTotalIngresos()
                + " | Mas vendida: " + modelFactoryController.obtenerPeliculaMasVendidaNombre());
    }

    private void mostrarAlerta(String mensaje)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("CINEMA UQ");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void onVenderEntrada()
    {
        TextInputDialog d1 = new TextInputDialog("V001");
        d1.setHeaderText("Codigo venta:"); String codVenta = d1.showAndWait().orElse(null);
        TextInputDialog d2 = new TextInputDialog("F001");
        d2.setHeaderText("Codigo funcion:"); String codFuncion = d2.showAndWait().orElse(null);
        TextInputDialog d3 = new TextInputDialog("A1");
        d3.setHeaderText("Asiento (A1):"); String codAsiento = d3.showAndWait().orElse(null);
        TextInputDialog d4 = new TextInputDialog("123");
        d4.setHeaderText("ID cliente:"); String idCliente = d4.showAndWait().orElse(null);
        ChoiceDialog<TipoEntrada> d5 = new ChoiceDialog<>(TipoEntrada.GENERAL, TipoEntrada.values());
        d5.setHeaderText("Tipo entrada:"); TipoEntrada tipo = d5.showAndWait().orElse(null);
        if (codVenta == null || codFuncion == null || codAsiento == null || idCliente == null || tipo == null) return;
        try {
            Venta v = modelFactoryController.venderEntrada(codVenta, codFuncion, codAsiento, idCliente, tipo);
            mostrarAlerta("Venta exitosa! Valor: " + v.getValorPagado());
            recargarListas();
            actualizarReportes();
        } catch (Exception e) {
            mostrarAlerta("ERROR: " + e.getMessage());
        }
    }

    @FXML
    private void onCancelarVenta()
    {
        TextInputDialog d = new TextInputDialog();
        d.setHeaderText("Codigo venta a cancelar:");
        String cod = d.showAndWait().orElse(null);
        if (cod == null) return;
        try {
            modelFactoryController.cancelarVenta(cod);
            mostrarAlerta("Venta cancelada, asiento liberado!");
            recargarListas();
            actualizarReportes();
        } catch (Exception e) { mostrarAlerta("ERROR: " + e.getMessage()); }
    }

    @FXML
    private void onProgramarFuncion()
    {
        TextInputDialog d1 = new TextInputDialog("F002");
        d1.setHeaderText("Codigo funcion:"); String cod = d1.showAndWait().orElse(null);
        TextInputDialog d2 = new TextInputDialog("P001");
        d2.setHeaderText("Codigo pelicula:"); String codPeli = d2.showAndWait().orElse(null);
        TextInputDialog d3 = new TextInputDialog("S01");
        d3.setHeaderText("Codigo sala:"); String codSala = d3.showAndWait().orElse(null);
        TextInputDialog d4 = new TextInputDialog(LocalDate.now().toString());
        d4.setHeaderText("Fecha (AAAA-MM-DD):"); String fechaStr = d4.showAndWait().orElse(null);
        TextInputDialog d5 = new TextInputDialog("18:30");
        d5.setHeaderText("Hora (18:30):"); String horaStr = d5.showAndWait().orElse(null);
        TextInputDialog d6 = new TextInputDialog("15000");
        d6.setHeaderText("Valor base:"); String valorStr = d6.showAndWait().orElse(null);
        if (cod == null || codPeli == null || codSala == null || fechaStr == null || horaStr == null || valorStr == null) return;
        try {
            Pelicula p = modelFactoryController.buscarPelicula(codPeli);
            Sala s = modelFactoryController.buscarSala(codSala);
            if (p == null || s == null) throw new Exception("Pelicula o sala no existe");
            Funcion f = new Funcion(cod, p, s, LocalDate.parse(fechaStr), LocalTime.parse(horaStr), Double.parseDouble(valorStr));
            modelFactoryController.programarFuncion(f);
            mostrarAlerta("Funcion programada!");
            recargarListas();
        } catch (Exception e) { mostrarAlerta("ERROR: " + e.getMessage()); }
    }

    @FXML
    private void onGestionarTarifas()
    {
        ChoiceDialog<TipoEntrada> d1 = new ChoiceDialog<>(TipoEntrada.GENERAL, TipoEntrada.values());
        d1.setHeaderText("Tipo entrada a modificar:"); TipoEntrada tipo = d1.showAndWait().orElse(null);
        if (tipo == null) return;
        TextInputDialog d2 = new TextInputDialog("0.2");
        d2.setHeaderText("Nuevo descuento (0.0 = sin descuento, 0.2 = 20%):");
        String val = d2.showAndWait().orElse(null);
        if (val == null) return;
        try {
            modelFactoryController.modificarTarifa(new Tarifa(tipo, Double.parseDouble(val)));
            mostrarAlerta("Tarifa actualizada!");
        } catch (Exception e) { mostrarAlerta("ERROR: " + e.getMessage()); }
    }

    @FXML
    private void onVerReportes()
    {
        actualizarReportes();
        try {
            TextInputDialog d = new TextInputDialog("F001");
            d.setHeaderText("Codigo funcion para ver ocupacion (Cancelar si no quieres):");
            String cod = d.showAndWait().orElse(null);
            if (cod != null) mostrarAlerta(modelFactoryController.consultarOcupacionFuncion(cod));
        } catch (Exception e) { mostrarAlerta("ERROR: " + e.getMessage()); }
    }
}