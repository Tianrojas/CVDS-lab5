package edu.eci.cvds.servlet.JCF;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.SessionScoped;

import java.util.ArrayList;

@ManagedBean(name = "guessBean")
@SessionScoped

public class BackingBean {
    int randomNumber = (int) (Math.random()*10)+1;
    int score = 100000;
    String status = "Playing";
    int tries = 0;

    ArrayList<Integer> played = new ArrayList<Integer>();

    public BackingBean(){

    }

    public int getRandomNumber(){
        return randomNumber;
    }

    public String getStatus(){
        return status;
    }

    public int getScore(){
        return score;
    }

    public int getTries() {
        return tries;
    }

    public ArrayList<Integer> getPlayed(){return played;}

    public void setStatus(String status){
        this.status = status;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void setRandomNumber(int randomNumber){
        this.randomNumber = randomNumber;
    }

    public void guess(int number){
        tries ++;
        if (number == randomNumber && (getStatus() !="Lose")){
            setStatus("Win");
        }else if ((getScore() - 10000) > 0){
            played.add(number);
            setScore(getScore() - 10000);
            setStatus("Playing");
        }else{
            setStatus("Lose");
            setScore(0);
        }
    }

    public void restart(){
        played.clear();
        tries = 0;
        setRandomNumber((int) (Math.random()*10)+1);
        setScore(100000);
        setStatus("Playing");
    }

}
