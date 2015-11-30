package four.elite.tournament;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MySimpleArrayAdapter extends ArrayAdapter{

    private final Context context;
    private final String[] names,teams;
    private final int[] rankings;

    public MySimpleArrayAdapter(Context context, String[] names, String[] teams, int[] ranking) {
        super(context, -1, names);
        this.context = context;

        this.names = names;
        this.rankings = ranking;
        this.teams = teams;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);

        TextView nameLabel = (TextView)rowView.findViewById(R.id.firstLine);
        TextView teamNameLabel = (TextView)rowView.findViewById(R.id.description);
        TextView rannkingLabel = (TextView)rowView.findViewById(R.id.rankingLabel);
        ImageView imageView = (ImageView)rowView.findViewById(R.id.icon);

        nameLabel.setText(names[position]);
        teamNameLabel.setText(teams[position]);
        rannkingLabel.setText("Rank: " + Integer.toString(rankings[position]));

        return rowView;
    }


}
