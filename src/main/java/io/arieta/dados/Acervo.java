package io.arieta.dados;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class Acervo {
    private ArrayList<Video> videos = new ArrayList<>();

    public boolean addVideo(Video v) {
        if (videos.stream().anyMatch(video -> video.getCodigo() == v.getCodigo())) {
            return false;
        }

        this.videos.add(v);
        return true;
    }

    public Video getVideoComMaiorTitulo() {
        return videos
                .stream()
                .max(Comparator.comparingInt(video -> video.getTitulo().length()))
                .orElse(null);
    }

    public Video getVideoComMenorCusto() {
        return videos
                .stream()
                .max(Comparator.comparingDouble(Video::calculaCusto))
                .orElse(null);
    }

    public Seriado getSeriadoComMaiorPeriodoDeExibicao() {
        return videos
                .stream()
                .filter(video -> video instanceof Seriado)
                .map(video -> (Seriado) video)
                .max(Comparator.comparingInt(seriado -> seriado.getAnoFim() - seriado.getAnoInicio()))
                .orElse(null);
    }

    public String getDiretorComMaisFilmes() {
        Map <String, Long> diretores = videos
                .stream()
                .filter(video -> video instanceof Filme)
                .map(video -> ((Filme) video).getDiretor())
                .collect(Collectors.groupingBy(diretor -> diretor, Collectors.counting()));

        return diretores.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> "5:"+entry.getKey()+","+entry.getValue())
                .orElse("5:Erro - nenhum filme cadastrado.");
    }

    public String getVideoComMenorDesvioPadrao() {
        double media = videos
                .stream()
                .mapToDouble(Video::calculaCusto)
                .average()
                .orElse(0);

        Video videoComMenorDesvio = videos
                .stream()
                .min(Comparator.comparingDouble(video -> Math.abs(video.calculaCusto() - media)))
                .orElse(null);

        if (videoComMenorDesvio == null) {
            return "6:Erro - nenhum video cadastrado";
        }

        BigDecimal mediaArredondada = new BigDecimal(media).setScale(2, RoundingMode.HALF_UP);
        return "6:"+mediaArredondada+","+videoComMenorDesvio.geraTexto();
    }
}
