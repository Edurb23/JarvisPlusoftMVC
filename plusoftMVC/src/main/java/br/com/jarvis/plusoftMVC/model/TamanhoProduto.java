package br.com.jarvis.plusoftMVC.model;

public enum TamanhoProduto {


    PP("PP"),
    P("P"),
    M("M"),
    G("G"),
    GG("GG"),
    XG("XG"),
    XXG("XXG");

    private final String label;

    TamanhoProduto(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
