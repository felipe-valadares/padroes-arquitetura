package com.padroes.chainofresponsibility;

public abstract class ManipuladorSuporte {

    protected ManipuladorSuporte proximo;

    public ManipuladorSuporte setProximo(ManipuladorSuporte proximo) {
        this.proximo = proximo;
        return proximo;
    }

    public abstract String manipular(TicketSuporte ticket);

    protected String passarParaProximo(TicketSuporte ticket) {
        if (proximo != null) {
            return proximo.manipular(ticket);
        }
        return "Ticket #" + ticket.id() + " sem responsável — escale para a equipe de engenharia.";
    }
}
