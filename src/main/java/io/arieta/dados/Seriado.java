package io.arieta.dados;

public class Seriado extends Video {
    private int anoInicio;
    private int anoFim;
    private int numEpisodios;

    public Seriado(int codigo, String titulo, int anoInicio, int anoFim, int numEpisodios) {
        super(codigo, titulo);
        this.anoInicio = anoInicio;
        this.anoFim = anoFim;
        this.numEpisodios = numEpisodios;
    }

    @Override
    public String geraTexto() {
        StringBuilder sb = new StringBuilder();
        sb
                .append(this.getCodigo())
                .append("-")
                .append(this.getTitulo())
                .append("-")
                .append(this.getAnoInicio())
                .append("-")
                .append(this.getAnoFim())
                .append("-")
                .append(this.getNumEpisodios())
                .append("-")
                .append(this.calculaCusto());

        return sb.toString();
    }

    public int getAnoInicio() {
        return anoInicio;
    }

    public int getAnoFim() {
        return anoFim;
    }

    public int getNumEpisodios() {
        return numEpisodios;
    }

    // Custa R$0,50 para cada episódio
    @Override
    public double calculaCusto() {
        return 0.50 * numEpisodios;
    }
}
