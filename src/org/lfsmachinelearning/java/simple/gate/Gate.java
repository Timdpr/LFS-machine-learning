package org.lfsmachinelearning.java.simple.gate;

import org.lfsmachinelearning.java.simple.main.Unit;

class Gate {

    private Unit uTop = new Unit();
    private Unit u0 = new Unit();
    private Unit u1 = new Unit();

    /**
     * uTop stores gate output value; later is given 'top pull' gradient for backwards pass
     */
    Unit getUTop() {
        return uTop;
    }

    /**
     * uTop stores gate output value; later is given 'top pull' gradient for backwards pass
     */
    void setUTop(Unit uTop) {
        this.uTop = uTop;
    }

    Unit getU0() {
        return u0;
    }

    void setU0(Unit u0) {
        this.u0 = u0;
    }

    Unit getU1() {
        return u1;
    }

    void setU1(Unit u1) {
        this.u1 = u1;
    }
}
