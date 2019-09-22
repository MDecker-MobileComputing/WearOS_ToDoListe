package de.mide.wearos.todoliste.fragmente;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.ArraySet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.Set;

import de.mide.wearos.todoliste.MainActivity;
import de.mide.wearos.todoliste.R;


/**
 * Fragment-Klasse für Darstellung der ToDo-Liste.
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class TodoListeFragment extends Fragment {

    /** SharedPreferences-Objekt mit den anzuzeigenden Einträgen. */
    protected SharedPreferences _shreadPrefs = null;

    /** Context der App. */
    protected Context _context = null;


    /**
     * Layout-Datei für Fragment mit Inflater laden und View daraus erzeugen.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate( R.layout.fragment_liste, container, false );
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

        _context     = view.getContext();
        _shreadPrefs = _context.getSharedPreferences( MainActivity.PREFERENCE_DATEINAME,
                                                     Context.MODE_PRIVATE
                                                    );

        ArrayAdapter<String> arrayAdapter = getArrayAdapterMitTodoTexten();

        ListView listView = view.findViewById(R.id.todoListView);
        listView.setAdapter(arrayAdapter);
    }


    /**
     * ArrayAdapter für ListView-Element anhand von in SharedPreferences gespeicherten
     * ToDo-Texten erzeugen.
     *
     * @return  ArrayAdapter mit Texten der Todo-Einträge für ListView-Element.
     */
    protected ArrayAdapter<String> getArrayAdapterMitTodoTexten() {

        ArrayAdapter<String> resultArrayAdapter = null;
        Set<String>          defaultSet         = null;
        Set<String>          stringSet          = null;
        String[]             stringArray        = null;


        defaultSet = new ArraySet<String>(1);
        defaultSet.add("Test-Eintrag");

        stringSet  = _shreadPrefs.getStringSet( MainActivity.PREFERENCES_KEYS_TODOS, defaultSet);

        stringArray = new String[ stringSet.size() ];

        Log.i(MainActivity.TAG4LOGGING,
         "Anzahl ToDo-Einträge für ListView: " + stringSet.size() );

        stringArray = stringSet.toArray(stringArray);


        resultArrayAdapter =
                new ArrayAdapter<String>( _context,
                                          R.layout.listitem,
                                          R.id.todoEintrag,
                                          stringArray
                                        );
        return resultArrayAdapter;
    }

}
