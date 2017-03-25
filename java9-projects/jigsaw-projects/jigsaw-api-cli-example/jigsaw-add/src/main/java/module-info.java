module calculator.add {
    requires calculator.api;
    provides com.rajendarreddyj.jigsaw.calculator.api.Algorithm with com.rajendarreddyj.jigsaw.calculator.api.impl.Add;
}
