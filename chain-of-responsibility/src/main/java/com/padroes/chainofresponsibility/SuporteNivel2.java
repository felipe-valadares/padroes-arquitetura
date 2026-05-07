package com.padroes.chainofresponsibility;

public class SuporteNivel2 extends ManipuladorSuporte {

    @Override
    public String manipular(TicketSuporte ticket) {
        if (ticket.prioridade() == Prioridade.MEDIA) {
            return "[N2] Ticket #" + ticket.id() + " resolvido: " + ticket.descricao();
        }
        return passarParaProximo(ticket);
    }
}
