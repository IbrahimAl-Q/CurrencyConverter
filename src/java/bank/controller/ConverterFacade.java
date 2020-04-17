/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.controller;

import bank.model.ConversionDTO;
import bank.model.ConvertRates;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

/**
 * A controller. All calls to the model that are executed because of an action
 * taken by the cashier pass through here.
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class ConverterFacade {

    @PersistenceContext(unitName = "bankPU")
    private EntityManager em;

    public float Convert(String fromCurrency, String toCurrency, float amount) {
        int id = 0;
        switch (fromCurrency) {
            case "EUR":

                switch (toCurrency) {
                    case "GBP":
                        id = 1;
                        break;

                    case "JPY":
                        id = 2;
                        break;

                    case "USD":
                        id = 3;
                        break;

                    case "AUD":
                        id = 4;
                        break;
                }

                break;

            case "USD":

                switch (toCurrency) {
                    case "AUD":
                        id = 5;
                        break;

                    case "JPY":
                        id = 6;
                        break;

                    case "GBP":
                        id = 7;
                        break;

                    case "EUR":
                        id = 8;
                        break;
                }

                break;
                
                case "AUD":

                switch (toCurrency) {
                    case "GBP":
                        id = 9;
                        break;

                    case "EUR":
                        id = 10;
                        break;

                    case "JPY":
                        id = 11;
                        break;

                    case "USD":
                        id = 16;
                        break;
                }

                break;
                
                
                 case "GBP":

                switch (toCurrency) {
                    case "USD":
                        id = 12;
                        break;

                    case "JPY":
                        id = 13;
                        break;

                    case "AUD":
                        id = 14;
                        break;

                    case "EUR":
                        id = 15;
                        break;
                }

                break;
            default:
                System.out.println("Not a valid currency to convert from");
                break;
        }
        ConvertRates cr = em.find(ConvertRates.class,id);
        float rate = cr.getConvRate();
        System.out.println("conversion rate between the two currencies is "+ rate);
        return convertrate(amount, rate);
    }

    public List<String> fromCurrencies() {
        Query query = em.createQuery("SELECT DISTINCT e.fromCurrency FROM ConvertRates e");
        List<String> Allcurrencies = (List<String>) query.getResultList();

        return Allcurrencies;
    }

    public List<String> toCurrencies() {
        Query query = em.createQuery("SELECT DISTINCT e.toCurrency FROM ConvertRates e");
        List<String> Allcurrencies = (List<String>) query.getResultList();
        return Allcurrencies;
    }

    public float convertrate(double amount, double rate) {
        BigDecimal amountDB = new BigDecimal(amount);
        BigDecimal rateDB = new BigDecimal(rate);
        return (float) amountDB.multiply(rateDB).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    

}
