package de.mide.wearos.todoliste;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/**
 * Fragment-Klasse für Darstellung der ToDo-Liste.
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class TodoListenFragment extends Fragment {

    /** Dateiname der SharedPreferences-Datei. */
    public static final String PREFERENCE_DATEINAME= "todos";

    /**
     * Key unter dem in der SharedPreferences-Datei ein Set<String> mit den ToDo-Einträgen
     * abgelegt ist.
     */
    public static final String PREFERENCES_KEYS_TODOS = "todo_set";


    /**
     * Layout-Datei für Fragment mit Inflater laden und View daraus erzeugen.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate( R.layout.fragment_liste, container, false );
        // attachToRoot=false
    }

    /**
     * Diese Methode entspricht der Methode {@link android.app.Activity#onCreate(Bundle)}
     * von Activities.
     *
     * @param view Referenz auf View-Objekt, das von Methode
     *             {@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}
     *             mit Inflater erstellt und mit return zurückgegeben wurde.
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }

}
