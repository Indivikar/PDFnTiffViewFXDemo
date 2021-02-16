package app;

import com.indi.pdfviewfx.PDFView;
//import com.sun.tools.javac.resources.launcher;

import java.io.File;

import org.scenicview.ScenicView;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class PDFViewApp extends Application {

    private FileChooser chooser;

    @Override
    public void start(Stage primaryStage) {
        PDFView pdfView = new PDFView();

        MenuItem loadItem = new MenuItem("Load PDF ...");
        loadItem.setAccelerator(KeyCombination.valueOf("SHORTCUT+o"));
        loadItem.setOnAction(evt -> {
            if (chooser == null) {
                chooser = new FileChooser();
                chooser.setTitle("Load PDF File");
                final ExtensionFilter filterPDF = new ExtensionFilter("PDF Files", "*.pdf");
                final ExtensionFilter filterTIFF = new ExtensionFilter("TIF Files", "*.tif", "*.tiff");
                chooser.getExtensionFilters().addAll(filterPDF, filterTIFF);
                chooser.setSelectedExtensionFilter(filterPDF);
            }

            final File file = chooser.showOpenDialog(pdfView.getScene().getWindow());
            if (file != null) {
                pdfView.loadPDF(file);
            }
        });

        try {
//        	pdfView.loadTIFF(new File(PDFViewApp.class.getResource("mehrseitig.tiff").getFile()));
//        	pdfView.loadTIFF(new File("F:\\workspace2019-09\\PDFnTiffViewFXDemo\\src\\app\\mehrseitig.tiff"));
//        	pdfView.loadPDF(PDFViewApp.class.getResourceAsStream("fong.pdf"));
          pdfView.loadPDF(PDFViewApp.class.getResourceAsStream("tesla3-owners-manual-short.pdf"));
//          pdfView.loadPDF(PDFViewApp.class.getResourceAsStream("2014-A3.pdf"));
//          pdfView.loadPDF(PDFViewApp.class.getResourceAsStream("O0-4663-BG-03-0001.pdf"));
//        pdfView.loadPDF(PDFViewApp.class.getResourceAsStream("2012- 0591.pdf"));  
//        pdfView.loadPDF(PDFViewApp.class.getResourceAsStream("!3603-2018-0021.pdf")); 
//        pdfView.loadPDF(PDFViewApp.class.getResourceAsStream("2021-A1.pdf")); 
        } catch (Exception e) {
            e.printStackTrace();
        }

        MenuItem closeItem = new MenuItem("Close PDF ...");
        closeItem.setAccelerator(KeyCombination.valueOf("SHORTCUT+c"));
        closeItem.setOnAction(evt -> pdfView.unload());
        closeItem.disableProperty().bind(Bindings.isNull(pdfView.documentProperty()));

        Menu fileMenu = new Menu("File");
        ObservableList<MenuItem> fileMenuItems = fileMenu.getItems();
        fileMenuItems.add(loadItem);
        fileMenuItems.add(closeItem);

        MenuBar menuBar = new MenuBar(fileMenu);
        menuBar.setUseSystemMenuBar(false);

        VBox.setVgrow(pdfView, Priority.ALWAYS);
        VBox box = new VBox(menuBar, pdfView);
        box.setFillWidth(true);

        Scene scene = new Scene(box);
        primaryStage.setTitle("PDF View");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(900);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
        
        ScenicView.show(scene);
    }
    
    public static void main(String[] args) {
		launch(args);
	}
}
