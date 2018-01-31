package it.fmgroup.pronostigram;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import model.Match;
import model.Pronostico;

/**
 * Created by Antonio on 18/01/2018.
 */

public class FeedAdapter extends ArrayAdapter<Pronostico> {

    private List<Pronostico> pronostici;
    private List<Match> match;

    // View lookup cache
    private static class ViewHolder {
        TextView textViewMatch;
        TextView textViewDataMatch;
        TextView textViewEsito;
        TextView textViewFollowers;
        ImageButton buttonFollow;
    }

    public FeedAdapter(@NonNull Context context, List<Pronostico> pronostici, List<Match> match) {
        super(context, R.layout.list_item, pronostici);
        this.pronostici = pronostici;
        this.match = match;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Pronostico pronostico = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.textViewMatch = (TextView) convertView.findViewById(R.id.textview_match);
            viewHolder.textViewDataMatch = (TextView) convertView.findViewById(R.id.textview_data_match);
            viewHolder.textViewEsito = (TextView) convertView.findViewById(R.id.textview_esito);
            viewHolder.buttonFollow = (ImageButton) convertView.findViewById(R.id.button_follow);
            viewHolder.textViewFollowers = (TextView) convertView.findViewById(R.id.textview_followers);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.textViewMatch.setText(pronostico.getDescrizione());
        viewHolder.textViewMatch.setTextColor(getContext().getResources().getColor(R.color.colorPrimaryDark));

        // Return the completed view to render on screen
        return result;

    }





}
