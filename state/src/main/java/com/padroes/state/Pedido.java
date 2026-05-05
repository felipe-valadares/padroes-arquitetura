package com.padroes.state;

import com.padroes.state.estados.EstadoPendente;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private EstadoPedido estado;
    private final List<String> historico = new ArrayList<>();

    public Pedido() {
        this.estado = new EstadoPendente();
        historico.add("Pedido criado: " + estado.getNome());
    }

    public void setEstado(EstadoPedido novoEstado) {
        this.estado = novoEstado;
        historico.add("Transição para: " + novoEstado.getNome());
    }

    public void pagar()    { estado.pagar(this); }
    public void enviar()   { estado.enviar(this); }
    public void entregar() { estado.entregar(this); }
    public void cancelar() { estado.cancelar(this); }

    public String getEstado()          { return estado.getNome(); }
    public List<String> getHistorico() { return List.copyOf(historico); }
}
