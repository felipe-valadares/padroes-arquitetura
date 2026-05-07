package com.padroes.chainofresponsibility;

public class SuporteNivel1 extends ManipuladorSuporte {

    @Override
    public String manipular(TicketSuporte ticket) {
        if (ticket.prioridade() == Prioridade.BAIXA) {
            return "[N1] Ticket #" + ticket.id() + " resolvido: " + ticket.descricao();
        }
        return passarParaProximo(ticket);
    }
}
