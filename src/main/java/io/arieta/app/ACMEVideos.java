package io.arieta.app;

import io.arieta.dados.*;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class ACMEVideos {
    private final Acervo acervo;

    public ACMEVideos() {
        this.acervo = new Acervo();
    }

    public void processar() {
        this.cadastrarVideos();
        this.mostrarVideoComMaiorTitulo();
        this.mostrarVideoComMenorCusto();
        this.mostrarSeriadoComMaiorPeriodoDeExibicao();
        this.mostrarDiretorComMaisFilmes();
        this.mostrarVideoComMenosDesvioPadrao();
    }

    public void cadastrarVideos() {
        try (Scanner scanner = new Scanner(new File("dadosentrada.txt"))) {
            StringBuilder saida = new StringBuilder();

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] partes = linha.split(";");
                int tipo = Integer.parseInt(partes[0]);

                if (tipo == 1) {
                    // Filme
                    int codigo = Integer.parseInt(partes[1]);
                    String titulo = partes[2];
                    String diretor = partes[3];
                    double duracao = Double.parseDouble(partes[4]);

                    Video filme = new Filme(codigo, titulo, diretor, duracao);
                    boolean result = acervo.addVideo(filme);
                    if (result) {
                        saida.append("1:").append(filme.geraTexto()).append("\n");
                    } else {
                        saida.append("1:Erro - codigo de video repetido").append("\n");
                    }
                } else if (tipo == 2) {
                    // Seriado
                    int codigo = Integer.parseInt(partes[1]);
                    String titulo = partes[2];
                    int anoInicio = Integer.parseInt(partes[3]);
                    int anoFim = Integer.parseInt(partes[4]);
                    int episodios = Integer.parseInt(partes[5]);

                    Video seriado = new Seriado(codigo, titulo, anoInicio, anoFim, episodios);
                    acervo.addVideo(seriado);
                    saida.append("1:").append(seriado.geraTexto()).append("\n");
                }
            }

            Files.write(Paths.get("relatorio.txt"), saida.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVideoComMaiorTitulo() {
        Video video = acervo.getVideoComMaiorTitulo();
        try {
            String saida = video == null ? "2:Erro – nenhum vídeo cadastrado.\n"
                    : String.format("2:%d,%s\n", video.getCodigo(), video.getTitulo());
            Files.write(Paths.get("relatorio.txt"), saida.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVideoComMenorCusto() {
        Video video = acervo.getVideoComMenorCusto();
        try {
            String saida = video == null ? "3:Erro – nenhum video cadastrado.\n"
                    : String.format("3:%d,%s,%.2f\n", video.getCodigo(), video.getTitulo(), video.calculaCusto());
            Files.write(Paths.get("relatorio.txt"), saida.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarSeriadoComMaiorPeriodoDeExibicao() {
        Seriado seriado = acervo.getSeriadoComMaiorPeriodoDeExibicao();
        try {
            String saida = seriado == null ? "4:Erro – nenhum seriado cadastrado.\n"
                    : String.format("4:%d,%s,%d\n", seriado.getCodigo(), seriado.getTitulo(),
                    seriado.getAnoFim() - seriado.getAnoInicio());
            Files.write(Paths.get("relatorio.txt"), saida.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarDiretorComMaisFilmes() {
        try {
            String saida = acervo.getDiretorComMaisFilmes() + "\n";
            Files.write(Paths.get("relatorio.txt"), saida.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVideoComMenosDesvioPadrao() {
        try {
            String saida = acervo.getVideoComMenorDesvioPadrao() + "\n";
            Files.write(Paths.get("relatorio.txt"), saida.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
