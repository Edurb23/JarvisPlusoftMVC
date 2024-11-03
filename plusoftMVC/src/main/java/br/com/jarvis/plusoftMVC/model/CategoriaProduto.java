package br.com.jarvis.plusoftMVC.model;

public enum CategoriaProduto {


    CAMISA("Camisa"),
    CALCA("Calça"),
    VESTIDO("Vestido"),
    SAIA("Saia"),
    SHORT("Short"),
    BLAZER("Blazer"),
    JAQUETA("Jaqueta"),
    SUETER("Suéter"),
    MOLETOM("Moletom");

    private final String label;

    CategoriaProduto(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
