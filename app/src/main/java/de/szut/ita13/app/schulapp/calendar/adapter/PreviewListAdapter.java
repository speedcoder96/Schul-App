package de.szut.ita13.app.schulapp.calendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import de.szut.ita13.app.schulapp.R;
import de.szut.ita13.app.schulapp.calendar.container.Preview;

/**
 * Created by Rene on 15.06.2015.
 */
public class PreviewListAdapter extends BaseAdapter {

    private Preview preview;
    private final LayoutInflater layoutInflater;

    public PreviewListAdapter(Context context, Preview preview) {
        this.preview = preview;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return preview.size();
    }

    @Override
    public Object getItem(int position) {
        return preview.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(R.layout.calendar_preview_entry_layout, parent, false);



        return convertView;
    }
}
