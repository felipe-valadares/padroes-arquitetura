package com.padroes.adapter;

public class StripeCliente {

    public StripeCharge charge(long amountCents, String description, String recipient) {
        return new StripeCharge("ch_" + System.nanoTime(), "succeeded", amountCents);
    }

    public record StripeCharge(String id, String status, long amountCents) {}
}
