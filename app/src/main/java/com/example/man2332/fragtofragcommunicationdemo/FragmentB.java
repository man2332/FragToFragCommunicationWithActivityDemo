package com.example.man2332.fragtofragcommunicationdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentB extends Fragment {
    private EditText editText;
    private Button buttonOk;

    private FragmentBListener listener;

    //-to communicate with our underlining activity we have to use interfaces
    public interface FragmentBListener {
        void onInputBSent(CharSequence input);//CharSequence is more general for text-so we don't have to transform it to a string....
    }
    //-later we will "implement" our Frag listener-then define it's required method


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_b, container, false);

        editText = v.findViewById(R.id.edit_text);
        buttonOk = v.findViewById(R.id.button_ok);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence input = editText.getText();
                listener.onInputBSent(input);//this call will be sent to whoever implements our interface
            }
        });

        return v;//this will set our layout as our fragment(v) layout
    }
    //-container is our parent view where we get our layout parameters from
    //-attachToRoot is how the view(fragment) will be attached to its parent view-but who cares

    //-use this method to pass data from FragmentB to activity to fragmentA-call it on activity and pass it text
    //  when will be used to set data on the fragment
    public void updateEditText(CharSequence newText) {
        editText.setText(newText);
    }

    //-onAttach is called when the frag is being attached to a host activity
    //-context is the activity that this fragment(FragmentA) is being attached to
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //if the context(MainActivity) implemented FragmentAListener interface & defined it's method
        if (context instanceof FragmentBListener) {
            listener = (FragmentBListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
