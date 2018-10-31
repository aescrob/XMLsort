/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tgdaero9
 */

package xmlsort;

public class Counter {
    private int count = 0;
    
    //Constructor
    public Counter(int i) {
        count = i;
    }
    
    public Counter() {
        this(0);
    }
    
    public int getCount(){
        return ++count;
    }
    
    public int setCount(int i) {
        count = i;
        return count;
    }
}
