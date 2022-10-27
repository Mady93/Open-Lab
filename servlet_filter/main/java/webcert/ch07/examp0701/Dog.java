/**
 *  Copyright (c) 2005 by David Bridgewater
 *  All rights reserved.
 *  
 *  You may study, use, modify, and distribute this
 *  software for any purpose provided that this
 *  copyright notice appears in all copies.
 *  
 *  This software is provided without warranty
 *  either expressed or implied.
 */
package webcert.ch07.examp0701;
/**
 * @author David Bridgewater
 *
 * About a Dog
 */
public class Dog implements Animal {
    private String name;
    private float mass;
    private boolean insured;
    private char sex;
    private String barkVolume;
    
    /* All these are general animal details so
     * the methods come from the Animal Interface.
     * @see webcert.ch07.examp0701.Animal#getName()
     */

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getWeight() {
        return mass;
    }
    public void setWeight(float weight) {
        mass = weight;
    }
    public boolean isInsured() {
        return insured;
    }
    public void setInsured(boolean insured) {
        this.insured = insured;
    }
    public char getSex() {
        return sex;
    }
    public void setSex(char sex) {
        this.sex = sex;
    }
    public String getBarkVolume() {
        return barkVolume;
    }
    public void setBarkVolume(String barkVolume) {
        this.barkVolume = barkVolume;
    }


}

