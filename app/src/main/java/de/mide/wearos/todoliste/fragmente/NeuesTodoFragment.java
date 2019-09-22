package de.mide.wearos.todoliste.fragmente;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.ArraySet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Set;

import de.mide.wearos.todoliste.MainActivity;
import de.mide.wearos.todoliste.R;


/**
 * Fragment-Klasse zum Einfügen eines neuen ToDo-Eintrags.
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class NeuesTodoFragment extends Fragment
                               implements View.OnClickListener {

    /** UI-Element zur Eingabe Text von neuem ToDo-Eintrag. */
    private EditText _neuTodoEditText = null;

    /** SharedPreferences-Objekt, dem ggf. ein neuer ToDo-Eintrag hinzugefügt werden muss. */
    private SharedPreferences _shreadPrefs = null;

    /**
     * Referenz auf App-Context, wird für Zugriff auf SharedPreferences und für Erzeugung von
     * Toast-Objekten benötigt.
     */
    private Context _context = null;


    /**
     * Layout-Datei für Fragment mit Inflater laden und View daraus erzeugen.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate( R.layout.fragment_neutodo, container, false );
        // attachToRoot=false
    }


    /**
     * Diese Methode entspricht der Methode {@code onCreate()} einer Activity-Klasse.
     *
     * @param view  Referenz auf View-Objekt, das von Methode
     *              {@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}
     *              mit Inflater erstellt und mit return zurückgegeben wurde.
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        Button button = view.findViewById(R.id.neuTodoButton);
        button.setOnClickListener(this);

        _neuTodoEditText = view.findViewById(R.id.neuTodoEditText);

        _context = view.getContext();

        _shreadPrefs = _context.getSharedPreferences(MainActivity.PREFERENCE_DATEINAME,
                                                     Context.MODE_PRIVATE);
    }


    /**
     * Einzige Methode aus Interface {@link View.OnClickListener}.
     *
     * @param view  Button, der Event ausgelöst hat.
     */
    @Override
    public void onClick(View view) {

        String inputText = _neuTodoEditText.getText().toString().trim();

        Log.i(MainActivity.TAG4LOGGING,
                "Button gedrückt, neuer ToDo-Text: \"" + inputText + "\"");


        if (inputText.length() == 0) {

            Toast.makeText(_context, "Bitte Text für ToDo eingeben!", Toast.LENGTH_LONG).show();
            return;
        }


        Set<String> defaultSet = new ArraySet<String>(1);
        Set<String> stringSet = _shreadPrefs.getStringSet(
                                    MainActivity.PREFERENCES_KEYS_TODOS,
                                    defaultSet
                                 );

        stringSet.add(inputText);

        Log.i(MainActivity.TAG4LOGGING,
         "Anzahl Einträge in String-Set danach: " + stringSet.size() );


        SharedPreferences.Editor editor = _shreadPrefs.edit();
        editor.putStringSet( MainActivity.PREFERENCES_KEYS_TODOS, stringSet );
        editor.commit();

        _neuTodoEditText.setText("");
    }

}
