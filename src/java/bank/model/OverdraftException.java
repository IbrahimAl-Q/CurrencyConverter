/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.model;

/**
 * Thrown when withdrawal would result in a negative balance.
 */
public class OverdraftException extends Exception {
    private static final long serialVersionUID = 16247164402L;

    public OverdraftException(String msg) {
        super(msg);
    }

}