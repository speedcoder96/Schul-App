package de.szut.ita13.app.schulapp.timetable;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import de.szut.ita13.app.schulapp.R;

/**
 * Created by Michelé on 24.06.2015.
 */
public class TimeTableItemDialog extends DialogFragment implements View.OnClickListener {

    public static final String TAG = TimeTableItemDialog.class.getSimpleName();

    private View view;
    private TimeTableActivity timeTableActivity;
    private FragmentManager fm;

    public TimeTableItemDialog(View view, TimeTableActivity timeTableActivity, FragmentManager fm){
        this.view = view;
        this.timeTableActivity = timeTableActivity;
        this.fm = fm;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.timetable_subject_list_layout, container,
                false);
        getDialog().setTitle("Fachauswahl");
        getDialog().setOnDismissListener(timeTableActivity);

        Button addSubject = (Button) rootView.findViewById(R.id.addSubjectButton);
        addSubject.setOnClickListener(this);
        Button removeSubject = (Button) rootView.findViewById(R.id.removeSubjectButton);
        removeSubject.setOnClickListener(this);


        return rootView;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addSubjectButton:
                Log.d("HalloWelt", view.getClass() + "");
                TimeTableChooserSubjectDialog timeTableChooseSubjectDialog =
                        new TimeTableChooserSubjectDialog(timeTableActivity.getApplicationContext()
                                ,((int[])this.view.getTag())[0], ((int[])this.view.getTag())[1], this);
                timeTableChooseSubjectDialog.show(fm, "asd");
                break;
            case R.id.removeSubjectButton :
                int[] vector = (int[])this.view.getTag();
                TimeTableLessonItem item = timeTableActivity.lessonItems.get(vector[0]).get(vector[1]);
                timeTableActivity.timeTableDataSource.open();
                timeTableActivity.timeTableDataSource.deleteLessonItem(item);
                timeTableActivity.timeTableDataSource.close();
                dismiss();
                break;
        }

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        timeTableActivity.onDismiss(dialog);
    }
}
