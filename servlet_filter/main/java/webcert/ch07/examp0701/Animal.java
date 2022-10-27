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
 */
public interface Animal {

    public abstract String getName();

    public abstract void setName(String name);

    public abstract float getWeight();

    public abstract void setWeight(float weight);

    public abstract boolean isInsured();

    public abstract void setInsured(boolean insured);

    public abstract char getSex();

    public abstract void setSex(char sex);

}