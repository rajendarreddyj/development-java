module calculator.gui {
    exports com.rajendarreddyj.jigsaw.calculator.gui to javafx.graphics;

    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires calculator.api;
    uses com.rajendarreddyj.jigsaw.calculator.api.Algorithm;
}
