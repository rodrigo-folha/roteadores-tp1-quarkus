package br.unitins.tp1.roteadores.service;

import java.io.File;
import java.io.IOException;

public interface FileService {
    
    String save(String nomeArquivo, byte[] arquivo) throws IOException;

    File find(String nomeArquivo);

    
}
