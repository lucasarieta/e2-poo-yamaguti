package io.arieta.dados;

public class Filme extends Video {
    private String diretor;
    private double duracao;

    public Filme(int codigo, String titulo, String diretor, double duracao) {
        super(codigo, titulo);
        this.diretor = diretor;
        this.duracao = duracao;
    }

    @Override
    public String geraTexto() {
        StringBuilder sb = new StringBuilder();
        sb
                .append(this.getCodigo())
                .append("-")
                .append(this.getTitulo())
                .append("-")
                .append(this.getDiretor())
                .append("-")
                .append(this.getDuracao())
                .append("-")
                .append(this.calculaCusto());


        return sb.toString();
    }

    public String getDiretor() {
        return diretor;
    }

    public double getDuracao() {
        return duracao;
    }

    // Custa R$0,30 por minuto de duração do filme
    @Override
    public double calculaCusto() {
        return 0.30 * duracao;
    }
}
