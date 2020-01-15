package com.example.android.practiceset2;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.*;
import android.view.*;


public class MainActivity extends AppCompatActivity {
    int score_a=0,wickets_a=0,score_b=0,wickets_b=0,score,wickets,over,ball,over_a=0,ball_a=0,over_b=0,ball_b=0,chance_a=0,chance_b=0;
    String value=null,number=null;
    Boolean check=true;
    Button b0,b1,b2,b3,b4,b6,bw,bwide,a0,a1,a2,a3,a4,a6,aw,awide;
    TextView ta,tb,oa,ob,result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        b0 = (Button) findViewById(R.id.b_i0);//b_i0 stands for increase in 0 run taken by team B and similarly other names are to the ids of different button.
        b1 = (Button) findViewById(R.id.b_i1);
        b2 = (Button) findViewById(R.id.b_i2);
        b3 = (Button) findViewById(R.id.b_i3);
        b4 = (Button) findViewById(R.id.b_i4);
        b6 = (Button) findViewById(R.id.b_i6);
        bw = (Button) findViewById(R.id.b_w);
        bwide = (Button) findViewById(R.id.b_wide);// In case of wide, the runs are counted but balls aren't
        a0 = (Button) findViewById(R.id.a_i0);
        a1 = (Button) findViewById(R.id.a_i1);
        a2 = (Button) findViewById(R.id.a_i2);
        a3 = (Button) findViewById(R.id.a_i3);
        a4 = (Button) findViewById(R.id.a_i4);
        a6 = (Button) findViewById(R.id.a_i6);
        aw = (Button) findViewById(R.id.a_w);
        awide = (Button) findViewById(R.id.a_wide);
        ta = (TextView) findViewById(R.id.a);
        tb = (TextView) findViewById(R.id.b);
        oa = (TextView) findViewById(R.id.over_a);
        ob = (TextView) findViewById(R.id.over_b);
        result=(TextView) findViewById(R.id.result);

    }

    public void innings_switch(){
        display();
        try {if (check)
        {if(value.equals("a"))
        { value="b";
            chance_b=2;
            buttons_enabled(false,true);
        }
        else{value="a";
            chance_a=2;
            buttons_enabled(true,false);}
        score=0;
        wickets=0;
        over=0;
        ball=0;
        check=false;}
        else {
            throw new IllegalArgumentException();
        }}
        catch (Exception e)
        { win();}

    }

    public void aorb(View view)
    { if(getResources().getResourceEntryName((view.getId())).startsWith("a")){ //finds whether the id starts with a or b
            value="a";
            if (chance_b==1&&chance_a==2){
                chance_a=2;
                chance_b=1; // chance defines which team plays first
            }
            else {chance_a=1;} }
        else{ value="b";
        if (chance_a==1&&chance_b==2){
            chance_b=2;
            chance_a=1;
        }
        else {chance_b=1;}}
        number=getResources().getResourceEntryName((view.getId())).substring(2);// number defines what is the next
        score();
    }

    public void score(){
        overs();
        int i=0;
        if(number.equals("i0"))
            i=0;
        if(number.equals("i1"))//increase in 1 run.
            i=1;
        if(number.equals("i2"))
            i=2;
        if(number.equals("i3"))
            i=3;
        if(number.equals("i4"))
            i=4;
        if(number.equals("i6"))
            i=6;
        if(number.equals("wide"))
            i=1;
        score+=i;
        if(number.equals("w"))//fall of a wicket
            wickets+=1;

        if (value.equals("a")){
            score_a=score;
            wickets_a=wickets;
            over_a=over;
            ball_a=ball;}
        else {score_b=score;
            wickets_b=wickets;
            over_b=over;
            ball_b=ball;}

            display();
        if (wickets==10||over==5)//If a team loses all its 10 wickets or its overs are finished , innings is changed
        { innings_switch();}
            win();}

        public void win(){
            if (chance_b==2&&score_b>score_a){
                result.setText("Team B won by "+(10-wickets_b)+" Wickets !");
                buttons_enabled(false,false);
            }
            else if (chance_b==2&&score_a>score_b&&(wickets_b==10||over_b==5)){
                result.setText("Team A won by "+(score_a-score_b)+" Runs !");
                buttons_enabled(false,false);
            }
            else if (chance_a==2&&score_a>score_b){
                result.setText("Team A won by "+(10-wickets_a)+" Wickets !");
                buttons_enabled(false,false);
            }
            else if (chance_a==2&&score_b>score_a&&(wickets_a==10||over_a==5)){
                result.setText("Team B won by "+(score_b-score_a)+" Runs !");
                buttons_enabled(false,false);
            }
            else if (score_b==score_a&&(wickets==10||over_b==5)&&(chance_b==2||chance_a==2)){
                result.setText("Match is drawn !");
                buttons_enabled(false,false);
            } }
        public void overs(){
            innings();
        if(awide.isPressed()||bwide.isPressed())
        {}
        else{++ball;}
        if (ball >5){ ++over; // Each over consists of 6 balls
            ball=0;} } // Currently limited to only 5 overs.

    public void innings(){
        if (value.equals("a")){
            score=score_a;
            wickets=wickets_a;
            over=over_a;
            ball=ball_a; }
        else{ score=score_b;
            wickets=wickets_b;
            over=over_b;
            ball=ball_b; } }

    public void buttons_enabled(Boolean a,Boolean b)
    {   a0.setEnabled(a);
        a1.setEnabled(a);
        a2.setEnabled(a);
        a3.setEnabled(a);
        a4.setEnabled(a);
        a6.setEnabled(a);
        aw.setEnabled(a);
        awide.setEnabled(a);
        b0.setEnabled(b);
        b1.setEnabled(b);
        b2.setEnabled(b);
        b3.setEnabled(b);
        b4.setEnabled(b);
        b6.setEnabled(b);
        bw.setEnabled(b);
        bwide.setEnabled(b); }

    public void display(){
        if(value.equals("a"))
        {ta.setText(score_a+"/"+wickets_a);
        buttons_enabled(true,false);
        oa.setText("Overs: "+over_a+"."+ball_a); }
        else { tb.setText(score_b+"/"+wickets_b);
            buttons_enabled(false,true);
            ob.setText("Overs: "+over_b+"."+ball_b); } }

    public void reset(View view){
        score_a=0;
        wickets_a=0;
        score_b=0;
        wickets_b=0;
        over_a=0;
        over_b=0;
        ball_a=0;
        ball_b=0;
        score=0;
        wickets=0;
        ball=0;
        over=0;
        ta.setText("0/0");
        tb.setText("0/0");
        oa.setText("Overs: 0.0");
        ob.setText("Overs: 0.0");
        result.setText(null);
        buttons_enabled(true,true);
        chance_b=0;
        chance_a=0;
        check=true;
    }
}