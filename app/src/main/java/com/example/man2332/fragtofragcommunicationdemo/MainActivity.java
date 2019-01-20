package com.example.man2332.fragtofragcommunicationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//-This app shows how to pass data from one fragment(FragmentA) to another fragment(FragmentB) using a activity
//  FragmentA passes data to MainActivity-then MainActivity passes that data to FragmentB-vise versa
public class MainActivity extends AppCompatActivity implements FragmentA.FragmentAListener, FragmentB.FragmentBListener{

    private FragmentA fragmentA;
    private FragmentB fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentA = new FragmentA();
        fragmentB = new FragmentB();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_a, fragmentA)
                .replace(R.id.container_b, fragmentB)
                .commit();
    }
    //override & implement FragmentA's interface(FragmentAListener) method
    @Override
    public void onInputASent(CharSequence input) {
        fragmentB.updateEditText(input);
    }
    //override & implement FragmentB's interface(FragmentAListener) method
    @Override
    public void onInputBSent(CharSequence input) {
        fragmentA.updateEditText(input);
    }
}
//-fragments should not be tightly coupled- not tied to another activity or
// fragment with code so use interface to communiticate