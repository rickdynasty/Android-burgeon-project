 package com.tencent.tws.burgeon.DesignPattern.Behavioral.State;

/**
 * Created by Administrator on 2016/1/8.
 */
public class Work {
    private int hour;//job hour
    private boolean finished;
    private State currentState;

    public Work() {
        currentState = new ForenoonState();//Start work at nine o 'clock in the morning
    }

    //State is set to finish my work
    public boolean taskFinished() {
        return finished;
    }

    //job content
    public void writeProgram() {
        currentState.writeProgram(this);
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }
}
