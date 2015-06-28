package de.szut.ita13.app.schulapp.timetable;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import de.szut.ita13.app.schulapp.R;


/**
 * Created by Rene on 19.06.2015.
 */
public class TimeTableActivity extends FragmentActivity implements View.OnClickListener, DialogInterface.OnDismissListener {

    private TimeTableSetup timeTableSetup;
    public static TimeTableModifier timeTableModifier;
    private FragmentManager fm;
    public static TimeTableDataSource timeTableDataSource;
    public static ArrayList<TimeTableItem> subjects;
    public static Hashtable<Integer,Hashtable<Integer,TimeTableLessonItem>> lessonItems;
    public static HashMap<Integer,TimeTableItem> subjectMap;
    public ArrayList<TimeTableItem> getSubjects() {
        return subjects;
    }


    public static Hashtable<Integer,Hashtable<Integer,TimeTableLessonItem>> getLessonItems() {
        return lessonItems;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getSupportFragmentManager();
        timeTableModifier = new TimeTableModifier(this);
        timeTableSetup = new TimeTableSetup(this, timeTableModifier);
        timeTableDataSource = new TimeTableDataSource(getApplicationContext());
        timeTableDataSource.open();
        subjects = timeTableDataSource.getItems();
        lessonItems = timeTableDataSource.getLessonItems();
        timeTableDataSource.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_timetable_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK :
                TimeTableSetupBundle bundle = new TimeTableSetupBundle.Builder(data)
                        .build();
                timeTableSetup.onReceiveSetupBundle(bundle);
                break;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.addSubject:
                Intent intent = new Intent(this, TimeTableSubjectActivity.class);
                startActivity(intent);
                break;
            case R.id.action_edit:
                timeTableModifier.editMode = !timeTableModifier.editMode;
                item.setChecked(timeTableModifier.editMode);
                break;
            case R.id.removeSubject :
                TimeTableRemoveSubjectDialog removeSubjectDialog = new TimeTableRemoveSubjectDialog(getApplicationContext());
                removeSubjectDialog.show(fm, "Fach entfernen");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        TimeTableItemDialog timeTableItemDialog = new TimeTableItemDialog(view, this, fm);
        timeTableItemDialog.show(fm, "Fachauswahl");

    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        Log.d("TimeTableActivity", "OnDismiss");
        timeTableDataSource.open();
        lessonItems = timeTableDataSource.getLessonItems();
        timeTableDataSource.close();


        timeTableModifier.update();
    }
}
