package de.mide.wearos.todoliste;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.app.FragmentManager;
import android.util.Log;

import androidx.wear.widget.drawer.WearableDrawerController;
import androidx.wear.widget.drawer.WearableNavigationDrawerView;

import de.mide.wearos.todoliste.fragmente.NeuesTodoFragment;
import de.mide.wearos.todoliste.fragmente.TodoListeFragment;


/**
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class MainActivity extends WearableActivity
                          implements WearableNavigationDrawerView.OnItemSelectedListener {


    /** Tag für Log-Statements von allen Klassen der App. */
    public static final String TAG4LOGGING = "ToDoListe";

    /** Dateiname der SharedPreferences-Datei. */
    public static final String PREFERENCE_DATEINAME= "todos";

    /**
     * Key unter dem in der SharedPreferences-Datei ein Set<String> mit den ToDo-Einträgen
     * abgelegt ist.
     */
    public static final String PREFERENCES_KEYS_TODOS = "todo_set";


    /**
     * FragmentManager, wird für Austausch von Fragmenten benötigt; wird in Methode
     * {@link #onCreate(Bundle)} gefüllt.
     */
    protected FragmentManager _fragmentManager = null;


    /**
     * Lifecycle-Methode, wird einmalig beim Start der Activity ausgefüllt.
     * Setzt das {@link TodoListeFragment} in den Platzhalter ein.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _fragmentManager = getFragmentManager();

        // Fragment einsetzen
        Fragment todoFragment = new TodoListeFragment();

        FragmentTransaction ft = _fragmentManager.beginTransaction();
        ft.replace(R.id.platzhalter_inhalt, todoFragment);
        ft.commit();


        WearableNavigationDrawerView drawerView = findViewById(R.id.navigation_drawer_oben);
        MeinNavigationAdapter navigationAdapter = new MeinNavigationAdapter();
        drawerView.setAdapter(navigationAdapter);
        drawerView.addOnItemSelectedListener(this);

        // Kurz die Schublade aufmachen, damit der Nutzer sich ihrer bewusst ist
        WearableDrawerController drawerController = drawerView.getController();
        drawerController.peekDrawer();

        setAmbientEnabled(); // Enables Always-on
    }


    /**
     * Einzige Methode aus Interface {@link WearableNavigationDrawerView.OnItemSelectedListener}.
     *
     * @param position  0-basierter Index für Element in Schublade.
     */
    @Override
    public void onItemSelected(int position) {

        Fragment fragmentNeu = null;

        switch (position) {

            case 0:
                fragmentNeu = new TodoListeFragment();
            break;

            case 1:
                fragmentNeu = new NeuesTodoFragment();
            break;

            default:
                Log.e(TAG4LOGGING, "Unerwarteter Wert für position=" + position);
        }

        FragmentTransaction ft = _fragmentManager.beginTransaction();
        ft.replace(R.id.platzhalter_inhalt, fragmentNeu);
        ft.commit();
    }


    /**
     * Innere Klasse zur Steuerung des NavigationDrawers.
     * Die Member-Variablen der inneren Klasse haben einen doppelten Unterstrich
     * als Prefix.
     */
    private final class MeinNavigationAdapter
                  extends WearableNavigationDrawerView.WearableNavigationDrawerAdapter {

        /**
         * Methode zur Abfrage des Anzeige-Texts für ein bestimmtes Element in der Schublade.
         *
         * @param position  0-basierter Index für Element in der Schublade.
         *
         * @return  Anzeige-Text für Schubladen-Eintrag.
         */
        @Override
        public String getItemText(int position) {

            switch (position) {

                case 0: return "ToDo-Liste";

                case 1: return "Neues ToDo";

                default:
                    Log.w(TAG4LOGGING, "Unerwartetes Item von NavigationDrawer aufgerufen: " + position);
                    return "???";
            }
        }


        /**
         * Methode liefert Icon für ein bestimmtes Schubladen-Element.
         * Es wurden die "Material Icons" von Google, die auch in Android Studio "eingebaut"
         * sind, verwendet;
         * siehe <a href="https://material.io/tools/icons/" target="_blank">hier</a> für alle diese
         * Icons auf einer Webseite.
         * Icons werden dem Projekt mit
         * <a href="https://developer.android.com/studio/write/vector-asset-studio#running" target="_blank">Assert Studio</a>
         * hinzugefügt.
         *
         * @param position  0-basierter Index für Element in der Schublade.
         *
         * @return  Icon für Element in Schublade.
         */
        @Override
        public Drawable getItemDrawable(int position) {

            Log.i(TAG4LOGGING, "Icon für Position: " + position);

            int drawableID = android.R.drawable.ic_dialog_alert;

            switch (position) {

                case 0: // TodoListeFragment
                    drawableID = R.drawable.ic_format_list_bulleted_black_24dp;
                    break;

                case 1: // NeuesTodoFragment
                    drawableID = R.drawable.ic_playlist_add_black_24dp;
                    break;

                default:
                    Log.w(TAG4LOGGING, "Unerwartetes Icon angefordert: " + position);
            }

            return getDrawable(drawableID);
        }


        /**
         * Methode zur Abfrage der Anzahl der Element/Fragmente in der Schublade.
         *
         * @return  Anzahl der Elemente in der Schublade, immer 2.
         */
        @Override
        public int getCount() {

            return 2;
        }

    } // Ende innere Klasse MeinNavigationAdapter

}
