package br.unitins.tp1.roteadores.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FuncionarioFileServiceImpl implements FileService{
    
    private final String PATH_FUNCIONARIO = "D:\\Arquivos\\quarkus\\ecommerce-roteadores\\funcionario\\";

    private static final List<String> SUPPORTED_MIME_TYPES =
        Arrays.asList("image/jpeg", "image/jpg", "image/png", "image/gif");

    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; // 10 MB


    @Override
    public String save(String nomeArquivo, byte[] arquivo) throws IOException {

        // verificarTipopArquivo(nomeArquivo);

        // verificarTamanhoArquivo(arquivo);

        Path diretorio = Paths.get(PATH_FUNCIONARIO);
        if(!new File(PATH_FUNCIONARIO).exists())
            Files.createDirectory(diretorio);

        String mimeType = Files.probeContentType(Paths.get(nomeArquivo));
        String extensao = mimeType.substring(mimeType.lastIndexOf("/") + 1);
        String novoNomeArquivo = UUID.randomUUID() + "." + extensao;

        // Criar funcao para se existir, rodar o UUID random de novo e gerar aleatorio, até não ter mais nenhum com mesmo nome

        // caminho final do arquivo
        Path filePath = diretorio.resolve(novoNomeArquivo);

        if (filePath.toFile().exists()) {
            // o ideal eh gerar um novo nome para o arquivo
            throw new IOException("O nome do arquivo (gerado) ja existe: " + novoNomeArquivo);
        }

        // salvando a imagem
        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            fos.write(arquivo);
        }

        return novoNomeArquivo;
        
    }

    @Override
    public File find(String nomeArquivo) {
        // eh ideal verificar se existe para nao retornar um file vazio
        return new File(PATH_FUNCIONARIO + nomeArquivo);
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("user.home"));
    }
}
