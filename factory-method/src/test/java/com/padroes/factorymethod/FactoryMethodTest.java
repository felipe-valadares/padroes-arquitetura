package com.padroes.factorymethod;

import com.padroes.factorymethod.criadores.CriadorDocumento;
import com.padroes.factorymethod.criadores.CriadorMarkdown;
import com.padroes.factorymethod.criadores.CriadorPDF;
import com.padroes.factorymethod.criadores.CriadorWord;
import com.padroes.factorymethod.documentos.DocumentoMarkdown;
import com.padroes.factorymethod.documentos.DocumentoPDF;
import com.padroes.factorymethod.documentos.DocumentoWord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Factory Method - Criadores de Documento")
class FactoryMethodTest {

    @Nested
    @DisplayName("CriadorPDF")
    class CriadorPDFTest {

        private final CriadorDocumento criador = new CriadorPDF();

        @Test
        @DisplayName("Deve criar instância de DocumentoPDF")
        void deveCriarDocumentoPDF() {
            assertInstanceOf(DocumentoPDF.class, criador.criarDocumento());
        }

        @Test
        @DisplayName("Formato deve ser 'PDF'")
        void formatoDeveSerPDF() {
            assertEquals("PDF", criador.criarDocumento().getFormato());
        }

        @Test
        @DisplayName("Deve abrir com referência ao PDF")
        void deveAbrirComReferenciaAoPDF() {
            assertTrue(criador.criarDocumento().abrir().toLowerCase().contains("pdf"));
        }
    }

    @Nested
    @DisplayName("CriadorWord")
    class CriadorWordTest {

        private final CriadorDocumento criador = new CriadorWord();

        @Test
        @DisplayName("Deve criar instância de DocumentoWord")
        void deveCriarDocumentoWord() {
            assertInstanceOf(DocumentoWord.class, criador.criarDocumento());
        }

        @Test
        @DisplayName("Formato deve ser 'DOCX'")
        void formatoDeveSerDOCX() {
            assertEquals("DOCX", criador.criarDocumento().getFormato());
        }

        @Test
        @DisplayName("Salvar deve referenciar DOCX")
        void salvarDeveReferirDOCX() {
            assertTrue(criador.criarDocumento().salvar().contains("DOCX"));
        }
    }

    @Nested
    @DisplayName("CriadorMarkdown")
    class CriadorMarkdownTest {

        @Test
        @DisplayName("Deve criar instância de DocumentoMarkdown")
        void deveCriarDocumentoMarkdown() {
            assertInstanceOf(DocumentoMarkdown.class, new CriadorMarkdown().criarDocumento());
        }

        @Test
        @DisplayName("Formato deve ser 'Markdown'")
        void formatoDeveSerMarkdown() {
            assertEquals("Markdown", new CriadorMarkdown().criarDocumento().getFormato());
        }
    }

    @Nested
    @DisplayName("processarDocumento")
    class ProcessarDocumentoTest {

        @Test
        @DisplayName("Deve incluir abertura, salvamento e fechamento")
        void deveIncluirCicloCompleto() {
            String log = new CriadorPDF().processarDocumento();
            assertAll(
                () -> assertTrue(log.contains("PDF")),
                () -> assertTrue(log.toLowerCase().contains("abrindo")),
                () -> assertTrue(log.toLowerCase().contains("salvando")),
                () -> assertTrue(log.toLowerCase().contains("fechando"))
            );
        }

        @Test
        @DisplayName("PDF não deve mencionar Word no processamento")
        void pdfNaoDeveMencionarWord() {
            String log = new CriadorPDF().processarDocumento();
            assertFalse(log.contains("Word"));
            assertFalse(log.contains("DOCX"));
        }

        @Test
        @DisplayName("Diferentes criadores geram logs distintos")
        void diferentesCriadoresGeramsLogsDiferentes() {
            assertNotEquals(
                new CriadorPDF().processarDocumento(),
                new CriadorWord().processarDocumento()
            );
        }
    }
}
