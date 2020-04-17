package bank.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A persistent representation of an account.
 */
@Entity
public class ConvertRates implements ConversionDTO, Serializable {

    private static final long serialVersionUID = 16247164401L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private float convRate;
    private String fromCurrency;
    private String toCurrency;

    /**
     * Creates a new instance of ConvertRates
     */
    public ConvertRates() {
    }

    /**
     * Creates a new instance of ConvertRates
     * @param fromCurrency
     * @param toCurrency
     */
    public ConvertRates(int id,float convRate,String fromCurrency, String toCurrency) {
        this.id=id;
        this.convRate=convRate;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
    }

    @Override
    public float getConvRate() {
        return convRate;
    }
    
    public float setConvRate(float ConvRate){
        return convRate;
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fromCurrency != null ? fromCurrency.hashCode() : 0);
        hash += (toCurrency != null ? toCurrency.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
      //  if (!(object instanceof ConvertRates)) {
        //    return false;
        //}
        
        //ConvertRates other = (ConvertRates) object;
        //System.out.println("In equals " + this.fromCurrency + " == " + other.fromCurrency);
        //System.out.println(this.toCurrency + " == " + other.toCurrency);
        //return (this.fromCurrency.equals(other.fromCurrency) && this.toCurrency.equals(other.toCurrency));
        
         if (!(object instanceof ConvertRates)) {
            return false;
        }
        ConvertRates other = (ConvertRates) object;
        if (fromCurrency.equals(other.fromCurrency) 
                && toCurrency.equals(other.toCurrency)) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "ConvertRates{" + "id=" + id + ", convRate=" + convRate + ", Convert1=" + fromCurrency + ", Convert2=" + toCurrency + '}';
    }

    
    
    @Override
    public String getFromCurrency() {
        return fromCurrency;
    }
    
    public void setFromCurrency(String fromCurrency){
        this.fromCurrency=fromCurrency;
    }

    public void setToCurrency(String toCurrency){
        this.toCurrency=toCurrency;
    }
    
    @Override
    public String getToCurrency() {
        return toCurrency;
    }

}
