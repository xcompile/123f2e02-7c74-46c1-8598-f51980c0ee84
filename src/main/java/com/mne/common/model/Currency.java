package com.mne.common.model;


/**
 * currency
 * 
 * model for currency
 * 
 * @author Moritz Neuhaeuser<moritz.neuhaeuser@gmail.com>
 * @since(0.1.0)
 *
 */
public class Currency {

    // Alpha3 Code
    public String code;
    // Extended name
    public String name;
    
    public Currency() {
        super();
    }
    public Currency(String code, String name) {
        super();
        this.code = code;
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
