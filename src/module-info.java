module PDFViewDemo {
    requires transitive javafx.controls;

    exports app;
    
    opens app to javafx.graphics;
    
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.materialdesign;

    requires java.logging;
    requires jdk.compiler;
    
    requires org.apache.pdfbox;
    requires org.apache.commons.lang3;
    requires java.desktop;
    requires javafx.swing;
    requires org.controlsfx.controls;
    
    requires com.indi.pdfviewfx;
	requires org.scenicview.scenicview;
	requires com.dlsc.pdfviewfx;

}