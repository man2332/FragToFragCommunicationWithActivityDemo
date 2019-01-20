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
public class FragmentA extends Fragment {
    private EditText editText;
    private Button buttonOk;
    private FragmentAListener listener;
    //-to communicate with our underlining activity we have to use interface listener
    public interface FragmentAListener {
        void onInputASent(CharSequence input);
        //CharSequence is more general for text-so we don't have to transform it to a string....
    }
    //-later we will "implement" our Frag listener-then define it's required method
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_a, container, false);
        editText = v.findViewById(R.id.edit_text);
        buttonOk = v.findViewById(R.id.button_ok);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence input = editText.getText();
                listener.onInputASent(input);
                //this call will be sent to whoever implements our interface
            }
        });
        //-if we look in MainActivity->onInputASent() calls fragmentB.updateEditText(input)
        //  to change the text of B to whatever we currently have in our editText on A
        return v;//this will set our layout as our fragment(v) layout
    }
    public void updateEditText(CharSequence newText) {
        editText.setText(newText);
    }//this method is called on MainActivity

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //if the context(MainActivity) implemented FragmentAListener interface & defined it's method
        if (context instanceof FragmentAListener) {
            listener = (FragmentAListener) context;
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
//-onCreateView() -
//-container is our parent view where we get our layout parameters from
//-attachToRoot is how the view(fragment) will be attached to its parent view-but who cares
//-use this method to pass data from FragmentA to activity to fragmentB-call it on activity and pass it text
//  when will be used to set data on the fragment

//-onAttach is called when the frag is being attached to a host activity
//-context is the activity that this fragment(FragmentA) is being attached to