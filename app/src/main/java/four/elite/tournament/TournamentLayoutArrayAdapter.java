package four.elite.tournament;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class TournamentLayoutArrayAdapter extends ArrayAdapter{

    Context context;

    List<Tournament> tournaments;

    public TournamentLayoutArrayAdapter(Context context, List<Tournament> tournaments) {
        super(context, -1, tournaments.size());
        this.context = context;
        this.tournaments = tournaments;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.tournament_layout, parent, false);

        TextView tournamentName = (TextView)rowView.findViewById(R.id.tournamentNameLabel);
        TextView players = (TextView)rowView.findViewById(R.id.numOfPlayerLabel);
        TextView type = (TextView)rowView.findViewById(R.id.tournamentTypeLabel);

        tournamentName.setText(tournaments.get(position).getName());
        players.setText("Players: " + Integer.toString(tournaments.get(position).getPlayers().size()));
        type.setText("Type: " + tournaments.getType());

        return rowView;

    }


}
