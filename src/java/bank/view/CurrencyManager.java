/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.view;

import bank.controller.ConverterFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import bank.model.ConversionDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;

@Named("currencyManager")
@ConversationScoped
public class CurrencyManager implements Serializable {

    private static final long serialVersionUID = 16247164405L;
    @EJB
    private ConverterFacade converterFacade;
    private String fromCurrency;
    private String toCurrency;
    private float amount;
    private float result;
    private List<String> fromCurrencies;
    private List<String> toCurrencies;

    private Exception transactionFailure;
    @Inject
    private Conversation conversation;

    @PostConstruct
    private void CurrencyManager() {

        fromCurrencies = converterFacade.fromCurrencies();
        toCurrencies = converterFacade.toCurrencies();
        // fromCurrency = fromCurrencies.get(0);
        // toCurrency = toCurrencies.get(0);

    }

    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    private void handleException(Exception e) {
        System.out.println("Exception is being handled. Conversation needs to be stopped. The exception is " + e.getMessage());
        stopConversation();
        e.printStackTrace(System.err);
        System.out.println("Exception is being handled. The exception is " + e.getMessage());
        transactionFailure = e;
    }

    /**
     * @return <code>true</code> if the latest transaction succeeded, otherwise
     * <code>false</code>.
     */
    public boolean getSuccess() {
        return transactionFailure == null;
    }

    /**
     * Returns the latest thrown exception.
     */
    public Exception getException() {
       if (transactionFailure != null){
           System.out.println("exception here !!!!!!!!! Is not null!!!!");
       }
        System.out.println("exception here !!!!!!!!!");
        return null;//transactionFailure;
    }

    private String jsf22Bugfix() {
        return "";
    }

    public void convert() {
        try {
            startConversation();
            transactionFailure = null;
            result = converterFacade.Convert(fromCurrency, toCurrency, amount);
            System.out.println("i am here man !!!!!!!!!!!!!!!!!!!!!!!!!");
            //resultOut= result + " " + toCurrency;
            System.out.println(" BLABLABALALALALAL :" + result);

            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");

        } catch (Exception e) {
            System.out.println("We are catching exceptions. More precisely " + e.getMessage());
            handleException(e);
            //stopConversation();
        }
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public List<String> getFromCurrencies() {
        return fromCurrencies;
    }

    public List<String> getToCurrencies() {
        return toCurrencies;
    }

    public void setFromCurrencies(List<String> fromCurrencies) {
        this.fromCurrencies = fromCurrencies;
    }

    public void setToCurrencies(List<String> toCurrencies) {
        this.toCurrencies = toCurrencies;
    }

    public void getCurrencies() {
        this.fromCurrencies = converterFacade.fromCurrencies();
        System.out.println(fromCurrencies);

    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }

    public Exception getTransactionFailure() {
        return transactionFailure;
    }

    public void setTransactionFailure(Exception transactionFailure) {
        this.transactionFailure = transactionFailure;
    }

    
    
}
