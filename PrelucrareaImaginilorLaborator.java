
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prelucrareaimaginilorlaborator;

import java.awt.Color;
import java.awt.Paint;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.imageio.ImageIO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramBin;
import org.jfree.data.statistics.HistogramDataset;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.CvType;
import org.opencv.core.Scalar;

/**
 *
 * @bogdan
 *
 *
 * API-urile JavaFX au fost portate direct în Java, bazându-se mai mult pe
 * standarde Internet, cum ar fi CSS pentru stilizarea controalelor sau ARIA
 * pentru specificări referitoare la accesibilitate.
 *
 * Graful de Scene Implementarea unei aplicații JavaFX implică proiectarea și
 * dezvoltarea unui graf de scene (eng. Scene Graph), structură ierarhică de
 * noduri ce conţine elementele vizuale ale interfeţei grafice cu utilizatorul,
 * care poate trata diferite evenimente legate de acestea şi care poate fi
 * redată.
 *
 * Un element din graful de scene (= un nod) este identificat în mod unic, fiind
 * caracterizat printr-o clasă de stil şi un volum care îl delimitează. Fiecare
 * nod are un părinte (cu excepția nodului rădăcină), putând avea nici unul,
 * unul sau mai mulţi copii. De asemenea, pentru un astfel de element pot fi
 * definite efecte (estompări sau umbre), opacitate, transformări, mecanisme de
 * tratare a diferitelor evenimente (care vizează interacţiunea cu utilizatorul)
 * precum şi starea aplicaţiei.
 *
 * Spre diferenţă de Swing sau AWT (Abstract Window Toolkit), JavaFX conţine pe
 * lângă mecanisme de dispunere a conţinutului, controale, imagini sau obiecte
 * multimedia şi primitive pentru elemente grafice (ca fi texte sau figuri
 * geometice cu care se pot crea animaţii, folosind metodele puse la dispoziţie
 * de API-urile javafx.animation).
 *
 * API-ul javafx.scene permite construirea următoarelor conţinuturi:
 *
 * noduri: forme 2D şi 3D, imagini, conţinut multimedia şi conţinut Internet,
 * text, controale pentru interacţiunea cu utilizatorul, grafice, containere;
 * stări: transformări (poziţionări şi orientări ale nodurilor), efecte vizuale;
 * efecte: obiecte care modifică aspectul nodurilor (mecanisme de estompare,
 * umbre, reglarea culorilor). Mecanisme de Dispunere a Conţinutului Controalele
 * din graful de scene pot fi grupate în containere sau panouri în mod flexibil,
 * folosind mai multe mecanisme de dispunere a conținutului (eng. layout).
 *
 * API-ul JavaFX defineşte mai multe clase de tip container pentru dispunerea
 * elementelor, în pachetul javafx.scene.layout:
 *
 * BorderPane dispune nodurile conţinute în regiunile de sus, jos, dreapta,
 * stânga sau centru; HBox îşi aranjează conţinutul orizontal pe un singur rând;
 * VBox îşi aranjează conţinutul vertical pe o singură coloană; StackPane
 * utilizează o stivă de noduri afişând elementele unele peste altele, din spate
 * către față; GridPane permite utilizatorului să îşi definească un tabel
 * (format din rânduri şi coloane) în care să poată fi încadrate elementele
 * conţinute; FlowPane dispune elementele fie orizontal, fie vertical, în
 * funcţie de limitele specificate de programator (lungime pentru dispunere
 * orizontală, respectiv înălţime pentru dispunere verticală); TilePane plasează
 * nodurile conţinute în celule de dimensiuni uniforme; AnchorPane oferă
 * programatorilor posibilitatea de a defini noduri ancoră (referinţă) în
 * funcţie de colţurile de jos / sus, din stânga / dreapta sau raportat la
 * centrul containerului sau panoului. Diferitele moduri de dispunere pot fi
 * imbricate în cadrul unei aplicaţii JavaFX pentru a se obţine funcţionalitatea
 * dorită.
 *
 */
public class PrelucrareaImaginilorLaborator extends Application {

    BufferedImage bufferedImag;
    Label name;
    Label pixelValueLabel;
    Label caleFisier;

    @Override
    public void start(Stage mainStage) {

        //Avem nevoie de o modalitate de a alege un fisier imagine
        //Vom folosi FileChooser
        //
        ImageView imageView = new ImageView();
        name = new Label();
        pixelValueLabel = new Label();

        StackPane root = new StackPane();
        Scene scene = new Scene(root);

        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("Fisiere");
        MenuItem menuItem_Open = new MenuItem("Afiseaza Imagine");
        MenuItem menuItem_Save = new MenuItem("Salveaza Imagine");
        SeparatorMenuItem sep = new SeparatorMenuItem();
        MenuItem menuItem_Exit = new MenuItem("Iesire");
        menuFile.getItems().addAll(menuItem_Open, menuItem_Save, sep, menuItem_Exit);

        Menu menuChange = new Menu("Modificare");
        MenuItem menuItem_RGB = new MenuItem("RGB");
        MenuItem menuItem_GetPixel = new MenuItem("Get Pixel");
        MenuItem menuItem_CopyChannels = new MenuItem("Canalele RGB");
        MenuItem menuItem_Canale = new MenuItem("Canale");
        MenuItem menuItem_GrayScale = new MenuItem("GrayScale");
        MenuItem menuItem_YUV = new MenuItem("YUV");
        MenuItem menuItem_YCbCr = new MenuItem("YCbCr");
        MenuItem menuItem_Inversa = new MenuItem("Inversa");
        MenuItem menuItem_HSV = new MenuItem("HSV");
        MenuItem menuItem_Histograma = new MenuItem("Histograma");
        MenuItem menuItem_Histograma1 = new MenuItem("Histograma1");
        MenuItem menuItem_Binarizare_Simpla = new MenuItem("Binarizare_Simpla");
        MenuItem menuItem_Praguri = new MenuItem("Praguri_Binarizare");
        MenuItem menuItem_Etichetare = new MenuItem("Etichetare");
        MenuItem menuItem_Egalizare_Histograma = new MenuItem("Egalizare_Histograma");
        MenuItem menuItem_GrayScale2 = new MenuItem("GrayScale2");
        MenuItem menuItem_GrayScale3 = new MenuItem("GrayScale3");
        MenuItem menuItem_FiltruMediere3 = new MenuItem("Filtru mediere 3x3");
        MenuItem menuItem_FiltruMediere = new MenuItem("Filtru mediere 5x5");
        MenuItem menuItem_Accentuare = new MenuItem("Filtru accentuare");
        MenuItem menuItem_Median = new MenuItem("Filtru median");
        MenuItem menuItem_Laplacian = new MenuItem("Filtru Laplacian");
        MenuItem menuItem_DilatareEroziune = new MenuItem("Filtru de dilatare/eroziune");
        MenuItem menuItem_DetectieContururi = new MenuItem("Detectie contururi");
        MenuItem menuItem_LZW_compress = new MenuItem("Compresia imaginii cu algoritmul LZW");
        menuChange.getItems().add(menuItem_LZW_compress);
        MenuItem menuItem_huffman = new MenuItem("Comprimare Huffman");
        menuItem_huffman.setDisable(false);
        menuChange.getItems().addAll(menuItem_huffman);
        MenuItem menuItem_LZW_Decompresie = new MenuItem("LZW_Decompresie");
        menuItem_huffman.setDisable(false);
        menuChange.getItems().addAll(menuItem_LZW_Decompresie);
        MenuItem menuItem_Decompresie_Huffman = new MenuItem("Decompresie_Huffman");
        menuItem_huffman.setDisable(false);
        menuChange.getItems().addAll(menuItem_Decompresie_Huffman);

        menuItem_RGB.setDisable(true);
        menuChange.getItems().addAll(/*menuItem_RGB,*/menuItem_GetPixel, menuItem_CopyChannels, menuItem_Canale, menuItem_GrayScale, menuItem_YUV, menuItem_YCbCr,
                menuItem_Inversa, menuItem_HSV, menuItem_Histograma, menuItem_Histograma1, menuItem_Binarizare_Simpla, menuItem_Praguri, menuItem_Etichetare, 
                menuItem_Egalizare_Histograma,menuItem_GrayScale2, menuItem_GrayScale3, menuItem_FiltruMediere3, menuItem_FiltruMediere,
                menuItem_Accentuare, menuItem_Median, menuItem_Laplacian, menuItem_DilatareEroziune, menuItem_DetectieContururi);

        menuBar.getMenus().addAll(menuFile, menuChange);

        VBox vbox = new VBox(menuBar);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 10, 0, 10));

        ScrollPane sp = new ScrollPane();
        vbox.getChildren().add(sp);

        menuItem_Open.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Afiseaza Imagine");

            File file = fileChooser.showOpenDialog(mainStage);
            if (file != null) {
                try {
                    VBox vbOpen = new VBox();

                    name = new Label(file.getAbsolutePath());
                    bufferedImag = ImageIO.read(file);

                    BufferedImage imageN = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_ARGB);

                    for (int y = 0; y < bufferedImag.getHeight(); y++) {
                        for (int x = 0; x < bufferedImag.getWidth(); x++) {
                            //Retrieving contents of a pixel
                            int pixel = bufferedImag.getRGB(x, y);
                            //Creating a Color object from pixel value
                            Color color = new Color(pixel, true);
                            //Retrieving the R G B values
                            int alpha = color.getAlpha();
                            int red = color.getRed();
                            int green = color.getGreen();
                            int blue = color.getBlue();
                            Color myWhite = new Color(red, green, blue, alpha);
                            imageN.setRGB(x, y, myWhite.getRGB());
                        }
                    }

                    Image image = SwingFXUtils.toFXImage(imageN, null);

                    vbOpen.getChildren().addAll(name, imageView);

                    imageView.setFitHeight(400);
                    imageView.setPreserveRatio(true);
                    imageView.setImage(image);
                    imageView.setSmooth(true);
                    imageView.setCache(true);

                    sp.setContent(vbOpen);

                    menuBar.getMenus().get(1).getItems().get(0).setDisable(false);

                } catch (IOException ex) {
                    Logger.getLogger(PrelucrareaImaginilorLaborator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        menuItem_Save.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Salvare Imagine");

            File file = fileChooser.showSaveDialog(mainStage);
            if (file != null) {
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(), null), "png", file);
                } catch (IOException ex) {
                    Logger.getLogger(PrelucrareaImaginilorLaborator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        //Tema 1 
        menuItem_RGB.setOnAction((ActionEvent event) -> {
            ScrollPane spR = new ScrollPane();
            ScrollPane spG = new ScrollPane();
            ScrollPane spB = new ScrollPane();
            ScrollPane spRGB = new ScrollPane();

            BufferedImage imageR = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage imageG = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage imageB = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage imageRGB = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);

            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                    //Retrieving contents of a pixel
                    int pixel = bufferedImag.getRGB(x, y);
                    //Creating a Color object from pixel value
                    Color color = new Color(pixel, true);
                    //Retrieving the R G B values
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    Color myR = new Color(red, 0, 0);
                    Color myG = new Color(0, green, 0);
                    Color myB = new Color(0, 0, blue);
                    imageR.setRGB(x, y, myR.getRGB());
                    imageG.setRGB(x, y, myG.getRGB());
                    imageB.setRGB(x, y, myB.getRGB());
                    imageRGB.setRGB(x, y, pixel);
                }
            }

            Image imgR = SwingFXUtils.toFXImage(imageR, null);
            ImageView imageViewR = new ImageView();
            imageViewR.setFitHeight(400);
            imageViewR.setPreserveRatio(true);
            imageViewR.setImage(imgR);
            imageViewR.setSmooth(true);
            imageViewR.setCache(true);
            spR.setContent(imageViewR);

            Image imgG = SwingFXUtils.toFXImage(imageG, null);
            ImageView imageViewG = new ImageView();
            imageViewG.setFitHeight(400);
            imageViewG.setPreserveRatio(true);
            imageViewG.setImage(imgG);
            imageViewG.setSmooth(true);
            imageViewG.setCache(true);
            spG.setContent(imageViewG);

            Image imgB = SwingFXUtils.toFXImage(imageB, null);
            ImageView imageViewB = new ImageView();
            imageViewB.setFitHeight(400);
            imageViewB.setPreserveRatio(true);
            imageViewB.setImage(imgB);
            imageViewB.setSmooth(true);
            imageViewB.setCache(true);
            spB.setContent(imageViewB);

            VBox vbRGB = new VBox();
            VBox vbR = new VBox();
            VBox vbG = new VBox();
            VBox vbB = new VBox();

            Image imgRGB = SwingFXUtils.toFXImage(imageRGB, null);
            ImageView imageViewRGB = new ImageView();
            imageViewRGB.setFitHeight(400);
            imageViewRGB.setPreserveRatio(true);
            imageViewRGB.setImage(imgRGB);
            imageViewRGB.setSmooth(true);
            imageViewRGB.setCache(true);

            Slider sliderRGB = new Slider();
            sliderRGB.setMin(0);
            sliderRGB.setMax(255);
            sliderRGB.setValue(0);
            sliderRGB.setShowTickLabels(true);
            sliderRGB.setShowTickMarks(true);
            sliderRGB.setMajorTickUnit(50);
            sliderRGB.setMinorTickCount(5);
            sliderRGB.setBlockIncrement(1);
            sliderRGB.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                int dif = new_val.intValue();// - old_val.intValue();
                for (int y = 0; y < bufferedImag.getHeight(); y++) {
                    for (int x = 0; x < bufferedImag.getWidth(); x++) {
                        int pixel = bufferedImag.getRGB(x, y);
                        Color color = new Color(pixel, true);
                        //Retrieving the R G B values
                        int red = this.adjustColor(color.getRed(), dif);
                        int green = this.adjustColor(color.getGreen(), dif);
                        int blue = this.adjustColor(color.getBlue(), dif);

                        Color myR = new Color(red, 0, 0);
                        Color myG = new Color(0, green, 0);
                        Color myB = new Color(0, 0, blue);
                        Color myRGB = new Color(red, green, blue);

                        imageRGB.setRGB(x, y, myRGB.getRGB());
                        imageR.setRGB(x, y, myR.getRGB());
                        imageG.setRGB(x, y, myG.getRGB());
                        imageB.setRGB(x, y, myB.getRGB());
                    }
                }
                imageViewR.setImage(SwingFXUtils.toFXImage(imageR, null));
                imageViewG.setImage(SwingFXUtils.toFXImage(imageG, null));
                imageViewB.setImage(SwingFXUtils.toFXImage(imageB, null));
                imageViewRGB.setImage(SwingFXUtils.toFXImage(imageRGB, null));
            });

            //tema1
            Slider sliderR = new Slider();
            sliderR.setMin(0);
            sliderR.setMax(255);
            sliderR.setValue(0);
            sliderR.setShowTickLabels(true);
            sliderR.setShowTickMarks(true);
            sliderR.setMajorTickUnit(50);
            sliderR.setMinorTickCount(5);
            sliderR.setBlockIncrement(1);
            sliderR.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                int dif = new_val.intValue();// - old_val.intValue();
                for (int y = 0; y < bufferedImag.getHeight(); y++) {
                    for (int x = 0; x < bufferedImag.getWidth(); x++) {
                        int pixel = bufferedImag.getRGB(x, y);
                        Color color = new Color(pixel, true);
                        //Retrieving the R G B values
                        int red = this.adjustColor(color.getRed(), dif);
                        Color myR = new Color(red, 0, 0);
                        imageR.setRGB(x, y, myR.getRGB());

                    }
                }
                imageViewR.setImage(SwingFXUtils.toFXImage(imageR, null));

            });

            Slider sliderG = new Slider();
            sliderG.setMin(0);
            sliderG.setMax(255);
            sliderG.setValue(0);
            sliderG.setShowTickLabels(true);
            sliderG.setShowTickMarks(true);
            sliderG.setMajorTickUnit(50);
            sliderG.setMinorTickCount(5);
            sliderG.setBlockIncrement(1);
            sliderG.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                int dif = new_val.intValue();// - old_val.intValue();
                for (int y = 0; y < bufferedImag.getHeight(); y++) {
                    for (int x = 0; x < bufferedImag.getWidth(); x++) {
                        int pixel = bufferedImag.getRGB(x, y);
                        Color color = new Color(pixel, true);
                        //Retrieving the R G B values
                        int green = this.adjustColor(color.getGreen(), dif);
                        Color myG = new Color(0, green, 0);
                        imageG.setRGB(x, y, myG.getRGB());
                    }
                }
                imageViewG.setImage(SwingFXUtils.toFXImage(imageG, null));
            });

            Slider sliderB = new Slider();
            sliderB.setMin(0);
            sliderB.setMax(255);
            sliderB.setValue(0);
            sliderB.setShowTickLabels(true);
            sliderB.setShowTickMarks(true);
            sliderB.setMajorTickUnit(50);
            sliderB.setMinorTickCount(5);
            sliderB.setBlockIncrement(1);
            sliderB.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                int dif = new_val.intValue();// - old_val.intValue();
                for (int y = 0; y < bufferedImag.getHeight(); y++) {
                    for (int x = 0; x < bufferedImag.getWidth(); x++) {
                        int pixel = bufferedImag.getRGB(x, y);
                        Color color = new Color(pixel, true);
                        //Retrieving the R G B values
                        int blue = this.adjustColor(color.getBlue(), dif);
                        Color myB = new Color(0, 0, blue);
                        imageB.setRGB(x, y, myB.getRGB());
                    }
                }
                imageViewB.setImage(SwingFXUtils.toFXImage(imageB, null));

            });

            vbRGB.getChildren().addAll(imageViewRGB, sliderRGB);
            spRGB.setContent(vbRGB);

            vbR.getChildren().addAll(imageViewR, sliderR);
            spR.setContent(vbR);

            vbG.getChildren().addAll(imageViewG, sliderG);
            spG.setContent(vbG);

            vbB.getChildren().addAll(imageViewB, sliderB);
            spB.setContent(vbB);

            Stage secondStage = new Stage();

            Scene sceneRGB = new Scene(new HBox(), 400, 350);
            ((HBox) sceneRGB.getRoot()).getChildren().addAll(spR, spG, spB, spRGB);

            secondStage.setTitle(name.getText());
            secondStage.setScene(sceneRGB);
            secondStage.show();
        });

        //Get pixel
        menuItem_GetPixel.setOnAction((ActionEvent event) -> {
            ScrollPane SP = new ScrollPane();
            Label label = new Label();
            Label labelCuloare = new Label();

            BufferedImage imageN = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);

            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                    //Retrieving contents of a pixel
                    int pixel = bufferedImag.getRGB(x, y);
                    //Creating a Color object from pixel value
                    Color color = new Color(pixel, true);
                    //Retrieving the R G B values
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    Color myWhite = new Color(red, green, blue);
                    imageN.setRGB(x, y, myWhite.getRGB());
                }
            }

            Image image = SwingFXUtils.toFXImage(imageN, null);
            ImageView imageViewRGB = new ImageView();
            //imageViewRGB.setFitHeight(400);
            imageViewRGB.setPreserveRatio(true);
            imageViewRGB.setImage(image);
            imageViewRGB.setSmooth(true);
            imageViewRGB.setCache(true);
            imageViewRGB.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventClick -> {

                int x = (int) eventClick.getX();
                int y = (int) eventClick.getY();
                System.out.println(x);
                System.out.println(y);
                int pixelSelectat = bufferedImag.getRGB(x, y);
                Color myColor = new Color(pixelSelectat, true);
                int r = myColor.getRed();
                int g = myColor.getGreen();
                int b = myColor.getBlue();
                System.out.println("R:" + r);
                System.out.println("G:" + g);
                System.out.println("B:" + b);
                label.setText("X:" + x + "  Y:" + y + "  R:" + r + "  G:" + g + "  B:" + b);
                labelCuloare.setText("                                       ");

                labelCuloare.setStyle("-fx-background-color: " + "RGB(" + r + "," + g + "," + b + ")" + ";");

            });

            SP.setContent(imageViewRGB);

            Stage secondStage = new Stage();
            VBox vboxRGB = new VBox();
            vboxRGB.setAlignment(Pos.CENTER);
            vboxRGB.setSpacing(10);
            vboxRGB.setPadding(new Insets(0, 10, 0, 10));
            vboxRGB.getChildren().addAll(SP);
            vboxRGB.getChildren().addAll(label);
            vboxRGB.getChildren().addAll(labelCuloare);
            Scene sceneRGB = new Scene(new VBox(), 800, 700);
            ((VBox) sceneRGB.getRoot()).getChildren().addAll(vboxRGB);

            secondStage.setTitle("Get Pixel");
            secondStage.setScene(sceneRGB);
            secondStage.show();
        });

        //Copiere canale
        menuItem_CopyChannels.setOnAction((ActionEvent event) -> {
            if (bufferedImag != null) {
                BufferedImage imageR = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
                BufferedImage imageG = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
                BufferedImage imageB = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);

                for (int y = 0; y < bufferedImag.getHeight(); y++) {
                    for (int x = 0; x < bufferedImag.getWidth(); x++) {
                        int pixel = bufferedImag.getRGB(x, y);
                        Color color = new Color(pixel, true);
                        int red = color.getRed();
                        int green = color.getGreen();
                        int blue = color.getBlue();
                        Color myR = new Color(red, 0, 0);
                        Color myG = new Color(0, green, 0);
                        Color myB = new Color(0, 0, blue);
                        imageR.setRGB(x, y, myR.getRGB());
                        imageG.setRGB(x, y, myG.getRGB());
                        imageB.setRGB(x, y, myB.getRGB());
                    }
                }

                Image imgR = SwingFXUtils.toFXImage(imageR, null);
                ImageView imageViewR = new ImageView(imgR);

                Image imgG = SwingFXUtils.toFXImage(imageG, null);
                ImageView imageViewG = new ImageView(imgG);

                Image imgB = SwingFXUtils.toFXImage(imageB, null);
                ImageView imageViewB = new ImageView(imgB);

                HBox hbox = new HBox(10);
                hbox.getChildren().addAll(imageViewR, imageViewG, imageViewB);

                Stage channelsStage = new Stage();
                channelsStage.setScene(new Scene(hbox));
                channelsStage.setTitle("Canale R, G, B");
                channelsStage.show();
            }
        });

        //Canale RGB
        menuItem_Canale.setOnAction((ActionEvent event) -> {
            ScrollPane spR = new ScrollPane();
            ScrollPane spG = new ScrollPane();
            ScrollPane spB = new ScrollPane();
            ScrollPane spRGB = new ScrollPane();

            BufferedImage imageR = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage imageG = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage imageB = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage imageRGB = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);

            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                    //Retrieving contents of a pixel
                    int pixel = bufferedImag.getRGB(x, y);
                    //Creating a Color object from pixel value
                    Color color = new Color(pixel, true);
                    //Retrieving the R G B values
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    Color myR = new Color(red, 0, 0);
                    Color myG = new Color(0, green, 0);
                    Color myB = new Color(0, 0, blue);
                    imageR.setRGB(x, y, myR.getRGB());
                    imageG.setRGB(x, y, myG.getRGB());
                    imageB.setRGB(x, y, myB.getRGB());
                    imageRGB.setRGB(x, y, pixel);
                }
            }

            Image imgR = SwingFXUtils.toFXImage(imageR, null);
            ImageView imageViewR = new ImageView();
            imageViewR.setFitHeight(400);
            imageViewR.setPreserveRatio(true);
            imageViewR.setImage(imgR);
            imageViewR.setSmooth(true);
            imageViewR.setCache(true);
            spR.setContent(imageViewR);

            Image imgG = SwingFXUtils.toFXImage(imageG, null);
            ImageView imageViewG = new ImageView();
            imageViewG.setFitHeight(400);
            imageViewG.setPreserveRatio(true);
            imageViewG.setImage(imgG);
            imageViewG.setSmooth(true);
            imageViewG.setCache(true);
            spG.setContent(imageViewG);

            Image imgB = SwingFXUtils.toFXImage(imageB, null);
            ImageView imageViewB = new ImageView();
            imageViewB.setFitHeight(400);
            imageViewB.setPreserveRatio(true);
            imageViewB.setImage(imgB);
            imageViewB.setSmooth(true);
            imageViewB.setCache(true);
            spB.setContent(imageViewB);

            VBox vbRGB = new VBox();

            Image imgRGB = SwingFXUtils.toFXImage(imageRGB, null);
            ImageView imageViewRGB = new ImageView();
            imageViewRGB.setFitHeight(400);
            imageViewRGB.setPreserveRatio(true);
            imageViewRGB.setImage(imgRGB);
            imageViewRGB.setSmooth(true);
            imageViewRGB.setCache(true);

            Slider sliderRGB = new Slider();
            sliderRGB.setMin(0);
            sliderRGB.setMax(255);
            sliderRGB.setValue(0);
            sliderRGB.setShowTickLabels(true);
            sliderRGB.setShowTickMarks(true);
            sliderRGB.setMajorTickUnit(50);
            sliderRGB.setMinorTickCount(5);
            sliderRGB.setBlockIncrement(1);
            sliderRGB.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                int dif = new_val.intValue();// - old_val.intValue();
                for (int y = 0; y < bufferedImag.getHeight(); y++) {
                    for (int x = 0; x < bufferedImag.getWidth(); x++) {
                        int pixel = bufferedImag.getRGB(x, y);
                        Color color = new Color(pixel, true);
                        //Retrieving the R G B values
                        int red = this.adjustColor(color.getRed(), dif);
                        int green = this.adjustColor(color.getGreen(), dif);
                        int blue = this.adjustColor(color.getBlue(), dif);

                        Color myR = new Color(red, 0, 0);
                        Color myG = new Color(0, green, 0);
                        Color myB = new Color(0, 0, blue);
                        Color myRGB = new Color(red, green, blue);

                        imageRGB.setRGB(x, y, myRGB.getRGB());
                        imageR.setRGB(x, y, myR.getRGB());
                        imageG.setRGB(x, y, myG.getRGB());
                        imageB.setRGB(x, y, myB.getRGB());
                    }
                }
                imageViewR.setImage(SwingFXUtils.toFXImage(imageR, null));
                imageViewG.setImage(SwingFXUtils.toFXImage(imageG, null));
                imageViewB.setImage(SwingFXUtils.toFXImage(imageB, null));
                imageViewRGB.setImage(SwingFXUtils.toFXImage(imageRGB, null));
            });

            vbRGB.getChildren().addAll(imageViewRGB, sliderRGB);
            spRGB.setContent(vbRGB);

            Stage secondStage = new Stage();

            Scene sceneRGB = new Scene(new HBox(), 1500, 800);
            ((HBox) sceneRGB.getRoot()).getChildren().addAll(spR, spG, spB, spRGB);

            secondStage.setTitle("Canale");
            secondStage.setScene(sceneRGB);
            secondStage.show();
        });

        //Tema 2 meniu GrayScale
        menuItem_GrayScale.setOnAction((ActionEvent event) -> {
            ScrollPane gr1 = new ScrollPane();
            ScrollPane gr2 = new ScrollPane();
            ScrollPane gr3 = new ScrollPane();

            BufferedImage imageGr1 = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage imageGr2 = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage imageGr3 = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);

            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                    //Retrieving contents of a pixel
                    int pixel = bufferedImag.getRGB(x, y);
                    //Creating a Color object from pixel value
                    Color color = new Color(pixel, true);
                    //Retrieving the R G B values
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    int GR1 = (red + green + blue) / 3;
                    int GR2 = (int) (0.299 * red + 0.587 * green + 0.114 * blue);
                    int minim = Math.min(Math.min(red, green), blue);
                    int maxim = Math.max(Math.max(red, green), blue);
                    int GR3 = minim / 2 + maxim / 2;
                    Color myGr1 = new Color(GR1, GR1, GR1);
                    Color myGr2 = new Color(GR2, GR2, GR2);
                    Color myGr3 = new Color(GR3, GR3, GR3);
                    imageGr1.setRGB(x, y, myGr1.getRGB());
                    imageGr2.setRGB(x, y, myGr2.getRGB());
                    imageGr3.setRGB(x, y, myGr3.getRGB());
                }
            }
            Image ImageGray1 = SwingFXUtils.toFXImage(imageGr1, null);
            ImageView imageViewgr1 = new ImageView();
            imageViewgr1.setFitHeight(400);
            imageViewgr1.setPreserveRatio(true);
            imageViewgr1.setImage(ImageGray1);
            imageViewgr1.setSmooth(true);
            imageViewgr1.setCache(true);

            Image ImageGray2 = SwingFXUtils.toFXImage(imageGr2, null);
            ImageView imageViewgr2 = new ImageView();
            imageViewgr2.setFitHeight(400);
            imageViewgr2.setPreserveRatio(true);
            imageViewgr2.setImage(ImageGray2);
            imageViewgr2.setSmooth(true);
            imageViewgr2.setCache(true);

            Image ImageGray3 = SwingFXUtils.toFXImage(imageGr3, null);
            ImageView imageViewgr3 = new ImageView();
            imageViewgr3.setFitHeight(400);
            imageViewgr3.setPreserveRatio(true);
            imageViewgr3.setImage(ImageGray3);
            imageViewgr3.setSmooth(true);
            imageViewgr3.setCache(true);

            gr1.setContent(imageViewgr1);
            gr2.setContent(imageViewgr2);
            gr3.setContent(imageViewgr3);

            Stage secondStage = new Stage();
            VBox vboxRGB = new VBox();
            vboxRGB.setAlignment(Pos.CENTER);
            vboxRGB.setSpacing(10);
            vboxRGB.setPadding(new Insets(0, 10, 0, 10));
            vboxRGB.getChildren().addAll(gr1, gr2, gr3);

            Scene sceneRGB = new Scene(new VBox(), 800, 700);
            ((VBox) sceneRGB.getRoot()).getChildren().addAll(vboxRGB);

            /*vboxRGB.getChildren().addAll(imageViewgr1);
            gr1.setContent(vboxRGB);*/
            secondStage.setTitle("Gray");
            secondStage.setScene(sceneRGB);
            secondStage.show();

        });

        //Tema3
        //YUV
        menuItem_YUV.setOnAction((ActionEvent event) -> {
            ScrollPane SP2 = new ScrollPane();
            BufferedImage YUV = getYUV();

            Image image = SwingFXUtils.toFXImage(YUV, null);
            ImageView imageViewRGB = new ImageView();
            imageViewRGB.setFitHeight(400);
            imageViewRGB.setPreserveRatio(true);
            imageViewRGB.setImage(image);
            imageViewRGB.setSmooth(true);
            imageViewRGB.setCache(true);

            SP2.setContent(imageViewRGB);

            Stage secondStage = new Stage();
            VBox vboxRGB = new VBox();
            vboxRGB.setAlignment(Pos.CENTER);
            vboxRGB.setSpacing(10);
            vboxRGB.setPadding(new Insets(0, 10, 0, 10));
            vboxRGB.getChildren().addAll(SP2);

            Scene sceneRGB = new Scene(new VBox(), 800, 700);
            ((VBox) sceneRGB.getRoot()).getChildren().addAll(vboxRGB);

            secondStage.setTitle("YUV");
            secondStage.setScene(sceneRGB);
            secondStage.show();
        });

        //YCbCr
        menuItem_YCbCr.setOnAction((ActionEvent event) -> {
            ScrollPane SP2 = new ScrollPane();
            BufferedImage YUV = getYCbCr();

            Image image = SwingFXUtils.toFXImage(YUV, null);
            ImageView imageViewRGB = new ImageView();
            imageViewRGB.setFitHeight(400);
            imageViewRGB.setPreserveRatio(true);
            imageViewRGB.setImage(image);
            imageViewRGB.setSmooth(true);
            imageViewRGB.setCache(true);

            SP2.setContent(imageViewRGB);

            Stage secondStage = new Stage();
            VBox vboxRGB = new VBox();
            vboxRGB.setAlignment(Pos.CENTER);
            vboxRGB.setSpacing(10);
            vboxRGB.setPadding(new Insets(0, 10, 0, 10));
            vboxRGB.getChildren().addAll(SP2);

            Scene sceneRGB = new Scene(new VBox(), 800, 700);
            ((VBox) sceneRGB.getRoot()).getChildren().addAll(vboxRGB);

            secondStage.setTitle("YCbCr");
            secondStage.setScene(sceneRGB);
            secondStage.show();

        });

        //Inversa
        menuItem_Inversa.setOnAction((ActionEvent event) -> {
            ScrollPane SP = new ScrollPane();
            ScrollPane SP_R = new ScrollPane();
            ScrollPane SP_G = new ScrollPane();
            ScrollPane SP_B = new ScrollPane();

            BufferedImage inversa = getInverse();

            BufferedImage imageR = new BufferedImage(inversa.getWidth(), inversa.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage imageG = new BufferedImage(inversa.getWidth(), inversa.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage imageB = new BufferedImage(inversa.getWidth(), inversa.getHeight(), BufferedImage.TYPE_INT_RGB);

            for (int y = 0; y < inversa.getHeight(); y++) {
                for (int x = 0; x < inversa.getWidth(); x++) {
                    //Retrieving contents of a pixel
                    int pixel = inversa.getRGB(x, y);
                    //Creating a Color object from pixel value
                    Color color = new Color(pixel, true);
                    //Retrieving the R G B values
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    Color myR = new Color(0, green, blue);
                    Color myG = new Color(red, 0, blue);
                    Color myB = new Color(red, green, 0);
                    imageR.setRGB(x, y, myR.getRGB());
                    imageG.setRGB(x, y, myG.getRGB());
                    imageB.setRGB(x, y, myB.getRGB());
                }
            }

            Image image = SwingFXUtils.toFXImage(inversa, null);
            ImageView imageViewRGB = new ImageView();
            imageViewRGB.setFitHeight(400);
            imageViewRGB.setPreserveRatio(true);
            imageViewRGB.setImage(image);
            imageViewRGB.setSmooth(true);
            imageViewRGB.setCache(true);

            Image imageRed = SwingFXUtils.toFXImage(imageR, null);
            ImageView imageViewR = new ImageView();
            imageViewR.setFitHeight(400);
            imageViewR.setPreserveRatio(true);
            imageViewR.setImage(imageRed);
            imageViewR.setSmooth(true);
            imageViewR.setCache(true);

            Image imageGreen = SwingFXUtils.toFXImage(imageG, null);
            ImageView imageViewG = new ImageView();
            imageViewG.setFitHeight(400);
            imageViewG.setPreserveRatio(true);
            imageViewG.setImage(imageGreen);
            imageViewG.setSmooth(true);
            imageViewG.setCache(true);

            Image imageBlue = SwingFXUtils.toFXImage(imageB, null);
            ImageView imageViewB = new ImageView();
            imageViewB.setFitHeight(400);
            imageViewB.setPreserveRatio(true);
            imageViewB.setImage(imageBlue);
            imageViewB.setSmooth(true);
            imageViewB.setCache(true);

            SP.setContent(imageViewRGB);
            SP_R.setContent(imageViewR);
            SP_G.setContent(imageViewG);
            SP_B.setContent(imageViewB);

            Stage secondStage = new Stage();
            HBox hboxRGB = new HBox();
            hboxRGB.setAlignment(Pos.CENTER);
            hboxRGB.setSpacing(10);
            hboxRGB.setPadding(new Insets(0, 10, 0, 10));
            hboxRGB.getChildren().addAll(SP);
            hboxRGB.getChildren().addAll(SP_R);
            hboxRGB.getChildren().addAll(SP_G);
            hboxRGB.getChildren().addAll(SP_B);

            Scene sceneRGB = new Scene(new VBox(), 1500, 800);
            ((VBox) sceneRGB.getRoot()).getChildren().addAll(hboxRGB);

            secondStage.setTitle("Inversa");
            secondStage.setScene(sceneRGB);
            secondStage.show();
        });

        //Tema 4
        //HSV
        menuItem_HSV.setOnAction((ActionEvent event) -> {
            ScrollPane SP2 = new ScrollPane();
            BufferedImage HSV = getHSV();

            Image image = SwingFXUtils.toFXImage(HSV, null);
            ImageView imageViewRGB = new ImageView();
            imageViewRGB.setFitHeight(400);
            imageViewRGB.setPreserveRatio(true);
            imageViewRGB.setImage(image);
            imageViewRGB.setSmooth(true);
            imageViewRGB.setCache(true);

            SP2.setContent(imageViewRGB);

            Stage secondStage = new Stage();
            VBox vboxRGB = new VBox();
            vboxRGB.setAlignment(Pos.CENTER);
            vboxRGB.setSpacing(10);
            vboxRGB.setPadding(new Insets(0, 10, 0, 10));
            vboxRGB.getChildren().addAll(SP2);

            Scene sceneRGB = new Scene(new VBox(), 800, 700);
            ((VBox) sceneRGB.getRoot()).getChildren().addAll(vboxRGB);

            secondStage.setTitle("HSV");
            secondStage.setScene(sceneRGB);
            secondStage.show();

        });

        //Histograma
        menuItem_Histograma.setOnAction((ActionEvent evenet) -> {
            ScrollPane sp1 = new ScrollPane();
            ScrollPane sp2 = new ScrollPane();

            BufferedImage imageH = new BufferedImage(
                    bufferedImag.getWidth(),
                    bufferedImag.getHeight(),
                    BufferedImage.TYPE_INT_RGB
            );

            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                    int rgb = bufferedImag.getRGB(x, y);
                    int r = (rgb >> 16) & 0xFF;
                    int g = (rgb >> 8) & 0xFF;
                    int b = rgb & 0xFF;

                    int gray = (r + g + b) / 3;

                    int grayPixel = (gray << 16) | (gray << 8) | gray;
                    imageH.setRGB(x, y, grayPixel);
                }
            }

            ImageView imageViewH = new ImageView();
            Image image = SwingFXUtils.toFXImage(imageH, null);
            imageViewH.setImage(image);

            VBox vb1 = new VBox();
            Label labelVB1 = new Label("Image");
            vb1.getChildren().addAll(labelVB1, imageViewH);
            sp1.setContent(vb1);

            try {
                int[] histogram = calculateHistogram(imageH);
                CategoryAxis xAxis = new CategoryAxis();
                NumberAxis yAxis = new NumberAxis();
                BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
                barChart.setTitle("Histogram");

                XYChart.Series<String, Number> series = new XYChart.Series<>();
                for (int i = 0; i < histogram.length; i++) {
                    series.getData().add(new XYChart.Data<>(String.valueOf(i), histogram[i]));
                }

                barChart.getData().add(series);
                sp2.setContent(barChart);
            } catch (IOException e) {
                System.out.println(e);
            }

            Stage secoundStage = new Stage();
            Scene sceneH = new Scene(new HBox(), 400, 350);

            ((HBox) sceneH.getRoot()).getChildren().addAll(sp1, sp2);

            secoundStage.setTitle("Histogram");
            secoundStage.setScene(sceneH);
            secoundStage.show();
        });
        menuItem_Histograma1.setOnAction((ActionEvent event) -> {
            ScrollPane spH = new ScrollPane();
            Stage secondStage = new Stage();
            SwingNode sn = new SwingNode();
            ChartPanel cn = this.createChartPanel(bufferedImag);
            sn.setContent(cn);
            spH.setContent(sn);
            Scene sceneH = new Scene(new HBox(), 900, 700);
            HBox hb = (HBox) sceneH.getRoot();
            hb.getChildren().addAll(spH);
            secondStage.setTitle("Histograma1");
            secondStage.setScene(sceneH);
            secondStage.show();
        });
        
        //Praguri Binarizare
        menuItem_Praguri.setOnAction((ActionEvent event) -> {
            ScrollPane SP = new ScrollPane();
            BufferedImage imageN = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);

            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                    //Retrieving contents of a pixel
                    int pixel = bufferedImag.getRGB(x, y);
                    //Creating a Color object from pixel value
                    Color color = new Color(pixel, true);
                    //Retrieving the R G B values
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    Color myWhite = new Color(red, green, blue);
                    imageN.setRGB(x, y, myWhite.getRGB());
                }
            }

            Image image = SwingFXUtils.toFXImage(imageN, null);
            ImageView imageViewRGB = new ImageView();
            imageViewRGB.setFitHeight(400);
            imageViewRGB.setPreserveRatio(true);
            imageViewRGB.setImage(image);
            imageViewRGB.setSmooth(true);
            imageViewRGB.setCache(true);

            Label limitaSuperioara = new Label("Limita superioara");
            Slider slider = new Slider(0, 255, 255);
            slider.setShowTickMarks(true);
            slider.setShowTickLabels(true);
            slider.setMajorTickUnit(5);
            slider.setBlockIncrement(10);

            BufferedImage imagineModificata = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);

            slider.valueProperty().addListener(
                    new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    limitaSuperioara.setText("Valoare limita superioara: " + newValue);
                    for (int y = 0; y < bufferedImag.getHeight(); y++) {
                        for (int x = 0; x < bufferedImag.getWidth(); x++) {
                            //Retrieving contents of a pixel
                            int pixel = bufferedImag.getRGB(x, y);
                            //Creating a Color object from pixel value
                            Color color = new Color(pixel, true);
                            //Retrieving the R G B values
                            int red = color.getRed();
                            int green = color.getGreen();
                            int blue = color.getBlue();
                            int valoareNoua = newValue.intValue();
                            int gri = (red + green + blue) / 3;

                            if (gri > valoareNoua) {
                                gri = 255;
                            } else {
                                gri = 0;
                            }
                            //Color myWhite = new Color(red, green, blue);
                            Color myWhite = new Color(gri, gri, gri);

                            imagineModificata.setRGB(x, y, myWhite.getRGB());
                        }
                    }
                    Image image = SwingFXUtils.toFXImage(imagineModificata, null);
                    ImageView imageViewR = new ImageView();
                    imageViewR.setFitHeight(00);
                    imageViewR.setPreserveRatio(true);
                    imageViewR.setImage(image);
                    imageViewR.setSmooth(true);
                    imageViewR.setCache(true);
                    SP.setContent(imageViewR);
                }
            });
            SP.setContent(imageViewRGB);

            Stage secondStage = new Stage();
            VBox vboxRGB = new VBox();
            vboxRGB.setAlignment(Pos.CENTER);
            vboxRGB.setSpacing(10);
            vboxRGB.setPadding(new Insets(0, 10, 0, 10));
            vboxRGB.getChildren().addAll(SP);
            vboxRGB.getChildren().addAll(slider);
            vboxRGB.getChildren().addAll(limitaSuperioara);

            Scene sceneRGB = new Scene(new VBox(), 800, 700);
            ((VBox) sceneRGB.getRoot()).getChildren().addAll(vboxRGB);

            secondStage.setTitle("Praguri");
            secondStage.setScene(sceneRGB);
            secondStage.show();
        });
        
        
        //Binarizare simpla
        menuItem_Binarizare_Simpla.setOnAction((ActionEvent event) -> {
            // Creați o nouă imagine alb-negru pentru a aplica binarizarea
            BufferedImage imageN = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);

            // Parcurgeți fiecare pixel al imaginii originale
            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                    // Recuperați conținutul pixelului
                    int pixel = bufferedImag.getRGB(x, y);
                    // Crearea unui obiect Color din valoarea pixelului
                    Color color = new Color(pixel, true);
                    // Recuperarea valorilor R G B
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    // Calculați nivelul de gri
                    int gray = (red + green + blue) / 3;

                    // Aplicați binarizarea cu un singur prag
                    int threshold = 128; // Puteți seta pragul după preferință
                    if (gray > threshold) {
                        gray = 255; // Alb
                    } else {
                        gray = 0; // Negru
                    }

                    // Setarea culorii pixelului pentru imaginea alb-negru
                    Color binaryColor = new Color(gray, gray, gray);
                    imageN.setRGB(x, y, binaryColor.getRGB());
                }
            }

            // Convertiți imaginea rezultată în obiectul ImageView pentru afișare
            Image binaryImage = SwingFXUtils.toFXImage(imageN, null);
            ImageView imageViewBinary = new ImageView();
            imageViewBinary.setFitHeight(400);
            imageViewBinary.setPreserveRatio(true);
            imageViewBinary.setImage(binaryImage);
            imageViewBinary.setSmooth(true);
            imageViewBinary.setCache(true);

            // Crearea unui panou de derulare pentru a afișa imaginea binarizată
            ScrollPane SP = new ScrollPane();
            SP.setContent(imageViewBinary);

            // Crearea și afișarea scenei
            Stage secondStage = new Stage();
            VBox vboxRGB = new VBox();
            vboxRGB.setAlignment(Pos.CENTER);
            vboxRGB.setSpacing(10);
            vboxRGB.setPadding(new Insets(0, 10, 0, 10));
            vboxRGB.getChildren().addAll(SP);

            Scene sceneRGB = new Scene(new VBox(), 800, 700);
            ((VBox) sceneRGB.getRoot()).getChildren().addAll(vboxRGB);

            secondStage.setTitle("Binarizare simplă");
            secondStage.setScene(sceneRGB);
            secondStage.show();
        });

        //Tema5
        //Etichetare
        menuItem_Etichetare.setOnAction((ActionEvent event) -> {
            ScrollPane spH = new ScrollPane();
            BufferedImage image = etichetare(bufferedImag, 0);
            String obiecte[] = {"Toate", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
            ComboBox cb = new ComboBox(FXCollections.observableArrayList(obiecte));
            EventHandler<ActionEvent> eventCombo = new EventHandler<ActionEvent>() {
                int nrOptiune = 0;

                @Override
                public void handle(ActionEvent e) {
                    String optiuneString = (String) cb.getValue();
                    switch (optiuneString) {
                        case "Toate":
                            nrOptiune = 0;
                            break;
                        default:
                            nrOptiune = Integer.parseInt(optiuneString);
                    }
                    BufferedImage imagineModificata = etichetare(bufferedImag, nrOptiune);
                    Image imgH = SwingFXUtils.toFXImage(imagineModificata, null);
                    ImageView imageViewH = new ImageView();
                    imageViewH.setFitHeight(600);
                    imageViewH.setPreserveRatio(true);
                    imageViewH.setImage(imgH);
                    imageViewH.setSmooth(true);
                    imageViewH.setCache(true);
                    spH.setContent(imageViewH);

                }
            };
            cb.setOnAction(eventCombo);
            Image imgH = SwingFXUtils.toFXImage(image, null);
            ImageView imageViewH = new ImageView();
            imageViewH.setFitHeight(600);
            imageViewH.setPreserveRatio(true);
            imageViewH.setImage(imgH);
            imageViewH.setSmooth(true);
            imageViewH.setCache(true);
            spH.setContent(imageViewH);
            VBox vbH = new VBox();
            vbH.getChildren().addAll(imageViewH);
            spH.setContent(vbH);
            Stage secondStage = new Stage();
            Scene sceneH = new Scene(new HBox(), 800, 750);
            ((HBox) sceneH.getRoot()).getChildren().addAll(spH, cb);
            secondStage.setTitle("Etichetare obiecte");
            secondStage.setScene(sceneH);
            secondStage.show();
        });
        //tema 6 egalizare histograma 
        menuItem_Egalizare_Histograma.setOnAction((ActionEvent event) -> {
            ScrollPane spH = new ScrollPane();
            BufferedImage imageH = this.equalize(bufferedImag);
            Image imgH = SwingFXUtils.toFXImage(imageH, null);
            ImageView imageViewH = new ImageView();
            imageViewH.setFitHeight(400);
            imageViewH.setPreserveRatio(true);
            imageViewH.setImage(imgH);
            imageViewH.setSmooth(true);
            imageViewH.setCache(true);
            spH.setContent(imageViewH);
            VBox vbH = new VBox();
            vbH.getChildren().addAll(imageViewH);
            spH.setContent(vbH);
            Stage secondStage = new Stage();
            Scene sceneH = new Scene(new HBox(), 800, 750);
            ((HBox) sceneH.getRoot()).getChildren().addAll(spH);
            secondStage.setTitle("Egalizare histograma");
            secondStage.setScene(sceneH);
            secondStage.show();
        });

        menuItem_GrayScale2.setOnAction((ActionEvent event) -> {
            ScrollPane spH = new ScrollPane();
            BufferedImage imageH = this.getGrayscaleImage(bufferedImag);
            Image imgH = SwingFXUtils.toFXImage(imageH, null);
            ImageView imageViewH = new ImageView();
            imageViewH.setFitHeight(400);
            imageViewH.setPreserveRatio(true);
            imageViewH.setImage(imgH);
            imageViewH.setSmooth(true);
            imageViewH.setCache(true);
            spH.setContent(imageViewH);
            VBox vbH = new VBox();
            vbH.getChildren().addAll(imageViewH);
            spH.setContent(vbH);
            Stage secondStage = new Stage();
            Scene sceneH = new Scene(new HBox(), 800, 750);
            ((HBox) sceneH.getRoot()).getChildren().addAll(spH);
            secondStage.setTitle("Gray Scale 3");
            secondStage.setScene(sceneH);
            secondStage.show();
        });

        menuItem_GrayScale3.setOnAction((ActionEvent event) -> {
            ScrollPane spH = new ScrollPane();
            BufferedImage imageH = this.getGray(bufferedImag);
            Image imgH = SwingFXUtils.toFXImage(imageH, null);
            ImageView imageViewH = new ImageView();
            imageViewH.setFitHeight(400);
            imageViewH.setPreserveRatio(true);
            imageViewH.setImage(imgH);
            imageViewH.setSmooth(true);
            imageViewH.setCache(true);
            spH.setContent(imageViewH);
            VBox vbH = new VBox();
            vbH.getChildren().addAll(imageViewH);
            spH.setContent(vbH);
            Stage secondStage = new Stage();
            Scene sceneH = new Scene(new HBox(), 800, 750);
            ((HBox) sceneH.getRoot()).getChildren().addAll(spH);
            secondStage.setTitle("Gray Scale 4");
            secondStage.setScene(sceneH);
            secondStage.show();
        });
        
        
        //Tema 7 filtru mediere, accentuare , median 
        menuItem_FiltruMediere.setOnAction((ActionEvent event) -> {
            ScrollPane spH = new ScrollPane();
            BufferedImage imageH = mediere(bufferedImag);
            Image imgH = SwingFXUtils.toFXImage(imageH, null);
            ImageView imageViewH = new ImageView();
            imageViewH.setFitHeight(400);
            imageViewH.setPreserveRatio(true);
            imageViewH.setImage(imgH);
            imageViewH.setSmooth(true);
            imageViewH.setCache(true);
            spH.setContent(imageViewH);
            VBox vbH = new VBox();
            vbH.getChildren().addAll(imageViewH);
            spH.setContent(vbH);
            Stage secondStage = new Stage();
            Scene sceneH = new Scene(new HBox(), 800, 750);
            ((HBox) sceneH.getRoot()).getChildren().addAll(spH);
            secondStage.setTitle("Filtru mediere");
            secondStage.setScene(sceneH);
            secondStage.show();
        });
        menuItem_FiltruMediere3.setOnAction((ActionEvent event) -> {
            ScrollPane spH = new ScrollPane();
            BufferedImage imageH = mediere3(bufferedImag);
            Image imgH = SwingFXUtils.toFXImage(imageH, null);
            ImageView imageViewH = new ImageView();
            imageViewH.setFitHeight(400);
            imageViewH.setPreserveRatio(true);
            imageViewH.setImage(imgH);
            imageViewH.setSmooth(true);
            imageViewH.setCache(true);
            spH.setContent(imageViewH);
            VBox vbH = new VBox();
            vbH.getChildren().addAll(imageViewH);
            spH.setContent(vbH);
            Stage secondStage = new Stage();
            Scene sceneH = new Scene(new HBox(), 800, 750);
            ((HBox) sceneH.getRoot()).getChildren().addAll(spH);
            secondStage.setTitle("Filtru mediere 3x3");
            secondStage.setScene(sceneH);
            secondStage.show();
        });
        menuItem_Accentuare.setOnAction((ActionEvent event) -> {
            ScrollPane spH = new ScrollPane();
            BufferedImage imageH = accentuare(bufferedImag);
            Image imgH = SwingFXUtils.toFXImage(imageH, null);
            ImageView imageViewH = new ImageView();
            imageViewH.setFitHeight(600);
            imageViewH.setPreserveRatio(true);
            imageViewH.setImage(imgH);
            imageViewH.setSmooth(true);
            imageViewH.setCache(true);
            spH.setContent(imageViewH);
            VBox vbH = new VBox();
            vbH.getChildren().addAll(imageViewH);
            spH.setContent(vbH);
            Stage secondStage = new Stage();
            Scene sceneH = new Scene(new HBox(), 800, 750);
            ((HBox) sceneH.getRoot()).getChildren().addAll(spH);
            secondStage.setTitle("Filtru accentuare");
            secondStage.setScene(sceneH);
            secondStage.show();
        });
        menuItem_Median.setOnAction((ActionEvent event) -> {
            ScrollPane SP = new ScrollPane();
            BufferedImage imageH = median(bufferedImag, 4);
            Image imgH = SwingFXUtils.toFXImage(imageH, null);
            ImageView imageViewH = new ImageView();
            imageViewH.setFitHeight(400);
            imageViewH.setPreserveRatio(true);
            imageViewH.setImage(imgH);
            imageViewH.setSmooth(true);
            imageViewH.setCache(true);
            SP.setContent(imageViewH);

            Slider slider = new Slider(0, 8, 4);
            slider.setBlockIncrement(1);
            slider.setMajorTickUnit(1);
            slider.setMinorTickCount(0);
            slider.setShowTickLabels(true);
            slider.setSnapToTicks(true);

            slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                int valoare = newValue.intValue();
                BufferedImage imagineModificata = median(bufferedImag, valoare);
                Image image = SwingFXUtils.toFXImage(imagineModificata, null);
                ImageView imageViewR = new ImageView();
                imageViewR.setPreserveRatio(true);
                imageViewR.setImage(image);
                imageViewR.setSmooth(true);
                imageViewR.setCache(true);
                SP.setContent(imageViewR);

            });

            VBox vb = new VBox();
            vb.getChildren().addAll(SP);
            vb.getChildren().addAll(slider);

            Stage secondStage = new Stage();
            Scene sceneH = new Scene(new VBox(), 800, 750);
            ((VBox) sceneH.getRoot()).getChildren().addAll(vb);
            secondStage.setTitle("Filtru minim");
            secondStage.setScene(sceneH);
            secondStage.show();
        });
        
        
        //Tema 8 Filtru Laplacian
        menuItem_Laplacian.setOnAction((ActionEvent event) -> {
            ScrollPane spH = new ScrollPane();
            BufferedImage imageH = filterLaplacian(bufferedImag);
            //BufferedImage imageH =threshold(BFI, 125);
            Image imgH = SwingFXUtils.toFXImage(imageH, null);
            ImageView imageViewH = new ImageView();
            imageViewH.setFitHeight(600);
            imageViewH.setPreserveRatio(true);
            imageViewH.setImage(imgH);
            imageViewH.setSmooth(true);
            imageViewH.setCache(true);
            spH.setContent(imageViewH);
            VBox vbH = new VBox();
            vbH.getChildren().addAll(imageViewH);
            spH.setContent(vbH);
            Stage secondStage = new Stage();
            Scene sceneH = new Scene(new HBox(), 800, 750);
            ((HBox) sceneH.getRoot()).getChildren().addAll(spH);
            secondStage.setTitle("Filtru Laplacian");
            secondStage.setScene(sceneH);
            secondStage.show();
        });
        
        
        //Dilatare si eroziune
        menuItem_DilatareEroziune.setOnAction((ActionEvent event) -> {
            ScrollPane SP = new ScrollPane();
            BufferedImage imageH = bufferedImag;

            Image imgH = SwingFXUtils.toFXImage(imageH, null);
            ImageView imageViewH = new ImageView();
            imageViewH.setFitHeight(400);
            imageViewH.setPreserveRatio(true);
            imageViewH.setImage(imgH);
            imageViewH.setSmooth(true);
            imageViewH.setCache(true);
            SP.setContent(imageViewH);

            Slider slider = new Slider(0, 10, 5);
            slider.setBlockIncrement(1);
            slider.setMajorTickUnit(1);
            slider.setMinorTickCount(0);
            slider.setShowTickLabels(true);
            slider.setSnapToTicks(true);

            CheckBox checkbox = new CheckBox("Dilatare/Eroziune");

            slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                int valoare = newValue.intValue();

                BufferedImage imagineModificata = threshold(bufferedImag, 125);
                for (int i = 0; i <= valoare; i++) {
                    imagineModificata = dilate(imagineModificata, checkbox.isSelected());
                }

                Image image = SwingFXUtils.toFXImage(imagineModificata, null);
                ImageView imageViewR = new ImageView();
                imageViewR.setPreserveRatio(true);
                imageViewR.setImage(image);
                imageViewR.setSmooth(true);
                imageViewR.setCache(true);
                SP.setContent(imageViewR);

            });

            VBox vb = new VBox();
            vb.getChildren().addAll(SP);
            vb.getChildren().addAll(slider, checkbox);

            Stage secondStage = new Stage();
            Scene sceneH = new Scene(new VBox(), 800, 750);
            ((VBox) sceneH.getRoot()).getChildren().addAll(vb);
            secondStage.setTitle("Filtru dilatare/eroziune");
            secondStage.setScene(sceneH);
            secondStage.show();
        });

        //Tema9
        //Detectie contururi
        menuItem_DetectieContururi.setOnAction((ActionEvent event) -> {
            ScrollPane spH = new ScrollPane();
            //BufferedImage imageH = EdgeDetect(BFI, 5);
            //BufferedImage imageH =threshold(BFI, 125);
            Image imgH = SwingFXUtils.toFXImage(bufferedImag, null);
            ImageView imageViewH = new ImageView();
            imageViewH.setFitHeight(600);
            imageViewH.setPreserveRatio(true);
            imageViewH.setImage(imgH);
            imageViewH.setSmooth(true);
            imageViewH.setCache(true);
            spH.setContent(imageViewH);

            String optiuni[] = {"Fara filtre", "Filtru orizontal", "Filtru vertical",
                "Filtru Sobel Vertical", "Filtru Sobel Orizontal",
                "Filtru Scharr Vertical", "Filtru Scharr Orizontal",
                "H1", "H2", "H3"};
            Slider slider = new Slider(0, 8, 1);
            slider.setBlockIncrement(1);
            slider.setMajorTickUnit(1);
            slider.setMinorTickCount(0);
            slider.setShowTickLabels(true);
            slider.setSnapToTicks(true);

            ComboBox cb = new ComboBox(FXCollections.observableArrayList(optiuni));

            EventHandler<ActionEvent> eventContur = new EventHandler<ActionEvent>() {
                int nrOptiune = 0;

                public void handle(ActionEvent e) {
                    System.out.println(cb.getValue() + " selected");
                    String optiuneSelectata = (String) cb.getValue();
                    switch (optiuneSelectata) {
                        case "Fara filtre":
                            nrOptiune = 0;
                            break;
                        case "Filtru orizontal":
                            nrOptiune = 1;
                            break;
                        case "Filtru vertical":
                            nrOptiune = 2;
                            break;
                        case "Filtru Sobel Vertical":
                            nrOptiune = 3;
                            break;
                        case "Filtru Sobel Orizontal":
                            nrOptiune = 4;
                            break;
                        case "Filtru Scharr Vertical":
                            nrOptiune = 5;
                            break;
                        case "Filtru Scharr Orizontal":
                            nrOptiune = 6;
                            break;
                        case "H1":
                            nrOptiune = 7;
                            break;
                        case "H2":
                            nrOptiune = 8;
                            break;
                        case "H3":
                            nrOptiune = 9;
                            break;
                    }
                    BufferedImage imagineModificata = bufferedImag;
                    if (nrOptiune > 0) {
                        for (int i = 0; i < slider.getValue(); i++) {
                            imagineModificata = EdgeDetect(imagineModificata, nrOptiune);
                        }
                    }
                    Image image = SwingFXUtils.toFXImage(imagineModificata, null);
                    ImageView imageViewH = new ImageView();
                    imageViewH.setPreserveRatio(true);
                    imageViewH.setImage(image);
                    imageViewH.setSmooth(true);
                    imageViewH.setCache(true);
                    spH.setContent(imageViewH);
                }
            };

            cb.setOnAction(eventContur);
            VBox vbH = new VBox();
            vbH.getChildren().addAll(imageViewH);
            spH.setContent(vbH);
            Stage secondStage = new Stage();
            Scene sceneH = new Scene(new HBox(), 800, 750);
            ((HBox) sceneH.getRoot()).getChildren().addAll(spH, cb, slider);
            secondStage.setTitle("Detectie contur");
            secondStage.setScene(sceneH);
            secondStage.show();
        });

        //Tema 10
        menuItem_LZW_compress.setOnAction((ActionEvent event) -> {
            if (bufferedImag != null) {
                try {

                    String compressedFileName = LZW_compress(bufferedImag);

                    System.out.println("Imaginea a fost comprimată cu succes! Fișierul comprimat: " + compressedFileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        menuItem_LZW_Decompresie.setOnAction((ActionEvent event) -> {
            // Se presupune că ai o modalitate de a selecta fișierul pentru decompresie
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Selectează fișierul comprimat LZW");
            Window primaryStage = null;
            File selectedFile = fileChooser.showOpenDialog(primaryStage);

            if (selectedFile != null) {
                try {
                    this.LZW_decompress(selectedFile.getAbsolutePath());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Decompresie Reușită");
                    alert.setHeaderText(null);
                    alert.setContentText("Imaginea a fost decompresată și salvată cu succes.");
                    alert.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Eroare de Decompresie");
                    alert.setHeaderText(null);
                    alert.setContentText("A apărut o eroare în timpul decompresiei: " + e.getMessage());
                    alert.showAndWait();
                }
            }
        });

        //Tema11
        menuItem_huffman.setOnAction((ActionEvent event) -> {
            this.HuffmanCompress(bufferedImag);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Compression Successful");
            alert.setHeaderText(null);
            alert.setContentText("Imaginea a fost salvată cu succes ");
            alert.showAndWait();
        });
        menuItem_Decompresie_Huffman.setOnAction((ActionEvent event) -> {
            // Aici adaugi codul pentru compresia Huffman
            // this.HuffmanCompress(bufferedImage);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Compression Successful");
            alert.setHeaderText(null);
            alert.setContentText("Imaginea a fost salvată cu succes.");
            alert.showAndWait();
        });
        

        menuItem_Exit.setOnAction((ActionEvent event) -> {
            Platform.exit();
            System.exit(0);
        });

        ((StackPane) scene.getRoot()).getChildren().addAll(vbox);

        mainStage.setTitle("Prelucrarea Imaginilor");
        mainStage.setScene(scene);
        mainStage.show();

        mainStage.setMaximized(true);

//        // Sau varianta nr 2
//        // Get current screen of the stage
//        ObservableList<Screen> screens = Screen.getScreensForRectangle(new Rectangle2D(mainStage.getX(), mainStage.getY(), mainStage.getWidth(), mainStage.getHeight()));
//
//        // Change stage properties
//        Rectangle2D bounds = screens.get(0).getVisualBounds();
//        mainStage.setX(bounds.getMinX());
//        mainStage.setY(bounds.getMinY());
//        mainStage.setWidth(bounds.getWidth());
//        mainStage.setHeight(bounds.getHeight());
    }

    private static void opencvTest() {
        try {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            Mat m = new Mat(5, 10, CvType.CV_8UC1, new Scalar(0));
            System.out.println("OpenCV Mat: " + m);
            Mat mr1 = m.row(1);
            mr1.setTo(new Scalar(1));
            Mat mc5 = m.col(5);
            mc5.setTo(new Scalar(5));
            System.out.println("OpenCV Mat data:\n" + m.dump());
        } catch (Exception ex) {
        }
    }

    private int adjustColor(int rgb, int dif) {
        int result;
        if (dif > 0) {
            if (rgb + dif > 255) {
                result = 255; //rgb + dif - 255; 
            } else {
                result = rgb + dif;
            }
        } else {
            if (rgb + dif > 0) {
                result = rgb + dif;
            } else {
                result = 0; //255 + rgb + dif;
            }
        }
        return result;
    }

    private BufferedImage getYUV() {
        BufferedImage YUV = this.bufferedImag;
        for (int y = 0; y < bufferedImag.getHeight(); y++) {
            for (int x = 0; x < bufferedImag.getWidth(); x++) {
                //Retrieving contents of a pixel
                int pixel = bufferedImag.getRGB(x, y);
                //Creating a Color object from pixel value
                Color color = new Color(pixel, true);
                //Retrieving the R G B values
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int Y = (int) Math.round(0.3 * red + 0.6 * green + 0.1 * blue);
                int U = (int) Math.round(0.74 * (red - Y) + 0.27 * (blue - Y));
                int V = (int) Math.round(0.48 * (red - Y) + 0.41 * (blue - Y));
                Y = corecteazaCuloare(Y);
                U = corecteazaCuloare(U);
                V = corecteazaCuloare(V);
                Color culoareNoua = new Color(Y, U, V);
                YUV.setRGB(x, y, culoareNoua.getRGB());
            }
        }
        return YUV;
    }

    private BufferedImage getYCbCr() {
        BufferedImage YCbCr = this.bufferedImag;
        for (int y = 0; y < bufferedImag.getHeight(); y++) {
            for (int x = 0; x < bufferedImag.getWidth(); x++) {
                //Retrieving contents of a pixel
                int pixel = bufferedImag.getRGB(x, y);
                //Creating a Color object from pixel value
                Color color = new Color(pixel, true);
                //Retrieving the R G B values
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int Y = (int) Math.round(0.299 * red + 0.587 * green + 0.114 * blue);
                int Cb = (int) Math.round(-0.1678 * red - 0.3313 * green + 0.498 * blue + 128);
                int Cr = (int) Math.round(0.498 * red - 0.4187 * green + 0.0813 * blue + 128);
                Y = corecteazaCuloare(Y);
                Cb = corecteazaCuloare(Cb);
                Cr = corecteazaCuloare(Cr);
                Color culoareNoua = new Color(Y, Cb, Cr);
                YCbCr.setRGB(x, y, culoareNoua.getRGB());
            }
        }
        return YCbCr;
    }

    public BufferedImage getHSV() {
        BufferedImage HSV = this.bufferedImag;
        for (int y = 0; y < bufferedImag.getHeight(); y++) {
            for (int x = 0; x < bufferedImag.getWidth(); x++) {
                //Retrieving contents of a pixel
                int pixel = bufferedImag.getRGB(x, y);
                //Creating a Color object from pixel value
                Color color = new Color(pixel, true);
                //Retrieving the R G B values
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                float r = (float) red / 255;
                float g = (float) green / 255;
                float b = (float) blue / 255;

                float M = Math.max(Math.max(r, g), b);
                float m = Math.min(Math.min(r, g), b);

                float C = M - m;
                float V = M;

                float S;//----------Saturation
                if (V != 0) {
                    S = C / V;
                } else {
                    S = 0;
                }
                //------------Hue
                float H = 0;
                if (C != 0) {
                    if (M == r) {
                        H = 60 * (g - b) / C;
                    }
                    if (M == g) {
                        H = 120 + 60 * (b - r) / C;
                    }
                    if (M == b) {
                        H = 240 + 60 * (r - g) / C;
                    }
                } else {
                    H = 0;
                }
                if (H < 0) {
                    H = H + 360;
                }
                H = normalizare(H) / 360.0f;
                S = normalizare(S);
                V = normalizare(V);

                int H_int = corecteazaCuloare((int) H);
                int S_int = corecteazaCuloare((int) S);
                int V_int = corecteazaCuloare((int) V);

                Color culoareNoua = new Color(H_int, S_int, V_int);
                HSV.setRGB(x, y, culoareNoua.getRGB());
            }
        }
        return HSV;
    }

    private float normalizare(float v) {
        return v * 255;
    }

    private int corecteazaCuloare(int v) {
        if (v < 0) {
            return 0;
        }
        if (v > 255) {
            return 255;
        }
        return v;
    }

    public BufferedImage getInverse() {
        BufferedImage imageI = this.bufferedImag;
        for (int y = 0; y < imageI.getHeight(); y++) {
            for (int x = 0; x < imageI.getWidth(); x++) {
                //Retrieving contents of a pixel
                int pixel = imageI.getRGB(x, y);
                //Creating a Color object from pixel value
                Color color = new Color(pixel, true);
                //Retrieving the R G B values
                int red = 255 - color.getRed();
                int green = 255 - color.getGreen();
                int blue = 255 - color.getBlue();

                Color newColor = new Color(red, green, blue);
                imageI.setRGB(x, y, newColor.getRGB());
            }
        }
        return imageI;
    }

    public static int[] calculateHistogram(BufferedImage image) throws IOException {
        // Dimensiunile imaginii
        int width = image.getWidth();
        int height = image.getHeight();

        // Inițializarea histogramă
        int[] histogram = new int[256]; // Histograma pentru tonuri de gri

        // Parcurgerea imaginii și calcularea histogramei
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                // Extrage componentele R, G, B și calculează media pentru a obține tonul de gri
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = pixel & 0xff;
                int gray = (red + green + blue) / 3;

                // Incrementarea corespunzătoare a valorii în histogramă
                histogram[gray]++;
            }
        }

        return histogram;
    }

    public ChartPanel createHistogramChartPanel(int[] histogramData) {
        // Creează un set de date pentru histogramă
        HistogramDataset dataset = new HistogramDataset();

        // Convertește array-ul de int într-un array de double
        double[] histogramDataDouble = new double[histogramData.length];
        for (int i = 0; i < histogramData.length; i++) {
            histogramDataDouble[i] = (double) histogramData[i];
        }

        // Adaugă datele histogramului în setul de date
        dataset.addSeries("Histogram", histogramDataDouble, histogramDataDouble.length);

        // Creează un grafic de tip histogramă folosind JFreeChart
        JFreeChart chart = ChartFactory.createHistogram(
                "Histograma", // Titlul graficului
                "Nivel de intensitate", // Eticheta axei X
                "Frecvență", // Eticheta axei Y
                dataset // Setul de date pentru histogramă
        );

        // Creează un panou de graficuri pentru graficul histogramă și returnează-l
        return new ChartPanel(chart);
    }

    private ChartPanel createChartPanel(BufferedImage img) {

        // dataset 
        XYBarRenderer renderer;
        HistogramDataset dataset = new HistogramDataset();
        Raster raster = img.getRaster();
        final int w = img.getWidth();
        final int h = img.getHeight();
        double[] r = new double[w * h];
        r = raster.getSamples(0, 0, w, h, 0, r);
        int BINS = 256;
        dataset.addSeries("Red", r, BINS);
        try {
            r = raster.getSamples(0, 0, w, h, 1, r);
            dataset.addSeries("Green", r, BINS);
        } catch (Exception ex) {

        }

        try {
            r = raster.getSamples(0, 0, w, h, 2, r);
            dataset.addSeries("Blue", r, BINS);
        } catch (Exception ex) {

        }

        // chart 
        JFreeChart chart = ChartFactory.createHistogram("Histogram", "Value",
                "Count", dataset, PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = (XYPlot) chart.getPlot();
        renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setBarPainter(new StandardXYBarPainter());

        // translucent red, green & blue 
        Paint[] paintArray = {
            new Color(0x80ff0000, true),
            new Color(0x8000ff00, true),
            new Color(0x800000ff, true)
        };

        plot.setDrawingSupplier(new DefaultDrawingSupplier(
                paintArray,
                DefaultDrawingSupplier.DEFAULT_FILL_PAINT_SEQUENCE,
                DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
                DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
                DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
                DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));
        ChartPanel panel = new ChartPanel(chart);

        panel.setMouseWheelEnabled(true);

        return panel;

    }

//etichetare
    public BufferedImage etichetare(BufferedImage src, int nrOptiune) {
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
        src = this.threshold(this.getGray(src), 50);
        int label = 0;
        int[][] labels = new int[src.getHeight()][src.getWidth()];
        for (int i = 0; i < src.getHeight(); ++i) {
            for (int j = 0; j < src.getWidth(); ++j) {
                labels[i][j] = 0;
            }
        }

        for (int i = 0; i < src.getHeight(); ++i) {
            for (int j = 0; j < src.getWidth(); ++j) {
                int pixel = src.getRGB(j, i);
                Color c = new Color(pixel, true);
                int color = c.getRed();
                if (color == 0 && labels[i][j] == 0) {
                    label++;
                    Queue<Object> queue = new LinkedList<>();
                    labels[i][j] = label;
                    queue.add(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] q = (int[]) queue.remove();
                        int q0 = q[0], q1 = q[1];
                        for (int k = -1; k <= 1; k++) {
                            for (int m = -1; m <= 1; m++) {
                                if ((q0 + k >= 0 && q0 + k <= src.getHeight())
                                        && (q1 + m >= 0 && q1 + m <= src.getWidth())) {
                                    try {
                                        int npixel = src.getRGB(q1 + m, q0 + k);
                                        Color nc = new Color(npixel, true);
                                        if (nc.getRed() == 0 && labels[q0 + k][q1 + m] == 0) {
                                            labels[q0 + k][q1 + m] = label;
                                            queue.add(new int[]{q0 + k, q1 + m});
                                        }
                                    } catch (Exception ex) {
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Color listaCulori[] = {Color.WHITE, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED};
        if (nrOptiune == 0) {
            for (int i = 0; i < src.getHeight(); ++i) {
                for (int j = 0; j < src.getWidth(); ++j) {
                    int nrEticheta = labels[i][j];
                    dst.setRGB(j, i, listaCulori[nrEticheta].getRGB());
                }
            }
        } else {
            for (int i = 0; i < src.getHeight(); ++i) {
                for (int j = 0; j < src.getWidth(); ++j) {
                    if (labels[i][j] == nrOptiune) {
                        dst.setRGB(j, i, Color.green.getRGB());
                    } else {
                        dst.setRGB(j, i, Color.white.getRGB());
                    }
                }
            }
        }

        System.out.println(label + " obiecte detectate");
        /*
        for (int i = 0; i < src.getHeight(); ++i) {
            for (int j = 0; j < src.getWidth(); ++j) {
                dst.setRGB(j, i, (new Color(255 - fixOutOfRangeRGBValues(labels[i][j]), 255 - fixOutOfRangeRGBValues(labels[i][j]), 255 - fixOutOfRangeRGBValues(labels[i][j]))).getRGB());
            }

        }
         */
        return dst;
    }

    private BufferedImage getGray(BufferedImage image) {
        BufferedImage g = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        ColorConvertOp op = new ColorConvertOp(image.getColorModel().getColorSpace(), ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        op.filter(image, g);
        return g;

    }

    BufferedImage threshold(BufferedImage src, int valoare) {
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < src.getHeight(); y++) {
            for (int x = 0; x < src.getWidth(); x++) {
                //Retrieving contents of a pixel
                int pixel = src.getRGB(x, y);
                //Creating a Color object from pixel value
                Color color = new Color(pixel, true);
                //Retrieving the R G B values
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                int gri = (red + green + blue) / 3;

                if (gri > valoare) {
                    gri = 255;
                } else {
                    gri = 0;
                }
                //Color myWhite = new Color(red, green, blue);
                Color myWhite = new Color(gri, gri, gri);

                dst.setRGB(x, y, myWhite.getRGB());
            }
        }
        return dst;
    }

    
    private BufferedImage equalize(BufferedImage src) {
        BufferedImage nImg = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster er = nImg.getRaster();
        int[] histogram = new int[256];
        for (int i = 0; i < 256; i++) {
            histogram[i] = 0;
        }
        int width = src.getWidth();
        int height = src.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                try {
                    int pixel = src.getRGB(j, j);
                    Color c = new Color(pixel, true);
                    int nivel = c.getRed();
                    histogram[nivel]++;
                } catch (Exception ex) {
                }
            }
        }
        double[] hc = new double[256];
        hc[0] = histogram[0];
        for (int i = 1; i < 256; i++) {
            hc[i] = hc[i - 1] + histogram[i];
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pixel = src.getRGB(i, j);
                Color c = new Color(pixel, true);
                int nivel = c.getRed();
                int nivel_nou = (int) ((hc[nivel] - hc[0]) * 255 / (width * height - hc[0]));
                er.setSample(i, j, 0, nivel_nou);
            }
        }
        nImg.setData(er);
        return nImg;
    }

    public BufferedImage mediere3(BufferedImage src) {
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
        int i, j;
        int k, l;
        int w, h;
        double[][] v = new double[3][3];
        //coeficientii mastii de filtrare  
        v[0][0] = 1.0 / 9.0;
        v[0][1] = 1.0 / 9.0;
        v[0][2] = 1.0 / 9.0;
        v[1][0] = 1.0 / 9.0;
        v[1][1] = 1.0 / 9.0;
        v[1][2] = 1.0 / 9.0;
        v[2][0] = 1.0 / 9.0;
        v[2][1] = 1.0 / 9.0;
        v[2][2] = 1.0 / 9.0;

        w = src.getWidth();
        h = src.getHeight();

        for (i = 1; i < w - 1; i++) {
            for (j = 1; j < h - 1; j++) {
                //suma ponderata  
                double sumr = 0, sumg = 0, sumb = 0;
                for (k = -1; k < 2; k++) {
                    for (l = -1; l < 2; l++) {
                        int pixel = src.getRGB(i + k, j + l);
                        Color c = new Color(pixel, true);
                        sumr += v[k + 1][l + 1] * c.getRed();
                        sumg += v[k + 1][l + 1] * c.getGreen();
                        sumb += v[k + 1][l + 1] * c.getBlue();
                        Color nc = new Color((int) sumr, (int) sumg, (int) sumb);
                        dst.setRGB(i, j, nc.getRGB());
                    }
                }
            }
        }
        return dst;
    }

    public BufferedImage mediere(BufferedImage src) {
        // filtru 5x5
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
        int i, j;
        int k, l;
        int w, h;

        double[][] v = new double[5][5];
        //coeficientii mastii de filtrare  
        v[0][0] = 1.0 / 9.0;
        v[0][1] = 1.0 / 9.0;
        v[0][2] = 1.0 / 9.0;
        v[0][3] = 1.0 / 9.0;
        v[0][4] = 1.0 / 9.0;
        v[1][0] = 1.0 / 9.0;
        v[1][1] = 1.0 / 9.0;
        v[1][2] = 1.0 / 9.0;
        v[1][3] = 1.0 / 9.0;
        v[1][4] = 1.0 / 9.0;
        v[2][0] = 1.0 / 9.0;
        v[2][1] = 1.0 / 9.0;
        v[2][2] = 1.0 / 9.0;
        v[2][3] = 1.0 / 9.0;
        v[2][4] = 1.0 / 9.0;
        v[3][0] = 1.0 / 9.0;
        v[3][1] = 1.0 / 9.0;
        v[3][2] = 1.0 / 9.0;
        v[3][3] = 1.0 / 9.0;
        v[3][4] = 1.0 / 9.0;
        v[4][0] = 1.0 / 9.0;
        v[4][1] = 1.0 / 9.0;
        v[4][2] = 1.0 / 9.0;
        v[4][3] = 1.0 / 9.0;
        v[4][4] = 1.0 / 9.0;

        w = src.getWidth();
        h = src.getHeight();

        for (i = 2; i < w - 2; i++) {
            for (j = 2; j < h - 2; j++) {
                //suma ponderata  
                double sumr = 0, sumg = 0, sumb = 0;
                for (k = -2; k < 3; k++) {
                    for (l = -2; l < 3; l++) {
                        int pixel = src.getRGB(i + k, j + l);
                        Color c = new Color(pixel, true);
                        sumr += v[k + 2][l + 2] * c.getRed();
                        sumg += v[k + 2][l + 2] * c.getGreen();
                        sumb += v[k + 2][l + 2] * c.getBlue();
                        sumr = corecteazaCuloare(sumr);
                        sumg = corecteazaCuloare(sumg);
                        sumb = corecteazaCuloare(sumb);
                        Color nc = new Color((int) sumr, (int) sumg, (int) sumb);
                        dst.setRGB(i, j, nc.getRGB());
                    }
                }
            }
        }
        return dst;
    }

    private double corecteazaCuloare(double v) {
        if (v < 0) {
            return 0;
        }
        if (v > 255) {
            return 255;
        }
        return v;
    }

    BufferedImage accentuare(BufferedImage src) {
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
        int i, j;
        int k, l;
        int w, h;
        double sumr, sumg, sumb;
        double[][] v = new double[3][3];
        //coeficientii mastii  
        v[0][0] = 0;
        v[0][1] = -1. / 4;
        v[0][2] = 0;
        v[1][0] = -1. / 4;
        v[1][1] = 1;
        v[1][2] = -1. / 4;
        v[2][0] = 0;
        v[2][1] = -1. / 4;
        v[2][2] = 0;
        w = src.getWidth();
        h = src.getHeight();
        for (i = 1; i < w - 1; i++) {
            for (j = 1; j < h - 1; j++) {
                sumr = 0;
                sumg = 0;
                sumb = 0;
                for (k = -1; k < 2; k++) {
                    for (l = -1; l < 2; l++) {
                        int pixel = src.getRGB(i + k, j + l);
                        Color c = new Color(pixel, true);
                        sumr += 1. * v[k + 1][l + 1] * c.getRed();
                        sumg += 1. * v[k + 1][l + 1] * c.getGreen();
                        sumb += 1. * v[k + 1][l + 1] * c.getBlue();
                        int nivr = c.getRed();
                        //nivr=(int)(nivr+0.6*sumr); 
                        int nivg = c.getGreen();
                        //nivg=(int)(nivg+0.6*sumg); 
                        int nivb = c.getBlue();
                        //nivb=(int)(nivb+0.6*sumb); 
                        Color nc = new Color((int) this.adjustColor(nivr, (int) (0.6 * sumr)),
                                (int) this.adjustColor(nivg, (int) (0.6 * sumg)),
                                (int) this.adjustColor(nivb, (int) (0.6 * sumb)));
                        dst.setRGB(i, j, nc.getRGB());
                    }
                }
            }
        }
        return dst;
    }

    public BufferedImage median(BufferedImage src, int poz) {
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
        int i, j;
        int w, h;
        int k, aux;
        int m, n;
        int med;

        int[] sir = new int[9];
        w = src.getWidth();
        h = src.getHeight();

        for (i = 1; i < w - 1; i++) {
            for (j = 1; j < h - 1; j++) {
                //formarea unui sir din elementele vecinatatii 3x3 
                k = 0;
                for (m = -1; m < 2; m++) {
                    for (n = -1; n < 2; n++) {
                        int pixel = src.getRGB(i + m, j + n);
                        Color c = new Color(pixel, true);
                        sir[k] = c.getRed();
                        k++;
                    }
                }

                //ordonarea crescatoare a valorilor pixelilor 
                //metoda BUBBLESORT 
                k = 0;
                while (k == 0) {
                    k = 1;
                    for (m = 0; m < 8; m++) {
                        if (sir[m] > sir[m + 1]) {
                            aux = sir[m];
                            sir[m] = sir[m + 1];
                            sir[m + 1] = aux;
                            k = 0;
                        }
                    }
                }

                //elementul median  
                med = sir[poz];
                Color nc = new Color(med, med, med);
                dst.setRGB(i, j, nc.getRGB());
            }
        }
        return dst;

    }

    //Filtru Laplacian
    private BufferedImage getGrayscaleImage(BufferedImage src) {
        BufferedImage gImg = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster wr = src.getRaster();
        WritableRaster gr = gImg.getRaster();
        for (int i = 0; i < wr.getWidth(); i++) {
            for (int j = 0; j < wr.getHeight(); j++) {
                gr.setSample(i, j, 0, wr.getSample(i, j, 0));
            }
        }
        gImg.setData(gr);
        return gImg;
    }

    private BufferedImage filterLaplacian(BufferedImage src) {
        BufferedImage grayScale = this.getGrayscaleImage(src);
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);

        int width = src.getWidth();
        int height = src.getHeight();
        double[][] v = new double[3][3];
        //coeficientii mastii  
        v[0][0] = -1;
        v[0][1] = -1;
        v[0][2] = -1;
        v[1][0] = -1;
        v[1][1] = 8;
        v[1][2] = -1;
        v[2][0] = -1;
        v[2][1] = -1;
        v[2][2] = -1;

        //3*3 Laplacian filter (-1,-1,-1), (-1,8,-1), (-1,-1,-1) 
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                int sum = 0;
                for (int m = -1; m < 2; m++) {
                    for (int n = -1; n < 2; n++) {
                        int pixel = src.getRGB(x + m, y + n);
                        sum += v[m + 1][n + 1] * (pixel & 0xff);
                    }
                }
                int a = ((grayScale.getRGB(x, y) >> 24) & 0xff); //alfa
                dst.setRGB(x, y, ((a << 24) | (sum << 16) | (sum << 8) | (sum)));
            }
        }
        return dst;
    }

    //dilatare 
    private BufferedImage dilate(BufferedImage src, boolean dilateBackgroundPixel) {
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        /**
         * Dimension of the image.
         */
        int width = src.getWidth();
        int height = src.getHeight();

        /**
         * This will hold the dilation result which will be copied to image.
         */
        int output[] = new int[width * height];
        /**
         * If dilation is to be performed on BLACK pixels then targetValue = 0
         * else targetValue = 255; //for WHITE pixels
         */

        int targetValue = (dilateBackgroundPixel == true) ? 0 : 255;
        /**
         * If the target pixel value is WHITE (255) then the reverse pixel value
         * will be BLACK (0) and vice-versa.
         */

        int reverseValue = (targetValue == 255) ? 0 : 255;

        //perform dilation 
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //For BLACK pixel RGB all are set to 0 and for WHITE pixel all are set to 255. 
                if ((new Color(src.getRGB(x, y))).getRed() == targetValue) {
                    /**
                     * We are using a 3x3 kernel [1, 1, 1 1, 1, 1 1, 1, 1]
                     */
                    boolean flag = false;   //this will be set if a pixel of reverse value is found in the mask 
                    for (int ty = y - 1; ty <= y + 1 && flag == false; ty++) {
                        for (int tx = x - 1; tx <= x + 1 && flag == false; tx++) {
                            if (ty >= 0 && ty < height && tx >= 0 && tx < width) {
                                //origin of the mask is on the image pixels 
                                if ((new Color(src.getRGB(tx, ty))).getRed() != targetValue) {
                                    flag = true;
                                    output[x + y * width] = reverseValue;
                                }
                            }
                        }
                    }
                    if (flag == false) {
                        //all pixels inside the mask [i.e., kernel] were of targetValue 
                        output[x + y * width] = targetValue;
                    }
                } else {
                    output[x + y * width] = reverseValue;
                }
            }
        }
        /**
         * Save the dilation value in image.
         */
        dst.getRaster().setSamples(0, 0, width, height, 0, output);
        return dst;

    }

    //detectie contururi 
    public BufferedImage EdgeDetect(BufferedImage src, int filter_type) {
        String HORIZONTAL_FILTER = "Horizontal Filter";
        String VERTICAL_FILTER = "Vertical Filter";
        String SOBEL_FILTER_VERTICAL = "Sobel Vertical Filter";
        String SOBEL_FILTER_HORIZONTAL = "Sobel Horizontal Filter";
        String SCHARR_FILTER_VETICAL = "Scharr Vertical Filter";
        String SCHARR_FILTER_HORIZONTAL = "Scharr Horizontal Filter";

        double[][] FILTER_VERTICAL = {{1, 0, -1}, {1, 0, -1}, {1, 0, -1}};
        double[][] FILTER_HORIZONTAL = {{1, 1, 1}, {0, 0, 0}, {-1, -1, -1}};
        double[][] FILTER_SOBEL_V = {{1, 0, -1}, {2, 0, -2}, {1, 0, -1}};
        double[][] FILTER_SOBEL_H = {{1, 2, 1}, {0, 0, 0}, {-1, -2, -1}};
        double[][] FILTER_SCHARR_V = {{3, 0, -3}, {10, 0, -10}, {3, 0, -3}};
        double[][] FILTER_SCHARR_H = {{3, 10, 3}, {0, 0, 0}, {-3, -10, -3}};
        double[][] FILTER_H1 = {{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}};
        double[][] FILTER_H2 = {{-1, -1, -1}, {-1, 9, -1}, {-1, -1, -1}};
        double[][] FILTER_H3 = {{1, -2, 1}, {-2, 5, -2}, {1, -2, 1}};
        HashMap<String, double[][]> filterMap;

        int width = src.getWidth();
        int height = src.getHeight();
        double[][][] image = new double[3][height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color color = new Color(src.getRGB(j, i));
                image[0][i][j] = color.getRed();
                image[1][i][j] = color.getGreen();
                image[2][i][j] = color.getBlue();
            }
        }

        double[][] filter = null;
        switch (filter_type) {
            case 1:
                filter = FILTER_VERTICAL;
                break;
            case 2:
                filter = FILTER_HORIZONTAL;
                break;
            case 3:
                filter = FILTER_SOBEL_V;
                break;
            case 4:
                filter = FILTER_SOBEL_H;
                break;
            case 5:
                filter = FILTER_SCHARR_V;
                break;
            case 6:
                filter = FILTER_SCHARR_H;
                break;
            case 7:
                filter = FILTER_H1;
                break;
            case 8:
                filter = FILTER_H2;
                break;
            case 9:
                filter = FILTER_H3;
                break;
        }

        double[][] redConv = convolutionType2(image[0], height, width, filter, 3, 3, 1);
        double[][] greenConv = convolutionType2(image[1], height, width, filter, 3, 3, 1);
        double[][] blueConv = convolutionType2(image[2], height, width, filter, 3, 3, 1);
        double[][] convolvedPixels = new double[redConv.length][redConv[0].length];

        for (int i = 0; i < redConv.length; i++) {
            for (int j = 0; j < redConv[i].length; j++) {
                convolvedPixels[i][j] = redConv[i][j] + greenConv[i][j] + blueConv[i][j];
            }
        }

        BufferedImage writeBackImage = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < convolvedPixels.length; i++) {
            for (int j = 0; j < convolvedPixels[i].length; j++) {
                Color color = new Color(fixOutOfRangeRGBValues(convolvedPixels[i][j]), fixOutOfRangeRGBValues(convolvedPixels[i][j]), fixOutOfRangeRGBValues(convolvedPixels[i][j]));
                writeBackImage.setRGB(j, i, color.getRGB());
            }
        }
        return writeBackImage;
    }

    public double[][] convolutionType2(double[][] input, int width, int height, double[][] kernel, int kernelWidth, int kernelHeight, int iterations) {

        double[][] newInput = (double[][]) input.clone();
        double[][] output = (double[][]) input.clone();
        for (int i = 0; i < iterations; ++i) {
            output = convolution2DPadded(newInput, width, height, kernel, kernelWidth, kernelHeight);
            newInput = (double[][]) output.clone();
        }
        return output;
    }

    public double[][] convolution2DPadded(double[][] input, int width, int height, double[][] kernel, int kernelWidth, int kernelHeight) {

        int smallWidth = width - kernelWidth + 1;
        int smallHeight = height - kernelHeight + 1;
        int top = kernelHeight / 2;
        int left = kernelWidth / 2;

        double small[][] = convolution2D(input, width, height, kernel, kernelWidth, kernelHeight);
        double large[][] = new double[width][height];
        for (int j = 0; j < height; ++j) {
            for (int i = 0; i < width; ++i) {

                large[i][j] = 0;
            }
        }
        for (int j = 0; j < smallHeight; ++j) {
            for (int i = 0; i < smallWidth; ++i) {
                large[i + left][j + top] = small[i][j];
            }
        }
        return large;
    }

    public double[][] convolution2D(double[][] input, int width, int height, double[][] kernel, int kernelWidth, int kernelHeight) {
        int smallWidth = width - kernelWidth + 1;
        int smallHeight = height - kernelHeight + 1;
        double[][] output = new double[smallWidth][smallHeight];
        for (int i = 0; i < smallWidth; ++i) {
            for (int j = 0; j < smallHeight; ++j) {
                output[i][j] = 0;
            }
        }
        for (int i = 0; i < smallWidth; ++i) {
            for (int j = 0; j < smallHeight; ++j) {

                output[i][j] = singlePixelConvolution(input, i, j, kernel, kernelWidth, kernelHeight);
            }
        }
        return output;
    }

    public double singlePixelConvolution(double[][] input, int x, int y, double[][] k, int kernelWidth, int kernelHeight) {

        double output = 0;
        for (int i = 0; i < kernelWidth; ++i) {
            for (int j = 0; j < kernelHeight; ++j) {
                output = output + (input[x + i][y + j] * k[i][j]);
            }
        }
        return output;
    }

    private int fixOutOfRangeRGBValues(double value) {
        if (value < 0.0) {
            value = -value;
        }
        if (value > 255) {
            return 255;
        } else {
            return (int) value;
        }
    }

    public String LZW_compress(BufferedImage src) throws IOException {

        HashMap<String, Integer> dictionary = new HashMap<>();

        int dictSize = 256;

        String P, fileName, BP;

        String extension = "bmp";

        byte[] buffer = new byte[3];

        boolean isLeft = true;

        int i, byteToInt;

        char C;

        // Character dictionary  
        for (i = 0; i < 256; i++) {

            dictionary.put(Character.toString((char) i), i);

        }

        // Get file name 
        fileName = this.name.getText();

        // Read input file and output file 
        String[] getFileNameWOExtn = fileName.split("\\.");

        RandomAccessFile outputFile = new RandomAccessFile(getFileNameWOExtn[0].concat(".lzw"), "rw");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ImageIO.write(src, extension, baos);

        byte[] bytes = baos.toByteArray();

        try {

            InputStream in = new ByteArrayInputStream(bytes);

            // Read first byte to initialize P 
            byteToInt = in.read();

            if (byteToInt < 0) {
                byteToInt += 256;
            }

            C = (char) byteToInt;

            P = "" + C;

            while (true) {

                byteToInt = in.read();

                if (byteToInt == -1) {

                    // End of file has been reached  
                    // We still have something in P 
                    BP = convertTo12Bit(dictionary.get(P));

                    if (isLeft) {

                        buffer[0] = (byte) Integer.parseInt(BP.substring(0, 8), 2);

                        buffer[1] = (byte) Integer.parseInt(BP.substring(8, 12) + "0000", 2);

                        outputFile.writeByte(buffer[0]);

                        outputFile.writeByte(buffer[1]);

                    } else {

                        buffer[1] += (byte) Integer.parseInt(BP.substring(0, 4), 2);

                        buffer[2] = (byte) Integer.parseInt(BP.substring(4, 12), 2);

                        for (i = 0; i < buffer.length; i++) {

                            outputFile.writeByte(buffer[i]);

                            buffer[i] = 0;

                        }

                    }

                    break;

                }

                if (byteToInt < 0) {
                    byteToInt += 256;
                }

                C = (char) byteToInt;

                // if P+C is present in dictionary 
                if (dictionary.containsKey(P + C)) {

                    P = P + C;

                } else {

                    BP = convertTo12Bit(dictionary.get(P));

                    if (isLeft) {

                        buffer[0] = (byte) Integer.parseInt(BP.substring(0, 8), 2);

                        buffer[1] = (byte) Integer.parseInt(BP.substring(8, 12) + "0000", 2);

                    } else {

                        buffer[1] += (byte) Integer.parseInt(BP.substring(0, 4), 2);

                        buffer[2] = (byte) Integer.parseInt(BP.substring(4, 12), 2);

                        for (i = 0; i < buffer.length; i++) {

                            outputFile.writeByte(buffer[i]);

                            buffer[i] = 0;

                        }

                    }

                    isLeft = !isLeft;

                    if (dictSize < 4096) {
                        dictionary.put(P + C, dictSize++);
                    }

                    P = "" + C;

                }

            }

        } catch (IOException ie) {

            ie.printStackTrace();

        } finally {

            outputFile.close();

        }

        return getFileNameWOExtn[0].concat(".lzw");

    }

    public String convertTo12Bit(int i) {

        String to12Bit = Integer.toBinaryString(i);

        while (to12Bit.length() < 12) {
            to12Bit = "0" + to12Bit;
        }

        return to12Bit;

    }

    public void LZW_decompress(String fileName) throws IOException {

        String[] arrayOfChar;

        String extension = "bmp";

        int dictSize = 256, currentword, previousword;

        byte[] buffer = new byte[3];

        boolean isLeft = true;

        arrayOfChar = new String[4096];

        int i;

        for (i = 0; i < 256; i++) {
            arrayOfChar[i] = Character.toString((char) i);
        }

        // Read input file and output file 
        RandomAccessFile inputFile = new RandomAccessFile(fileName, "r");

        RandomAccessFile outputFile = new RandomAccessFile("output_image.".concat(extension), "rw");

        try {

            buffer[0] = inputFile.readByte();

            buffer[1] = inputFile.readByte();

            previousword = getIntValue(buffer[0], buffer[1], isLeft);

            isLeft = !isLeft;

            outputFile.writeBytes(arrayOfChar[previousword]);

            // Reads three bytes and generates corresponding characters 
            while (true) {

                if (isLeft) {

                    buffer[0] = inputFile.readByte();

                    buffer[1] = inputFile.readByte();

                    currentword = getIntValue(buffer[0], buffer[1], isLeft);

                } else {

                    buffer[2] = inputFile.readByte();

                    currentword = getIntValue(buffer[1], buffer[2], isLeft);

                }

                isLeft = !isLeft;

                /* 

                 currentword not in dictionary, we just add the previousword in the entry. 

                 */
                if (currentword >= dictSize) {

                    if (dictSize < 4096) {

                        arrayOfChar[dictSize] = arrayOfChar[previousword] + arrayOfChar[previousword].charAt(0);

                    }

                    dictSize++;

                    outputFile.writeBytes(arrayOfChar[previousword] + arrayOfChar[previousword].charAt(0));

                } /* 

                 If word is present, we form a word with the previousword and the first character of the  

                 currentword and add it in a new entry 

                 */ else {

                    if (dictSize < 4096) {

                        arrayOfChar[dictSize] = arrayOfChar[previousword] + arrayOfChar[currentword].charAt(0);

                    }

                    dictSize++;

                    outputFile.writeBytes(arrayOfChar[currentword]);

                }

                previousword = currentword;

            }

        } catch (EOFException e) {

            inputFile.close();

            outputFile.close();

        }

    }

    /* 

        Converting 2 bytes to 12-bit code. 

        Converting 12-bit code to integer value. 

     */
    public int getIntValue(byte b1, byte b2, boolean isLeft) {

        String t1 = Integer.toBinaryString(b1);

        String t2 = Integer.toBinaryString(b2);

        while (t1.length() < 8) {
            t1 = "0" + t1;
        }

        if (t1.length() == 32) {
            t1 = t1.substring(24, 32);
        }

        while (t2.length() < 8) {
            t2 = "0" + t2;
        }

        if (t2.length() == 32) {
            t2 = t2.substring(24, 32);
        }

        if (isLeft) {
            return Integer.parseInt(t1 + t2.substring(0, 4), 2);
        } else {
            return Integer.parseInt(t1.substring(4, 8) + t2, 2);
        }

    }

    class HuffmanNode {

        int freq;
        int pixel;
        HuffmanNode left;
        HuffmanNode right;

        public HuffmanNode() {
        }

        public HuffmanNode(int pixel, int freq) {
            this.pixel = pixel;
            this.freq = freq;
        }
    }

    // For comparing the nodes
    class HuffmanComparator implements Comparator<HuffmanNode> {

        public int compare(HuffmanNode x, HuffmanNode y) {

            return x.freq - y.freq;
        }
    }

    // Implementing the huffman algorithm
    public void buildHuffmanCode(HuffmanNode root, String s, HashMap<Integer, String> ht) {
        if (root.left == null && root.right == null) {
            ht.put(root.pixel, s);
            return;
        }
        buildHuffmanCode(root.left, s + "0", ht);
        buildHuffmanCode(root.right, s + "1", ht);
    }

    public void HuffmanCompress(BufferedImage src) {
        int w = src.getWidth();
        int h = src.getHeight();

        // pixel, freq
        Map<Integer, Integer> charOcurrence = new HashMap<>();
        // Get image
        int[] image = src.getRGB(0, 0, w, h, null, 0, w);
//Pas 1
        for (int i = 0; i < image.length; ++i) {
            int pixel = image[i];
            if (charOcurrence.containsKey(pixel)) {
                charOcurrence.put(pixel, charOcurrence.get(pixel) + 1);
            } else {
                charOcurrence.put(pixel, 1);
            }
        }
//Generam fisierul huff
        try {
            String[] f = this.name.getText().split("\\.");
            FileWriter fileWriter = new FileWriter(f[0] + ".huff");
            try (BufferedWriter buff = new BufferedWriter(fileWriter)) {
                Set<Map.Entry<Integer, Integer>> set = charOcurrence.entrySet();
                Iterator<Map.Entry<Integer, Integer>> iterator = set.iterator();
                while (iterator.hasNext()) {
                    @SuppressWarnings("rawtypes")
                    Map.Entry entry = (Map.Entry) iterator.next();
                    buff.write(entry.getKey() + " " + entry.getValue());
                    buff.newLine();
                }
            }
        } catch (Exception ex) {
        }
        int n = charOcurrence.size();
//Pas2
        PriorityQueue<HuffmanNode> q = new PriorityQueue<>(n, new HuffmanComparator());
        for (int key : charOcurrence.keySet()) {
            HuffmanNode hn = new HuffmanNode();
            hn.pixel = key;
            hn.freq = charOcurrence.get(key);
            hn.left = null;
            hn.right = null;
            q.add(hn);
        }
//Pas 3,4
        HuffmanNode root = null;
        while (q.size() > 1) {
            HuffmanNode x = q.peek();
            q.poll();
            HuffmanNode y = q.peek();
            q.poll();
            HuffmanNode f = new HuffmanNode();
            f.freq = x.freq + y.freq;
            f.pixel = -1;
            f.left = x;
            f.right = y;
            root = f;
            q.add(f);
        }
        HashMap<Integer, String> htim = new HashMap<>();
        buildHuffmanCode(root, "", htim);
// Generam fisierul himg
        generateHuffmanBitString(src, htim);
    }

    public void generateHuffmanBitString(BufferedImage img, HashMap<Integer, String> ht) {
        String bit = "";
        String[] f = this.name.getText().split("\\.");
        String filename = f[0] + ".himg";
        File hname = new File(filename);
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(hname));
            int w = img.getWidth();
            int h = img.getHeight();
            out.writeInt(w);
            out.writeInt(h);
            int[] dataBuffInt = img.getRGB(0, 0, w, h, null, 0, w);
            for (int i = 0; i < dataBuffInt.length; i++) {
                if (ht.containsKey(dataBuffInt[i])) {
                    bit = bit + ht.get(dataBuffInt[i]);
                    while (bit.length() >= 31) {
                        out.writeInt(Integer.parseInt(bit.substring(0, 31), 2));
                        bit = bit.substring(31, bit.length());
                    }
                } else {
                    break;
                }
            }
            if (bit.length() > 0) {
                out.writeInt(Integer.parseInt(bit, 2));
                out.writeInt(bit.length());
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        opencvTest();
        launch(args);
    }

}
