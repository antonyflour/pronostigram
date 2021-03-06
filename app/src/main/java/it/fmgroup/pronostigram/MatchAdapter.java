package it.fmgroup.pronostigram;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import model.Match;

/**
 * Created by Luigi on 20/01/2018.
 */

public class MatchAdapter extends ArrayAdapter<Match> {

    private List<Match> match;

    // View lookup cache
    private static class ViewHolder {
        TextView textViewIncontro;
        TextView textViewDataIncontro;
    }

    public MatchAdapter(@NonNull Context context, List<Match> match) {
        super(context, R.layout.match_item, match);
        this.match = match;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Match match = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        final View result;


        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.match_item, parent, false);
            viewHolder.textViewIncontro = (TextView) convertView.findViewById(R.id.textViewNomeMatch);
            viewHolder.textViewDataIncontro = (TextView) convertView.findViewById(R.id.textViewDataMatch);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }
        viewHolder.textViewIncontro.setText(match.toString());
        String data = match.getDataMatch();
        viewHolder.textViewDataIncontro.setText(data);

        viewHolder.textViewIncontro.setTextColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
        viewHolder.textViewDataIncontro.setTextColor(Color.GRAY);



        // Return the completed view to render on screen
        return result;

    }
}
