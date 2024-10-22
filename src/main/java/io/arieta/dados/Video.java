package io.arieta.dados;

public abstract class Video implements Imprimivel {
    private int codigo;
    private String titulo;

    public Video(int codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }
}
