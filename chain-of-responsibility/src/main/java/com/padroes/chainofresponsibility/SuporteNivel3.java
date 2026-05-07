package com.padroes.chainofresponsibility;

public class SuporteNivel3 extends ManipuladorSuporte {

    @Override
    public String manipular(TicketSuporte ticket) {
        if (ticket.prioridade() == Prioridade.ALTA) {
            return "[N3] Ticket #" + ticket.id() + " resolvido: " + ticket.descricao();
        }
        return passarParaProximo(ticket);
    }
}
