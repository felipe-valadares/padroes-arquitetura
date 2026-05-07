package com.padroes.chainofresponsibility;

public class Main {

    public static void main(String[] args) {
        ManipuladorSuporte n1 = new SuporteNivel1();
        ManipuladorSuporte n2 = new SuporteNivel2();
        ManipuladorSuporte n3 = new SuporteNivel3();

        n1.setProximo(n2).setProximo(n3);

        var tickets = new TicketSuporte[]{
            new TicketSuporte(1, "Redefinir senha", Prioridade.BAIXA),
            new TicketSuporte(2, "Lentidão no sistema", Prioridade.MEDIA),
            new TicketSuporte(3, "Falha crítica em produção", Prioridade.ALTA),
        };

        for (TicketSuporte ticket : tickets) {
            System.out.println(n1.manipular(ticket));
        }
    }
}
